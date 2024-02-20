# Utilizamos la imagen de OpenJDK 16 como base
FROM openjdk:17-jdk-alpine

# Establecemos el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR de la aplicación Spring Boot al contenedor
COPY target/viaticos-0.0.1-SNAPSHOT.jar viaticos-app.jar

# Expone el puerto 8090 para la aplicación Spring Boot
EXPOSE 8090

# Comando para ejecutar la aplicación Spring Boot al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "viaticos-app.jar"]