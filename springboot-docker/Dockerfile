# The base image
FROM openjdk:8-jdk-alpine

# create a directory inside the docker image
VOLUME /tmp

# More instructions here that install software and copy files into the image.
ADD target/springboot-docker-demo-0.0.1-SNAPSHOT.jar springboot-docker-demo.jar

# making your Docker image an executable command itself, which can be started up and which shut down automatically when finished.
ENTRYPOINT [ "sh", "-c", "java -jar /springboot-docker-demo.jar" ]
