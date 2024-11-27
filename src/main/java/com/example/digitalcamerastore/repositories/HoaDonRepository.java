/*
 * @(#) $(NAME).java    1.0     11/27/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.repositories;


import com.example.digitalcamerastore.entities.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 27-November-2024 8:30 PM
 */
@RepositoryRestResource
public interface HoaDonRepository extends JpaRepository<HoaDon,Integer> {

}

    