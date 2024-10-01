pipeline {
    agent any

    tools {
        maven 'M2_HOME'
    }

    stages {
        stage('Checkout Git repository') {
            steps {
                script {
                    echo 'Pulling from Git repository...'
                    git branch: 'master', url: 'https://github.com/OmarEssidd/donation'
                }
            }
        }
    }
}