FROM openjdk:8-jre-slim

COPY target/sw-auth-server.jar /app.jar
CMD java ${JAVA_OPTS} -Dspring.profiles.active=${ACTIVE_PROFILES} -jar /app.jar
