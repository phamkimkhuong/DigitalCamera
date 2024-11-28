package com.example.digitalcamerastore.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.digitalcamerastore.dtos.MayAnhDTO;

public interface MayAnhService {
	 public MayAnhDTO findById(int id);

	    public List<MayAnhDTO> findAll();

	    public Page<MayAnhDTO> findAllWithPaging(int pageNo, int pageSize, String sortBy, String sortDirection);

	    public MayAnhDTO save(MayAnhDTO mayAnhDTO);

	    public MayAnhDTO update(int id, MayAnhDTO mayAnhDTO);

	    public boolean delete(int id);

	    public List<MayAnhDTO> search(String keyword);
}
