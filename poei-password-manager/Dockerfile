FROM openjdk:8-slim

COPY target/poei-password-manager.jar .

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "poei-password-manager.jar"]