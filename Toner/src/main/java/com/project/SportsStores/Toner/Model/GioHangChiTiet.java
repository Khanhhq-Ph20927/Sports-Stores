package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="GioHangChiTiet")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GioHangChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="IdSanPhamChiTiet")
    private SanPhamChiTiet spct;

    @ManyToOne
    @JoinColumn(name="IdGioHang")
    private GioHang gh;

    @Column(name="SoLuong")
    private int soLuong;

    @Column(name="NgayChinhSua")
    private LocalDateTime ngaySua;
}
