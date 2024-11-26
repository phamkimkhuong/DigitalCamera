/*
 * @(#) $(NAME).java    1.0     11/26/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.entities;

import jakarta.persistence.*;
import lombok.*;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 26-November-2024 9:33 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maKhachHang;
    private String tenKhachHang;
    private String diaChi;
    private String soDienThoai;
    private String email;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maTaiKhoan", referencedColumnName = "maTaiKhoan")
    private TaiKhoan taiKhoan;
}
