package jek.services;

import jek.models.User;
import jek.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

public class UserServiceTests {
    @Mock
    UserRepository userRepository = Mockito.mock(UserRepository.class);

    UserService userService = new UserService(userRepository);

    @Test
    public void getUserTest(){
        // Arrange
        Mockito.when(userRepository.getUserById(1)).thenReturn(new User("Test", "Password"));
        // Act
        User user = userService.getUserById(1);
        // Assert
        Assertions.assertEquals("Test", user.getUsername());
        Assertions.assertEquals("Password", user.getPassword());
    }

    @Test
    public void createUserTest(){
        // Arrange
        User user = new User("Test", "Password");
        Mockito.when(userRepository.getUserByUsername("Test")).thenReturn(user);
        // Act
        userService.createUserAndReturnId(user);
        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).createUser(user);
    }

    @Test
    public void doesUserExistTest(){
        // Arrange
        Mockito.when(userRepository.getAllUsernames()).thenReturn(List.of("Test"));
        // Act
        userService.doesUsernameExistInDB("Test");
        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).getAllUsernames();
    }
}
