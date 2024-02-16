package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.NhanVien;
import com.project.SportsStores.Toner.Service.ChucVuService;
import com.project.SportsStores.Toner.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin/staff")
//@RestController
public class NhanVienRestController {
    @Autowired
    NhanVienService service;
    @Autowired
    ChucVuService chucVuService;

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(Model model, @PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @ModelAttribute("nhanVien") NhanVien nhanVien) {
        nhanVien.setNgayTao(LocalDateTime.now());
        service.save(nhanVien);
        return "redirect:admin/staff/add";
    }
}
