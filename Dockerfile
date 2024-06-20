FROM gradle:7.6.0-jdk17 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build -x test || return 0
COPY . .
RUN gradle clean build -x test

# actual container
FROM openjdk:20-jdk
ENV ARTIFACT_NAME=co-working.jar
ENV APP_HOME=/usr/app/

VOLUME /tmp
ADD build/libs/co-working.jar /home/MyApp/App.jar
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

EXPOSE 8080
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
