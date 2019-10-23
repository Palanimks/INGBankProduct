node('master'){

 
   stage('Git checkout'){

                  git 'https://github.com/Palanimks/INGBankProduct.git'

              }

   stage('Code analysis'){

             sh '/opt/maven/bin/mvn clean package sonar:sonar -Dsonar.password=admin -Dsonar.login=admin'
         }

   stage('Build'){

             sh '/opt/maven/bin/mvn clean install'

         }
   stage('Execution'){

             sh 'export JENKINS_NODE_COOKIE=dontKillMe ;nohup java -Dspring.profiles.active=dev -jar $WORKSPACE/target/*.jar &'
         }
}
