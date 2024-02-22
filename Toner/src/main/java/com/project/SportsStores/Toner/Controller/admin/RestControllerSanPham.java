package com.project.SportsStores.Toner.Controller.admin;

//import com.project.SportsStores.Toner.Model.DTO.SanPhamDTO;

import com.project.SportsStores.Toner.Model.DTO.SanPhamChiTietDTO;
import com.project.SportsStores.Toner.Model.SanPham;
//import com.project.SportsStores.Toner.Repository.NhaCungCapRepository;
//import com.project.SportsStores.Toner.Repository.ThuongHieuRepository;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Service.Impl.MauSacServiceImpl;
import com.project.SportsStores.Toner.Service.Impl.SanPhamChiTietServiceImpl;
import com.project.SportsStores.Toner.Service.Impl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Locale;


@RestController
@RequestMapping("/api/admin/product")
public class RestControllerSanPham {

    @Autowired
    private SanPhamServiceImpl sv;

    @Autowired
    private SanPhamChiTietServiceImpl sv2;

    @Autowired
    private MauSacServiceImpl sv3;

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

    @RequestMapping(value = "/product_detail/{id}/{pageNumber}", method = RequestMethod.GET)
    private ResponseEntity<?> getProductDetail(@PathVariable("id") String id,
                                               @PathVariable("pageNumber") String pageNumber) {
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber), 3, Sort.by("ngayTao").descending());
        Page<SanPhamChiTiet> page = sv2.getSpct(pageable, id);
        return ResponseEntity.ok().body(page);
    }

    @RequestMapping(value = "/page/{keyWord}/{pageNumber}/{status}/{collection}", method = RequestMethod.GET)
    private ResponseEntity<?> paginationAndFilter(@PathVariable("keyWord") String keyWord,
                                                  @PathVariable("pageNumber") String pageNumber,
                                                  @PathVariable("status") String status,
                                                  @PathVariable("collection") String collection) {
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber), 5, Sort.by("ngayTao").descending());
        Page<SanPham> page = sv.getPagination(pageable);
        //search
        if (Integer.parseInt(status) == -1 && Integer.parseInt(collection) == -1 && !keyWord.equals("null")) {
            page = sv.seacrhProduct(keyWord,pageable);
        }
        //filter by status
        if (Integer.parseInt(status) != -1 && Integer.parseInt(collection) == -1 && keyWord.equalsIgnoreCase("null")) {
            page = sv.filterByStatusNoSearch(pageable,status);
        }
        //filter by collection
        if (Integer.parseInt(status) == -1 && Integer.parseInt(collection) != -1 && keyWord.equalsIgnoreCase("null")) {
            page = sv.filterByCollectionNoSearch(pageable,collection);
        }
        //filter by status and collection
        if (Integer.parseInt(status) != -1 && Integer.parseInt(collection) != -1 && keyWord.equalsIgnoreCase("null")) {
            page = sv.filterByStatusAndCollectionNoSearch(pageable,status,collection);
        }
        //search and filter by status
        if (Integer.parseInt(status) != -1 && Integer.parseInt(collection) == -1 && !keyWord.equalsIgnoreCase("null")) {
            page = sv.searchAndfilterByStatus(keyWord,pageable,status);
        }
        //search and filter by collection
        if (Integer.parseInt(status) == -1 && Integer.parseInt(collection) != -1 && !keyWord.equalsIgnoreCase("null")) {
            page = sv.searchAndfilterByCollection(keyWord,pageable,collection);
        }
        //search and filter by status + collection
        if (Integer.parseInt(status) != -1 && Integer.parseInt(collection) != -1 && !keyWord.equalsIgnoreCase("null")) {
            page = sv.searchAndfilterByStatusAndCollection(keyWord,pageable,status,collection);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/update_status/{id}/{status}", method = RequestMethod.GET)
    private ResponseEntity<?> updateStatus(@PathVariable("id") String id,
                                           @PathVariable("status") String status) {
        boolean isExist = sv.getById(Long.parseLong(id)).isPresent();
        if (isExist == true) {
            SanPham sp = sv.getById(Long.parseLong(id)).get();
            sp.setTrangThai(Integer.parseInt(status));
            sv.update(sp);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("fail", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/save_product_detail/{id}", method = RequestMethod.POST)
    private ResponseEntity<?> saveProductDetail(@RequestBody() SanPhamChiTietDTO spct, RedirectAttributes redirectAttributes
            , @PathVariable("id") String id) {
        if (sv.getById(Long.valueOf(id)).isPresent()) {
            SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
            SanPham sp = sv.getById(Long.valueOf(id)).get();
            sanPhamChiTiet.setMs(sv3.getById(Integer.parseInt(spct.getMs())));
            sanPhamChiTiet.setSize(spct.getSize().toUpperCase(Locale.ROOT));
            sanPhamChiTiet.setSoLuong(Integer.parseInt(spct.getSl()));
            sanPhamChiTiet.setSp(sp);
            sanPhamChiTiet.setNgayTao(LocalDateTime.now());
            sv2.save(sanPhamChiTiet);
            redirectAttributes.addFlashAttribute("saveSuccess", true);
        } else {
            redirectAttributes.addFlashAttribute("saveFailure", true);
        }
        return ResponseEntity.ok(spct);
    }
}
