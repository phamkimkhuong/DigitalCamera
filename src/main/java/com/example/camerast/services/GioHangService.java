package com.example.camerast.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.camerast.dtos.GioHangDTO;
import com.example.camerast.dtos.HoaDonDTO;

public interface GioHangService {
	 public GioHangDTO findById(int id);

	    public List<GioHangDTO> findAll();

	    public Page<GioHangDTO> findAllWithPaging(int pageNo, int pageSize, String sortBy, String sortDirection);

	    public GioHangDTO save(GioHangDTO gioHangDTO);

	    public GioHangDTO update(int id, GioHangDTO gioHangDTO);

	    public boolean delete(int id);

//	    public List<HoaDonDTO> search(String keyword);
}
