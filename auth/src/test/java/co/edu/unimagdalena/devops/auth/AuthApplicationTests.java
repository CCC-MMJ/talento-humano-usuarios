package co.edu.unimagdalena.devops.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@Import(TestEnvironmentConfig.class)
@TestPropertySource(properties = {
    "spring.datasource.url=${DATABASE_URL:jdbc:h2:mem:testdb}",
    "spring.datasource.username=${DATABASE_USERNAME:sa}",
    "spring.datasource.password=${DATABASE_PASSWORD:}",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
@SpringBootTest
class AuthApplicationTests {

    @Test
    void contextLoads() {
    }

}
