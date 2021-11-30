# Auction

It was about 10 years ago, when I created this test project for educational purposes. 
JSP,Java Beans and pure JDBC connection.
It still works!
 
#Usage
For simple check:
  Clone repository
  Run "mvn package"
  Run "mvn jetty:run"
  See localhost:8080
  
For full work:
  Clone repository
  Run "mvn package"
  Have a Tomcat installed and running.
  Have a MySQL installed and running.
  Execute auction.sql to create DB in MySQL.
  Add GlobalNamingResource as described in Tomcat Doc https://tomcat.apache.org/tomcat-7.0-doc/config/globalresources.html
  Deploy war file from target/auction-jsp-beans.war to Tomcat (see Tomcat Doc again)
  See localhost:8080
  Done!
  
  


