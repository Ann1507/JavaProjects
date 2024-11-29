FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/course-service.jar course-service.jar
ENTRYPOINT ["java","-jar","/course-service.jar"]