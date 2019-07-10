FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar financeiro-contasreceber-app.jar
ENTRYPOINT ["java","-jar","/financeiro-contasreceber-app.jar"]