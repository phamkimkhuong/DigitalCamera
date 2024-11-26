/*
 * @(#) $(NAME).java    1.0     11/26/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 26-November-2024 9:13 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tai_khoan")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maTaiKhoan;
    private String tenDangNhap;
    private String matKhau;
    @OneToOne(mappedBy = "taiKhoan", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private NhanVien nhanVien;

    @OneToOne(mappedBy = "taiKhoan", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private KhachHang khachHang;
}
