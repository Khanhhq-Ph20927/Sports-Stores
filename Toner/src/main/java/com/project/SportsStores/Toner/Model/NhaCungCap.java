package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="NhaCungCap")
public class NhaCungCap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="TenNCC")
    private String ten;

    @Column(name="DiaChi")
    private String diaChi;

    @Column(name="MoTa")
    private String moTa;

    @Column(name="SDT")
    private String sdt;

    @Column(name="Email")
    private String email;

    @Column(name="ThongTinKhac")
    private String thongTinKhac;


}
