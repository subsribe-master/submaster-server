FROM arm64v8/openjdk:21-ea-21-jdk-slim

RUN mkdir -p /home/project
WORKDIR /home/project

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean
RUN ./gradlew bootJar

ENTRYPOINT ["java", "-Duser.timezone=GMT+09:00", "-jar", "/home/project/build/libs/submaster-server-0.0.1-SNAPSHOT.jar"]
