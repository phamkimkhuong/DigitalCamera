package com.example.digitalcamerastore.controllers;

import com.example.digitalcamerastore.entities.Product;
import com.example.digitalcamerastore.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/regis")
    public String getProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/register";
    }

    // trả về danh sách sản phẩm
    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product/list";
    }
     @PostMapping("/register")
        public String registration(@Valid @ModelAttribute Product user,
                                   BindingResult result, Model model){
    		if(result.hasErrors()){
    	       model.addAttribute("user", user);
    	       return "user/register";
    	    }
//    		Product existingUser = productRepository.findByCode(user.getCode());
//
//            if(existingUser != null
//            		&& existingUser.getEmail() != null
//            		&& !existingUser.getEmail().isEmpty()){
//
//            	result.rejectValue("email", null, "Email da ton tai");
//
////            	return "product/list";
//            }
            productRepository.save(user);
            return "";
        }
}
