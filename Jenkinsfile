pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/VoElenaS/Automation_Java.git'
        MAVEN_HOME = 'C:\\Java\\apache-maven-3.9.9'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: "${env.GIT_REPO}"
            }
        }

        stage('Build and Test') {
             steps {
                  bat "mvn clean test"
             }
        }
    }
}