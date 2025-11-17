pipeline {
    agent any

    // 🔔 Pour surveiller ton dépôt Git (toutes les 5 minutes)
    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {

        stage('Checkout') {
            steps {
                // Si tu utilises "Pipeline script from SCM", tu peux laisser Jenkins gérer le checkout.
                // Sinon, tu peux utiliser :
                // git branch: 'main', url: 'https://github.com/ton-compte/ton-projet.git'
                echo 'Récupération du code depuis Git...'
            }
        }

        stage('Build') {
            steps {
                echo 'Compilation du projet...'
                sh 'mvn clean compile'
            }
        }

        stage('Tests') {
            steps {
                echo 'Lancement des tests unitaires...'
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo 'Création du package (jar/war)...'
                sh 'mvn package -DskipTests'
            }
        }
    }

    post {
        success {
            echo '✅ Build réussi !'
        }
        failure {
            echo '❌ Build cassé, va voir les logs.'
        }
    }
}
