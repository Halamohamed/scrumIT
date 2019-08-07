FROM openjdk:8
ADD /target/scrumIT.jar scrumIT.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar","scrumIT.jar"]