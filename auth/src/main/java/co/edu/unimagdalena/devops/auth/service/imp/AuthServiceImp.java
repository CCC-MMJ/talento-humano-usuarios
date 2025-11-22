package co.edu.unimagdalena.devops.auth.service.imp;

import co.edu.unimagdalena.devops.auth.dto.LoginRequestDto;
import co.edu.unimagdalena.devops.auth.dto.LoginResponseDto;
import co.edu.unimagdalena.devops.auth.dto.RegisterRequestDto;
import co.edu.unimagdalena.devops.auth.dto.UserDto;
import co.edu.unimagdalena.devops.auth.entity.User;
import co.edu.unimagdalena.devops.auth.entity.UserType;
import co.edu.unimagdalena.devops.auth.mapper.UserMapper;
import co.edu.unimagdalena.devops.auth.repository.UserRepository;
import co.edu.unimagdalena.devops.auth.security.JwtIssuer;
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
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JwtIssuer jwtIssuer;

    @Override
    public LoginResponseDto login(LoginRequestDto req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Use userType for JWT token generation
        var roles = user.getUserType() != null
                ? java.util.List.of("ROLE_" + user.getUserType().name())
                : java.util.List.of("ROLE_APPLICANT"); // Default fallback

        String token = jwtIssuer.generate(user.getEmail(), user.getId(), roles);
        return new LoginResponseDto(token, userMapper.toDto(user));
    }

    @Override
    public UserDto register(RegisterRequestDto req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }

        // Validate and parse user type
        if (req.getType() == null || req.getType().trim().isEmpty()) {
            throw new RuntimeException("El tipo de usuario es requerido");
        }

        UserType userType;
        try {
            userType = UserType.valueOf(req.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tipo de usuario inválido. Valores permitidos: applicant, company, institution");
        }

        User u = new User();
        u.setName(req.getName());
        u.setEmail(req.getEmail());
        u.setAddress(req.getAddress());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setUserType(userType);

        User saved = userRepository.save(u);
        return userMapper.toDto(saved);
    }
}
