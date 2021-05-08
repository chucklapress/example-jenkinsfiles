pipeline {
  agent any
  stages {
      stage('Stage 1') {
          steps {
          pwsh ('''
                    $text = ((Invoke-Webrequest -Uri "https://uselessfacts.jsph.pl/random.json?language=en" -UseBasicParsing).content | ConvertFrom-Json).text
                    Write-Output "$text"
            ''')
          }
      }
  }
}
