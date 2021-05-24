FROM openjdk:11
VOLUME /tmp
EXPOSE 8082
ADD ./target/udemy-servicio-items-0.0.1-SNAPSHOT.jar servicio-items.jar
ENTRYPOINT ["java","-jar","servicio-items.jar"]