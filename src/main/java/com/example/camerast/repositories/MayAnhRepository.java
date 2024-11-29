package com.example.camerast.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.camerast.entities.MayAnh;

@RepositoryRestResource(collectionResourceRel = "mayanhs", path = "mayanhs")
public interface MayAnhRepository extends JpaRepository<MayAnh, Integer> {
	@Query(value = "SELECT e FROM MayAnh e WHERE e.ten LIKE  %:keyword%")
	//+ " OR e.lastName LIKE %:keyword% OR e.emailAddress LIKE  %:keyword%" + " OR e.phoneNumber LIKE  %:keyword%")
		List<MayAnh> search(@Param("keyword") String keyword);
}
