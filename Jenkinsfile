pipeline {
  agent any
  stages {
    stage('Init message') {
      agent any
      steps {
        sh 'echo "Starting build pipeline of java restful app"'
      }
    }

    stage('Build & Test') {
      steps {
        build 'grupo-javabuild'
      }
    }

    stage('Generate JavaDoc') {
      parallel {
        stage('Generate JavaDoc') {
          steps {
            build 'csmma1 - javadoc'
          }
        }

        stage('JUnit Tests') {
          steps {
            build 'falos - junitTests'
          }
        }

      }
    }

  }
}