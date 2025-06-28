pipeline {
    agent any

    tools {
        // Usa el nombre que configuraste en Global Tool Configuration
        maven 'apache-maven'
        jdk 'jdk-17'
    }

    environment {
        // Variables de entorno opcionales
    }

    stages {
        stage('Clonar repositorio') {
            steps {
                // Cambia la URL a tu repo real
                git branch: 'main', url: 'https://github.com/antonier-g/holaDockerLocal.git'
            }
        }

        stage('Compilar con Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Pruebas') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Post-build') {
            steps {
                echo 'Pipeline completado!'
            }
        }
    }
}
