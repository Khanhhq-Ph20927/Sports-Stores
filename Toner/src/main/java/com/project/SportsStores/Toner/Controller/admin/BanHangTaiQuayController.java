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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Repository.NhanVienRepository;
import com.project.SportsStores.Toner.Service.Impl.DonHangTQServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/admin/sell-off")
public class BanHangTaiQuayController {

    @Autowired
    private DonHangTQServiceImpl dhsv;

    @Autowired
    private NhanVienRepository nvrp;

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String satc() {
        return "admin/satc";
    }

    @RequestMapping(value = "/save_invoice", method = RequestMethod.GET)
    private ResponseEntity<?> add() {
        DonHang dh = new DonHang();
        List<DonHang> list = dhsv.getAllByStatus();
        for (DonHang donHang : list) {
            // lấy index của phần tử cuối cùng trong list
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

    @GetMapping("/san-pham-chi-tiet")
    public ResponseEntity<?> findAll(@RequestParam(value = "search", required = false) String search,
                                     Pageable pageable) {
        Page<SanPhamChiTiet> result = sanPhamChiTietService.sanPhamChiTietBanTaiQuay(search, pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
