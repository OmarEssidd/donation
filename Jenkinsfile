pipeline {
    agent any

    environment {
        SONARQUBE_CREDENTIALS = credentials('f861bec6-fef9-462c-b5ae-0ef24474e8b6')
    }

    stages {
        stage('Checkout SCM') {
            steps {
                echo "Getting project from Git"
                checkout([$class: 'GitSCM', 
                          branches: [[name: '*/master']], 
                          doGenerateSubmoduleConfigurations: false, 
                          extensions: [[$class: 'CleanCheckout']], 
                          submoduleCfg: [], 
                          userRemoteConfigs: [[url: 'https://github.com/OmarEssidd/donation.git', credentialsId: 'f861bec6-fef9-462c-b5ae-0ef24474e8b6']]])
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
                        -Dsonar.login="${SONARQUBE_CREDENTIALS_USR}" \
                        -Dsonar.password="${SONARQUBE_CREDENTIALS_PSW}"
                    """
                }
            }
        }
    }
}
