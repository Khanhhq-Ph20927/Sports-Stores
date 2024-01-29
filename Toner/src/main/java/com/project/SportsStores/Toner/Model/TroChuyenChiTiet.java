package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="TroChuyenChiTiet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TroChuyenChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NguoiGui")
    private boolean nguoiGui;

    @Column(name="IdNguoiGui")
    private String idNguoiGui;

    @Column(name="NoiDung")
    private String noiDung;

    @Column(name="ThoiGianGui")
    private LocalDateTime thoiGianGui;

    @Column(name="LoaiTinNhan")
    private int loaiTinNhan;

    @Column(name="LinkFile")
    private String linkFile;

    @ManyToOne
    @JoinColumn(name="IdTroChuyen")
    private TroChuyen troChuyen;

}
