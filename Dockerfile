FROM openjdk

WORKDIR /app

COPY myJar.jar /app

CMD ["java", "-jar", "/app/myJar.jar"]

EXPOSE 8080
