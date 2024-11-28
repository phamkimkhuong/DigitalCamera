package com.example.digitalcamerastore.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.digitalcamerastore.dtos.ChiTietHoaDonDTO;

public interface ChiTietHoaDonService {
	 public ChiTietHoaDonDTO findById(int id);

	    public List<ChiTietHoaDonDTO> findAll();

	    public Page<ChiTietHoaDonDTO> findAllWithPaging(int pageNo, int pageSize, String sortBy, String sortDirection);

	    public ChiTietHoaDonDTO save(ChiTietHoaDonDTO cthdDTO);

	    public ChiTietHoaDonDTO update(int id, ChiTietHoaDonDTO cthdDTO);

	    public boolean delete(int id);

	    public List<ChiTietHoaDonDTO> search(String keyword);
}
