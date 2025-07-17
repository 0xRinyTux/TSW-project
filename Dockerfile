# ┌───────────────────────────────────────────────────────────────────────────┐
# │ 1) Build stage: compile your Maven project into a WAR file              │
# └───────────────────────────────────────────────────────────────────────────┘
FROM maven:3.8-jdk-8 AS builder

WORKDIR /app

# Copy only pom first (for layer caching), then sources
COPY pom.xml .
COPY src ./src

# Build the WAR (skip tests for speed; remove -DskipTests if you prefer)
RUN mvn package -DskipTests

# ┌───────────────────────────────────────────────────────────────────────────┐
# │ 2) Runtime stage: deploy WAR on Tomcat                                   │
# └───────────────────────────────────────────────────────────────────────────┘
FROM tomcat:10.0-jdk8

# remove default Tomcat apps
RUN rm -rf /usr/local/tomcat/webapps/*

# copy your freshly built WAR as ROOT
COPY --from=builder /app/target/BuyandPlay-1.0-SNAPSHOT.war \
     /usr/local/tomcat/webapps/ROOT.war

# expose Tomcat port
EXPOSE 8080

# pass DB_* env into system properties (adjust if your app reads differently)
ENV JAVA_OPTS="-Ddb.host=${DB_HOST:-db} \
               -Ddb.port=${DB_PORT:-3306} \
               -Ddb.name=${DB_NAME:-mydb} \
               -Ddb.user=${DB_USER:-appuser} \
               -Ddb.pass=${DB_PASS:-apppass}"

CMD ["sh", "-c", "catalina.sh run"]
