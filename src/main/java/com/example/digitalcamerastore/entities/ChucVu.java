/*
 * @(#) $(NAME).java    1.0     11/26/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 26-November-2024 4:03 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "chuc_vu")
public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maChucVu;
    private String tenChucVu;
    @ManyToMany(mappedBy = "dsChucVu")
    private List<NhanVien> dsNhanVien;
}
