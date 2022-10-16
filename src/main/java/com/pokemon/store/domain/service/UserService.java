package com.pokemon.store.domain.service;

import com.pokemon.store.domain.model.User;
import com.pokemon.store.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByName(String userName) {
        Optional<User> userOptional =  userRepository.findByUserName(userName);
        if(userOptional.isEmpty()){
            User user = new User();
            user.setUserName(userName);
            userRepository.save(user);
            return user;
        }
        return userOptional.get();
    }
}
