package com.project.SportsStores.Toner.Controller.admin;


import com.project.SportsStores.Toner.Model.KhachHang;
import com.project.SportsStores.Toner.Service.Impl.KhachHangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/api/admin/customer")
public class RestControllerKhachHang {
    @Autowired
    private KhachHangServiceImpl khsv;

    @RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
    private ResponseEntity<?> paginationLoad(@PathVariable("pageNumber") int pageNumber
    ) {
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("ngayTao").descending());
        Page<KhachHang> page = khsv.pageOfKhachHang(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/page/search/{pageNumber}/{keyWord}/{gender}", method = RequestMethod.GET)
    private ResponseEntity<?> getPageAndSearchAndFilter(
            @PathVariable("pageNumber") int pageNumber,
            @PathVariable("keyWord") String keyWord,
            @PathVariable("gender") String gender
    ) {
        boolean gender1;
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("ngayTao").descending());
        Page<KhachHang> page = khsv.pageOfKhachHang(pageable);
        if (Integer.parseInt(gender) == -1 && !keyWord.equalsIgnoreCase("null")) {
            page = khsv.seacrch(keyWord, pageable);
        }
        if (Integer.parseInt(gender) != -1 && keyWord.equalsIgnoreCase("null")) {
            if (Integer.parseInt(gender) == 0) {
                gender1 = true;
            } else {
                gender1 = false;
            }
            page = khsv.fillterByGenderNoSearch(pageable, gender1);
        }
        if (Integer.parseInt(gender) != -1 && !keyWord.equalsIgnoreCase("null")) {
            if (Integer.parseInt(gender) == 0) {
                gender1 = true;
            } else {
                gender1 = false;
            }
            page = khsv.fillterByGenderAndSearch(pageable, keyWord, gender1);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
