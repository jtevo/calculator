pipeline {
        agent any
        triggers {
        		pollSCM('* * * * *')
		}
		 post {
        		always {
             	//mail to: 'zenmatix@gmail.com',
             	//subject: "Completed Pipeline: ${currentBuild.fullDisplayName}",
             	//body: "Your build completed, please check: ${env.BUILD_URL}"
             	sh "docker stop calculator"
             	
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
             		sh "./gradlew build"
        			}
			}
			stage("Docker build") {
        			steps {
             		sh "docker build -t calculator ."
        			}
			}
			/*stage("Docker push") {
        			steps {
             		sh "docker push zeno2/calculator"
        			}
			}*/
			stage("Deploy to staging") {
        			steps {
             		sh "docker run -d --rm -p 8765:8080 --name calculator
   					calculator"
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