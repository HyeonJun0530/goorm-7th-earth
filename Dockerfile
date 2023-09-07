FROM openjdk:17-alpine

CMD ["java","-jar","/build/libs/goorm-7th-earth-0.0.1-SNAPSHOT.jar"]

WORKDIR /usr/src/app

ARG JAR_PATH=./build/libs

COPY ${JAR_PATH}/goorm-7th-earth-0.0.1-SNAPSHOT.jar ${JAR_PATH}/goorm-7th-earth-0.0.1-SNAPSHOT.jar
