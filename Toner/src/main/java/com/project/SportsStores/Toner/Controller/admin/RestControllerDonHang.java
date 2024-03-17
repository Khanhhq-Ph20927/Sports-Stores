package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Model.DonHangChiTiet;
import com.project.SportsStores.Toner.Service.Impl.DonHangChiTietServiceImpl;
import com.project.SportsStores.Toner.Service.Impl.DonHangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/invoice")
public class RestControllerDonHang {

    @Autowired
    DonHangServiceImpl serviceInvoice;

    @Autowired
    DonHangChiTietServiceImpl serviceDetailInvoce;

    @GetMapping("/index/{pageNumber}")
    private ResponseEntity<?> index(@PathVariable("pageNumber") int pageNumber,
                                    @RequestParam(value = "status", required = false) String status) {
        Page<DonHang> page;
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("ngayTao").descending());
        if (status == null) {
            page = serviceInvoice.page(pageable);
        } else {
            page = serviceInvoice.filterByStatus(pageable, Integer.parseInt(status));
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/invoice-detail/{id}")
    private ResponseEntity<?> getInvoiceDetailById(@PathVariable("id") String id) {

        List<DonHangChiTiet> listInvoiceDetail = serviceDetailInvoce.findByIdHD(id);

        return new ResponseEntity<>(listInvoiceDetail, HttpStatus.OK);
    }
}
