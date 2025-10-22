# Utilisation d'une image avec Maven et JDK
FROM maven:3.8.8-eclipse-temurin-11 AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers nécessaires
COPY pom.xml .
COPY src ./src

# Construire l'application
RUN mvn clean package -DskipTests

# Créer un répertoire pour l'application
RUN mkdir -p /app/run

# Copier le JAR construit
RUN cp /app/target/*.jar /app/run/app.jar

# Étape d'exécution
FROM maven:3.8.8-eclipse-temurin-11

# Installer les dépendances système nécessaires
RUN apt-get update && apt-get install -y \
    && rm -rf /var/lib/apt/lists/*

# Définir le répertoire de travail
WORKDIR /app/run

# Copier le JAR construit depuis l'étape de build
COPY --from=build /app/run/app.jar .

# Exposer le port 8080
EXPOSE 8080

# Commande pour exécuter l'application avec Maven
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.profiles=prod"]
