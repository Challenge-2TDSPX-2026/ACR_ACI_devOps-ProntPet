# ==========================================
# ESTÁGIO 1 - BUILD
# ==========================================

FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests


# ==========================================
# ESTÁGIO 2 - EXECUÇÃO
# ==========================================

FROM eclipse-temurin:17-jre

WORKDIR /app

# Cria usuário sem privilégios administrativos
RUN useradd --system --create-home appuser

COPY --from=builder /app/target/*.jar app.jar

# Dá permissão do JAR para o usuário da aplicação
RUN chown appuser:appuser app.jar

# Executa a aplicação como usuário não-root
USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
