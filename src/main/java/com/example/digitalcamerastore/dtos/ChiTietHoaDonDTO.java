package com.example.digitalcamerastore.dtos;

import com.example.digitalcamerastore.entities.HoaDon;
import com.example.digitalcamerastore.entities.MayAnh;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChiTietHoaDonDTO {
	private int maChiTietHoaDon;
	private int soLuong;
	private double giaBan;
	@JsonIgnore
	private MayAnh mayAnh;
	@JsonIgnore
	private HoaDon hoaDon;
}
