package com.example.camerast.dtos;

import java.util.List;

import com.example.camerast.entities.MayAnh;
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
public class ThuongHieuDTO {
	private int maThuongHieu;
    private String tenThuongHieu;
    @JsonIgnore
    private List<MayAnh> dsMayAnh;
}
