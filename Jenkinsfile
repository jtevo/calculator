pipeline {
        agent any
        triggers {
        		pollSCM('* * * * *')
		}
		 post {
        		always {
             	
             	sh "sudo docker-compose down"
             	
			} 
		} 
        stages {
        
			stage('SonarQube analysis') {
     			steps {
	    					withSonarQubeEnv('My SonarQube Server') {
		      				// requires SonarQube Scanner for Gradle 2.1+
		      				// It's important to add --info because of SONARJNKNS-281
		      				sh './gradlew --info sonarqube'
    						}
  					}
    			 }        
             stage("Compile") {
                  steps {
                       sh "./gradlew compileJava"
					} 
			}
             stage("Unit test") {
                  steps {
                       sh "./gradlew test"
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
     		stage("Package") {
        			steps {
             		sh "./gradlew clean build"
        			}
			}
			/* stage("Docker build") {
        			steps {
             		sh "sudo docker build -t calculator ."
        			}
			} */
			/*stage("Docker push") {
        			steps {
             		sh "docker push zeno2/calculator"
        			}
			}*/
			stage("Deploy to staging") {
        			steps {
             		//sh "sudo docker run -d --rm -p 8765:8080 --name calculator calculator"
             		sh "sudo docker-compose up -d"
				} 
			}
			stage("Acceptance test") {
        			steps {
					sleep 60
             		sh "./acceptance_test.sh"
        			}
			}

      }
}
