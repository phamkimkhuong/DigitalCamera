package com.example.digitalcamerastore.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Code is required")
    private String code;
//    @NotNull(message = "Name is required")
    private String name;
//    @NotNull(message = "Description is required")
//    @NotEmpty(message = "Description is required")
    private String description;
//    @NotNull(message = "Register date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date register_date;
    private Double price;
    private Boolean id_active;
    @OneToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

}
