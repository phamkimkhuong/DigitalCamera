/*
 * @(#) $(NAME).java    1.0     11/27/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.services;


import com.example.digitalcamerastore.dtos.EmployeeDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 27-November-2024 2:28 PM
 */
public interface EmployeeService {
    public EmployeeDTO findById(int id);

    public List<EmployeeDTO> findAll();

    public Page<EmployeeDTO> findAllWithPaging(int pageNo, int pageSize, String sortBy, String sortDirection);

    public EmployeeDTO save(EmployeeDTO employeeDTO);

    public EmployeeDTO update(int id, EmployeeDTO employeeDTO);

    public boolean delete(int id);

    public List<EmployeeDTO> search(String keyword);
}

    