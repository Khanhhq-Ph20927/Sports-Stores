package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Repository.NhanVienRepository;
import com.project.SportsStores.Toner.Service.Impl.DonHangTQServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BanHangTaiQuayController {
    @Autowired
    private DonHangTQServiceImpl dhsv;

    @Autowired
    private NhanVienRepository nvrp;

    @RequestMapping(value = "/sell-off", method = RequestMethod.GET)
    public String satc() {
        return "admin/satc";
    }


    @RequestMapping(value = "/save_invoice", method = RequestMethod.GET)
    private String add(@ModelAttribute("donhang") DonHang dh,
                       Model model) {
        List<DonHang> list = dhsv.getAllByStatus();
        for (DonHang donHang : list) {
            dh.setMaDonHang("DH" + (list.size() + 1));
            if (dh.getMaDonHang().equalsIgnoreCase(donHang.getMaDonHang())) {
                dh.setMaDonHang("DH" + (list.size() + 1));
            }
        }
        // fix cứng id nên cần kiểm tra data trước khi chạy
        dh.setNv(nvrp.getById(Long.valueOf(4)));
        dh.setNgayTao(LocalDateTime.now());
        dh.setTrangThai(0);
        if (list.size() >= 20) {
            return "redirect:/admin/sell-off";
        } else {
            model.addAttribute("message", true);
            dhsv.save(dh);
            return "redirect:/admin/sell-off";
        }
    }
}



