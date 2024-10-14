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
        stage('Status Mysql') {
            steps {
                script {
                    sh 'sudo systemctl start mysql'
                }
            }
        }

        stage('Maven Clean Compile') {
            steps {
                sh 'mvn clean --settings settings.xml'
                echo 'Running Maven Compile'
                sh 'mvn compile --settings settings.xml'
            }
        }

        stage('Tests - JUnit/Mockito') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test --settings settings.xml'
            }
        }

        stage('Build package') {
            steps {
                echo 'Packaging the application...'
                sh 'mvn package --settings settings.xml'
            }
        }

        stage('Maven Install') {
            steps {
                echo 'Installing Maven dependencies...'
                sh 'mvn install --settings settings.xml'
            }
        }

        stage('JaCoCo Report') {
            steps {
                echo 'Running JaCoCo for code coverage...'
                sh 'mvn test --settings settings.xml'
                sh 'mvn jacoco:report --settings settings.xml'
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
                    sh 'mvn sonar:sonar --settings settings.xml'
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                echo 'Deploying to Nexus...'
                sh 'mvn deploy --settings settings.xml'
            }
        }

        stage('Build Docker Image (Spring Part)') {
            steps {
                script {
                    echo 'Building Docker image...'
                    def dockerImage = docker.build("omaressid/donation:latest")
                }
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhubpwd', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        echo 'Pushing Docker image to DockerHub...'
                        
                        sh '''
                        echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                        docker push omaressid/donation:latest
                        '''
                    }
                }
            }
        }

        stage('Stop MySQL Service') {
            steps {
                script {
                    sh 'sudo systemctl stop mysql || true'
                }
            }
        }

        stage('Start Services with Docker Compose') {
            steps {
                script {
                    sh 'docker-compose down || true'
                    sh 'docker-compose up -d'  
                }
            }
        }

        stage('Monitoring Services G/P') {
            steps {
                script {
                    echo 'Starting Grafana and Prometheus services...'
                    sh 'docker-compose up -d prometheus grafana'
                    sleep(10)
                    sh 'docker-compose ps'
                }
            }
        }

        stage('Email Notification') {
            steps {
                script {
                    def emailBody = '''\
Stage: GIT Pull
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

Final Report: The pipeline has completed successfully. No action required.'''

                    mail bcc: '',
 
                         body: emailBody,
                         cc: '', 
                         from: 'contact.omar.essid@gmail.com',  // Remplacez par votre adresse e-mail
                         replyTo: '', 
                         subject: 'Succès de la pipeline DevOps Project donation', 
                         to: 'Omar.Essid@esprit.tn'
                }
            }
        }
    }

    post {
        failure {
            mail to: 'contact.omar.essid@gmail.com',
                 subject: "Échec de la pipeline: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Le pipeline a échoué. Consultez le résultat ici : ${env.BUILD_URL}"
        }
    }
}
