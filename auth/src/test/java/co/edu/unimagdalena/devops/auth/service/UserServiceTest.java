package co.edu.unimagdalena.devops.auth.service;

import co.edu.unimagdalena.devops.auth.dto.UserDto;
import co.edu.unimagdalena.devops.auth.entity.User;
import co.edu.unimagdalena.devops.auth.entity.UserType;
import co.edu.unimagdalena.devops.auth.mapper.UserMapper;
import co.edu.unimagdalena.devops.auth.repository.UserRepository;
import co.edu.unimagdalena.devops.auth.service.imp.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImp userService;

    private UUID userId;
    private User testUser;
    private UserDto testUserDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();

        testUser = new User();
        testUser.setId(userId);
        testUser.setName("John Doe");
        testUser.setEmail("john@example.com");
        testUser.setPassword("password123");
        testUser.setAddress("123 Main St");
        testUser.setUserType(UserType.APPLICANT);

        testUserDto = new UserDto();
        testUserDto.setId(userId);
        testUserDto.setName("John Doe");
        testUserDto.setEmail("john@example.com");
        testUserDto.setAddress("123 Main St");
    }

    @Test
    void testGetUserByEmail_Success() {
        // Arrange
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(testUser));
        when(userMapper.toDto(testUser)).thenReturn(testUserDto);

        // Act
        Optional<UserDto> result = userService.getUserByEmail("john@example.com");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("john@example.com", result.get().getEmail());
        assertEquals("John Doe", result.get().getName());
        verify(userRepository).findByEmail("john@example.com");
        verify(userMapper).toDto(testUser);
    }

    @Test
    void testGetUserByEmail_NotFound() {
        // Arrange
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Act
        Optional<UserDto> result = userService.getUserByEmail("nonexistent@example.com");

        // Assert
        assertFalse(result.isPresent());
        verify(userRepository).findByEmail("nonexistent@example.com");
    }

    @Test
    void testGetUserByName_Success() {
        // Arrange
        when(userRepository.findByName("John Doe")).thenReturn(Optional.of(testUser));
        when(userMapper.toDto(testUser)).thenReturn(testUserDto);

        // Act
        Optional<UserDto> result = userService.getUserByName("John Doe");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(userRepository).findByName("John Doe");
    }

    @Test
    void testGetUserById_Success() {
        // Arrange
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(userMapper.toDto(testUser)).thenReturn(testUserDto);

        // Act
        Optional<UserDto> result = userService.getUserById(userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
        verify(userRepository).findById(userId);
    }

    @Test
    void testGetUserById_NotFound() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act
        Optional<UserDto> result = userService.getUserById(nonExistentId);

        // Assert
        assertFalse(result.isPresent());
        verify(userRepository).findById(nonExistentId);
    }

    @Test
    void testCreateUser_Success() {
        // Arrange
        when(userMapper.toEntity(testUserDto)).thenReturn(testUser);
        when(userRepository.save(testUser)).thenReturn(testUser);
        when(userMapper.toDto(testUser)).thenReturn(testUserDto);

        // Act
        UserDto result = userService.createUser(testUserDto);

        // Assert
        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
        verify(userMapper).toEntity(testUserDto);
        verify(userRepository).save(testUser);
        verify(userMapper).toDto(testUser);
    }

    @Test
    void testUpdateUser_Success() {
        // Arrange
        testUserDto.setName("Jane Doe");
        testUser.setName("Jane Doe");

        when(userMapper.toEntity(testUserDto)).thenReturn(testUser);
        when(userRepository.save(testUser)).thenReturn(testUser);
        when(userMapper.toDto(testUser)).thenReturn(testUserDto);

        // Act
        UserDto result = userService.updateUser(testUserDto);

        // Assert
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        verify(userRepository).save(testUser);
    }

    @Test
    void testDeleteUserById() {
        // Act
        userService.deleteUserById(userId);

        // Assert
        verify(userRepository).deleteById(userId);
    }

    @Test
    void testGetAllUsers() {
        // Act
        userService.getAllUsers();

        // Assert
        verify(userRepository).findAll();
    }
}
