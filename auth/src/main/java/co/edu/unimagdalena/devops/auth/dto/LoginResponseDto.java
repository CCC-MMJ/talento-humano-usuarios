package co.edu.unimagdalena.devops.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String token;
    private String tokenType = "Bearer";
    private UserDto user;

    public LoginResponseDto(String token, UserDto user) {
        this.token = token;
        this.user = user;
    }
}
