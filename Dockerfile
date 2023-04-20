FROM openjdk

WORKDIR /app

COPY myJar.jar /app

CMD ["java", "-jar", "/app/myJar.jar", "migrate"]

EXPOSE 8080
