pipeline {
    agent any

    tools {
        maven 'M2_HOME'
    }

    stages {
        stage('Start Services with Docker Compose') {
            when {
                expression {
                    // Uncomment this line if you want to use Docker Compose for service management
                    // env.DOCKER_COMPOSE_ENABLED == 'true'
                    return false // Currently disabled by default
                }
            }
            steps {
                script {
                    echo 'Starting services with Docker Compose...'
                    sh 'docker-compose up -d'
                }
            }
        }
    }
}