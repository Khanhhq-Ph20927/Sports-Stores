package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="DiaChi")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DiaChi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Tinh_ThanhPho")
    private String ttp;

    @Column(name="Quan_Huyen")
    private String qh;

    @Column(name="Xa_Phuong")
    private String xp;

    @Column(name="DiaChiCuThe")
    private String diaChiCuThe;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao;

    @ManyToOne
    @JoinColumn(name="IdKhachHang")
    private KhachHang kh;
}
