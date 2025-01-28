# исходный образ(операц.системы)
FROM openjdk:17-jdk-slim 
#подключение папки temp
VOLUME /tmp 
#копирование jar файла из нашего проекта в образ
COPY target/course-service-1.0.jar course-service.jar 
#указание команды запуска приложения внутри контейнера
ENTRYPOINT ["java","-jar","/course-service.jar"] 