/*
 * @(#) $(NAME).java    1.0     11/27/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.services.impl;

import com.example.digitalcamerastore.dtos.EmployeeDTO;
import com.example.digitalcamerastore.entities.Employee;
import com.example.digitalcamerastore.exceptions.ItemNotFoundException;
import com.example.digitalcamerastore.repositories.EmployeeRepository;
import com.example.digitalcamerastore.services.EmployeeService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 27-November-2024 2:29 PM
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        return employeeDTO;
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        return employee;
    }

    @Override
    public EmployeeDTO findById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ItemNotFoundException("Can not find Employee with id: " + id));

        return this.convertToDTO(employee);
    }

    @Transactional
    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EmployeeDTO> findAllWithPaging(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return employeeRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Transactional
    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = this.convertToEntity(employeeDTO);
        employee = employeeRepository.save(employee);
        return this.convertToDTO(employee);
    }

    @Override
    public EmployeeDTO update(int id, EmployeeDTO employeeDTO) {

        // Check id exists or not
        this.findById(id);

        // Update
        employeeRepository.save(this.convertToEntity(employeeDTO));
        return employeeDTO;
    }

    @Override
    public boolean delete(int id) {

        this.findById(id);
        employeeRepository.deleteById(id);
        return true;
    }

    @Transactional
    @Override
    public List<EmployeeDTO> search(String keyword) {
        return employeeRepository.search(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
