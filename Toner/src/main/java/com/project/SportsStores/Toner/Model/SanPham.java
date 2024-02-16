package com.project.SportsStores.Toner.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="SanPham")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="MaSP")
    private String maSP;

    @Column(name="TenSP")
    private String tenSP;

    @Column(name="LoaiSanPham")
    private int loaiSanPham;

    @Column(name="SoLuong")
    private int soLuong;

    @Column(name="NgayTao")
    private LocalDateTime ngayTao;

    @Column(name="DonGia")
    private BigDecimal donGia;

    @Column(name="MoTa")
    private String moTa;

    @Column(name="TrangThai")
    private int trangThai;

    @Column(name="DanhMuc")
    private int danhMuc;

    @ManyToOne
    @JoinColumn(name="IdNhaCungCap")
    private NhaCungCap ncc;

    @ManyToOne
    @JoinColumn(name="IdTheLoai")
    private TheLoai tl;

    @ManyToOne
    @JoinColumn(name="IdThuongHieu")
    private ThuongHieu th;

    @OneToMany(mappedBy = "sp", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<AnhSanPham> anhSanPhams = new ArrayList<>();
}
