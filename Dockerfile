FROM openjdk:11-jre-slim
WORKDIR /app
EXPOSE 8091
COPY target/workflow-1.0.jar workflow-service.jar
ENV SPRING_DATASOURCE_URL=jdbc:mysql://user-mysql:3306/workflowdb?useSSL=false
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=
ENTRYPOINT ["java", "-jar", "/workflow-service.jar"]