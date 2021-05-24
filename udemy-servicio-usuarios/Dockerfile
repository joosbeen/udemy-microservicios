FROM openjdk:11
VOLUME /tmp
ADD ./target/udemy-servicio-usuarios-0.0.1-SNAPSHOT.jar servicio-usuarios.jar
ENTRYPOINT ["java","-jar","servicio-usuarios.jar"]