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

   stage('undeploy the app in tomcat'){
      sh "wget --http-user=admin --http-password=welcome1 'http://localhost:8080/manager/text/undeploy?path=/SpringBootTest' -O -"
   }
   stage('deploy to tomcat'){
      echo "deployihng to tomat now"
      //sh "curl -X POST --upload-file ${WORKSPACE}/target/SpringBootTest-0.0.1-SNAPSHOT.war http://deployer:deployer@localhost:8080/manager/deploy?path=/SpringBootTest&update=true"
      //sh "curl -X POST -v -u deployer:deployer -T ${WORKSPACE}/target/SpringBootTest-0.0.1-SNAPSHOT.war http://localhost:8080/manager/deploy?path=/SpringBootTest&update=true"
      sh "wget --http-user=admin --http-password=welcome1 'http://localhost:8080/manager/text/deploy?war=file:${WORKSPACE}/target/SpringBootTest-0.0.1-SNAPSHOT.war&path=/SpringBootTest&update=true' -O -"
      echo "deployed to tomat now"
   }
}
