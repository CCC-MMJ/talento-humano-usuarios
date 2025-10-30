package co.edu.unimagdalena.devops.auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

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
        }
)
public class OpenApiConfig {
}
