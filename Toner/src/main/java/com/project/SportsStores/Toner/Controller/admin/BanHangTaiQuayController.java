package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.KhuyenMai;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
@RequestMapping("/admin/sell-off")
public class BanHangTaiQuayController {

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @RequestMapping("")
    public String satc() {
        return "admin/satc";
    }

    @GetMapping("/san-pham-chi-tiet")
    public ResponseEntity<?> findAll(@RequestParam(value = "search", required = false) String search,
                                     Pageable pageable){
        Page<SanPhamChiTiet> result = sanPhamChiTietService.sanPhamChiTietBanTaiQuay(search, pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
