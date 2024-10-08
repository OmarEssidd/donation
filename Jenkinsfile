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
            // Utiliser usernamePassword pour obtenir le nom d'utilisateur et le mot de passe
            withCredentials([usernamePassword(credentialsId: 'dockerhubpwd', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                echo 'Pushing Docker image to DockerHub...'
                
                // Connexion à DockerHub
                sh '''
                echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                docker push omaressid/donation:latest
                '''
            }
        }
    }
}

       stage('Start Services with Docker Compose') {
    steps {
        script {
            // Arrêtez le service MySQL sans sudo
            sh 'systemctl stop mysql'
            // Lancez les services avec Docker Compose
            sh 'docker-compose up -d'
        }
    }
}


        stage('Monitoring Services G/P') {
            steps {
                script {
                    echo 'Starting Grafana and Prometheus services...'
                    sh 'docker start 0be4cb49cf95'
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
