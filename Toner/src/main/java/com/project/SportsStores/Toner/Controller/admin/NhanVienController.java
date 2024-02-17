package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.NhanVien;
import com.project.SportsStores.Toner.Service.ChucVuService;
import com.project.SportsStores.Toner.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/admin/staff")
//@RestController
public class NhanVienController {
    @Autowired
    NhanVienService service;
    @Autowired
    ChucVuService chucVuService;

//    @GetMapping()
//    public String getAll(Model model) {
//        model.addAttribute("list", service.getAll());
//        return "admin/staff/staff-list";
//    }

    @GetMapping()
    public String getPage(Model model, @RequestParam(value = "number", defaultValue = "1") int number) {
        Pageable pageable = PageRequest.of(number - 1, 5);
        service.page(pageable);
        model.addAttribute("number", number);
        model.addAttribute("totalPages", service.page(pageable).getTotalPages());
        model.addAttribute("totalElements", service.page(pageable).getTotalElements());
        model.addAttribute("list", service.page(pageable).getContent());
        return "admin/staff/staff-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("chucVu", chucVuService.getAll());
        return "admin/staff/staff-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("nhanVien") NhanVien nhanVien) {
        Random rand = new Random();
        int ranNum = rand.nextInt(100) + 1;
        nhanVien.setMaNV("NV" + ranNum);
        nhanVien.setNgayTao(LocalDateTime.now());
        service.save(nhanVien);
        return "redirect:/admin/staff/add";
    }
//    @GetMapping("/detail/{id}")
//    public String detail(Model model,@PathVariable(value = "id") int id) {
//        model.addAttribute("nhanVien", new NhanVien());
//        return "admin/staff/staff-add";
//    }
//    @PostMapping("/update/{id}")
//    public String update(@PathVariable(value = "id") int id,@ModelAttribute("nhanVien") NhanVien nhanVien) {
//        nhanVien.setNgayTao(LocalDateTime.now());
//        service.save(nhanVien);
//        return "redirect:admin/staff/add";
//    }
}
