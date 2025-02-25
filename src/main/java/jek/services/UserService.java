package jek.services;

import jek.models.User;
import jek.repositories.UserRepository;
import java.sql.SQLException;
import static jek.controllers.LoginController.*;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public int getUserIdByUsername(String name){
        return userRepository.getUserByUsername(name).getUserId();
    }

    public void setActiveUserByUsername(String name){
        User user = userRepository.getUserByUsername(name);
        activeUser.setUserId(user.getUserId());
        activeUser.setUsername(user.getUsername());
        activeUser.setPassword(user.getPassword());
    }

    public int createUserAndReturnId(String username, String password) throws SQLException {
        userRepository.createUserByString(username, password);
        return userRepository.getUserByUsername(username).getUserId();
    }

    public String getPasswordByUsername(String name) {
        return userRepository.getUserByUsername(name).getPassword();
    }

    public boolean doesUsernameExistInDB(String name) throws SQLException {
        return userRepository.returnListOfUsernames().contains(name);
    }
}
