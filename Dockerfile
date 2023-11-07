FROM openjdk:11
EXPOSE 8091
ADD target/workflow-1.0.jar workflow-1.0.jar
ENTRYPOINT ["java", "-jar", "/workflow-1.0.jar"]