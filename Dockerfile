FROM openjdk:11.0.6-jdk
#FROM maven:3.6.3-jdk-14
#COPY . .
#RUN mvn install
COPY target/microservice.medicinePO-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]