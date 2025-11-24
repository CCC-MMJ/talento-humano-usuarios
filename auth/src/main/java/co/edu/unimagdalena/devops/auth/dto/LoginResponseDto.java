package co.edu.unimagdalena.devops.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response returned after successful authentication")
public class LoginResponseDto {
    @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
    @Schema(description = "Type of the token", example = "Bearer")
    private String tokenType = "Bearer";
    @Schema(description = "Authenticated user info")
    private UserDto user;

    public LoginResponseDto(String token, UserDto user) {
        this.token = token;
        this.user = user;
    }
}
