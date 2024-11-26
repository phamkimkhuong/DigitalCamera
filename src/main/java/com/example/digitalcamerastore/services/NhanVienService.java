/*
 * @(#) $(NAME).java    1.0     11/26/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.services;


import com.example.digitalcamerastore.entities.NhanVien;

import java.util.List;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 26-November-2024 3:24 PM
 */
public interface NhanVienService {
    public void save(NhanVien nhanVien);

    public List<NhanVien> findAll();

    public NhanVien findById(int id);

    public NhanVien findByTaiKhoan_TenDangNhap(String tenDangNhap);

    public boolean delete(int id);

    public NhanVien update(int id, NhanVien nhanVien);
}

    