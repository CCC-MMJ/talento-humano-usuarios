package co.edu.unimagdalena.devops.auth.controller;

import co.edu.unimagdalena.devops.auth.dto.LoginRequestDto;
import co.edu.unimagdalena.devops.auth.dto.LoginResponseDto;
import co.edu.unimagdalena.devops.auth.dto.RegisterRequestDto;
import co.edu.unimagdalena.devops.auth.dto.UserDto;
import co.edu.unimagdalena.devops.auth.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private UUID userId;
    private LoginRequestDto loginRequest;
    private RegisterRequestDto registerRequest;
    private UserDto userDto;
    private LoginResponseDto loginResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();

        loginRequest = new LoginRequestDto();
        loginRequest.setEmail("john@example.com");
        loginRequest.setPassword("password123");

        registerRequest = new RegisterRequestDto();
        registerRequest.setName("John Doe");
        registerRequest.setEmail("john@example.com");
        registerRequest.setPassword("password123");
        registerRequest.setAddress("123 Main St");
        registerRequest.setType("APPLICANT");

        userDto = new UserDto();
        userDto.setId(userId);
        userDto.setName("John Doe");
        userDto.setEmail("john@example.com");
        userDto.setAddress("123 Main St");

        loginResponse = new LoginResponseDto();
        loginResponse.setToken("jwt-token-123");
        loginResponse.setUser(userDto);
    }

    @Test
    void testLogin_Success() {
        // Arrange
        when(authService.login(loginRequest)).thenReturn(loginResponse);

        // Act
        ResponseEntity<LoginResponseDto> response = authController.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("jwt-token-123", response.getBody().getToken());
        assertNotNull(response.getBody().getUser());
        assertEquals("john@example.com", response.getBody().getUser().getEmail());
        verify(authService).login(loginRequest);
    }

    @Test
    void testLogin_InvalidCredentials() {
        // Arrange
        when(authService.login(loginRequest)).thenThrow(new RuntimeException("Usuario no encontrado"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> authController.login(loginRequest));
        verify(authService).login(loginRequest);
    }

    @Test
    void testRegister_Success() {
        // Arrange
        when(authService.register(registerRequest)).thenReturn(userDto);

        // Act
        ResponseEntity<UserDto> response = authController.register(registerRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("john@example.com", response.getBody().getEmail());
        assertEquals("John Doe", response.getBody().getName());
        verify(authService).register(registerRequest);
    }

    @Test
    void testRegister_EmailAlreadyExists() {
        // Arrange
        when(authService.register(registerRequest))
                .thenThrow(new RuntimeException("Email ya registrado"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> authController.register(registerRequest));
        verify(authService).register(registerRequest);
    }

    @Test
    void testLogin_WithDifferentUserType() {
        // Arrange
        registerRequest.setType("COMPANY");
        UserDto companyUserDto = new UserDto();
        companyUserDto.setId(userId);
        companyUserDto.setName("Company Name");
        companyUserDto.setEmail("company@example.com");

        LoginResponseDto companyLoginResponse = new LoginResponseDto();
        companyLoginResponse.setToken("jwt-token-company");
        companyLoginResponse.setUser(companyUserDto);

        when(authService.login(loginRequest)).thenReturn(companyLoginResponse);

        // Act
        ResponseEntity<LoginResponseDto> response = authController.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("jwt-token-company", response.getBody().getToken());
    }

    @Test
    void testRegister_AsInstitution() {
        // Arrange
        registerRequest.setType("INSTITUTION");
        registerRequest.setName("University of Example");
        registerRequest.setEmail("institution@example.com");

        UserDto institutionDto = new UserDto();
        institutionDto.setId(UUID.randomUUID());
        institutionDto.setName("University of Example");
        institutionDto.setEmail("institution@example.com");

        when(authService.register(registerRequest)).thenReturn(institutionDto);

        // Act
        ResponseEntity<UserDto> response = authController.register(registerRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("institution@example.com", response.getBody().getEmail());
        verify(authService).register(registerRequest);
    }

    @Test
    void testLogin_ReturnsValidToken() {
        // Arrange
        when(authService.login(loginRequest)).thenReturn(loginResponse);

        // Act
        ResponseEntity<LoginResponseDto> response = authController.login(loginRequest);

        // Assert
        assertNotNull(response.getBody().getToken());
        assertFalse(response.getBody().getToken().isEmpty());
        assertTrue(response.getBody().getToken().contains("-"));
    }

    @Test
    void testRegister_ValidatesUserData() {
        // Arrange
        when(authService.register(registerRequest)).thenReturn(userDto);

        // Act
        ResponseEntity<UserDto> response = authController.register(registerRequest);

        // Assert
        UserDto registeredUser = response.getBody();
        assertEquals("john@example.com", registeredUser.getEmail());
        assertEquals("John Doe", registeredUser.getName());
        assertEquals("123 Main St", registeredUser.getAddress());
    }
}
