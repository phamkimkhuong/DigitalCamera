///*
// * @(#) $(NAME).java    1.0     11/26/2024
// *
// * Copyright (c) 2024 IUH. All rights reserved.
// */
//
//package com.example.digitalcamerastore.security;
//
//import com.example.digitalcamerastore.entities.NhanVien;
//import com.example.digitalcamerastore.repositories.NhanVienRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.stream.Collectors;
//
///*
// * @description
// * @author: Tran Tan Dat
// * @version: 1.0
// * @created: 26-November-2024 10:31 PM
// */
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private NhanVienRepository nhanVienRepository;
//
//    @Transactional
//    @Override
//    public UserDetails loadUserByUsername(String tenDangNhap) throws UsernameNotFoundException {
//        NhanVien nhanVien = nhanVienRepository.findByTaiKhoan_TenDangNhap(tenDangNhap);
//
//        if(nhanVien == null){
//            throw new UsernameNotFoundException("Invalid username or password");
//        }
//
//        return new org.springframework.security.core.userdetails.User(
//                nhanVien.getTaiKhoan().getTenDangNhap(),
//                nhanVien.getTaiKhoan().getMatKhau(),
//                nhanVien.getDsChucVu()
//                        .stream().map(
//                                (chucVu)->new SimpleGrantedAuthority(chucVu.getTenChucVu())).collect(Collectors.toList()));
//    }
//
//}
