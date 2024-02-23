package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.GioHang;
import com.project.SportsStores.Toner.Service.Impl.GioHangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/card")
public class RestControllerGioHang {

    @Autowired
    private GioHangServiceImpl sv;

    //tạo 1 giỏ hàng mới không có idKhachHang
    @PostMapping("/save")
    private ResponseEntity<?> saveCard() {
        GioHang gh = new GioHang();
        sv.save(gh);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
