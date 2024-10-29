package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    User createUser(User user);

    List<UserDto> getAllUsers();

    List<SimpleUserDto> getAllSimpleUsers(); // Nowa metoda

    UserDto getUserById(Long id);

    List<UserEmailDto> getUsersByEmail(String email); // Zmieniona metoda

    List<UserDto> getUsersOlderThan(LocalDate date);

    User updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}
