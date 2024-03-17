package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.AnhSanPham;
import com.project.SportsStores.Toner.Model.DTO.SanPhamChiTietDTO;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Service.Impl.AnhSanPhamServiceImpl;
import com.project.SportsStores.Toner.Service.Impl.MauSacServiceImpl;
import com.project.SportsStores.Toner.Service.Impl.SanPhamChiTietServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AnhSanPhamServiceImpl serviceASP;

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> getProductDetailById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(sv.getById(id));
    }

    @RequestMapping(value = "/picture/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> getPictureByIdProductDetail(@PathVariable("id") String id) {
        if (serviceASP.getByIdProductDetail(id) == null) {
            return ResponseEntity.status(400).body("null");
        }
        return ResponseEntity.ok().body(serviceASP.getByIdProductDetail(id));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    private ResponseEntity<?> saveProductDetail(@RequestBody SanPhamChiTietDTO spct, @PathVariable("id") String id) {
        SanPhamChiTiet sanPhamChiTiet = sv.getById(id);
        sanPhamChiTiet.setSoLuong(Integer.parseInt(spct.getSl()));
        sanPhamChiTiet.setSize(spct.getSize().toUpperCase(Locale.ROOT));
        sanPhamChiTiet.setMs(serviceMS.getById(Integer.valueOf(spct.getMs())));
        if (spct.getImgSrc() != null) {
            AnhSanPham pic = serviceASP.getByIdProductDetail(String.valueOf(sanPhamChiTiet.getId()));
            if(pic==null){
                AnhSanPham picture = new AnhSanPham();
                picture.setLinkAnh(spct.getImgSrc());
                picture.setSpct(sanPhamChiTiet);
                System.out.println(spct.toString());
                serviceASP.save(picture);
            } else {
                pic.setLinkAnh(spct.getImgSrc());
                serviceASP.save(pic);
            }
        }
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