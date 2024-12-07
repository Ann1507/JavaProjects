FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/course-service-1.0.jar course-service.jar
ENTRYPOINT ["java","-jar","/course-service.jar"]