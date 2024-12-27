FROM arm64v8/openjdk:21-ea-21-jdk-slim

# 프로젝트 디렉토리 생성
RUN mkdir -p /home/project
WORKDIR /home/project

COPY ./build/libs/submaster-server-0.0.1-SNAPSHOT.jar /home/project
COPY ./scouter /home/project

ENTRYPOINT ["java", "-javaagent:scouter.agent.jar",\
"-Dscouter.config=scouter.conf",\
"-Duser.timezone=GMT+09:00", "-jar", "/home/project/submaster-server-0.0.1-SNAPSHOT.jar"]
