package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="ChucVu")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChucVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="TenChucVu")
    private String tenChucVu;


}
