package com.example.camerast.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.camerast.entities.ChiTietHoaDon;

@RepositoryRestResource(collectionResourceRel = "chitiethoadon", path = "chitiethoadon")
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, Integer> {

}
