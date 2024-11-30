package com.example.camerast.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.camerast.dtos.MayAnhDTO;
import com.example.camerast.services.MayAnhService;

import jakarta.validation.Valid;

@RepositoryRestController
public class MayAnhController {
	@Autowired
	private MayAnhService mayAnhService;

	@GetMapping("/mayanh/{id}")
	public ResponseEntity<Map<String, Object>> getUserById(@PathVariable int id) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("status", HttpStatus.OK.value());
		response.put("data", mayAnhService.findById(id));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/mayanhs")
	public ResponseEntity<Map<String, Object>> saveMayAnh(@Valid @RequestBody MayAnhDTO MayAnhDTO,
			BindingResult bindingResult) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();

		if (bindingResult.hasErrors()) {
			Map<String, Object> errors = new LinkedHashMap<String, Object>();

			bindingResult.getFieldErrors().stream().forEach(result -> {
				errors.put(result.getField(), result.getDefaultMessage());
			});

			System.out.println(bindingResult);
			response.put("status", HttpStatus.BAD_REQUEST.value());
			response.put("errors", errors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} else {
			response.put("status", HttpStatus.OK.value());
			response.put("data", mayAnhService.save(MayAnhDTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}

	}

	@PutMapping("/mayanh/{id}")
	public ResponseEntity<Map<String, Object>> updateUser(@PathVariable int id, @Valid @RequestBody MayAnhDTO MayAnhDTO,
			BindingResult bindingResult) {

		Map<String, Object> response = new LinkedHashMap<String, Object>();

		if (bindingResult.hasErrors()) {
			Map<String, Object> errors = new LinkedHashMap<String, Object>();
			bindingResult.getFieldErrors().stream().forEach(result -> {
				errors.put(result.getField(), result.getDefaultMessage());
			});

			response.put("status", HttpStatus.BAD_REQUEST.value());
			response.put("errors", errors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} else {
			response.put("status", HttpStatus.OK.value());
			response.put("data", mayAnhService.update(id, MayAnhDTO));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@DeleteMapping("/mayanh/{id}")
	public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable int id) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("status", HttpStatus.OK.value());
		response.put("data", mayAnhService.delete(id));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/mayanhs")
	public ResponseEntity<Map<String, Object>> getUsers(@RequestParam(required = false) String keyword) {

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("status", HttpStatus.OK.value());

		if (keyword == null || keyword.isEmpty()) {
			response.put("data", mayAnhService.findAll());
		} else {
			response.put("data", mayAnhService.search(keyword));
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/mayanhs/page")
	public ResponseEntity<Map<String, Object>> getList(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "20", required = false) int size,
			@RequestParam(defaultValue = "id", required = false) String sortBy,
			@RequestParam(defaultValue = "ASC", required = false) String sortDirection) {
		Page<MayAnhDTO> MayAnh = mayAnhService.findAllWithPaging(page, size, sortBy, sortDirection);
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("data", MayAnh);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
