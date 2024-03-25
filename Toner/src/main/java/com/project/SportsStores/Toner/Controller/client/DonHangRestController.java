package com.project.SportsStores.Toner.Controller.client;

import com.project.SportsStores.Toner.Model.*;
import com.project.SportsStores.Toner.Model.DTO.GioHangChiTietDTO;
import com.project.SportsStores.Toner.Model.DTO.SPDTO;
import com.project.SportsStores.Toner.Service.*;
import com.project.SportsStores.Toner.Service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client/order/")
public class DonHangRestController {

    @Autowired
    private KhachHangServiceImpl khachHangService;

    @Autowired
    private NhanVienServiceImpl nhanVienService;

    @Autowired
    private PhuongThucTTServiceImpl ptttService;

    @Autowired
    private SanPhamChiTietServiceImpl spService;

    @Autowired
    private DonHangService donHangService;

    @Autowired
    private DonHangChiTietService donHangCTService;

    @Autowired
    private DiaChiService diaChiService;

    @Autowired
    private TTVCService ttvcService;

    @Autowired
    private GioHangChiTietService ghService;

    // đơn hàng được xác nhận -> chỉnh sửa số lượng sản phẩm
    @PostMapping("/invoice/save/{id}/{idAddress}")
    private ResponseEntity<?> saveInvoice(@RequestBody List<GioHangChiTietDTO> list,
                                          @PathVariable("id") String id,
                                          @PathVariable("idAddress") String idAddress) {

        ThongTinVanChuyen ttvc = new ThongTinVanChuyen();
        DiaChi diaChi = diaChiService.getById(idAddress);
        ttvc.setTtp(diaChi.getTtp());
        ttvc.setQh(diaChi.getQh());
        ttvc.setXp(diaChi.getXp());
        ttvc.setDiaChiCuThe(diaChi.getDiaChiCuThe());
        ttvc.setSdtNguoiNhan(diaChi.getSdt());
        ttvc.setTenNguoiNhan(diaChi.getKh().getHoTen());
        ttvcService.save(ttvc);

        // tạo đơn hàng
        DonHang dh = new DonHang();
        dh.setTtvc(ttvc);
        List<DonHang> listAll = donHangService.getAll();
        List<Integer> listId = new ArrayList<>();
        for (DonHang donHang : listAll) {
            int index = Integer.parseInt(donHang.getMaDonHang().substring(2));
            listId.add(index);
        }
        Optional<Integer> maxNumber = listId.stream().max(Integer::compareTo);
        maxNumber.ifPresent(integer -> dh.setMaDonHang("DH" + (integer + 1)));
        dh.setKh(khachHangService.getByID(Long.parseLong(id)));
        dh.setPttt(ptttService.getById("1"));
        dh.setTrangThai(1);
        dh.setNgayTao(LocalDateTime.now());
        donHangService.save(dh);

        DonHang donHangNew = donHangService.findByDH(dh.getMaDonHang());

        // tạo hoá đơn chi tiết
        for (GioHangChiTietDTO ghct : list) {
            DonHangChiTiet dhct = new DonHangChiTiet();
            dhct.setSpct(spService.getById(String.valueOf(ghct.getIdSanPhamChiTiet())));
            dhct.setSoLuong(Integer.parseInt(ghct.getSoLuong()));
            dhct.setDh(donHangNew);
            dhct.setNgayTao(LocalDateTime.now());
            dhct.setGiaGoc(spService.getById(String.valueOf(ghct.getIdSanPhamChiTiet())).getSp().getDonGia());
            dhct.setGiaThoiDiemMua(spService.getById(String.valueOf(ghct.getIdSanPhamChiTiet())).getSp().getDonGia());
            BigDecimal tongTien = BigDecimal.valueOf(Integer.parseInt(ghct.getSoLuong()))
                    .multiply(spService.getById(String.valueOf(ghct.getIdSanPhamChiTiet())).getSp().getDonGia());
            dhct.setTongTien(tongTien);
            donHangCTService.save(dhct);
            for (GioHangChiTiet gioHangChiTiet : ghService.getByIdGHList(ghct.getIdGioHang())) {
                if (Long.parseLong(ghct.getIdSanPhamChiTiet()) == gioHangChiTiet.getSpct().getId()) {
                    ghService.delete(String.valueOf(gioHangChiTiet.getId()));
                    break;
                }
            }

        }

        BigDecimal tongGiaTri = new BigDecimal("0");
        for (DonHangChiTiet donHangChiTiet :
                donHangCTService.findByIdHD(String.valueOf(donHangNew.getId()))
        ) {
            tongGiaTri = tongGiaTri.add(donHangChiTiet.getTongTien());
        }

        donHangNew.setTongTien(tongGiaTri);
        donHangService.save(donHangNew);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/invoice/buy-now/{id}/{idAddress}")
    private ResponseEntity<?> saveInvoiceBuyNow(@RequestBody SPDTO dtoSP,
                                                @PathVariable("id") String id,
                                                @PathVariable("idAddress") String idAddress) {

        ThongTinVanChuyen ttvc = new ThongTinVanChuyen();
        DiaChi diaChi = diaChiService.getById(idAddress);
        ttvc.setTtp(diaChi.getTtp());
        ttvc.setQh(diaChi.getQh());
        ttvc.setXp(diaChi.getXp());
        ttvc.setDiaChiCuThe(diaChi.getDiaChiCuThe());
        ttvc.setSdtNguoiNhan(diaChi.getSdt());
        ttvc.setTenNguoiNhan(diaChi.getKh().getHoTen());
        ttvcService.save(ttvc);

        // tạo đơn hàng
        DonHang dh = new DonHang();
        dh.setTtvc(ttvc);
        List<DonHang> listAll = donHangService.getAll();
        List<Integer> listId = new ArrayList<>();
        for (DonHang donHang : listAll) {
            int index = Integer.parseInt(donHang.getMaDonHang().substring(2));
            listId.add(index);
        }
        Optional<Integer> maxNumber = listId.stream().max(Integer::compareTo);
        maxNumber.ifPresent(integer -> dh.setMaDonHang("DH" + (integer + 1)));
        dh.setKh(khachHangService.getByID(Long.parseLong(id)));
        dh.setPttt(ptttService.getById("1"));
        dh.setTrangThai(1);
        dh.setNgayTao(LocalDateTime.now());
        donHangService.save(dh);

        DonHang donHangNew = donHangService.findByDH(dh.getMaDonHang());

        //tạo hoá đơn chi tiết
        DonHangChiTiet dhct = new DonHangChiTiet();
        BigDecimal donGia = new BigDecimal(dtoSP.getDonGia());
        dhct.setSpct(spService.getById(String.valueOf(dtoSP.getId())));
        dhct.setSoLuong(dtoSP.getSoLuong());
        dhct.setDh(donHangNew);
        dhct.setNgayTao(LocalDateTime.now());
        dhct.setGiaGoc(donGia);
        dhct.setGiaThoiDiemMua(donGia);
        BigDecimal tongTien = BigDecimal.valueOf(dtoSP.getSoLuong())
                .multiply(donGia);
        dhct.setTongTien(tongTien);
        donHangCTService.save(dhct);
        donHangNew.setTongTien(tongTien);
        donHangService.save(donHangNew);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/invoice/change-status/{id}")
    private ResponseEntity<?> confirmOrder(@PathVariable("id") String id,
                                           @RequestParam("status") int status,
                                           @RequestParam("idStaff") Long idStaff) {
        DonHang dh = donHangService.findById(Long.parseLong(id)).get();
        if (status == dh.getTrangThai()) {
            return new ResponseEntity<>("failure", HttpStatus.OK);
        }
        if (status == 2) {
            List<DonHangChiTiet> list = donHangCTService.findByIdHD(String.valueOf(dh.getId()));
            for (DonHangChiTiet dhct : list
            ) {
                for (SanPhamChiTiet spct : spService.getAll()) {
                    if (spct.getId() == dhct.getSpct().getId()) {
                        spct.setSoLuong(spct.getSoLuong() - dhct.getSoLuong());
                        spService.save(spct);
                        break;
                    }
                }
            }
            dh.setNv(nhanVienService.findById(idStaff).get());
        }
        dh.setTrangThai(status);
        donHangService.save(dh);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
