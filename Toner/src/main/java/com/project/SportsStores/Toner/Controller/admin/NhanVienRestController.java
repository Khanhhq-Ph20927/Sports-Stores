package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.NhanVien;
import com.project.SportsStores.Toner.Service.ChucVuService;
import com.project.SportsStores.Toner.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/staff")
//@RestController
public class NhanVienRestController {
    @Autowired
    NhanVienService service;
    @Autowired
    ChucVuService chucVuService;

    //Page
//            model.addAttribute("number", number);
//        model.addAttribute("totalPages", Service.page(pageable).getTotalPages());
//        model.addAttribute("totalElements", Service.page(pageable).getTotalElements());
//        model.addAttribute("list", Service.page(pageable).getContent());
    @GetMapping("/page/{number}/{keyword}/{status}/{position}")
    ///{sort}
    public ResponseEntity<?> getPageAndSearchAndFilter(Model model, @PathVariable("number") int number
            , @PathVariable("keyword") String keyword
            , @PathVariable("status") String status
            , @PathVariable("position") String position
//            , @PathVariable("sort") String sort
    ) {
        Pageable pageable = PageRequest.of(number, 5, Sort.by("ngayTao").descending());
//        Service.page(pageable);
        Page<NhanVien> page = service.page(pageable);
        if (Integer.parseInt(status) == -1 && Long.parseLong(position) == 0 && !keyword.equals("null")) {
            page = service.SearchPage(pageable, keyword);
        }
        if (Integer.parseInt(status) != -1 && Long.parseLong(position) == 0 && keyword.equalsIgnoreCase("null")) {
            page = service.filterByStatusNoSearch(pageable, Integer.parseInt(status));
        }
        if (Integer.parseInt(status) != -1 && Long.parseLong(position) == 0 && !keyword.equalsIgnoreCase("null")) {
            page = service.filterByStatusAndSearch(pageable, keyword, Integer.parseInt(status));
        }
        if (Integer.parseInt(status) == -1 && Long.parseLong(position) != 0 && keyword.equalsIgnoreCase("null")) {
            page = service.filterByPositionNoSearch(pageable, Long.parseLong(position));
        }
        if (Integer.parseInt(status) == -1 && Long.parseLong(position) != 0 && !keyword.equalsIgnoreCase("null")) {
            page = service.filterByPositionAndSearch(pageable, keyword, Long.parseLong(position));
        }
        if (Integer.parseInt(status) != -1 && Long.parseLong(position) != 0 && keyword.equalsIgnoreCase("null")) {
            page = service.filterByStatusAndPositionNoSearch(pageable, Integer.parseInt(status), Long.parseLong(position));
        }
        if (Integer.parseInt(status) != -1 && Long.parseLong(position) != 0 && !keyword.equals("null")) {
            page = service.SearchAndFilter(pageable, keyword, Integer.parseInt(status), Long.parseLong(position));
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/update_status/{id}/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") String id
            , @PathVariable("status") String status) {
        boolean isExist = service.findById(Long.parseLong(id)).isPresent();
        if (isExist) {
            NhanVien nv = service.findById(Long.parseLong(id)).get();
            nv.setTrangThai(Integer.parseInt(status));
            service.save(nv);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("fail", HttpStatus.OK);
        }
    }
}