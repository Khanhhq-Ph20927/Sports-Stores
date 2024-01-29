package com.project.SportsStores.Toner.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TheLoai")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TheLoai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Ten")
    private String ten;

    @Column(name="MoTa")
    private String moTa;

    @Column(name="HinhAnh")
    private String hinhAnh;


}
