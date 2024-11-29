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

import com.example.camerast.dtos.MayAnhDTO;
import com.example.camerast.entities.MayAnh;
import com.example.camerast.exceptions.ItemNotFoundException;
import com.example.camerast.repositories.MayAnhRepository;
import com.example.camerast.services.MayAnhService;

@Service
public class MayAnhServiceImpl implements MayAnhService {
	@Autowired
	private MayAnhRepository mayAnhRepository;

	@Autowired
	private ModelMapper modelMapper;

	private MayAnhDTO convertToDTO(MayAnh mayAnh) {
		MayAnhDTO mayAnhDTO = modelMapper.map(mayAnh, MayAnhDTO.class);
		return mayAnhDTO;
	}

	private MayAnh convertToEntity(MayAnhDTO mayAnhDTO) {
		MayAnh mayAnh = modelMapper.map(mayAnhDTO, MayAnh.class);
		return mayAnh;
	}

	@Override
	public MayAnhDTO findById(int id) {
		MayAnh mayAnh = mayAnhRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException("Can not find MayAnh with id: " + id));

		return this.convertToDTO(mayAnh);
	}

	@Transactional
	@Override
	public List<MayAnhDTO> findAll() {
		return mayAnhRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public Page<MayAnhDTO> findAllWithPaging(int pageNo, int pageSize, String sortBy, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return mayAnhRepository.findAll(pageable).map(this::convertToDTO);
	}

	@Override
	@Transactional
	public MayAnhDTO save(MayAnhDTO mayAnhDTO) {
		MayAnh mayAnh = this.convertToEntity(mayAnhDTO);
		mayAnh = mayAnhRepository.save(mayAnh);
		return this.convertToDTO(mayAnh);
	}

	@Override
	public MayAnhDTO update(int id, MayAnhDTO mayAnhDTO) {
		// Check id exists or not
		this.findById(id);

		// Update
		mayAnhRepository.save(this.convertToEntity(mayAnhDTO));
		return mayAnhDTO;
	}

	@Override
	public boolean delete(int id) {
		this.findById(id);
		mayAnhRepository.deleteById(id);
		return true;
	}

	@Transactional
	@Override
	public List<MayAnhDTO> search(String keyword) {
		return mayAnhRepository.search(keyword).stream().map(this::convertToDTO).collect(Collectors.toList());
	}

}
