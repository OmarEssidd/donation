pipeline {
    agent any

    environment {
        SONARQUBE_CREDENTIALS = credentials('squ_f0dd2672a033ceedff48e22766abba6997398d74')
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
                          userRemoteConfigs: [[url: 'https://github.com/OmarEssidd/donation.git', credentialsId: 'squ_f0dd2672a033ceedff48e22766abba6997398d74']]])
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
                withSonarQubeEnv('sonarqube') {
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
