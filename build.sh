#! sh

cd ./eureka-server
mvn clean package

cd ./collect-service
mvn clean package  -X org.jacoco:jacoco-maven-plugin:report  -Dmaven.test.failure.ignore=true