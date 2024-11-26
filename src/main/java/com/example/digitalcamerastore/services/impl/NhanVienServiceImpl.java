/*
 * @(#) $(NAME).java    1.0     11/26/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.services.impl;

import com.example.digitalcamerastore.entities.ChucVu;
import com.example.digitalcamerastore.entities.NhanVien;
import com.example.digitalcamerastore.repositories.ChucVuRepository;
import com.example.digitalcamerastore.repositories.NhanVienRepository;
import com.example.digitalcamerastore.services.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 26-November-2024 3:24 PM
 */
@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    NhanVienRepository nhanVienRepository;
    @Autowired
    ChucVuRepository chucVuRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(NhanVien nhanVien) {
        ChucVu chucVu = chucVuRepository.findByTenChucVu("ROLE_ADMIN");
        if (chucVu == null) {
            chucVu = new ChucVu();
            chucVu.setTenChucVu("ROLE_ADMIN");
            chucVuRepository.save(chucVu);
        }
        nhanVien.getTaiKhoan().setMatKhau(passwordEncoder.encode(nhanVien.getTaiKhoan().getMatKhau()));
        nhanVien.setDsChucVu(List.of(chucVu));
        nhanVienRepository.save(nhanVien);
    }

    @Override
    public List<NhanVien> findAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien findById(int id) {
        return nhanVienRepository.findById(id).orElse(null);
    }

    @Override
    public NhanVien findByTaiKhoan_TenDangNhap(String tenDangNhap) {
        return nhanVienRepository.findByTaiKhoan_TenDangNhap(tenDangNhap);
    }

    @Override
    public boolean delete(int id) {
        if (this.findById(id) != null) {
            nhanVienRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public NhanVien update(int id, NhanVien nhanVien) {
        if (this.findById(id) != null) {
            nhanVien.setMaNhanVien(id);
            return nhanVienRepository.save(nhanVien);
        }
        return null;
    }
}
