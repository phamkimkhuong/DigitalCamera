package com.example.camerast.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.camerast.dtos.GioHangDTO;
import com.example.camerast.entities.GioHang;
import com.example.camerast.exceptions.ItemNotFoundException;
import com.example.camerast.repositories.GioHangRepository;
import com.example.camerast.services.GioHangService;

@Service
public class GioHangServiceImpl implements GioHangService {
	@Autowired
	private GioHangRepository gioHangRepository;
	@Autowired
	private ModelMapper modelMapper;

	private GioHangDTO convertToDTO(GioHang gioHang) {
		GioHangDTO gioHangDTO = modelMapper.map(gioHang, GioHangDTO.class);
		return gioHangDTO;
	}

	private GioHang convertToEntity(GioHangDTO gioHangDTO) {
		GioHang gioHang = modelMapper.map(gioHangDTO, GioHang.class);
		return gioHang;
	}

	@Override
	public GioHangDTO findById(int id) {
		GioHang gioHang = gioHangRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException("Can not find GioHang with id: " + id));

		return this.convertToDTO(gioHang);
	}

	@org.springframework.transaction.annotation.Transactional
	@Override
	public List<GioHangDTO> findAll() {
		return gioHangRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public Page<GioHangDTO> findAllWithPaging(int pageNo, int pageSize, String sortBy, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return gioHangRepository.findAll(pageable).map(this::convertToDTO);
	}

	@Transactional
	@Override
	public GioHangDTO save(GioHangDTO gioHangDTO) {
		GioHang gioHang = this.convertToEntity(gioHangDTO);
		gioHang = gioHangRepository.save(gioHang);
		return this.convertToDTO(gioHang);
	}

	@Override
	public GioHangDTO update(int id, GioHangDTO gioHangDTO) {
		// Check id exists or not
		this.findById(id);

		// Update
		gioHangRepository.save(this.convertToEntity(gioHangDTO));
		return gioHangDTO;
	}

	@Override
	public boolean delete(int id) {
		this.findById(id);
		gioHangRepository.deleteById(id);
		return true;
	}

//	@Transactional
//	@Override
//	public List<GioHangDTO> search(String keyword) {
//		return gioHangRepository.search(keyword).stream().map(this::convertToDTO).collect(Collectors.toList());
//
//	}
}
