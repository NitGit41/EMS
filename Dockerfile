#FROM openjdk:18.0-slim
#COPY target/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app.jar"]

#In above way we are creating jar file first and then we are adding it to image. but in below step we creating and
# executing jar file inside docker image, which is a preferable way

# Below is Multi stage docker file[consist of different stages to create a docker image]
#First stage is creating jar file. second stage is exexcution of jar file

#FROM maven:3.8.6-openjdk-18-slim AS build
#WORKDIR /home/app
#COPY . /home/app
#RUN mvn -f /home/app/pom.xml clean package
#
#FROM openjdk:18.0-slim
#EXPOSE 8080
#COPY --from=build /home/app/target/*.jar app.jar
#ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]

#FROM maven:3.8.6-openjdk-18-slim AS build
#WORKDIR /home/app
#
#COPY ./pom.xml /home/app/pom.xml
#COPY ./src/main/java/employee/EmployeeManagementSystemApplication.java/ /home/app/src/main/java/employee/EmployeeManagementSystemApplication.java
#
#RUN mvn -f /home/app/pom.xml clean install
#
#FROM openjdk:18.0-slim
#EXPOSE 4005
#COPY --from=build /home/app/target/*.jar app.jar 
#ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]

# Stage 1: Build the application
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app

# Copy the pom.xml and the source code
COPY ./pom.xml ./pom.xml
COPY ./src ./src

# Build the application and install it in the local repository
RUN mvn clean install -DskipTests

# Stage 2: Create the image to run the application
FROM openjdk:18.0-slim
WORKDIR /app

# Expose the application port
EXPOSE 4005

# Copy the jar file from the build stage
COPY --from=build /home/app/target/*.jar app.jar 

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
