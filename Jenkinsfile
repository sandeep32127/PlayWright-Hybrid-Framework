pipeline 
{
    agent any
    
    tools{
    	maven 'maven'
        }

    stages 
    {
                      
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
                
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/sandeep32127/PlayWright-Hybrid-Framework'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testRunners/testNgTestsSeleniumGrid.xml"
                    
                }
            }
        }
              
        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'reports', 
                                  reportFiles: 'ExtentReport.html', 
                                  reportName: 'HTML Extent Report', 
                                  reportTitles: ''])
            }
        }      
    }
}