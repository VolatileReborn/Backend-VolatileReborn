FROM openjdk:8-jdk-alpine

# 如果是在eureka-server项目目录中build, 则需要把目录改为"target/eureka-server-0.0.1-SNAPSHOT.jar"， 即去掉"./eureka-server/"
ADD ./collect-gateway/target/collect-gateway-0.0.1-SNAPSHOT.jar /collect-gateway.jar
EXPOSE 9999
ENTRYPOINT ["java","-jar","/eureka-service.jar"]
