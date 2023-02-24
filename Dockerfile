FROM openjdk:17-oracle
COPY target/alar-0.0.1-SNAPSHOT.jar BackendAlar.jar
ENTRYPOINT ["java","-jar","/BackendAlar.jar"]