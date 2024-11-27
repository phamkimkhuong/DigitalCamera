/*
 * @(#) $(NAME).java    1.0     11/27/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.repositories;


import com.example.digitalcamerastore.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 27-November-2024 2:30 PM
 */
@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT e FROM Employee e WHERE e.fullName LIKE  %:keyword%"
            + " OR e.email LIKE %:keyword%"
            + " OR e.phone LIKE  %:keyword%")
    List<Employee> search(@Param("keyword") String keyword);
}

    