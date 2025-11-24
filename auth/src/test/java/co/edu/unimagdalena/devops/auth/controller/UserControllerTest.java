package co.edu.unimagdalena.devops.auth.controller;

import co.edu.unimagdalena.devops.auth.dto.UserDto;
import co.edu.unimagdalena.devops.auth.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UUID userId;
    private UserDto testUserDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();

        testUserDto = new UserDto();
        testUserDto.setId(userId);
        testUserDto.setName("John Doe");
        testUserDto.setEmail("john@example.com");
        testUserDto.setAddress("123 Main St");
    }

    @Test
    void testGetAllUsers_Success() {
        // Arrange
        List<UserDto> userList = new ArrayList<>();
        userList.add(testUserDto);

        when(userService.getAllUsers()).thenReturn(userList);

        // Act
        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(userService).getAllUsers();
    }

    @Test
    void testGetUserById_Success() {
        // Arrange
        when(userService.getUserById(userId)).thenReturn(Optional.of(testUserDto));

        // Act
        ResponseEntity<UserDto> response = userController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("john@example.com", response.getBody().getEmail());
        verify(userService).getUserById(userId);
    }

    @Test
    void testGetUserById_NotFound() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        when(userService.getUserById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert - cuando no encuentra el usuario retorna una respuesta vacía
        ResponseEntity<UserDto> response = userController.getUserById(nonExistentId);

        // Assert que se llama al servicio
        verify(userService).getUserById(nonExistentId);
    }

    @Test
    void testCreateUser_Success() {
        // Arrange
        when(userService.createUser(testUserDto)).thenReturn(testUserDto);

        // Act
        ResponseEntity<UserDto> response = userController.createUser(testUserDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("john@example.com", response.getBody().getEmail());
        verify(userService).createUser(testUserDto);
    }

    @Test
    void testUpdateUser_Success() {
        // Arrange
        testUserDto.setName("Jane Doe");
        when(userService.updateUser(testUserDto)).thenReturn(testUserDto);

        // Act
        ResponseEntity<UserDto> response = userController.updateUser(testUserDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().getName());
        verify(userService).updateUser(testUserDto);
    }

    @Test
    void testDeleteUser_Success() {
        // Act
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).deleteUserById(userId);
    }

    @Test
    void testGetAllUsers_Empty() {
        // Arrange
        when(userService.getAllUsers()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());
    }
}
