pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'python3 --version'
                sh 'pip3 --version '
                sh 'whoami'
                sh '/home/chuck/python_simple/hello.py'
                sh '''
                    pip3 install robotframework
                    '''
                sh 'pwd'
                sh 'python3 -m robot.run --outputdir reports /var/lib/jenkins/workspace/python-test /var/lib/jenkins/workspace/python-test/resource/tests/*.robot '
                //[report Location] [Test Location]

            }
        }
    }
    post {
        always {
            echo 'how to echo reports?'
        }
    }
}
