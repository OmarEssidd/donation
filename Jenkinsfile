pipeline {
    agent any

    environment {
        SONARQUBE_TOKEN = credentials('squ_f0dd2672a033ceedff48e22766abba6997398d74')
        // Ensure 'squ_f0dd2672a033ceedff48e22766abba6997398d74' is the correct credentials ID for your SonarQube token
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
                    sh """
                    mvn sonar:sonar \
                        -Dsonar.login="${SONARQUBE_TOKEN}"
                    """
                }
            }
        }
    }
}
