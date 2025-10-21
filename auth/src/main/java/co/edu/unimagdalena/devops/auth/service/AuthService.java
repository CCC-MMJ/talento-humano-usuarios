package co.edu.unimagdalena.devops.auth.service;

import co.edu.unimagdalena.devops.auth.dto.LoginRequestDto;
import co.edu.unimagdalena.devops.auth.dto.LoginResponseDto;
import co.edu.unimagdalena.devops.auth.dto.RegisterRequestDto;
import co.edu.unimagdalena.devops.auth.dto.UserDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequest);
    UserDto register(RegisterRequestDto registerRequest);
}
