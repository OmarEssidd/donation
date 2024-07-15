pipeline {
    agent any

    environment {
<<<<<<< HEAD
        SONARQUBE_CREDENTIALS = credentials('squ_f0dd2672a033ceedff48e22766abba6997398d74')
=======
        // Remplacer 'github_credentials' par le nom symbolique de vos credentials dans Jenkins
        GIT_CREDENTIALS = credentials('github_credentials')
>>>>>>> 122f8df4fadb19d12d24ef3dca1da69944087ae8
    }

    stages {
        stage('Checkout SCM') {
            steps {
                echo "Getting project from Git"
                checkout([$class: 'GitSCM', 
                          branches: [[name: '*/master']], 
<<<<<<< HEAD
                          doGenerateSubmoduleConfigurations: false, 
                          extensions: [[$class: 'CleanCheckout']], 
                          submoduleCfg: [], 
                          userRemoteConfigs: [[url: 'https://github.com/OmarEssidd/donation.git', credentialsId: 'squ_f0dd2672a033ceedff48e22766abba6997398d74']]])
=======
                          userRemoteConfigs: [[url: 'https://github.com/OmarEssidd/donation.git', credentialsId: 'github_credentials']]])
>>>>>>> 122f8df4fadb19d12d24ef3dca1da69944087ae8
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
                        -Dsonar.login="${GIT_CREDENTIALS_USR}" \
                        -Dsonar.password="${GIT_CREDENTIALS_PSW}"
                    """
                }
            }
        }
    }
}
