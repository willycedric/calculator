pipeline {
  agent {
    label "remote-docker-slave"
  }
	
  // triggers {
  //   pollSCM('* * * * *')
  // }
	
  // stages {
  //   stage("Compile") {
  //     steps {
  //        sh "chmod +x gradlew"
  //       sh "./gradlew compileJava"
  //     }
  //   }
	  
  //   stage("Unit test") {
  //     steps {
  //       sh "./gradlew test"
  //     }
  //   }
	
    // stage("Code coverage") {
    //   steps {
    //     sh "./gradlew jacocoTestReport"
    //     publishHTML (target: [
    //            reportDir: 'build/reports/jacoco/test/html',
    //            reportFiles: 'index.html',
    //            reportName: "JaCoCo Report" ])
    //     sh "./gradlew jacocoTestCoverageVerification"
    //   }
    // }

    // stage("Static code analysis") {
    //   steps {
    //     sh "./gradlew checkstyleMain"
    //     publishHTML (target: [
    //            reportDir: 'build/reports/checkstyle/',
    //            reportFiles: 'main.html',
    //            reportName: "Checkstyle Report" ])
    //   }
    // }
  stages{
    stage("Build") {
      steps {
        sh "chmod +x gradlew"
        sh "./gradlew build"
      }
    }

    stage("Docker login") {
      steps {
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'willycedric',
                          usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
          sh "docker login --username $USERNAME --password $PASSWORD"
        }
      }
    }

    stage("Docker build") {
      steps {
        sh "docker build -t willycedric/calculator:${BUILD_TIMESTAMP} ."
      }
    }

    

    stage("Docker push") {
      steps {
        sh "docker push willycedric/calculator:${BUILD_TIMESTAMP}"
      }
    }
    stage("Deploy to staging") {
      steps {
          sh "docker-compose up -d"
      }
    }

    stage("Acceptance test") {
          steps {
              sleep 60
              sh "chmod +x acceptance_test.sh"
              sh "./acceptance_test.sh"
          }
      }     


    // stage("Deploy to staging") {
    //   steps {
    //     sh "ansible-playbook playbook.yml -i inventory/staging"
    //     sleep 60
    //   }
    // }

    // stage("Acceptance test") {
    //   steps {
    //     sh "chmod +x acceptance_test.sh"
	  //     sh "./acceptance_test.sh 192.168.3.179"
    //   }
    // }
	  
  //   // Performance test stages

  //   stage("Release") {
  //     steps {
  //       sh "ansible-playbook playbook.yml -i inventory/production"
  //       sleep 60
  //     }
  //   }

  //   stage("Smoke test") {
  //     steps {
	// sh "./smoke_test.sh 192.168.0.115"
  //     }
  //   }
  }
  post {
      always {
          sh "docker-compose down"
      }
  }
}
