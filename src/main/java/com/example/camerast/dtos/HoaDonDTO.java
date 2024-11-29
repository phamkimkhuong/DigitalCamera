package com.example.camerast.dtos;

import java.time.LocalDate;
import java.util.List;

import com.example.camerast.entities.ChiTietHoaDon;
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
public class HoaDonDTO {
    private int maHoaDon;
    private LocalDate ngayLap;
    private double tongTien;
    private String trangThai;
    @JsonIgnore
    private User user;
    @JsonIgnore
    private List<ChiTietHoaDon> dsChiTietHoaDon;
}
