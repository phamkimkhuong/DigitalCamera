/*
 * @(#) $(NAME).java    1.0     11/27/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.camerast.services;


import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.camerast.dtos.UserDTO;

import java.util.List;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 27-November-2024 2:28 PM
 */
public interface UserService extends UserDetailsService {
    public UserDTO findById(int id);

    public List<UserDTO> findAll();

    public Page<UserDTO> findAllWithPaging(int pageNo, int pageSize, String sortBy, String sortDirection);

    public UserDTO save(UserDTO userDTO);

    public UserDTO update(int id, UserDTO userDTO);

    public boolean delete(int id);

    public List<UserDTO> search(String keyword);
    
    public UserDTO findByEmail(String email);

    public boolean findByEmailAndPassword(String email, String password);
}

    