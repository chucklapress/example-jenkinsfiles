pipeline {
  agent any
  stages {
    stage('call to Kayne rest quote') {
      steps {
          sh 'newman run https://www.getpostman.com/collections/c5acfa103b6225c68c85 --reporters cli,junit --reporter-junit-export newmanv1.xml --suppress-exit-code 1'
      }
    }
    stage('call to Simpsons quote API') {
        steps {
            sh 'newman run https://www.getpostman.com/collections/363b7134f890414c4d48 --reporters cli,junit --reporter-junit-export newmanv2.xml --suppress-exit-code 1'
        }
    }
  }
  post {
    always {
      echo 'cleaning workspace'
      junit 'newmanv1.xml, newmanv2.xml'
      cleanWs()
    }
  }
}
