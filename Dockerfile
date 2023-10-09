FROM amazoncorretto:17

WORKDIR /project

COPY . .

# Ensure that Maven Wrapper (mvnw) is executable
RUN sed -i -e 's/\r$//' mvnw
RUN chmod +x mvnw

RUN ./mvnw clean package

RUN mv $(pwd)/target/*.jar $(pwd)/target/app.jar

ENTRYPOINT ["java", "-jar", "target/app.jar"]