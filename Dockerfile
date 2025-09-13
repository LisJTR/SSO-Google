# Dockerfile
FROM eclipse-temurin:17-jre
WORKDIR /app
# jar creado por Maven
COPY target/*.jar app.jar
EXPOSE 9778
ENTRYPOINT ["java","-jar","/app/app.jar"]