package com.project.SportsStores.Toner.Model.DTO;

import lombok.Data;

@Data
public class SanPhamChiTietDTOValidation {

    private String size;

    private String ms;

    private String sl;

    private String imgSrc;

    private boolean isValid;

    @Override
    public String toString() {
        return
                "size = " + size + '\'' +
                        ", ms = " + ms + '\'' +
                        ", sl = " + sl + '\'' +
                        ", isValid = " + isValid + '\n';
    }
}
