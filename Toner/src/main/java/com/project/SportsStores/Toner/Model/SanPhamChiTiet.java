package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

@Entity
@Table(name="SanPhamChiTiet")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Size")
    private String size;

    @Column(name="ngayTao")
    private LocalDateTime ngayTao;

    @Column(name="SoLuong")
    private int soLuong;

    @ManyToOne
    @JoinColumn(name="MauSac")
    private MauSac ms;

    @ManyToOne
    @JoinColumn(name="idSanPham")
    private SanPham sp;
}
