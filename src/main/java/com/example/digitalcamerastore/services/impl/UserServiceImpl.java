/*
 * @(#) $(NAME).java    1.0     11/26/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.services.impl;

import com.example.digitalcamerastore.entities.User;
import com.example.digitalcamerastore.repositories.UserRepository;
import com.example.digitalcamerastore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 26-November-2024 3:24 PM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean delete(int id) {
        if (this.findById(id) != null) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User update(int id, User user) {
        if (this.findById(id) != null) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }
}
