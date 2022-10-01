#FROM openjdk:8-jdk-alpine
FROM openjdk:8-jdk-alpine
VOLUME /tmp

# 如果是在collect-service项目目录中build, 则需要把目录改为"target/collect-service-0.0.1-SNAPSHOT.jar"， 即去掉"./collect-service/"
ADD ./collect-service/target/collect-service-0.0.1-SNAPSHOT.jar /app.jar

RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]