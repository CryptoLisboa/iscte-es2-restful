#Get java image from docker hub
FROM openjdk:11
#Attribute non root previleges to mitigate risk
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#
#CMD ls
#ARG JAR_FILE=target/*.jar
COPY target/rest-service-4.2.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
