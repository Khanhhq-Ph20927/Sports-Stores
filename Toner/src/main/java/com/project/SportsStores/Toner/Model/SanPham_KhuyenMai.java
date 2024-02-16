package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="SanPham_KhuyenMai")
public class SanPham_KhuyenMai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IdSanPham")
    private SanPham sp;

    @ManyToOne
    @JoinColumn(name="IdKhuyenMai")
    private KhuyenMai km;
}
