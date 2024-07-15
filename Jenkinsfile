pipeline {
    agent any

    environment {
        SONARQUBE_TOKEN = credentials('squ_7d7d3b46de99dae7e941a99ab0105b37f4d6931d')
    }

    stages {
        stage('Checkout SCM') {
            steps {
                echo "Getting project from Git"
                checkout([$class: 'GitSCM', 
                          branches: [[name: '*/master']], 
                          userRemoteConfigs: [[url: 'https://github.com/OmarEssidd/donation.git']]])
            }
        }

        stage('Clean and Compile Project') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    script {
                        // Use the SONARQUBE_TOKEN directly
                        def scannerHome = tool 'sonarqube_scanner';
                        withEnv(["PATH+MAVEN=${tool 'Maven'}/bin"]) {
                            sh "${scannerHome}/bin/sonar-scanner -Dsonar.login=${env.SONARQUBE_TOKEN}"
                        }
                    }
                }
            }
        }
    }
}
