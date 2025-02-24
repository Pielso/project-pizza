package jek.services;
import jek.models.User;
import jek.repositories.UserRepository;
import java.sql.SQLException;


import static jek.controllers.LoginController.activeUser;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void SaveNewUser(User newUser) {
        userRepository.SaveNewUser(newUser);
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

    public String getPasswordByUsername(String name) {
        return userRepository.getUserByUsername(name).getPassword();
    }

    public boolean doesUsernameExistInDB(String name) throws SQLException {
        return userRepository.returnListOfUsernames().contains(name);
    }
}
