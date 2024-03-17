package com.project.SportsStores.Toner.Model.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamDTO {
    private String id;
    private String tenSP;
    private String donGia;
    private Integer danhMuc;
    private Integer trangThai;
    private Long ncc;
    private Long thieu;
}