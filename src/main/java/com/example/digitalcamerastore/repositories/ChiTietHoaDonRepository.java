package com.example.digitalcamerastore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.digitalcamerastore.entities.ChiTietHoaDon;

@RepositoryRestResource(collectionResourceRel = "chitiethoadon", path = "chitiethoadon")
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, Integer> {

}
