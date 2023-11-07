FROM openjdk:11
EXPOSE 8091
ADD /target/workflow-1.0.jar workflow-service
ENTRYPOINT ["java", "-jar", "/workflow-service.jar"]