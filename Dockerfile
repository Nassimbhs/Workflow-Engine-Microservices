FROM openjdk:11
EXPOSE 8091
ADD /target/Workflow-0.0.1-SNAPSHOT.jar workflow-service.jar
ENTRYPOINT ["java", "-jar", "/workflow-service.jar"]