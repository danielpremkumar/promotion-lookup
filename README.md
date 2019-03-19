# promotion-lookup
# Download ojdbc7.jar from oracle and install it in the local mvn or nexus repository
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.1 -Dpackaging=jar -Dfile=C:\Users\dan1987\Downloads\ojdbc7.jar

# This project uses Spring Profiles to load the right datasources for different env.
# Start the spring-boot app with param -Dspring.profiles.active=ca-perf for Catherines Performance Env.

Swagger URL
http://localhost:8083/swagger-ui.html


To Generate Fat jar, run the below command
mvnw package

To execute the jar
java -jar -Dspring.profiles.active=ju-perf promotion-lookup-0.0.1-SNAPSHOT.jar