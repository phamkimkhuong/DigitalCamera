package com.example.camerast.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class ThuongHieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maThuongHieu;
    private String tenThuongHieu;
    @OneToMany(mappedBy = "thuongHieu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MayAnh> dsMayAnh;
}
