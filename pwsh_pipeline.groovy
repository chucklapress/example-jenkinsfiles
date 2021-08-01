def notify(status) {
    slackSend channel: "#general",
    color: '#1f29d7',
    message: "${status}",
    tokenCredentialId: 'slack-token'
}
pipeline {
  agent any

  stages {
      stage('Slack Notify kick off') {
          steps {
              notify('pwsh-pipeline kicking off')
          }
      }
      stage('Stage 1') {
          steps {
          notify("Build Started")
          pwsh ('''
                    $text = ((Invoke-Webrequest -Uri "https://uselessfacts.jsph.pl/random.json?language=en" -UseBasicParsing).content | ConvertFrom-Json).text
                    Write-Output "$text"
            ''')
          }
      }
      stage('Stage 2') {
          steps {
              pwsh ('''
                    $details = ($PSVersionTable.PSVersion)
                    Write-Output "Here are the details of the powershell version" "`n $details"
              ''')
              notify("Build Completed")
          }
      }
  }
  post {
        always {
            cleanWs()
        }
    }
}
