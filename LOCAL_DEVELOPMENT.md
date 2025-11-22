# Configuración de Entorno Local - Guía Rápida

## Opción 1: SQL Server con Docker (Recomendado)

### Prerrequisitos
1. Docker Desktop instalado y ejecutándose
2. Conexión a internet para descargar la imagen de SQL Server

### Pasos

**1. Iniciar SQL Server:**
```bash
docker-compose --profile local up -d auth-sqlserver
```

**2. Verificar que el contenedor está corriendo:**
```bash
docker ps
```

Deberías ver `auth_sqlserver_db` en la lista.

**3. Ejecutar la aplicación con perfil local:**
```bash
cd auth
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

---

## Opción 2: SQL Server Local (Sin Docker)

Si Docker no está disponible o prefieres instalar SQL Server directamente:

### Prerrequisitos
- SQL Server 2019 o superior instalado localmente
- SQL Server Management Studio (SSMS) o Azure Data Studio

### Pasos

**1. Crear la base de datos:**
```sql
CREATE DATABASE authdb;
```

**2. Modificar `application-local.properties`:**

Si tu SQL Server usa autenticación de Windows, cambia:
```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=authdb;integratedSecurity=true;encrypt=true;trustServerCertificate=true
```

Si usas autenticación SQL Server, mantén:
```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=authdb;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=TuContraseña
```

**3. Ejecutar la aplicación:**
```bash
cd auth
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

---

## Opción 3: Usar PostgreSQL (Ya configurado en docker-compose)

Si prefieres usar PostgreSQL en lugar de SQL Server:

### Pasos

**1. Iniciar PostgreSQL:**
```bash
docker-compose --profile auth up -d auth-postgres
```

**2. Crear/modificar `application-local.properties`:**
```properties
spring.application.name=auth

springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs

logging.level.org.springframework.web=DEBUG

# JWT Configuration
jwt.secret=Qaxrv+GFaLRI8Olkr6g32a9jkrWHxMkk6e1Cu1yU97M=
jwt.expiration=86400000

# Local PostgreSQL Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/authdb
spring.datasource.username=user
spring.datasource.password=pwd2
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate Configuration
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

**3. Verificar que tienes el driver de PostgreSQL en `pom.xml`:**
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

**4. Ejecutar la aplicación:**
```bash
cd auth
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

---

## Solución de Problemas

### Docker no puede descargar la imagen de SQL Server

**Error:** `unable to get image` o `EOF`

**Soluciones:**
1. Verificar que Docker Desktop está corriendo
2. Verificar conexión a internet
3. Intentar descargar manualmente:
   ```bash
   docker pull mcr.microsoft.com/mssql/server:2022-latest
   ```
4. Si el problema persiste, usar Opción 2 o 3

### Puerto 1433 ya está en uso

**Error:** `port is already allocated`

**Solución:**
- Si tienes SQL Server instalado localmente, usa la Opción 2
- O cambia el puerto en `docker-compose.yml`:
  ```yaml
  ports:
    - "1434:1433"  # Cambia el puerto externo
  ```
  Y actualiza `application-local.properties`:
  ```properties
  spring.datasource.url=jdbc:sqlserver://localhost:1434;...
  ```

### La aplicación no puede conectarse a la base de datos

**Verificar:**
1. Que el contenedor/servicio de base de datos está corriendo
2. Que el puerto es correcto
3. Que las credenciales son correctas
4. Revisar los logs de la aplicación para más detalles

---

## Verificación

Una vez que la aplicación esté corriendo:

1. **Acceder a Swagger UI:**
   http://localhost:8080/swagger-ui.html

2. **Probar el endpoint de registro:**
   ```json
   POST /auth/register
   {
     "name": "Test User",
     "email": "test@example.com",
     "password": "Test123!",
     "address": "123 Test St"
   }
   ```

3. **Verificar en la base de datos:**
   - Deberías ver la tabla `app_user` creada automáticamente
   - Deberías ver el usuario registrado

---

## Cambiar entre Entornos

### Desarrollo Local
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### Producción (Azure)
```bash
mvn spring-boot:run
# O simplemente ejecutar sin especificar profile
```

---

## Notas Importantes

- ✅ Los archivos de configuración están listos
- ✅ `application-local.properties` - para desarrollo local
- ✅ `application.properties` + `env` - para producción Azure
- ✅ `docker-compose.yml` - incluye SQL Server y PostgreSQL
- ⚠️ Nunca commitear credenciales de producción en el repositorio
- ⚠️ El archivo `env` debe estar en `.gitignore`
