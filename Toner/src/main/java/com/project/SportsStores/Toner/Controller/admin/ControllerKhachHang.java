package com.project.SportsStores.Toner.Controller.admin;


import com.project.SportsStores.Toner.Model.DiaChi;
import com.project.SportsStores.Toner.Model.KhachHang;
import com.project.SportsStores.Toner.Repository.DiaChiRepository;
import com.project.SportsStores.Toner.Service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/customer")
public class ControllerKhachHang {
    @Autowired
    private KhachHangService khsv;

    @Autowired
    private DiaChiRepository dcrp;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String show() {
        return "admin/customer/customer-list";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> detailInvoice(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(khsv.getByID(Long.valueOf(id)));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    private String update(@ModelAttribute("khachhang") KhachHang kh, @PathVariable("id") String id) {
        kh.setTrangThai(kh.getTrangThai());
        khsv.update(kh);
        return "redirect:/admin/customer";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "admin/customer/customer-create";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String add(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("khachHang") KhachHang khachHang,
                      @RequestParam(value = "tinh") String tinh,
                      @RequestParam(value = "quan") String quan,
                      @RequestParam(value = "xa") String xa,
                      @RequestParam(value = "diaChiCT") String diaChiCT
    ) {

        boolean isValid = false;
        for (KhachHang kh : khsv.getALL()) {
            khachHang.setMaKH("KH" + khsv.getALL().size());
            if (khachHang.getMaKH().equalsIgnoreCase(kh.getMaKH())) {
                khachHang.setMaKH("NV" + (khsv.getALL().size() + 1));
            }
            if (khachHang.getSdt().equalsIgnoreCase(kh.getSdt())) {
                isValid = true;
                model.addAttribute("errorPhone", "Số điện thoại trùng !");
            }
            if (khachHang.getEmail().equalsIgnoreCase(kh.getEmail())) {
                isValid = true;
                model.addAttribute("errorEmail", "Email trùng !");
            }
        }
        khachHang.setTrangThai(0);
        khachHang.setMatKhau("12345");
        khachHang.setLoaiKhachHang(0);
        khachHang.setNgayTao(LocalDateTime.now());
        khachHang.setMatKhauMaHoa(passwordEncoder.encode(khachHang.getMatKhau()));
        DiaChi diaChi = new DiaChi();
        diaChi.setKh(khachHang);
        diaChi.setNgayTao(LocalDateTime.now());
        diaChi.setTtp(tinh);
        diaChi.setQh(quan);
        diaChi.setXp(xa);
        diaChi.setDiaChiCuThe(diaChiCT);
        if (khachHang.getHoTen().isBlank()) {
            isValid = true;
            model.addAttribute("errorName", "Please Choose Name");
        }
        if (khachHang.getSdt().isBlank()) {
            isValid = true;
            model.addAttribute("errorPhone", "Please Choose Phone Number");
        } else if (!khachHang.getSdt().matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b")) {
            isValid = true;
            model.addAttribute("errorPhone", "Please Regex Phone Number");
        }
        if (khachHang.getEmail().isBlank()) {
            isValid = true;
            model.addAttribute("errorEmail", "Please Choose Email");
        } else if (!khachHang.getEmail().matches(".+@.+\\.+.+")) {
            isValid = true;
            model.addAttribute("errorEmail", "Please Regex Email");
        }
        if (khachHang.getNgaySinh() == null) {
            isValid = true;
            model.addAttribute("errorBirthday", "Please Choose  Date ");
        } else if (!khachHang.getNgaySinh().toLocalDate().isBefore(LocalDate.now())) {
            isValid = true;
            model.addAttribute("errorBirthday", "Please Choose  Date ");
        }
        if (diaChi.getDiaChiCuThe().isBlank()) {
            isValid = true;
            model.addAttribute("errorAddress", "Please Choose  Address");
        }
        if (diaChi.getTtp().isBlank()) {
            isValid = true;
            model.addAttribute("errorProvince", "Chưa chọn Tỉnh !");
        }
        if (diaChi.getQh().isBlank()) {
            isValid = true;
            model.addAttribute("errorDistrict", "Chưa chọn Quận Huyện !");
        }
        if (diaChi.getXp().isBlank()) {
            isValid = true;
            model.addAttribute("errorWard", "Chưa chọn Xã Phường !");
        }
        if (!isValid) {
            khsv.save(khachHang);
            dcrp.save(diaChi);
            redirectAttributes.addFlashAttribute("message", true);
            return "redirect:/admin/customer";
        } else {
            model.addAttribute("message", false);
            return "admin/customer/customer-create";
        }
    }
}
