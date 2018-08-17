pipeline {
     agent {
         label 'remote-slave'
     }
     stages {

          stage("Compile") {
               steps {
                    sh "chmod +x gradlew" 
                    sh "./gradlew compileJava"
               }
          }
          stage("Unit test") {
               steps {
                    sh "./gradlew test"
                    publishHTML (target: [
                    reportDir: 'build/reports/tests/test/',
                    reportFiles: 'index.html',
                    reportName: "Unit Test Report"
                ])
               }
          }
          stage("Code coverage") {
            steps {
                sh "./gradlew jacocoTestReport"
                publishHTML (target: [
                    reportDir: 'build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: "JaCoCo Report"
                ])
                sh "./gradlew jacocoTestCoverageVerification"
            }
         }
         stage("Static code analysis") {
            steps {
                sh "./gradlew checkstyleMain"
                publishHTML (target: [
                    reportDir: 'build/reports/checkstyle/',
                    reportFiles: 'main.html',
                    reportName: "Checkstyle Report"
                ])
            }
        }
     }
}