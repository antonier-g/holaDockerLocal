pipeline {
  agent any

  tools {
    maven 'apache-maven'
    jdk 'jdk-17'
  }

  environment {
    APP_NAME = "mi-app-aer-spring"
    JAR_FILE = "target/mi-app-aer-spring"
    PORT = "9999"
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'master', url: 'https://github.com/antonier-g/holaDockerLocal.git'
      }
    }

    stage('Build') {
      steps {
        bat 'mvn clean install'
      }
    }

    stage('Deploy') {
      steps {
        script {
          // Si ya se está ejecutando, detenerlo primero
          bat 'taskkill /F /IM java.exe || echo No java process running'
          // Ejecutar la app en background (Windows)
          bat "start java -jar ${JAR_FILE}"
        }
      }
    }

    stage('Test Deployment') {
      steps {
        // Darle unos segundos para arrancar
        bat 'ping 127.0.0.1 -n 10 > nul'

        // Probar con curl si responde
        bat 'curl -I http://localhost:9999 || echo Error al verificar la app'
      }
    }
  }

  post {
    always {
      echo 'Pipeline finalizado.'
    }
  }
}
