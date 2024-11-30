package com.example.camerast.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class GioHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maGioHang;
	private int soLuong;
	// ngay
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private User user;
	@ManyToOne
	@JoinColumn(name = "maMayAnh", referencedColumnName = "id")
	private MayAnh mayAnh;
}
