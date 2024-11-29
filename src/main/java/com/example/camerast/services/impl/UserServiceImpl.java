/*
 * @(#) $(NAME).java    1.0     11/27/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.camerast.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.camerast.dtos.UserDTO;
import com.example.camerast.entities.Role;
import com.example.camerast.entities.User;
import com.example.camerast.exceptions.ItemNotFoundException;
import com.example.camerast.repositories.RoleRepository;
import com.example.camerast.repositories.UserRepository;
import com.example.camerast.services.UserService;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 27-November-2024 2:29 PM
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private UserDTO convertToDTO(User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	private User convertToEntity(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return user;
	}

	@Override
	public UserDTO findById(int id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException("Can not find User with id: " + id));

		return this.convertToDTO(user);
	}

	@Transactional
	@Override
	public List<UserDTO> findAll() {
		return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public Page<UserDTO> findAllWithPaging(int pageNo, int pageSize, String sortBy, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return userRepository.findAll(pageable).map(this::convertToDTO);
	}

	@Transactional
	@Override
	public UserDTO save(UserDTO userDTO) {
		Role role = roleRepository.findByName("ROLE_ADMIN");
		if (role == null) {
			role = new Role();
			role.setName("ROLE_ADMIN");
			roleRepository.save(role);
		}

		User user = this.convertToEntity(userDTO);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(List.of(role));
		user = userRepository.save(user);
		return this.convertToDTO(user);
	}

	@Override
	public UserDTO update(int id, UserDTO userDTO) {

		// Check id exists or not
		this.findById(id);

		// Update
		userRepository.save(this.convertToEntity(userDTO));
		return userDTO;
	}

	@Override
	public boolean delete(int id) {

		this.findById(id);
		userRepository.deleteById(id);
		return true;
	}

	@Transactional
	@Override
	public List<UserDTO> search(String keyword) {
		return userRepository.search(keyword).stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public UserDTO findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return this.convertToDTO(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid email or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
						.collect(Collectors.toList()));

	}
}
