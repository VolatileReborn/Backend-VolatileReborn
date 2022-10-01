#!/bin/sh

cd ./eureka-server
mvn clean package

pwd

cd ../collect-service

pwd
#mvn clean package  -X org.jacoco:jacoco-maven-plugin:report  -Dmaven.test.failure.ignore=true
mvn clean package