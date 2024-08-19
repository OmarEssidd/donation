pipeline {
    agent any

    environment {
        // Nom de l'installation SonarQube dans Jenkins
        SONARQUBE_SCANNER = 'sonarqube_scanner'
        // Credentials pour Nexus (si nécessaire)
        NEXUS_CREDENTIALS_ID = 'nexus-credentials'
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
    script {
      try {
        sh 'mvn clean compile -e -X'
      } catch (Exception e) {
        echo "Maven clean and compile failed: ${e.getMessage()}"
        throw e
      }
    }
  }
}

       stage('Unit Tests') {
  steps {
    script {
      try {
        sh 'mvn test -e -X'
      } catch (Exception e) {
        echo "Maven tests failed: ${e.getMessage()}"
        throw e
      }
    }
  }
}

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(SONARQUBE_SCANNER) {
                    sh 'mvn sonar:sonar -e -X'
                }
            }
        }

        stage('Prepare Release') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Deploy to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: NEXUS_CREDENTIALS_ID, 
                                                 usernameVariable: 'NEXUS_USER', 
                                                 passwordVariable: 'NEXUS_PASS')]) {
                    sh "mvn deploy -DaltDeploymentRepository=nexus::default::http://localhost:8081/repository/maven-releases/ -Dnexus.user=$NEXUS_USER -Dnexus.password=$NEXUS_PASS"
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t omaressidd/donation:latest .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', 
                                                 usernameVariable: 'DOCKER_USER', 
                                                 passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh 'docker push omaressidd/donation:latest'
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
