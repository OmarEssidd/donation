pipeline {
    agent any

     tools {
        maven 'M2_HOME'
    }

    stages {
        stage('Checkout Git repository') {
            steps {
                echo 'Pulling from Git repository...'
                git branch: 'master', url: 'https://github.com/OmarEssidd/donation.git'
            }
        }

        stage('Start Services with Docker Compose') {
            steps {
                script {
                    echo 'Starting services with Docker Compose...'
                    sh 'docker-compose up -d'
                }
            }
        }

        stage('Maven Clean Compile') {
            steps {
                echo 'Running Maven clean...'
                sh 'mvn clean'
                echo 'Running Maven compile...'
                sh 'mvn compile'
            }
        }

        stage('Tests - JUnit/Mockito') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test'
            }
        }

        stage('Build package') {
            steps {
                echo 'Packaging the application...'
                sh 'mvn package'
            }
        }

        stage('Maven Install') {
            steps {
                echo 'Installing Maven dependencies...'
                sh 'mvn install'
            }
        }

        stage('JaCoCo Report') {
            steps {
                echo 'Running JaCoCo for code coverage...'
                sh 'mvn test'
                sh 'mvn jacoco:report'
            }
        }

        stage('JaCoCo coverage report') {
            steps {
                step([$class: 'JacocoPublisher',
                      execPattern: '**/target/jacoco.exec',
                      classPattern: '**/classes',
                      sourcePattern: '**/src',
                      exclusionPattern: '*/target/**,**/*Test*,**/_javassist/**'
                ])
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonartokenomar') {
                    echo 'Running SonarQube analysis...'
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                echo 'Deploying to Nexus...'
                sh 'mvn deploy'
            }
        }

        stage('Build Docker Image (Spring Part)') {
            steps {
                script {
                    echo 'Building Docker image...'
                    sh 'sudo chmod 666 /var/run/docker.sock'
                    def dockerImage = docker.build("omaressid/donation:1")
                }
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerpwd')]) {
                        echo 'Pushing Docker image to DockerHub...'
                        sh '''
                        docker login -u omaressid89 -p "$dockerpwd"
                        docker push omaressid/donation:latest
                        '''
                    }
                }
            }
        }

        stage('Monitoring Services G/P') {
            steps {
                script {
                    echo 'Starting Grafana and Prometheus services...'
                    sh 'docker start 0be4cb49cf95'
                    sh 'docker start 9d7d8575dded'
                }
            }
        }

        stage('Email Notification') {
            steps {
                mail bcc: '', 
                     body: '''Stage: GIT Pull
 - Pulling from Git...

Stage: Maven Clean Compile
 - Building Spring project...

Stage: Test - JUNIT/MOCKITO
 - Testing Spring project...

Stage: JaCoCo Report
 - Generating JaCoCo Coverage Report...

Stage: SonarQube Analysis
 - Running Sonarqube analysis...

Stage: Deploy to Nexus
 - Deploying to Nexus...

Stage: Build Docker Image
 - Building Docker image for the application...

Stage: Push Docker Image
 - Pushing Docker image to Docker Hub...

Stage: Monitoring Services G/P
 - Starting Prometheus and Grafana...

Final Report: The pipeline has completed successfully. No action required.''',
                     cc: '', 
                     from: '', 
                     replyTo: '', 
                     subject: 'Succès de la pipeline DevOps Project donation', 
                     to: 'OmarEssid@esprit.tn'
            }
        }
    }
}
