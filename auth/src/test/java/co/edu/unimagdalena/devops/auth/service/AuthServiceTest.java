package co.edu.unimagdalena.devops.auth.service;

import co.edu.unimagdalena.devops.auth.dto.LoginRequestDto;
import co.edu.unimagdalena.devops.auth.dto.LoginResponseDto;
import co.edu.unimagdalena.devops.auth.dto.RegisterRequestDto;
import co.edu.unimagdalena.devops.auth.dto.UserDto;
import co.edu.unimagdalena.devops.auth.entity.User;
import co.edu.unimagdalena.devops.auth.entity.UserType;
import co.edu.unimagdalena.devops.auth.mapper.UserMapper;
import co.edu.unimagdalena.devops.auth.repository.UserRepository;
import co.edu.unimagdalena.devops.auth.security.JwtIssuer;
import co.edu.unimagdalena.devops.auth.service.imp.AuthServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserMapper userMapper;

    @Mock
    private JwtIssuer jwtIssuer;

    @InjectMocks
    private AuthServiceImp authService;

    private UUID userId;
    private User testUser;
    private UserDto testUserDto;
    private LoginRequestDto loginRequest;
    private RegisterRequestDto registerRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();

        testUser = new User();
        testUser.setId(userId);
        testUser.setName("John Doe");
        testUser.setEmail("john@example.com");
        testUser.setPassword("hashedPassword123");
        testUser.setAddress("123 Main St");
        testUser.setUserType(UserType.APPLICANT);

        testUserDto = new UserDto();
        testUserDto.setId(userId);
        testUserDto.setName("John Doe");
        testUserDto.setEmail("john@example.com");
        testUserDto.setAddress("123 Main St");

        loginRequest = new LoginRequestDto();
        loginRequest.setEmail("john@example.com");
        loginRequest.setPassword("password123");

        registerRequest = new RegisterRequestDto();
        registerRequest.setName("John Doe");
        registerRequest.setEmail("john@example.com");
        registerRequest.setPassword("password123");
        registerRequest.setAddress("123 Main St");
        registerRequest.setType("APPLICANT");
    }

    @Test
    void testLogin_Success() {
        // Arrange
        String token = "jwt-token-123";
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(testUser));
        when(jwtIssuer.generate(anyString(), any(UUID.class), anyList())).thenReturn(token);
        when(userMapper.toDto(testUser)).thenReturn(testUserDto);

        // Act
        LoginResponseDto result = authService.login(loginRequest);

        // Assert
        assertNotNull(result);
        assertEquals(token, result.getToken());
        assertNotNull(result.getUser());
        verify(authenticationManager).authenticate(any());
        verify(userRepository).findByEmail("john@example.com");
        verify(jwtIssuer).generate(anyString(), any(UUID.class), anyList());
    }

    @Test
    void testLogin_UserNotFound() {
        // Arrange
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());
        loginRequest.setEmail("nonexistent@example.com");

        // Act & Assert
        assertThrows(RuntimeException.class, () -> authService.login(loginRequest));
        verify(userRepository).findByEmail("nonexistent@example.com");
    }

    @Test
    void testRegister_Success() {
        // Arrange
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword123");
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        when(userMapper.toDto(testUser)).thenReturn(testUserDto);

        // Act
        UserDto result = authService.register(registerRequest);

        // Assert
        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
        verify(userRepository).findByEmail("john@example.com");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testRegister_EmailAlreadyExists() {
        // Arrange
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(testUser));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> authService.register(registerRequest));
        verify(userRepository).findByEmail("john@example.com");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testLogin_WithDifferentUserType() {
        // Arrange
        testUser.setUserType(UserType.COMPANY);
        String token = "jwt-token-company";

        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(testUser));
        when(jwtIssuer.generate(anyString(), any(UUID.class), anyList())).thenReturn(token);
        when(userMapper.toDto(testUser)).thenReturn(testUserDto);

        // Act
        LoginResponseDto result = authService.login(loginRequest);

        // Assert
        assertNotNull(result);
        assertEquals(token, result.getToken());
        verify(jwtIssuer).generate("john@example.com", userId, java.util.List.of("ROLE_COMPANY"));
    }

    @Test
    void testRegister_DefaultUserType() {
        // Arrange - el tipo de usuario es requerido, por lo que no usamos un registro sin tipo
        RegisterRequestDto validRequest = new RegisterRequestDto();
        validRequest.setName("Jane Doe");
        validRequest.setEmail("jane@example.com");
        validRequest.setPassword("password123");
        validRequest.setAddress("456 Oak St");
        validRequest.setType("APPLICANT"); // Tipo obligatorio

        User userWithType = new User();
        userWithType.setId(UUID.randomUUID());
        userWithType.setEmail("jane@example.com");
        userWithType.setUserType(UserType.APPLICANT);

        UserDto dtoWithType = new UserDto();
        dtoWithType.setEmail("jane@example.com");

        when(userRepository.findByEmail("jane@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(userWithType);
        when(userMapper.toDto(userWithType)).thenReturn(dtoWithType);

        // Act
        UserDto result = authService.register(validRequest);

        // Assert
        assertNotNull(result);
        assertEquals("jane@example.com", result.getEmail());
    }
}
