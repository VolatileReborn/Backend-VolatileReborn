#!/bin/sh

# First, eureka
echo "Eureka"
cd ./eureka-server
pwd
mvn clean package



echo "Zuul"
cd ../collect-gateway
pwd
mvn clean package



echo "Collect"
pwd
cd ../collect-service
#mvn clean package  -X org.jacoco:jacoco-maven-plugin:report  -Dmaven.test.failure.ignore=true
mvn clean package