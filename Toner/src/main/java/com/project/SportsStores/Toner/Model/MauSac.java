package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name="MauSac")
@Entity
public class MauSac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="ten")
    private String ten;
}
