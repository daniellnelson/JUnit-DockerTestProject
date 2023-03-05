# JUnit-DockerTestProject
This project takes a MYSQL database inside a docker container and allows a java application to create a random table and insert 10 records. In addition, a JUnit test class with 6 unit tests and 1 integration test are provided to test the java application. 


#Staging

With Docker Desktop running (I used version 4.17.0 for Mac, and I'm alson running Ventura MacOS 13.2.1 (22D68)), perform either of the following commands:

Option1: 
- docker run -it -p 3306:3306 --name altrdb -e MYSQL_ROOT_PASSWORD=HELLOWORLD mysql 

- docker exec -it altrdb 
  mysql -uroot -p
  Show databases;
  
Option 2: 

- docker-compose -f docker-compose.yml up 
  - *To run option two, the docker-compose.yml file is found in */Java/ALTR_JDBC/src/main, so please make sure you make this your current directory.
  Once the database is connected with docker-compose, you may still need to perform the following:
    - docker exec -it altrdb 
      mysql -uroot -p
      Show databases;
      
After entering your password, please make sure to run the MYSQL command 'Use altr_prod_db;'

#Running the Java application

1.Make the following your current directory in your terminal:
- */Java/ALTR_JDBC/src/main

2.Run javac App.java
- Run java App

#Running the Java Test


For myself, I ended up running my AppTest.java program straight through my Eclipse IDE. I downloaded Version: 2022-12 (4.26.0) and used JUnit 4; I played around with a few run configurations to get to the point where I was running a success rate of 85% of the test I wrote (6/7 test cases present). I simply ran the tests by right-clicking the AppTest.java file and going to 'Run As' and selecting 'JUnit Test'; 

      
     




