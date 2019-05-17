FROM openjdk:8-slim

COPY target/poei-file-storage.jar .

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "poei-file-storage.jar"]