package co.edu.unimagdalena.devops.auth.service;

import co.edu.unimagdalena.devops.auth.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<UserDto> getUserByName(String name);
    Optional<UserDto> getUserByEmail(String email);

    Optional<UserDto> getUserById(UUID id);
    List<UserDto> getAllUsers();
    UserDto createUser(UserDto userDto);
    void deleteUserById(UUID id);
    UserDto updateUser(UserDto userDto);
}
