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

    @RequestMapping(value = "/picture/{id}/{idColor}", method = RequestMethod.GET)
    private ResponseEntity<?> getPictureByIdProductDetail(@PathVariable("id") String id,
                                                          @PathVariable("idColor") String idColor) {
        if (serviceASP.getByIdProductAndColor(id, idColor).size() == 0) {
            return ResponseEntity.status(400).body("null");
        }
        return ResponseEntity.ok().body(serviceASP.getByIdProductAndColor(id, idColor));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    private ResponseEntity<?> saveProductDetail(@RequestBody SanPhamChiTietDTO spct, @PathVariable("id") String id) {
        SanPhamChiTiet sanPhamChiTiet = sv.getById(id);
        sanPhamChiTiet.setSoLuong(Integer.parseInt(spct.getSl()));
        sanPhamChiTiet.setSize(spct.getSize().toUpperCase(Locale.ROOT));
        sanPhamChiTiet.setMs(serviceMS.getById(Integer.valueOf(spct.getMs())));
        if (serviceASP.getByIdProductAndColor(id, spct.getMs()).size() == 0) {
            AnhSanPham pic = new AnhSanPham();
            pic.setLinkAnh(spct.getImgSrc());
            pic.setSpct(sanPhamChiTiet);
            System.out.println(spct.toString());
            serviceASP.save(pic);
        } else {
            AnhSanPham pic = serviceASP.getByIdProductAndColor(id, spct.getMs()).get(0);
            pic.setLinkAnh(spct.getImgSrc());
            serviceASP.save(pic);
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