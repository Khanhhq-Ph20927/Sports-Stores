package com.project.SportsStores.Toner.Model.DTO;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamDTO {
    private Long id;
    private String tenSP;
    private BigDecimal donGia;
    private Integer danhMuc;
    private Integer trangThai;
    private Long ncc;
    private Long thieu;
}