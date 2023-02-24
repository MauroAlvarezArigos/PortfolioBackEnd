FROM amazoncorretto:17-alpine-jdk
MAINTAINER MauroAlvarezArigos
COPY target/spring-boot-docker.jar malar-app.jar
ENTRYPOINT ["java","-jar","/malar-app.jar"]