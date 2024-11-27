/*
 * @(#) $(NAME).java    1.0     11/27/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.controllers;

import com.example.digitalcamerastore.dtos.EmployeeDTO;
import com.example.digitalcamerastore.services.EmployeeService;
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
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable int id) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("status", HttpStatus.OK.value());
        response.put("data", employeeService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/employees")
    public ResponseEntity<Map<String, Object>> saveEmployee(
            @Valid @RequestBody EmployeeDTO employeeDTO,
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
            response.put("data", employeeService.save(employeeDTO));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Map<String, Object>> updateEmployee(@PathVariable int id, @Valid @RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) {

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
            response.put("data", employeeService.update(id, employeeDTO));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable int id) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("status", HttpStatus.OK.value());
        response.put("data", employeeService.delete(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/employees")
    public ResponseEntity<Map<String, Object>> getEmployees(@RequestParam(required = false) String keyword) {

        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("status", HttpStatus.OK.value());

        if (keyword == null || keyword.isEmpty()) {
            response.put("data", employeeService.findAll());
        }
        else {
            response.put("data", employeeService.search(keyword));
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/employees/page")
    public ResponseEntity<Map<String, Object>> getList(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "20", required = false) int size,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "ASC", required = false) String sortDirection) {
        Page<EmployeeDTO> employees = employeeService.findAllWithPaging(page, size, sortBy, sortDirection);
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("data", employees);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
