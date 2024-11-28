package com.example.digitalcamerastore.services.impl;

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

import com.example.digitalcamerastore.dtos.ChiTietHoaDonDTO;
import com.example.digitalcamerastore.entities.ChiTietHoaDon;
import com.example.digitalcamerastore.exceptions.ItemNotFoundException;
import com.example.digitalcamerastore.repositories.ChiTietHoaDonRepository;
import com.example.digitalcamerastore.services.ChiTietHoaDonService;

@Service
public class ChiTietHoaDonServiceImpl implements ChiTietHoaDonService {
	@Autowired
	private ChiTietHoaDonRepository hoaDonRepository;
	@Autowired
	private ModelMapper modelMapper;

	private ChiTietHoaDonDTO convertToDTO(ChiTietHoaDon hoaDon) {
		ChiTietHoaDonDTO hoaDonDTO = modelMapper.map(hoaDon, ChiTietHoaDonDTO.class);
		return hoaDonDTO;
	}

	private ChiTietHoaDon convertToEntity(ChiTietHoaDonDTO hoaDonDTO) {
		ChiTietHoaDon hoaDon = modelMapper.map(hoaDonDTO, ChiTietHoaDon.class);
		return hoaDon;
	}

	@Override
	public ChiTietHoaDonDTO findById(int id) {
		ChiTietHoaDon hoaDon = hoaDonRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException("Can not find HoaDon with id: " + id));

		return this.convertToDTO(hoaDon);
	}

	@org.springframework.transaction.annotation.Transactional
	@Override
	public List<ChiTietHoaDonDTO> findAll() {
		return hoaDonRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public Page<ChiTietHoaDonDTO> findAllWithPaging(int pageNo, int pageSize, String sortBy, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return hoaDonRepository.findAll(pageable).map(this::convertToDTO);
	}

	@Transactional
	@Override
	public ChiTietHoaDonDTO save(ChiTietHoaDonDTO hoaDonDTO) {
		ChiTietHoaDon hoaDon = this.convertToEntity(hoaDonDTO);
		hoaDon = hoaDonRepository.save(hoaDon);
		return this.convertToDTO(hoaDon);
	}

	@Override
	public ChiTietHoaDonDTO update(int id, ChiTietHoaDonDTO hoaDonDTO) {
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

	@Override
	public List<ChiTietHoaDonDTO> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}


//	@Transactional
//	@Override
//	public List<ChiTietHoaDonDTO> search(String keyword) {
//		return hoaDonRepository.search(keyword).stream().map(this::convertToDTO).collect(Collectors.toList());
//
//	}
}
