package com.project.SportsStores.Toner.Controller.admin;


import com.project.SportsStores.Toner.Model.NhaCungCap;
import com.project.SportsStores.Toner.Service.NhaCungCapService;
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
@RequestMapping("/api/admin/supplier")
public class RestControllerNhaCungCap {
    @Autowired
    private NhaCungCapService ncc;

    @RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
    private ResponseEntity<?> paginationLoad(@PathVariable("pageNumber") int pageNumber
    ) {
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("id").descending());
        Page<NhaCungCap> page = ncc.pageOfNCC(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
    @RequestMapping(value = "/page/search/{pageNumber}/{keyWord}", method = RequestMethod.GET)
    private ResponseEntity<?> getPageAndSearchAndFilter(
            @PathVariable("pageNumber") int pageNumber,
            @PathVariable("keyWord") String keyWord
    ) {
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("id").descending());
        Page<NhaCungCap> page = ncc.pageOfNCC(pageable);
        if (!keyWord.equalsIgnoreCase("null")) {
            page = ncc.searchNCC(keyWord, pageable);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

}
