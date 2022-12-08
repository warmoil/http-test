FROM openjdk:17-jdk-slim
COPY build/libs/http-test-1.0-SNAPSHOT.jar httptest.jar

ENTRYPOINT ["java","-jar","httptest.jar"]