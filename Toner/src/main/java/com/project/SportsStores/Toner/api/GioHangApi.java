package com.project.SportsStores.Toner.api;

import com.project.SportsStores.Toner.Model.GioHang;
import com.project.SportsStores.Toner.Model.GioHangChiTiet;
import com.project.SportsStores.Toner.Model.KhachHang;
import com.project.SportsStores.Toner.Repository.GioHangChiTietRepository;
import com.project.SportsStores.Toner.Service.GioHangService;
import com.project.SportsStores.Toner.exception.MessageException;
import com.project.SportsStores.Toner.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/giohang")
@CrossOrigin
public class GioHangApi {

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private UserUtils userUtils;

    @GetMapping("/getbytoken")
    public ResponseEntity<?> findByKhachHang(Pageable pageable, @RequestParam(value = "search", required = false) String search){
        KhachHang khachHang = userUtils.getKhachHangWithJwt();
        if(khachHang == null){
            throw new MessageException("chua dang nhap");
        }
        Page<GioHangChiTiet> result = null;
        if(search == null){
            result = gioHangChiTietRepository.findByKhachHang(khachHang.getId(), pageable);
        }
        else{
            result = gioHangChiTietRepository.findByKhachHangAndParam(khachHang.getId(), "%"+search+"%", pageable);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
