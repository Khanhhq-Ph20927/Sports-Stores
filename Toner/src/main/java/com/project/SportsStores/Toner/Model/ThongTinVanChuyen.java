package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="ThongTinVanChuyen")
public class ThongTinVanChuyen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="SDT_NguoiNhan")
    private String sdtNguoiNhan;

    @Column(name="TenNguoiNhan")
    private String tenNguoiNhan;

    @ManyToOne
    @JoinColumn(name="IdDonViVanChuyen")
    private DonViVanChuyen dvvc;

    @Column(name="Tinh_ThanhPho")
    private String ttp;

    @Column(name="Quan_Huyen")
    private String qh;

    @Column(name="Xa_Phuong")
    private String xp;

    @Column(name="DiaChiCuThe")
    private String diaChiCuThe;

    @Column(name = "ngayDuKienGiaoHang")
    private Date ngayDuKienGiaoHang;
}
