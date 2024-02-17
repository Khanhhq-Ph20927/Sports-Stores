package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="GioHang")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="IdKhachHang", referencedColumnName = "Id")
    private KhachHang kh;
}
