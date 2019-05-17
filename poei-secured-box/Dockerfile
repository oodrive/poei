FROM openjdk:8-slim

COPY target/poei-secured-box.jar .

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "poei-secured-box.jar"]