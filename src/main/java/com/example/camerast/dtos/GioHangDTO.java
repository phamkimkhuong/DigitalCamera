package com.example.camerast.dtos;

import com.example.camerast.entities.MayAnh;
import com.example.camerast.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GioHangDTO {
	private int maGioHang;
	private int soLuong;
	@JsonIgnore
	private User user;
	@JsonIgnore
	private MayAnh mayAnh;
}
