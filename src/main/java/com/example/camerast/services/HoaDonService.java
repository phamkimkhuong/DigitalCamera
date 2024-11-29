package com.example.camerast.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.camerast.dtos.HoaDonDTO;
import com.example.camerast.dtos.UserDTO;

public interface HoaDonService {
	 public HoaDonDTO findById(int id);

	    public List<HoaDonDTO> findAll();

	    public Page<HoaDonDTO> findAllWithPaging(int pageNo, int pageSize, String sortBy, String sortDirection);

	    public HoaDonDTO save(HoaDonDTO hoaDonDTO);

	    public HoaDonDTO update(int id, HoaDonDTO hoaDonDTO);

	    public boolean delete(int id);

	    public List<HoaDonDTO> search(String keyword);
}
