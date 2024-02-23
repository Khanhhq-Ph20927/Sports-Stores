package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.NhanVien;
import com.project.SportsStores.Toner.Service.ChucVuService;
import com.project.SportsStores.Toner.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/staff")
//@RestController
public class NhanVienController {
    @Autowired
    NhanVienService service;
    @Autowired
    ChucVuService chucVuService;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("list", service.getAll());
        model.addAttribute("chucVu", chucVuService.getAll());
        return "admin/staff/staff-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("chucVu", chucVuService.getAll());
        return "admin/staff/staff-add";
    }

    @PostMapping("/add")
    public String add(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("nhanVien") NhanVien nhanVien) {
        model.addAttribute("chucVu", chucVuService.getAll());
        for (NhanVien nv : service.getAll()) {
            nhanVien.setMaNV("NV" + service.getAll().size());
            if (nhanVien.getMaNV().equalsIgnoreCase(nv.getMaNV())) {
                nhanVien.setMaNV("NV" + (service.getAll().size() + 1));
            }
        }
        nhanVien.setMatKhau("12345");
        nhanVien.setNgayTao(LocalDateTime.now());
        boolean isValid = false;
        if (nhanVien.getHoTen().isEmpty()) {
            isValid = true;
            model.addAttribute("errorName", "Please Choose Name");
        }
        if (nhanVien.getSdt().isEmpty()) {
            isValid = true;
            model.addAttribute("errorPhone", "Please Choose Phone Number");
        } else if (!nhanVien.getSdt().matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b")) {
            isValid = true;
            model.addAttribute("errorPhone", "Please Regex Phone Number");
        }
        if (nhanVien.getEmail().isEmpty()) {
            isValid = true;
            model.addAttribute("errorEmail", "Please Choose Email");
        } else if (!nhanVien.getEmail().matches(".+@.+\\.+.+")) {
            isValid = true;
            model.addAttribute("errorEmail", "Please Regex Email");
        }
        if (nhanVien.getNgaySinh() == null) {
            isValid = true;
            model.addAttribute("errorBirthday", "Please Choose  Phone Number");
        }
        if (nhanVien.getCv().getId() == 0) {
            isValid = true;
            model.addAttribute("errorPosition", "Please Choose  Phone Number");
        }
        if (isValid==false) {
            service.save(nhanVien);
            redirectAttributes.addFlashAttribute("message", true);
            return "redirect:/api/admin/staff/add";
//            return "admin/staff/staff-add";
        } else {
            model.addAttribute("message", false);
            return "admin/staff/staff-add";
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") Long id,RedirectAttributes redirectAttributes
            , @ModelAttribute("nhanVien") NhanVien nhanVien) {
        NhanVien updateNV = service.findById(id).get();
        updateNV.setHoTen(nhanVien.getHoTen());
        updateNV.setSdt(nhanVien.getSdt());
        updateNV.setNgaySinh(nhanVien.getNgaySinh());
        updateNV.setTrangThai(nhanVien.getTrangThai());
        updateNV.setEmail(nhanVien.getEmail());
        updateNV.setCv(nhanVien.getCv());
//        updateNV.setAnhNhanVien(nhanVien.getAnhNhanVien());
        service.save(nhanVien);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/api/admin/staff";
//        return "admin/staff/staff-list";
    }

}