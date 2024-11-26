/*
 * @(#) $(NAME).java    1.0     11/26/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.entities;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 26-November-2024 8:56 PM
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "may_anh")
public class MayAnh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maMayAnh;
    private String tenMayAnh;
    private int soLuong;
    private double giaBan;
    private int baoHanh;
    private String moTa;
    private String thongTinChiTiet;
    @ManyToOne(fetch = FetchType.LAZY)
    private ThuongHieu thuongHieu;

}
