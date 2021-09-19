#
# Build stage
#
FROM maven:3.8.2-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true

#
# Package stage
#
FROM openjdk:8-jre-slim
COPY --from=build /home/app/target/sw-auth-server.jar /usr/local/lib/app.jar
EXPOSE 11080
#ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
CMD java ${JAVA_OPTS} -Dspring.profiles.active=${ACTIVE_PROFILES} -jar /usr/local/lib/app.jar
