package com.project.SportsStores.Toner.Controller.admin;

//import com.project.SportsStores.Toner.Model.DTO.SanPhamDTO;

import com.project.SportsStores.Toner.Model.SanPham;
//import com.project.SportsStores.Toner.Repository.NhaCungCapRepository;
//import com.project.SportsStores.Toner.Repository.ThuongHieuRepository;
import com.project.SportsStores.Toner.Service.Impl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/product")
public class RestControllerSanPham {

    @Autowired
    private SanPhamServiceImpl sv;

//    @Autowired
//    private NhaCungCapRepository rp1;
//
//    @Autowired
//    private ThuongHieuRepository rp2;

    @RequestMapping(value = "/page/search/{pageNumber}/{keyWord}", method = RequestMethod.GET)
    private ResponseEntity<?> pagination(@PathVariable("pageNumber") int pageNumber,
                                         @PathVariable("keyWord") String keyWord) {
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("ngayTao").descending());
        if (keyWord.equalsIgnoreCase("null")) {
            Page<SanPham> page = sv.getPagination(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            Page<SanPham> page = sv.seacrhProduct(keyWord, pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/page/{keyWord}/{pageNumber}/{status}/{collection}", method = RequestMethod.GET)
    private ResponseEntity<?> paginationAndFilter(@PathVariable("keyWord") String keyWord,
                                                  @PathVariable("pageNumber") String pageNumber,
                                                  @PathVariable("status") String status,
                                                  @PathVariable("collection") String collection) {
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber), 5, Sort.by("ngayTao").descending());
        Page<SanPham> page = sv.getPagination(pageable);
        if (Integer.parseInt(status) == -1 && Integer.parseInt(collection) == -1 && !keyWord.equals("null")) {
            page = sv.seacrhProduct(keyWord,pageable);
        }
        if (Integer.parseInt(status) != -1 && Integer.parseInt(collection) == -1 && keyWord.equalsIgnoreCase("null")) {
            page = sv.filterByStatusNoSearch(pageable, status);
        }
        if (Integer.parseInt(status) == -1 && Integer.parseInt(collection) != -1 && keyWord.equalsIgnoreCase("null")) {
            page = sv.filterByCollectionNoSearch(pageable, collection);
        }
        if (Integer.parseInt(status) != -1 && Integer.parseInt(collection) != -1 && keyWord.equalsIgnoreCase("null")) {
            page = sv.filterByStatusAndCollectionNoSearch(pageable, status, collection);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

}
