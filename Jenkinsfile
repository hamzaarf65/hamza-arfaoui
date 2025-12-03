pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/hamzaarf65/hamza-arfaoui.git'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test -Dspring.profiles.active=test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -Dspring.profiles.active=test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t medyassineelhaj/student-management:latest .'
            }
        }
    }

    post {
        success { echo 'Build SUCCESS 🎉' }
        failure { echo 'Build FAILED ❌' }
    }
}
