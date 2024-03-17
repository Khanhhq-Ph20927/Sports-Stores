package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.KhuyenMai;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Service.SanPhamChiTietService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Repository.NhanVienRepository;
import com.project.SportsStores.Toner.Service.Impl.DonHangTQServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/admin/sell-off")
public class BanHangTaiQuayController {

    @Autowired
    private DonHangTQServiceImpl dhsv;

    @Autowired
    private NhanVienRepository nvrp;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String sellOff() {
        return "admin/sell-off";
    }

    @RequestMapping(value = "/ui-old", method = RequestMethod.GET)
    public String satc() {
        return "admin/satc";
    }

    @RequestMapping(value = "/save_invoice", method = RequestMethod.GET)
    private ResponseEntity<?> add() {
        DonHang dh = new DonHang();
        List<DonHang> list = dhsv.getAllByStatus();
        for (DonHang donHang : list) {
//            // Tạo một UUID ngẫu nhiên
//            UUID uuid = UUID.randomUUID();
//
//            // Lấy giá trị của UUID dưới dạng số hexa không có dấu gạch nối
//            String uuidString = uuid.toString().replace("-", "");
//
//            // Lấy 12 ký tự đầu tiên của chuỗi UUID
//            String shortUUID = uuidString.substring(0, 12);
//
//            dh.setMaDonHang(shortUUID);
//            if (dh.getMaDonHang().equalsIgnoreCase(donHang.getMaDonHang())) {
//                UUID uuidAgain = UUID.randomUUID();
//                String uuidStringAgain = uuidAgain.toString().replace("-", "");
//                String shortUUIDAgain = uuidStringAgain.substring(0, 12);
//                dh.setMaDonHang(shortUUIDAgain);
//            }

             //lấy index của phần tử cuối cùng trong list
            int index = Integer.parseInt(list.get(list.size() - 1).getMaDonHang().substring(2));
            dh.setMaDonHang("DH" + (index + 1));
            if (dh.getMaDonHang().equalsIgnoreCase(donHang.getMaDonHang())) {
                dh.setMaDonHang("DH" + (index + 1));
            }
        }
        // fix cứng id nên cần kiểm tra data trước khi chạy
        dh.setNv(nvrp.getById(Long.valueOf(4)));
        dh.setNgayTao(LocalDateTime.now());
        dh.setTrangThai(0);
        if (dhsv.getSizeBySatus0().size() >= 20) {
            return new ResponseEntity<>("fail", HttpStatus.OK);
        } else {
            dhsv.save(dh);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
    }
}
