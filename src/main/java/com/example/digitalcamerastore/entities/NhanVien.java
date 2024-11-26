/*
 * @(#) $(NAME).java    1.0     11/26/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 26-November-2024 3:00 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNhanVien;
    private String tenNhanVien;
    private String email;
    private String soDienThoai;
    private String DiaChi;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "nhanvien_chucvu", joinColumns = {
            @JoinColumn(name = "maNhanVien", referencedColumnName = "maNhanVien") }, inverseJoinColumns = {
            @JoinColumn(name = "maChucVu", referencedColumnName = "maChucVu") })
    private List<ChucVu> dsChucVu = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maTaiKhoan", referencedColumnName = "maTaiKhoan")
    private TaiKhoan taiKhoan;
}
