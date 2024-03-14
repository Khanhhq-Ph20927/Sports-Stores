package com.project.SportsStores.Toner.Controller.admin;


import com.project.SportsStores.Toner.Model.ThuongHieu;
import com.project.SportsStores.Toner.Service.ThuongHieuService;
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
@RequestMapping("/api/admin/trademark")
public class RestControllerThuongHieu {
    @Autowired
    private ThuongHieuService thsv;

    @RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
    private ResponseEntity<?> paginationLoad(@PathVariable("pageNumber") int pageNumber
    ) {
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("id").descending());
        Page<ThuongHieu> page = thsv.pageOfTH(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
    @RequestMapping(value = "/page/search/{pageNumber}/{keyWord}", method = RequestMethod.GET)
    private ResponseEntity<?> getPageAndSearchAndFilter(
            @PathVariable("pageNumber") int pageNumber,
            @PathVariable("keyWord") String keyWord
    ) {
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("id").descending());
        Page<ThuongHieu> page = thsv.pageOfTH(pageable);
        if (!keyWord.equalsIgnoreCase("null")) {
            page = thsv.searchTH(keyWord, pageable);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
