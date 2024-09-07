pipeline{
    agent any

    environment{
        IMAGE_NAME = 'suyash874/devopsapp'
        DOCKER_REGISTRY_CREDENTIALS = 'dockerhub-credentials-id'
        APP_PORT = '9090'
    }

    stages{
        stage('Checkout'){
            steps{
                git branch: 'main', url: 'https://github.com/CompCodeHub/java-demo-app'
            }
        }
        stage('Build'){
            steps{
                script{
                    // Build image
                    docker.build("${IMAGE_NAME}", ".")
                }
                
            }
        }
        stage('Login to Dockerhub'){
            steps{
                script{
                    // Login to DockerHub using credentials stored in Jenkins
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_REGISTRY_CREDENTIALS) {
                        echo 'Logged in to DockerHub'
                    }
                }

            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Push the image to DockerHub
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_REGISTRY_CREDENTIALS) {
                        docker.image("${IMAGE_NAME}").push('latest')
                    }
                }
            }
        }

        stage('Deploy'){
            steps{
                script{
                    sh """
                    docker stop java-app || true
                    docker rm java-app || true
                    docker run -d -p ${APP_PORT}:${APP_PORT} --name java-app ${IMAGE_NAME}:latest
                    """
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}