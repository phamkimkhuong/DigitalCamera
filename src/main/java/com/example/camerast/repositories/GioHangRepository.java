package com.example.camerast.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.camerast.entities.GioHang;

@RepositoryRestResource(collectionResourceRel = "giohangs", path = "giohangs")
public interface GioHangRepository extends JpaRepository<GioHang, Integer> {

}
