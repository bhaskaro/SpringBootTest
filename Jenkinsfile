node{
   stage('SCM Checkout'){
     git 'https://github.com/bhaskaro/SpringBootTest'
   }
   stage('Compile-Package'){
    
      def mvnHome =  tool name: 'maven-3', type: 'maven'   
      sh "${mvnHome}/bin/mvn package"
   }
   stage('Email Notification'){
     // mail bcc: '', body: '''Hi Welcome to jenkins email alerts
      //Thanks
      //Hari''', cc: '', from: '', replyTo: '', subject: 'Jenkins Job', to: 'hari.kammana@gmail.com'
echo "mail sent successfully to bhaskaro@gmai.com"
   }
   stage('deploy to tomcat'){
      echo "deployihng to tomat now"
      //sh "curl --upload-file ${WORKSPACE}/target/SpringBootTest-0.0.1-SNAPSHOT.war http://deployer:deployer@localhost:8080/manager/deploy?path=/SpringBootTest&update=true"
      sh "curl -v -u deployer:deployer -T ${WORKSPACE}/target/SpringBootTest-0.0.1-SNAPSHOT.war http://localhost:8080/manager/deploy?path=/SpringBootTest&update=true"
      echo "deployed to tomat now"
   }
}
