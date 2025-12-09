pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "hamzaarfaoui/student-management"
        DOCKER_TAG   = "latest"
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

        stage('Code Quality - SonarQube') {
    steps {
        withSonarQubeEnv('local-sonarqube') {
            sh """
                mvn sonar:sonar \
                  -Dsonar.projectKey=student-management \
                  -Dsonar.projectName=student-management \
                  -Dsonar.host.url=$SONAR_HOST_URL \
                  -Dsonar.token=$SONAR_AUTH_TOKEN
            """
        }
    }
}



        stage('Package') {
            steps {
                sh 'mvn package -Dspring.profiles.active=test'
            }
        }

        stage('Build Docker Image') {
            steps {
                // on construit l'image avec le même nom que sur Docker Hub
                sh 'docker build -t $DOCKER_IMAGE:$DOCKER_TAG .'
            }
        }

        stage('Push Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-cred',
                    usernameVariable: 'DOCKERHUB_USER',
                    passwordVariable: 'DOCKERHUB_PASS'
                )]) {
                    sh '''
                        echo "$DOCKERHUB_PASS" | docker login -u "$DOCKERHUB_USER" --password-stdin
                        docker push $DOCKER_IMAGE:$DOCKER_TAG
                        docker logout
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Build SUCCESS 🎉 Image poussée sur Docker Hub !'
        }
        failure {
            echo 'Build FAILED ❌'
        }
    }
}
