package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.DTO.SanPhamChiTietDTO;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Service.Impl.MauSacServiceImpl;
import com.project.SportsStores.Toner.Service.Impl.SanPhamChiTietServiceImpl;
import com.project.SportsStores.Toner.Service.Impl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/admin/product_detail")
public class RestControllerSanPhamChiTiet {

    @Autowired
    private SanPhamChiTietServiceImpl sv;

    @Autowired
    private MauSacServiceImpl serviceMS;

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> getProductDetailById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(sv.getById(id));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    private ResponseEntity<?> saveProductDetail(@RequestBody SanPhamChiTietDTO spct, @PathVariable("id") String id) {
        SanPhamChiTiet sanPhamChiTiet = sv.getById(id);
        sanPhamChiTiet.setSoLuong(Integer.parseInt(spct.getSl()));
        sanPhamChiTiet.setSize(spct.getSize().toUpperCase(Locale.ROOT));
        sanPhamChiTiet.setMs(serviceMS.getById(Integer.valueOf(spct.getMs())));
        List<SanPhamChiTiet> listSPCT = sv.getListByIdSp(String.valueOf(sanPhamChiTiet.getSp().getId()));
        listSPCT.remove(sanPhamChiTiet);
        String valid = "Valid is null";
        for (SanPhamChiTiet spctFor : listSPCT) {
            String sizeTemp = sanPhamChiTiet.getSize();
            String size = spctFor.getSize();
            boolean isValidColor = spctFor.getMs().getId() == sanPhamChiTiet.getMs().getId();
            boolean isValidSize = sizeTemp.equals(size);
            if (isValidSize && isValidColor) {
                valid = "Sản Phẩm Chi Tiết Đã Tồn Tại!";
                break;
            } else {
                valid = "No Valid";
            }
        }
        if (valid.equalsIgnoreCase("No Valid")) {
            sv.update(sanPhamChiTiet);
        }
        return ResponseEntity.ok().body(valid);
    }
}