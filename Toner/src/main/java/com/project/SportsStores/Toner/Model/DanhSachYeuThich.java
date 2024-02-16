package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="DanhSachYeuThic")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DanhSachYeuThich {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NgayTao")
    private LocalDateTime ngayTao;

    @ManyToOne
    @JoinColumn(name="IdSanPham")
    private SanPham sp;

    @ManyToOne
    @JoinColumn(name="IdKhachHang")
    private KhachHang kh;
}
