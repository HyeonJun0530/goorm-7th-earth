FROM gradle:8.3-jdk17

WORKDIR /home/gradle/project

COPY . .

CMD ["java","-jar","/home/gradle/project/build/libs/goorm-7th-earth-0.0.1-SNAPSHOT.jar"]