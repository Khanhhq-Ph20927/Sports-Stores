package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.*;
import com.project.SportsStores.Toner.Model.CustomModel.ResponseCustom;
import com.project.SportsStores.Toner.Model.DTO.SanPhamChiTietDTO;
import com.project.SportsStores.Toner.Service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private GioHangChiTietServiceImpl ghctsv;

    @Autowired
    private SanPhamChiTietServiceImpl sanPhamChiTietService;

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
        List<DonHangChiTiet> listInvoiceDetailDelete = dhctsv.findByIdHD(String.valueOf(id));
        for (DonHangChiTiet invoice : listInvoiceDetailDelete
        ) {
            SanPhamChiTiet productDetail = sanPhamChiTietService.getById(String.valueOf(invoice.getSpct().getId()));
            productDetail.setSoLuong(productDetail.getSoLuong() + invoice.getSoLuong());
            sanPhamChiTietService.save(productDetail);
            dhctsv.deleteById(String.valueOf(invoice.getId()));
        }
        dhsv.delete(id);
        if (dhsv.existById(String.valueOf(id))) {
            return new ResponseEntity<>("failure", HttpStatus.OK);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/cart/{pageNumber}")
    public ResponseEntity<?> getProductInCard(@PathVariable("pageNumber") int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("ngaySua").descending());
        Page<GioHangChiTiet> page = ghctsv.getByIdGH("1", pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping("/cart/save/{idInvoice}")
    public ResponseEntity<?> saveCart(@PathVariable("idInvoice") String idInvoice,
                                      @Param("idProductDetail") String idProductDetail) {
        List<DonHangChiTiet> listInvoiceDetailForIdInvoice = dhctsv.findByIdHD(idInvoice);
        SanPhamChiTiet productDetail = sanPhamChiTietService.getById(idProductDetail);
        if (productDetail.getSoLuong() == 1) {
            return new ResponseEntity<>("failure-1", HttpStatus.OK);
        }
        for (DonHangChiTiet invoice : listInvoiceDetailForIdInvoice
        ) {
            if (invoice.getSpct().getId().equals(productDetail.getId())) {
                return new ResponseEntity<>("failure-2", HttpStatus.OK);
            }
        }
        if (donHangService.findById(Long.parseLong(idInvoice)).isPresent()) {
            DonHang invoice = donHangService.findById(Long.parseLong(idInvoice)).get();
            DonHangChiTiet invoiceDetail = new DonHangChiTiet();
            BigDecimal price = productDetail.getSp().getDonGia();
            invoiceDetail.setDh(invoice);
            invoiceDetail.setSpct(productDetail);
            invoiceDetail.setSoLuong(1);
            invoiceDetail.setGiaGoc(price);
            invoiceDetail.setGiaThoiDiemMua(price);
            invoiceDetail.setTongTien(price);
            invoiceDetail.setNgayTao(LocalDateTime.now());
            productDetail.setSoLuong(productDetail.getSoLuong() - 1);
            sanPhamChiTietService.save(productDetail);
            dhctsv.save(invoiceDetail);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("failure-3", HttpStatus.OK);
    }

    @GetMapping("/cart/delete/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") String id) {
        DonHangChiTiet dhct = dhctsv.getById2(id);
        SanPhamChiTiet spct = sanPhamChiTietService.getById(String.valueOf(dhct.getSpct().getId()));
        spct.setSoLuong(spct.getSoLuong() + dhct.getSoLuong());
        sanPhamChiTietService.save(spct);
        dhctsv.deleteById(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/cart/add-quantity/{id}")
    public ResponseEntity<?> addQuantity(@PathVariable("id") String id) {

        DonHangChiTiet dhct = dhctsv.getById2(id);
        SanPhamChiTiet spct = spsv.getById(String.valueOf(dhct.getSpct().getId()));

        if (dhct.getSoLuong() >= spct.getSoLuong() - 1) {
            System.out.println("quantity of product is zero");
            return new ResponseEntity<>(spct.getSoLuong(), HttpStatus.OK);
        } else {
            dhct.setSoLuong(dhct.getSoLuong() + 1);
            spct.setSoLuong(spct.getSoLuong() - 1);
            sanPhamChiTietService.save(spct);
            dhctsv.save(dhct);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
    }


    @GetMapping("/cart/edit-quantity/{id}")
    public ResponseEntity<?> editQuantity(@PathVariable("id") String id,
                                          @RequestParam("quantity") int quantity) {

        DonHangChiTiet dhct = dhctsv.getById2(id);

        SanPhamChiTiet spct = spsv.getById(String.valueOf(dhct.getSpct().getId()));
        ResponseCustom response = new ResponseCustom();

        if (quantity < 1) {
            response.setStatusText("failure");
            response.setMessage("Hãy nhập số lượng(lớn hơn 0)!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (quantity >= (spct.getSoLuong() + dhct.getSoLuong())) {
            response.setStatusText("failure");
            response.setMessage("Số lượng sản phẩm chỉ còn lại " + spct.getSoLuong() + "!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            if (quantity > dhct.getSoLuong()) {
                spct.setSoLuong(spct.getSoLuong() - (quantity - dhct.getSoLuong()));
            }
            if (quantity < dhct.getSoLuong()) {
                spct.setSoLuong(spct.getSoLuong() + (dhct.getSoLuong() - quantity));
            }
            dhct.setSoLuong(quantity);
            dhctsv.save(dhct);
            response.setStatusText("success");
            response.setMessage("Sửa thành công!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/cart/minus-quantity/{id}")
    public ResponseEntity<?> minusQuantity(@PathVariable("id") String id) {

        DonHangChiTiet dhct = dhctsv.getById2(id);
        SanPhamChiTiet spct = sanPhamChiTietService.getById(String.valueOf(dhct.getSpct()));
        if (dhct.getSoLuong() == 1) {
            System.out.println("quantity of products in cart not less than 0");
            spct.setSoLuong(spct.getSoLuong() + 1);
            sanPhamChiTietService.save(spct);
            dhctsv.deleteById(id);
        } else {
            spct.setSoLuong(spct.getSoLuong() + 1);
            sanPhamChiTietService.save(spct);
            dhct.setSoLuong(dhct.getSoLuong() - 1);
            dhctsv.save(dhct);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/invoice/save/{id}")
    public ResponseEntity<?> saveInvoiceDetail(@RequestBody List<String> listProductDetail,
                                               @PathVariable("id") String id) {
        int tongTien = 0;
        if (donHangService.findById(Long.parseLong(id)).isPresent()) {
            for (String idProductDetail : listProductDetail
            ) {
                //Lấy giá của sản phẩm
                SanPhamChiTiet spct = sanPhamChiTietService.getById(id);
                BigDecimal donGia = spct.getSp().getDonGia();
                //Thêm Đơn Hàng Chi Tiết
                DonHangChiTiet dhct = new DonHangChiTiet();
                dhct.setDh(donHangService.findById(Long.parseLong(id)).get());
                dhct.setSoLuong(1);
                dhct.setNgayTao(LocalDateTime.now());
                dhct.setGiaGoc(donGia);
                dhct.setGiaThoiDiemMua(donGia);
                tongTien = Integer.parseInt(String.valueOf(donGia));
                dhct.setTongTien(BigDecimal.valueOf(tongTien));
                dhct.setSpct(spct);

                //Cập nhật số lượng sản phẩm
                spct.setSoLuong(spct.getSoLuong() - 1);
                spsv.save(spct);

                dhctsv.save(dhct);
            }
            ghctsv.deleteAll();
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("failure", HttpStatus.OK);
    }

    @GetMapping("/invoice/list-invoice/{id}/{pageNumber}")
    public ResponseEntity<?> getInvoiceDetail(@PathVariable("id") String id, @PathVariable("pageNumber") int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("ngayTao").descending());
        Page<DonHangChiTiet> page = dhctsv.getPagination(pageable, id);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/invoice/change-status/{id}")
    public ResponseEntity<?> changeStatusInvoice(@PathVariable("id") String id,
                                                 @RequestParam(value = "total_amount", required = false) Long totalAmount,
                                                 @RequestParam(value = "cash_from_customer", required = false) Long cash) {
        DonHang dh = donHangService.findById(Long.parseLong(id)).get();
        dh.setTrangThai(1);
        dh.setTongTien(BigDecimal.valueOf(totalAmount));
        dh.setTienKhachTra(BigDecimal.valueOf(cash));
        donHangService.save(dh);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/product-detail/{id}")
    public ResponseEntity<?> getProductDetailById(@PathVariable("id") String id) {
        return new ResponseEntity<>(spsv.getById(id), HttpStatus.OK);
    }

    @GetMapping("/product-detail/search/{id}")
    public ResponseEntity<?> searchProductDetail(@PathVariable("id") String id) {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("ngayTao").descending());
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

    @GetMapping("/product-detail/get-by-id/{id}")
    public ResponseEntity<?> getProductDetail(@PathVariable("id") String id) {
        SanPhamChiTiet productDetail = sanPhamChiTietService.getById(id);
        if (productDetail == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(productDetail, HttpStatus.OK);
    }

    @GetMapping("/product-detail/pagination/{page}")
    public ResponseEntity<?> searchAndFilterProductDetail(@PathVariable(value = "page") int page,
                                                          @RequestParam(value = "keyword", required = false) String keyword,
                                                          @RequestParam(value = "color", required = false) String color,
                                                          @RequestParam(value = "size", required = false) String size) {
        Page<SanPhamChiTiet> result = sanPhamChiTietService.searchAndFilter(page, keyword, color, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/product-detail/search-by-id/{id}")
    public ResponseEntity<?> searchByIdProductDetail(@PathVariable(value = "id") String id) {
        SanPhamChiTiet spct = sanPhamChiTietService.getById(id);
        if (spct == null) {
            return new ResponseEntity<>("null", HttpStatus.OK);
        }
        return new ResponseEntity<>(spct, HttpStatus.OK);
    }

    @GetMapping("/product-detail/search-by-ids/{id}")
    public ResponseEntity<?> searchByIdProductDetails(@PathVariable(value = "id") String id) {
        List<SanPhamChiTiet> listAll = sanPhamChiTietService.getAll();
        List<SanPhamChiTiet> filteredProducts = listAll.stream()
                .filter(product -> String.valueOf(product.getId()).contains(id))
                .collect(Collectors.toList());
        return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
    }
}
