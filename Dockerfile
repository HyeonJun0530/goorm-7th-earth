# Stage 1: Build the application
FROM krmp-d2hub-idock.9rum.cc/goorm/gradle:8.2.1-jdk17

WORKDIR /home/gradle/project

COPY . .

RUN echo "systemProp.http.proxyHost=krmp-proxy.9rum.cc\nsystemProp.http.proxyPort=3128\nsystemProp.https.proxyHost=krmp-proxy.9rum.cc\nsystemProp.https.proxyPort=3128" > /root/.gradle/gradle.properties

RUN gradle wrapper

RUN ./gradlew clean build

ENV DATABASE_URL=jdbc:mariadb://mariadb/krampoline

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "/home/gradle/project/build/libs/kakao-1.0.jar"]