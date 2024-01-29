package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="SanPhamChiTiet")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="MauSac")
    private String mauSac;

    @Column(name="Size")
    private int size;

    @ManyToOne
    @JoinColumn(name="idSanPham")
    private SanPham sp;
}
