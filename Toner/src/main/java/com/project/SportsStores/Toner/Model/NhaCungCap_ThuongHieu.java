package com.project.SportsStores.Toner.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="NhaCungCap_ThuongHieu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NhaCungCap_ThuongHieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="IdThuongHieu")
    private ThuongHieu th;

    @ManyToOne
    @JoinColumn(name="IdNhaCungCap")
    private NhaCungCap ncc;
}
