package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="ThuongHieu")
public class ThuongHieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="TenTT")
    private String ten;

    @Column(name="MoTa")
    private String moTa;

    @Column(name="Logo")
    private String logo;

    @Column(name="Email",unique = true)
    private String email;

    @Column(name="QuocGia")
    private String quocGia;

}
