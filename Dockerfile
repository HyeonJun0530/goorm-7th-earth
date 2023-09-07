# Stage 1: Build the application
FROM gradle:8.2.1-openjdk:17-oracle

WORKDIR /home/gradle/project

COPY . .

RUN echo "systemProp.http.proxyHost=krmp-proxy.9rum.cc\nsystemProp.http.proxyPort=3128\nsystemProp.https.proxyHost=krmp-proxy.9rum.cc\nsystemProp.https.proxyPort=3128" > /root/.gradle/gradle.properties

RUN gradle wrapper

RUN ./gradlew clean build

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "/home/gradle/project/build/libs/kakao-1.0.jar"]