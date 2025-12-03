pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                // récupère le code du repo (ton fork)
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        success {
            echo "✅ Build OK !"
        }
        failure {
            echo "❌ Build KO ! Regarde les logs."
        }
    }
}
