/*
 * @(#) $(NAME).java    1.0     11/26/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.services;


import com.example.digitalcamerastore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 26-November-2024 3:24 PM
 */
public interface UserService  {
    public User save(User user);

    public List<User> findAll();

    public User findById(int id);

    public User findByEmail(String email);

    public boolean delete(int id);

    public User update(int id, User user);
}

    