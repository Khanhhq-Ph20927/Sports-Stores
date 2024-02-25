package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "DonHang")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MaDonHang", unique = true)
    private String maDonHang;

    @Column(name = "TrangThai")
    private int trangThai;

    @Column(name = "PhiVanChuyen")
    private BigDecimal phiVanChuyen;

    @Column(name = "TongTien")
    private BigDecimal tongTien;

    @Column(name = "TienPhaiTra")
    private BigDecimal tienPhaiTra;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao;

    @Column(name = "GhiChu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "IdKhachHang")
    private KhachHang kh;

    @ManyToOne
    @JoinColumn(name = "IdNhanVien")
    private NhanVien nv;

    @ManyToOne
    @JoinColumn(name = "IdPhuongThucThanhToan")
    private PhuongThucThanhToan pttt;

    @ManyToOne
    @JoinColumn(name = "IdThongTinVanChuyen")
    private ThongTinVanChuyen ttvc;
}
