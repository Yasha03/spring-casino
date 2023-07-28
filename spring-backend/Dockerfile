FROM openjdk:17-slim

ENV PORT=8099

WORKDIR /app

COPY build/libs/demo-0.0.1-SNAPSHOT.jar .

EXPOSE $PORT

CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
