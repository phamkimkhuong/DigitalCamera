/*
 * @(#) $(NAME).java    1.0     11/27/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.digitalcamerastore.entities.HoaDon;

@RepositoryRestResource(collectionResourceRel = "hoadons", path = "hoadons")
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
	@Query(value = "SELECT e FROM HoaDon e WHERE e.trangThai LIKE  %:keyword%")
//+ " OR e.lastName LIKE %:keyword% OR e.emailAddress LIKE  %:keyword%" + " OR e.phoneNumber LIKE  %:keyword%")
	List<HoaDon> search(@Param("keyword") String keyword);
}
