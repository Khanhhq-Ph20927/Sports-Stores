package com.project.SportsStores.Toner.Controller.client;

import com.project.SportsStores.Toner.Model.DTO.GioHangChiTietDTO;
import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Model.SanPham;
import com.project.SportsStores.Toner.Service.DonHangService;
import com.project.SportsStores.Toner.Service.Impl.KhachHangServiceImpl;
import com.project.SportsStores.Toner.Service.Impl.PhuongThucTTServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client/invoice")
public class DonHangRestController {

    private final HttpSession httpSession;

    public DonHangRestController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Autowired
    private KhachHangServiceImpl khachHangService;

    @Autowired
    private PhuongThucTTServiceImpl ptttService;

    @Autowired
    private DonHangService donHangService;

    @PostMapping("/save")
    private ResponseEntity<?> saveInvoice(@RequestBody List<GioHangChiTietDTO> list){
        // tạo đơn hàng
        DonHang dh = new DonHang();
        String idUser = (String) httpSession.getAttribute("idUer");
        List<DonHang> listAll = donHangService.getAll();
        List<Integer> listId = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int index = Integer.parseInt(listAll.get(i).getMaDonHang().substring(2));
            listId.add(index);
        }
        Optional<Integer> maxNumber = listId.stream().max(Integer::compareTo);
        maxNumber.ifPresent(integer -> dh.setMaDonHang("DH" + integer + 1));
        dh.setKh(khachHangService.getByID(Long.parseLong(idUser)));
        dh.setPttt(ptttService.getById("1"));
        dh.setTrangThai(0);
        dh.setNgayTao(LocalDateTime.now());
        donHangService.save(dh);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
