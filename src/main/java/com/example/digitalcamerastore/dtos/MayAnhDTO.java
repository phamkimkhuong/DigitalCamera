package com.example.digitalcamerastore.dtos;

import com.example.digitalcamerastore.entities.ThuongHieu;
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
	 private int maMayAnh;
	    private String tenMayAnh;
	    private int soLuong;
	    private double giaBan;
	    private String moTa;
	    @JsonIgnore
	    private ThuongHieu thuongHieu;
}
