/*
 * @(#) $(NAME).java    1.0     11/27/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.camerast.dtos;

import java.util.List;

import com.example.camerast.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 27-November-2024 2:59 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoleDTO {
    private int id;
    private String name;
    @JsonIgnore
    private List<User> users;
}
