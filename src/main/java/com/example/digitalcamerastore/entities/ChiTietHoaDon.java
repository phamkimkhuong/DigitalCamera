/*
 * @(#) $(NAME).java    1.0     11/27/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.entities;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
@Entity
public class ChiTietHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maChiTietHoaDon;
    private int soLuong;
    private double giaBan;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private MayAnh mayAnh;
    @ManyToOne
    @JoinColumn(name = "maHoaDon", referencedColumnName = "maHoaDon")
    private HoaDon hoaDon;
}