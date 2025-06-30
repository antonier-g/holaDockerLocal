pipeline {
  agent any

  tools {
    maven 'apache-maven'
    jdk 'jdk-17'
  }

  environment {
    APP_NAME = "holaDockerLocal-0.0.1-SNAPSHOT"
    JAR_FILE = "target/holaDockerLocal-0.0.1-SNAPSHOT.jar"
    PORT = "9999"
    PID_FILE = "app.pid"
    WINDOW_TITLE = "MySpringBootApp"
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
          bat '''
          start "MySpringBootApp" /B java -jar target/holaDockerLocal-0.0.1-SNAPSHOT.jar > app.log 2>&1
          for /f "skip=3 tokens=2" %%i in ('tasklist /FI "WINDOWTITLE eq MySpringBootApp"') do echo %%i > app.pid
          '''
        }
      }
    }

    stage('Test Deployment') {
      steps {
        bat 'ping 127.0.0.1 -n 10 > nul'
        bat 'curl -I http://localhost:9999'
      }
    }
  }

  post {
    always {
      echo 'Pipeline completado.'
    }
  }
}
