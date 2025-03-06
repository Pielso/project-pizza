package jek.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserTests {

    @Test
    public void testConstructorAndGetters(){

        // Arrange
        var user = new User("Test", "Password");

        // Act
        var username = user.getUsername();
        var password = user.getPassword();

        // Assert
        Assertions.assertEquals("Test", username);
        Assertions.assertEquals("Password", password);

    }
}
