package co.edu.unimagdalena.devops.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // Lista orígenes exactos que SÍ harán llamadas al backend
        config.setAllowedOrigins(List.of(
                "https://profiles-auth-fadbasetc6fja8hs.westus3-01.azurewebsites.net",
                "http://localhost:8080",
                "http://localhost:3000"
        ));
        // Alternativa con patrones válidos (puerto comodín):
        // config.setAllowedOriginPatterns(List.of(
        //     "https://profiles-auth-fadbasetc6fja8hs.westus3-01.azurewebsites.net",
        //     "http://localhost:[*]"
        // ));

        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization","Content-Type","Accept","Origin"));
        config.setExposedHeaders(List.of("Authorization","Content-Range"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
