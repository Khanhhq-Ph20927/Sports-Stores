package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DonViVanChuyen")
public class DonViVanChuyen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TenDonVi")
    private String tenDonVi;

    @Column(name = "Email", unique = true)
    private String email;
}
