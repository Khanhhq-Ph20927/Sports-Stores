package com.project.SportsStores.Toner.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="AnhSanPham")
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
    @JsonBackReference
    private SanPham sp;
}
