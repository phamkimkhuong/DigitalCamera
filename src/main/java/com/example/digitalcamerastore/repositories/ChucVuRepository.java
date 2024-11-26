/*
 * @(#) $(NAME).java    1.0     11/26/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.repositories;

import com.example.digitalcamerastore.entities.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 26-November-2024 4:09 PM
 */
@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, Integer> {
    public ChucVu findByTenChucVu(String name);
}
