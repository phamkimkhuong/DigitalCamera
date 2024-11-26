/*
 * @(#) $(NAME).java    1.0     11/26/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package com.example.digitalcamerastore.controllers;

import com.example.digitalcamerastore.entities.NhanVien;
import com.example.digitalcamerastore.services.NhanVienService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/*
 * @description
 * @author: Tran Tan Dat
 * @version: 1.0
 * @created: 26-November-2024 3:51 PM
 */
@Controller
public class NhanVienController {

    @Autowired
    HttpSession httpSession;
    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("/register")
    public String showForm(Model model) {
        NhanVien nhanvien = new NhanVien();
        model.addAttribute("nhanvien", nhanvien);
        return "nhanvien/register";
    }
    @GetMapping("/dsNhanVien")
    public String getUsers(Model model){
        System.out.println(httpSession.getServletContext());
        List<NhanVien> dsNhanVien = nhanVienService.findAll();
        model.addAttribute("dsNhanVien", dsNhanVien);
        return "nhanvien/ds";
    }

}
