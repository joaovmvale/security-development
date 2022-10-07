package com.securityDevelopment.user.service;

import com.securityDevelopment.user.model.UserModel;
import com.securityDevelopment.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void save(UserModel usuario) {
        userRepository.save(usuario);
    }

    public List<UserModel> listAll() {
        return userRepository.findAll();
    }

    public UserModel findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
