call mvn clean package spring-boot:repackage
call java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar target/article-app-0.0.1.jar