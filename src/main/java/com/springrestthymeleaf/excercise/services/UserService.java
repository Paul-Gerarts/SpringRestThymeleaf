package com.springrestthymeleaf.excercise.services;

import com.springrestthymeleaf.excercise.entities.SecurityRoles;
import com.springrestthymeleaf.excercise.entities.User;
import com.springrestthymeleaf.excercise.exceptions.UserNotFoundException;
import com.springrestthymeleaf.excercise.factories.UserFactory;
import com.springrestthymeleaf.excercise.repositories.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
@NoArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserFactory userFactory;

    @Autowired
    public UserService(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public User findByUserName(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        return user.orElseThrow(() -> new UserNotFoundException("This user cannot be found!"));
    }

    public void initialize() {
        if (userRepository.count() == 0) {
            userRepository.saveAll(List.of(
                    userFactory.createUser(
                            SecurityRoles.READER,
                            "user",
                            "password"),
                    userFactory.createUser(
                            SecurityRoles.ADMIN,
                            "admin",
                            "admin"),
                    userFactory.createUser(
                            SecurityRoles.SUPER_ADMIN,
                            "super-admin",
                            "super-admin")
            ));
        }
    }
}
