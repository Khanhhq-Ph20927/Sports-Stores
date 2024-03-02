package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.NhanVien;
import com.project.SportsStores.Toner.Service.ChucVuService;
import com.project.SportsStores.Toner.Service.NhanVienService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
        boolean isValid = false;
        model.addAttribute("chucVu", chucVuService.getAll());
        for (NhanVien nv : service.getAll()) {
            nhanVien.setMaNV("NV" + service.getAll().size());
            if (nhanVien.getMaNV().equalsIgnoreCase(nv.getMaNV())) {
                nhanVien.setMaNV("NV" + (service.getAll().size() + 1));
            }
            if (nhanVien.getSdt().equalsIgnoreCase(nv.getSdt())) {
                isValid = true;
                model.addAttribute("errorPhone", "Số điện thoại trùng !");
            }
            if (nhanVien.getEmail().equalsIgnoreCase(nv.getEmail())) {
                isValid = true;
                model.addAttribute("errorEmail", "Email trùng !");
            }
        }
        nhanVien.setMatKhau("12345");
        nhanVien.setNgayTao(LocalDateTime.now());
        if (nhanVien.getHoTen().isEmpty()) {
            isValid = true;
            model.addAttribute("errorName", "Họ tên trống !");
        } else if (!nhanVien.getHoTen().matches("^([AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+)((\\s{1}[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+){1,})$")) {
            isValid = true;
            model.addAttribute("errorName", "Họ tên không đúng định dạng !");
        }
        if (nhanVien.getSdt().isEmpty()) {
            isValid = true;
            model.addAttribute("errorPhone", "Số điện thoại trống !");
        } else if (!nhanVien.getSdt().matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b")) {
            isValid = true;
            model.addAttribute("errorPhone", "Số điện thoại không đúng định dạng !");
        }
        if (nhanVien.getEmail().isEmpty()) {
            isValid = true;
            model.addAttribute("errorEmail", "Email trống !");

        } else if (!nhanVien.getEmail().matches("^(([a-zA-Z0-9\\._-]+)@([a-zA-Z0-9-]+)\\.([a-zA-Z]{2,4})(\\.([a-zA-Z]{2,4})|))$")) {
            isValid = true;
            model.addAttribute("errorEmail", "Email không đúng định dạng !");
        }
        if (nhanVien.getNgaySinh()==null) {
            isValid = true;
            model.addAttribute("errorBirthday", "Ngày sinh trống !");
        }
//        else if () {
//            isValid = true;
//            model.addAttribute("errorBirthday", "Ngày sinh trống");
//        }
        if (nhanVien.getCv() == null) {
            isValid = true;
            model.addAttribute("errorPosition", "Chưa chọn chức vụ !");
        }
        if (isValid) {
            service.save(nhanVien);
            redirectAttributes.addFlashAttribute("message", true);
            return "redirect:/admin/staff/add";
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
    public String update(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes
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
        return "redirect:/admin/staff";
//        return "admin/staff/staff-list";
    }

}
