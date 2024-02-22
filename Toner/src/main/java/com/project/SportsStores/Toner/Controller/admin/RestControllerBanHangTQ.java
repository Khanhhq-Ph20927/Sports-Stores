package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Model.KhachHang;
import com.project.SportsStores.Toner.Service.Impl.DonHangTQServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/sell-off")
public class RestControllerBanHangTQ {
    @Autowired
    private DonHangTQServiceImpl dhsv;

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
}
