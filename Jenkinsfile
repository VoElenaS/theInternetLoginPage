pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\Java\\apache-maven-3.9.9'
    }

    stages {
        stage('Build and Test') {
             steps {
                  bat "mvn clean test"
             }
        }
    }
}