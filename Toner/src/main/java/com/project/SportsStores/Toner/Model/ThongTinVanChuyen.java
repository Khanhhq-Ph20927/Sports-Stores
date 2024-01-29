package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name="idDiaChi")
    private DiaChi dc;

}
