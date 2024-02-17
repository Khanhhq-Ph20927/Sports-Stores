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
public class AnhSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="LinkAnh")
    private String linkAnh;

    @ManyToOne
    @JoinColumn(name="IdSanPham")
    private SanPham sp;
}
