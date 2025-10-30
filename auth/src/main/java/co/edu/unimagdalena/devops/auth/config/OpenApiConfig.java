package co.edu.unimagdalena.devops.auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API AUTENTICACION",
                description = "Documentación de la API de autenticación y perfiles",
                termsOfService = "www.talentolocal.com/terminos_y_servicios",
                version = "1.0.0",
                contact = @Contact(
                        name = "CCC-MMJ",
                        url = "www.talentolocal.com/contact",
                        email = "cacastrillon@unimagdalena.edu.co"
                ),
                license = @License(
                        name = "Standard Software Use License for TalentoLocal",
                        url = "www.talentolocal.com/contact"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD SERVER",
                        url = "jdbc:sqlserver://qa-devops-azure-sql-server.database.windows.net:1433"
                )
        },
        security = @SecurityRequirement(
                name = "Token de Seguridad"
        )
)
@SecurityScheme(
        name = "Token de Seguridad",
        description = "Token de Acceso para mi API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {
}
