pipeline {
  agent any
  stages {
    stage('Init message') {
      parallel {
        stage('Init message') {
          agent any
          steps {
            sh 'echo "Starting build pipeline of java restful app"'
          }
        }

        stage('Teste') {
          steps {
            build 'HelloWorld'
          }
        }

      }
    }

    stage('Build ') {
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