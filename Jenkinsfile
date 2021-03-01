def COLOR_MAP = ['SUCCESS': 'good', 'FAILURE': 'danger','UNSTABLE': 'danger']

pipeline {
    agent any
    options { skipStagesAfterUnstable() }
    environment {
        AWS_ACCESS_KEY_ID     = credentials('jenkins-aws-secret-key-id')
        AWS_SECRET_ACCESS_KEY = credentials('jenkins-aws-secret-access-key')
        DOCKER_AUTH_CREDENTIALS = credentials('dockerHub-login-pass')
    }

    stages {
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh "./gradlew clean assemble -PbuildNumber=${env.BUILD_ID} -PbranchName=${env.BRANCH_NAME}"
            }
        }
        stage('Test') {
//             parallel {
//                 stage('Unit Tests') {
//                     steps {
//                         script {
//                             try {
//                                 sh './gradlew test'
//                             } catch (Exception err) {
//                                 currentBuild.result = 'UNSTABLE'
//                             }
//                         }
//                     }
//                 }
//                 stage('Integration Tests') {
//                     steps {
//                         script {
//                             try {
//                                 sh './gradlew integrationTest'
//                             } catch (Exception err) {
//                                 currentBuild.result = 'UNSTABLE'
//                             }
//                         }
//                     }
//                 }
//             }
            steps {
                script {
                    try {
                        sh './gradlew test'
//                         sh './gradlew integrationTest'
                    } catch (Exception err) {
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
            post {
                always {
                    junit 'build/test-results/**/*.xml'
                }
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh "./gradlew sonarqube -PbuildNumber=${env.BUILD_ID} -PbranchName=${env.BRANCH_NAME}"
                }
            }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Publish Artifacts') {
            parallel {
                stage('Upload Artifact') {
                    steps {
                        echo "./gradlew :publish"
                        //sh './gradlew :publish'
                    }
                }
                stage('Upload Image') {
                    steps {
                        sh "docker login -u $DOCKER_AUTH_CREDENTIALS_USR -p $DOCKER_AUTH_CREDENTIALS_PSW"
                        sh "./gradlew pushImage -PawsAccessKeyId=$AWS_ACCESS_KEY_ID -PawsSecretAccessKey=$AWS_SECRET_ACCESS_KEY -PbuildNumber=${env.BUILD_ID} -PbranchName=${env.BRANCH_NAME}"
                    }
                }
            }
        }
    }
    post {
        success {
            script {
                currentBuild.result = 'SUCCESS'
            }
            slackSend channel: '#embl-ci',
                      color: 'good',
                      message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}"
        }
        fixed {
            script {
                echo 'currentBuild.result = ' + currentBuild.result
                currentBuild.result = 'SUCCESS'
            }
            emailext body: '${JELLY_SCRIPT,template="static-analysis2"}', mimeType: 'text/html', recipientProviders: [upstreamDevelopers(), requestor(), developers()], subject: '$DEFAULT_SUBJECT'
            slackSend channel: '#embl-ci',
                      color: 'good',
                      message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}"
        }

        failure {
            emailext body: '${JELLY_SCRIPT,template="static-analysis2"}', mimeType: 'text/html', recipientProviders: [upstreamDevelopers(), requestor(), developers()], subject: '$DEFAULT_SUBJECT'
            slackSend channel: '#embl-ci',
                      color: 'danger',
                      message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}"
        }
        unstable {
            emailext body: '${JELLY_SCRIPT,template="static-analysis2"}', mimeType: 'text/html', recipientProviders: [upstreamDevelopers(), requestor(), developers()], subject: '$DEFAULT_SUBJECT'
            slackSend channel: '#embl-ci',
                      color: 'danger',
                      message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}"
        }
    }
}
