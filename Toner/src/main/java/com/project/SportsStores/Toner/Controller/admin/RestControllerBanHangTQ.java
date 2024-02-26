package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.DTO.GioHangChiTietDTO;
import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Model.DonHangChiTiet;
import com.project.SportsStores.Toner.Model.GioHangChiTiet;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/sell-off")
public class RestControllerBanHangTQ {

    @Autowired
    private DonHangTQServiceImpl dhsv;

    @Autowired
    private DonHangChiTietServiceImpl dhctsv;

    @Autowired
    private DonHangServiceImpl donHangService;

    @Autowired
    private SanPhamChiTietServiceImpl spsv;

    @Autowired
    private GioHangServiceImpl ghsv;

    @Autowired
    private GioHangChiTietServiceImpl ghctsv;

    @RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
    private ResponseEntity<?> paginationLoad(@PathVariable("pageNumber") int pageNumber
    ) {
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("ngayTao").descending());
        Page<DonHang> page = dhsv.pageOfDonHang(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


    @RequestMapping(value = "/page/search/{pageNumber}/{keyWord}", method = RequestMethod.GET)
    private ResponseEntity<?> pagination(@PathVariable("pageNumber") int pageNumber,
                                         @PathVariable("keyWord") String keyWord) {
        Page<DonHang> page;
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("ngayTao").descending());
        if (keyWord.equalsIgnoreCase("null")) {
            page = dhsv.pageOfDonHang(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            page = dhsv.seacrhSellOff(keyWord, pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        dhsv.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cart/{pageNumber}")
    public ResponseEntity<?> getProductInCard(@PathVariable("pageNumber") int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("ngaySua").descending());
        Page<GioHangChiTiet> page = ghctsv.getByIdGH("1", pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping("/cart/save")
    public ResponseEntity<?> saveCart(@RequestBody GioHangChiTietDTO ghct) {
        System.out.println(ghct.toString());
        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
        gioHangChiTiet.setGh(ghsv.getById(ghct.getIdGioHang()));
        gioHangChiTiet.setSpct(spsv.getById(ghct.getIdSanPhamChiTiet()));
        gioHangChiTiet.setSoLuong(1);
        gioHangChiTiet.setNgaySua(LocalDateTime.now());
        ghctsv.save(gioHangChiTiet);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/cart/delete/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") String id) {
        ghctsv.delete(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/cart/add-quantity/{id}")
    public ResponseEntity<?> addQuantity(@PathVariable("id") String id) {
        GioHangChiTiet ghct = ghctsv.getById(id);
        SanPhamChiTiet spct = spsv.getById(String.valueOf(ghct.getSpct().getId()));

        if (ghct.getSoLuong() >= spct.getSoLuong()) {
            System.out.println("quantity of products in cart not more than quantity of products in stock");
            return new ResponseEntity<>("failure", HttpStatus.OK);
        } else {
            ghct.setSoLuong(ghct.getSoLuong() + 1);
            ghctsv.save(ghct);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
    }

    @GetMapping("/cart/minus-quantity/{id}")
    public ResponseEntity<?> minusQuantity(@PathVariable("id") String id) {
        GioHangChiTiet ghct = ghctsv.getById(id);

        if (ghct.getSoLuong() <= 0) {
            System.out.println("quantity of products in cart not less than 0");
            ghctsv.delete(id);
        } else {
            ghct.setSoLuong(ghct.getSoLuong() - 1);
            ghctsv.save(ghct);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/invoice/save/{id}")
    public ResponseEntity<?> saveInvoiceDetail(@PathVariable("id") String id) {
        System.out.println(id);
        List<GioHangChiTiet> listGioHangCT = ghctsv.getByIdGHList("1");
        int tongTien = 0;
        if (donHangService.findById(Long.parseLong(id)).isPresent()) {
            for (GioHangChiTiet ghct : listGioHangCT
            ) {
                //Thêm Hoá Đơn Chi Tiết
                DonHangChiTiet dhct = new DonHangChiTiet();
                dhct.setDh(donHangService.findById(Long.parseLong(id)).get());
                dhct.setSoLuong(ghct.getSoLuong());
                dhct.setNgayTao(LocalDateTime.now());
                dhct.setGiaGoc(ghct.getSpct().getSp().getDonGia());
                dhct.setGiaThoiDiemMua(ghct.getSpct().getSp().getDonGia());
                tongTien = ghct.getSpct().getSp().getDonGia().intValue() * dhct.getSoLuong();
                dhct.setTongTien(BigDecimal.valueOf(tongTien));
                dhct.setSpct(ghct.getSpct());

                //Cập nhật số lượng sản phẩm
                SanPhamChiTiet spct = ghct.getSpct();
                spct.setSoLuong(spct.getSoLuong() - ghct.getSoLuong());
                spsv.save(spct);

                dhctsv.save(dhct);
            }
            ghctsv.deleteAll();
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("failure", HttpStatus.OK);
    }

    @GetMapping("/invoice/list-invoice/{id}")
    public ResponseEntity<?> getInvoiceDetail(@PathVariable("id") String id) {
        List<DonHangChiTiet> listGioHangCT = dhctsv.findByIdHD(id);
        return new ResponseEntity<>(listGioHangCT, HttpStatus.OK);
    }

    @GetMapping("/invoice/change-status/{id}")
    public ResponseEntity<?> changeStatusInvoice(@PathVariable("id") String id) {
        DonHang dh = donHangService.findById(Long.parseLong(id)).get();
        dh.setTrangThai(1);
        donHangService.save(dh);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/product-detail/{id}")
    public ResponseEntity<?> getProductDetailById(@PathVariable("id") String id) {
        return new ResponseEntity<>(spsv.getById(id), HttpStatus.OK);
    }

    @GetMapping("/product-detail/search/{id}")
    public ResponseEntity<?> searchProductDetail(@PathVariable("id") String id) {
        Pageable pageable = PageRequest.of(0, 5,Sort.by("ngayTao").descending());
        Page<SanPhamChiTiet> page = spsv.pagination(pageable);
        while (true) {
            // Duyệt qua từng sản phẩm trong trang hiện tại
            for (SanPhamChiTiet product : page.getContent()) {
                if (product.getId().equals(Long.parseLong(id))) {
                    // Sản phẩm được tìm thấy trong trang hiện tại
                    int pageNumber = page.getNumber(); // Số trang
                    System.out.println("Product found on page: " + pageNumber);
                    return new ResponseEntity<>(pageNumber, HttpStatus.OK);
                }
            }

            // Kiểm tra xem còn trang nào nữa không
            if (page.hasNext()) {
                page = spsv.pagination(page.nextPageable());
            } else {
                // Đã duyệt qua tất cả các trang mà không tìm thấy sản phẩm
                System.out.println("Product not found!");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        }
    }
}
