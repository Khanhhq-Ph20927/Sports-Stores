package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="KhuyenMai")
public class KhuyenMai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="TenKhuyenMai")
    private String tenKhuyenMai;

    @Column(name="NgayBatDau")
    private Date ngayBatDau;

    @Column(name="NgayKetThuc")
    private Date ngayKetThuc;

    @Column(name="TrangThai")
    private int trangThai;

    @Column(name="Banner")
    private String banner;

    @Column(name="LoaiKhuyenMai")
    private boolean loaiKM;

    @Column(name="GiaTriGiam")
    private float giaTriGiam;

    @Column(name="NgayTao")
    private LocalDateTime ngayTao;

}
