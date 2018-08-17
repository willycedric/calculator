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
               }
          }
     }
}