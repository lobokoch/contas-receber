FROM adoptopenjdk/openjdk8-openj9:latest
VOLUME /tmp
COPY target/*.jar financeiro-contasreceber-app.jar
ENTRYPOINT ["java", "-Xshareclasses:name=kerubin", "-jar", "/financeiro-contasreceber-app.jar"]