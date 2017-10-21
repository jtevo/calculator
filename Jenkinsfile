pipeline {
        agent any
        triggers {
        		pollSCM('* * * * *')
		}
		post {
        		always {
             	mail to: 'zenmatix@gmail.com',
             	subject: "Completed Pipeline: ${currentBuild.fullDisplayName}",
             	body: "Your build completed, please check: ${env.BUILD_URL}"
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

      }
}