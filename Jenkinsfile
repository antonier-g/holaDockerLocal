pipeline {
  agent any

  tools {
    maven 'apache-maven'
    jdk 'jdk-17'
  }

  environment {
    APP_NAME = "mi-app-spring"
    JAR_FILE = "target/mi-app-spring.jar"
    PORT = "8080"
    PID_FILE = "app.pid"
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

    stage('Stop Previous') {
      steps {
        script {
          // Si existe un archivo de PID, mata SOLO ese proceso
          bat '''
          if exist app.pid (
            for /f %%i in (app.pid) do taskkill /F /PID %%i
            del app.pid
          ) else (
            echo No PID file found, skipping stop.
          )
          '''
        }
      }
    }

    stage('Deploy') {
      steps {
        script {
          // Lanza la app en background y guarda el PID
          bat '''
          start /B java -jar target/mi-app-spring.jar > app.log 2>&1
          for /f "tokens=2" %%i in ('tasklist /FI "IMAGENAME eq java.exe" /NH') do echo %%i > app.pid
          '''
        }
      }
    }

    stage('Test Deployment') {
      steps {
        // Espera unos segundos para que arranque
        bat 'ping 127.0.0.1 -n 10 > nul'
        // Prueba la app
        bat 'curl -I http://localhost:8080'
      }
    }
  }

  post {
    always {
      echo 'Pipeline completado.'
    }
  }
}
