FROM arm64v8/openjdk:21-ea-21-jdk-slim

# 프로젝트 디렉토리 생성
RUN mkdir -p /home/project
WORKDIR /home/project

# Gradle 파일과 의존성 파일 먼저 복사
COPY gradlew gradle/ ./

# Gradle의 권한 설정 및 의존성 다운로드
RUN chmod +x gradlew

# 나머지 소스 코드 복사
COPY . .

# JAR 파일 생성
RUN ./gradlew bootJar --no-daemon

ENTRYPOINT ["java", "-Duser.timezone=GMT+09:00", "-jar", "/home/project/build/libs/submaster-server-0.0.1-SNAPSHOT.jar"]
