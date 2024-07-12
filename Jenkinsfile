pipeline {
    agent any

    environment {
        // Remplacer 'github_credentials' par le nom symbolique de vos credentials dans Jenkins
        GIT_CREDENTIALS = credentials('github_credentials')
    }

    stages {
        stage('Checkout SCM') {
            steps {
                echo "Getting project from Git"
                checkout([$class: 'GitSCM', 
                          branches: [[name: '*/master']], 
                          userRemoteConfigs: [[url: 'https://github.com/OmarEssidd/donation.git', credentialsId: 'github_credentials']]])
            }
        }

        stage('Clean Project') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Compile Project') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('MonInstanceSonarQube') {
                    sh """
                    mvn sonar:sonar \
                        -Dsonar.login="${GIT_CREDENTIALS_USR}" \
                        -Dsonar.password="${GIT_CREDENTIALS_PSW}"
                    """
                }
            }
        }
    }
}
