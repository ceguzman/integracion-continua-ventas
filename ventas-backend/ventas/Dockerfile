FROM adoptopenjdk/openjdk8-openj9:x86_64-alpine-jre8u292-b10_openj9-0.26.0
COPY "./target/ventas-1.0.0-SNAPSHOT.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]