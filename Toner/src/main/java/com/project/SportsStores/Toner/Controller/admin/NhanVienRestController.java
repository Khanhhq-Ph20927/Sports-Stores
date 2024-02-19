package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.NhanVien;
import com.project.SportsStores.Toner.Service.ChucVuService;
import com.project.SportsStores.Toner.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/staff")
//@RestController
public class NhanVienRestController {
    @Autowired
    NhanVienService service;
    @Autowired
    ChucVuService chucVuService;

    @GetMapping("/page/{number}/{keyword}/{status}/{position}")
    public ResponseEntity<?> getPageAndSearchAndFilter(Model model, @PathVariable("number") int number
            , @PathVariable("keyword") String keyword
            , @PathVariable("status") int status
            , @PathVariable("position") Long position
    ) {
        Pageable pageable = PageRequest.of(number, 5, Sort.by("ngayTao").descending());
//        service.page(pageable);
        model.addAttribute("number", number);
        model.addAttribute("totalPages", service.page(pageable).getTotalPages());
        model.addAttribute("totalElements", service.page(pageable).getTotalElements());
        model.addAttribute("list", service.page(pageable).getContent());
        return new ResponseEntity<>(service.SearchAndFilter(pageable, keyword, status, position), HttpStatus.OK);
    }

    @GetMapping("/page/{number}/{keyword}")
    public ResponseEntity<?> getPage(Model model, @PathVariable("keyword") String keyword
            , @PathVariable("number") int number) {
        Pageable pageable = PageRequest.of(number, 5, Sort.by("ngayTao").descending());
        model.addAttribute("number", number);
        model.addAttribute("totalPages", service.page(pageable).getTotalPages());
        model.addAttribute("totalElements", service.page(pageable).getTotalElements());
        model.addAttribute("list", service.page(pageable).getContent());
//        service.page(pageable);
        if (keyword.equals("null")) {
            Page<NhanVien> page = service.page(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            Page<NhanVien> page = service.SearchPage(pageable, keyword);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
    }
}
