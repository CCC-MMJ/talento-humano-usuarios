package co.edu.unimagdalena.devops.auth;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class TestEnvironmentConfig {
    
    static {
        // Load .env file before Spring context starts
        Dotenv dotenv = Dotenv.configure()
            .directory("../") // Look for .env in project root
            .filename(".env")
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();
        
        // Set system properties from .env
        dotenv.entries().forEach(entry ->
            System.setProperty(entry.getKey(), entry.getValue())
        );
        dotenv.entries().forEach(dotenvEntry -> System.out.println(dotenvEntry.getKey() + "=" + dotenvEntry.getValue()));
    }
}
