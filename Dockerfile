FROM openjdk:17
ADD target/jobs.jar jobs.jar
ENTRYPOINT ["java", "-jar","jobs.jar"]

EXPOSE 8087