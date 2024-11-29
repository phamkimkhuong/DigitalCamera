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

import com.example.camerast.dtos.HoaDonDTO;
import com.example.camerast.entities.HoaDon;
import com.example.camerast.exceptions.ItemNotFoundException;
import com.example.camerast.repositories.HoaDonRepository;
import com.example.camerast.services.HoaDonService;

@Service
public class HoaDonServiceImpl implements HoaDonService {
	@Autowired
	private HoaDonRepository hoaDonRepository;
	@Autowired
	private ModelMapper modelMapper;

	private HoaDonDTO convertToDTO(HoaDon hoaDon) {
		HoaDonDTO hoaDonDTO = modelMapper.map(hoaDon, HoaDonDTO.class);
		return hoaDonDTO;
	}

	private HoaDon convertToEntity(HoaDonDTO hoaDonDTO) {
		HoaDon hoaDon = modelMapper.map(hoaDonDTO, HoaDon.class);
		return hoaDon;
	}

	@Override
	public HoaDonDTO findById(int id) {
		HoaDon hoaDon = hoaDonRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException("Can not find HoaDon with id: " + id));

		return this.convertToDTO(hoaDon);
	}

	@org.springframework.transaction.annotation.Transactional
	@Override
	public List<HoaDonDTO> findAll() {
		return hoaDonRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public Page<HoaDonDTO> findAllWithPaging(int pageNo, int pageSize, String sortBy, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return hoaDonRepository.findAll(pageable).map(this::convertToDTO);
	}

	@Transactional
	@Override
	public HoaDonDTO save(HoaDonDTO hoaDonDTO) {
		HoaDon hoaDon = this.convertToEntity(hoaDonDTO);
		hoaDon = hoaDonRepository.save(hoaDon);
		return this.convertToDTO(hoaDon);
	}

	@Override
	public HoaDonDTO update(int id, HoaDonDTO hoaDonDTO) {
		// Check id exists or not
		this.findById(id);

		// Update
		hoaDonRepository.save(this.convertToEntity(hoaDonDTO));
		return hoaDonDTO;
	}

	@Override
	public boolean delete(int id) {
		this.findById(id);
		hoaDonRepository.deleteById(id);
		return true;
	}


	@Transactional
	@Override
	public List<HoaDonDTO> search(String keyword) {
		return hoaDonRepository.search(keyword).stream().map(this::convertToDTO).collect(Collectors.toList());

	}
}
