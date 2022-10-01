FROM openjdk:8-jdk-alpine

ADD ./eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar /eureka-service.jar
EXPOSE 8001
ENTRYPOINT ["java","-jar","/eureka-service.jar"]
