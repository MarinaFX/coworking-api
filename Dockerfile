FROM openjdk:20-jdk
VOLUME /tmp
ADD build/libs/co-working-plain.jar /home/MyApp/App.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/MyApp/App.jar"]
