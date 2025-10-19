package co.edu.unimagdalena.devops.auth.service.imp;

import co.edu.unimagdalena.devops.auth.dto.LoginRequestDto;
import co.edu.unimagdalena.devops.auth.dto.LoginResponseDto;
import co.edu.unimagdalena.devops.auth.dto.RegisterRequestDto;
import co.edu.unimagdalena.devops.auth.dto.UserDto;
import co.edu.unimagdalena.devops.auth.entity.User;
import co.edu.unimagdalena.devops.auth.mapper.UserMapper;
import co.edu.unimagdalena.devops.auth.repository.UserRepository;
import co.edu.unimagdalena.devops.auth.security.JwtService;
import co.edu.unimagdalena.devops.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        // Autentica las credenciales
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Busca el usuario en la base de datos
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Genera el token JWT
        String jwtToken = jwtService.generateToken(user.getEmail(), user.getId());

        // Retorna la respuesta con token y datos del usuario
        return new LoginResponseDto(jwtToken, userMapper.toDto(user));
    }

    @Override
    public UserDto register(RegisterRequestDto registerRequest) {
        // Verifica que no exista el email
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }

        // Crea el nuevo usuario
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setAddress(registerRequest.getAddress());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Encripta la contraseña

        // Guarda en la base de datos
        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }
}
