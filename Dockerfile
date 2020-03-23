FROM openjdk:11.0.6-jdk
COPY . .
#RUN ./mvnw install
COPY ./target/microservice.medicine-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]