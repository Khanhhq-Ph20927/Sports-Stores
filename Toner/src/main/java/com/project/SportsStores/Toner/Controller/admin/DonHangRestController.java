package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/donhang")
@CrossOrigin
public class DonHangRestController {
    @Autowired
    private DonHangService donHangService;

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        donHangService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/page/{number}/{keyword}/{status}/{position}")
    public ResponseEntity<?> getPageAndSearchAndFilter(Model model, @PathVariable("number") int number
            , @PathVariable("keyword") String keyword
            , @PathVariable("status") String status
            , @PathVariable("position") String position
    ) {
        Pageable pageable = PageRequest.of(number, 6, Sort.by("ngayTao").descending());
        Page<DonHang> page = donHangService.page(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

}