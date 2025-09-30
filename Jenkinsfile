pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\Java\\apache-maven-3.9.9'
        ALLURE_RESULTS = 'target\\allure-results'
         //  GIT_REPO = 'https://github.com/VoElenaS/Automation_Java.git'
    }

    stages {
        stage('Build and Test') {
             steps {
                  bat "mvn clean test"
             }
        }
        stage('Generate Allure Report') {
                    steps {
                        allure([
                            includeProperties: false,
                            jdk: '',
                            results: [[path: "${env.ALLURE_RESULTS}"]],
                            reportBuildPolicy: 'ALWAYS'
                        ])
                    }
                }
    }
}