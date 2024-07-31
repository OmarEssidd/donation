pipeline {
    agent any

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
                withSonarQubeEnv('sonarqube_scanner') {  // Utiliser le nom configuré ici
                    sh '/var/lib/jenkins/tools/hudson.plugins.sonar.SonarRunnerInstallation/sonarqube_scanner/bin/sonar-scanner -X'
                }
            }
        }
    }
}
