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
                                    @RequestParam(value = "status", required = false) String status,
                                    @RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestParam(value = "startDate", required = false) String startDate,
                                    @RequestParam(value = "endDate", required = false) String endDate
    ) {
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("ngayTao").descending());
        Page<DonHang> page = serviceInvoice.page(pageable);
        if (status != null && keyword == null && startDate == null & endDate == null) {
            page = serviceInvoice.filterByStatus(pageable, Integer.parseInt(status));
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
        if (status == null && keyword != null && startDate == null & endDate == null) {
            page = serviceInvoice.search(pageable, keyword);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
        if (status != null && keyword != null && startDate == null & endDate == null) {
            page = serviceInvoice.searchAndFilter(pageable, keyword, Integer.parseInt(status));
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
        if (status == null && keyword == null && startDate != null & endDate != null) {
            page = serviceInvoice.filterByDate(pageable, startDate, endDate);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
        if (status != null && keyword == null && startDate != null & endDate != null) {
            page = serviceInvoice.filterByAll(pageable, Integer.parseInt(status), startDate, endDate);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
        if (status == null && keyword != null && startDate != null & endDate != null) {
            page = serviceInvoice.searchAndFilterByDate(pageable, keyword, startDate, endDate);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
        if (status != null && keyword != null && startDate != null & endDate != null) {
            page = serviceInvoice.searchAndFilterByAll(pageable, keyword, Integer.parseInt(status), startDate, endDate);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/invoice-detail/{id}")
    private ResponseEntity<?> getInvoiceDetailById(@PathVariable("id") String id) {

        List<DonHangChiTiet> listInvoiceDetail = serviceDetailInvoce.findByIdHD(id);

        return new ResponseEntity<>(listInvoiceDetail, HttpStatus.OK);
    }
}
