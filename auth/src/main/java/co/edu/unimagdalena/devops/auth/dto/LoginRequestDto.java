package co.edu.unimagdalena.devops.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Login request payload containing user credentials")
public class LoginRequestDto {
    @Schema(description = "User email address", example = "john.doe@example.com")
    private String email;
    @Schema(description = "User password", example = "P@ssw0rd!")
    private String password;
}
