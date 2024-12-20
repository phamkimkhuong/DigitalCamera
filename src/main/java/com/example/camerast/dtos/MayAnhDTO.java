package com.example.camerast.dtos;

import com.example.camerast.entities.ThuongHieu;
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
public class MayAnhDTO {
	private int id;
	private String name;
	private int soLuong;
	private double oldPrice;
	private double currentPrice;
	private String maSanPham;
	private String link;
	private String linkHome;
	@JsonIgnore
	private ThuongHieu thuongHieu;
}
