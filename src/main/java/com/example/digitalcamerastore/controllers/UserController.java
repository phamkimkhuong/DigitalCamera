/*
 * @(#) $(NAME).java    1.0     11/27/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.controllers;

import com.example.digitalcamerastore.dtos.UserDTO;
import com.example.digitalcamerastore.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 27-November-2024 2:37 PM
 */
@RepositoryRestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable int id) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("status", HttpStatus.OK.value());
        response.put("data", userService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/user")
    public ResponseEntity<Map<String, Object>> saveUser(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult bindingResult) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            Map<String, Object> errors = new LinkedHashMap<String, Object>();

            bindingResult.getFieldErrors().stream().forEach(result -> {
                errors.put(result.getField(), result.getDefaultMessage());
            });

            System.out.println(bindingResult);
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else {
            response.put("status", HttpStatus.OK.value());
            response.put("data", userService.save(userDTO));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable int id, @Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {

        Map<String, Object> response = new LinkedHashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            Map<String, Object> errors = new LinkedHashMap<String, Object>();
            bindingResult.getFieldErrors().stream().forEach(result -> {
                errors.put(result.getField(), result.getDefaultMessage());
            });

            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else {
            response.put("status", HttpStatus.OK.value());
            response.put("data", userService.update(id, userDTO));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable int id) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("status", HttpStatus.OK.value());
        response.put("data", userService.delete(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getUsers(@RequestParam(required = false) String keyword) {

        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("status", HttpStatus.OK.value());

        if (keyword == null || keyword.isEmpty()) {
            response.put("data", userService.findAll());
        }
        else {
            response.put("data", userService.search(keyword));
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/users/page")
    public ResponseEntity<Map<String, Object>> getList(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "20", required = false) int size,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "ASC", required = false) String sortDirection) {
        Page<UserDTO> user = userService.findAllWithPaging(page, size, sortBy, sortDirection);
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("data", user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/users/email")
	public ResponseEntity<Map<String, Object>> getUserByEmail(@RequestParam String email) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("status", HttpStatus.OK.value());
		response.put("data", userService.findByEmail(email));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
