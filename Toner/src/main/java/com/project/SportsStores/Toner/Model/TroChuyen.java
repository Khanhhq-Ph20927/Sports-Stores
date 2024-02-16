package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="TroChuyen")
public class TroChuyen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="TrangThai")
    private int trangThai;

    @Column(name="NgayTao")
    private LocalDateTime ngayTao;

    @ManyToOne
    @JoinColumn(name="IdNhanVien")
    private NhanVien nv;

    @ManyToOne
    @JoinColumn(name="IdKhachHang")
    private KhachHang kh;
}
