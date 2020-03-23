pipeline {
  agent any
  stages {
    stage('Init message') {
      agent any
      steps {
        sh 'echo "Starting build pipeline of java restful app"'
      }
    }

    stage('Build ') {
      steps {
        build 'grupo-javabuild'
      }
    }

    stage('Generate JavaDoc') {
      steps {
        build 'csmma1 - javadoc'
      }
    }

  }
}