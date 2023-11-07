FROM openjdk:11
ARG JAR_FILE=target/workflow-1.0.jar
COPY ${JAR_FILE} workflow-1.0.jar
ENTRYPOINT ["java", "-jar", "/workflow-1.0.jar"]
EXPOSE 8091