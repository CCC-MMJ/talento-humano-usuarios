package co.edu.unimagdalena.devops.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request payload to register a new user")
public class RegisterRequestDto {
    @Schema(description = "Full name of the user", example = "John Doe")
    private String name;
    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;
    @Schema(description = "Password for the new account", example = "P@ssw0rd!")
    private String password;
    @Schema(description = "Home address of the user", example = "123 Main St, Springfield")
    private String address;
}
