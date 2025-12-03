pipeline {
    agent any

    tools {
        maven 'M2_HOME'
    }

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

        // 👉 Tu rajouteras ce genre de stage plus tard quand tu feras SonarQube
        // stage('SonarQube Analysis') {
        //     steps {
        //         withSonarQubeEnv('SonarQube') {
        //             sh 'mvn sonar:sonar ...'
        //         }
        //     }
        // }
    }

    post {
        success { echo 'Build SUCCESS 🎉' }
        failure { echo 'Build FAILED ❌' }
    }
}
