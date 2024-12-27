FROM arm64v8/openjdk:21-ea-21-jdk-slim

# Gradle 캐시를 사용하기 위해 디렉터리 생성
RUN mkdir -p /home/project/.gradle/caches
RUN mkdir -p /home/project/.gradle/wrapper

# working directory 설정
WORKDIR /home/project

# build.gradle 파일과 gradlew 파일 복사
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle .

# 필요한 의존성을 먼저 다운로드하여 레이어 캐싱 활용
RUN chmod +x gradlew && ./gradlew build --no-daemon --offline

# 나머지 소스 코드 복사
COPY . .

# JAR 파일 생성
RUN ./gradlew bootJar --no-daemon

# 엔트리포인트 설정
ENTRYPOINT ["java", "-Duser.timezone=GMT+09:00", "-jar", "/home/project/build/libs/submaster-server-0.0.1-SNAPSHOT.jar"]
