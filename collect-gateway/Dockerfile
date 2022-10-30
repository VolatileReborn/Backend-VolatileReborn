FROM openjdk:8-jdk-alpine

# 如果是在eureka-server项目目录中build, 则需要把目录改为"target/eureka-server-0.0.1-SNAPSHOT.jar"， 即去掉"./eureka-server/"
ADD ./eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar /eureka-service.jar
EXPOSE 9999
ENTRYPOINT ["java","-jar","/collect-gateway.jar"]
