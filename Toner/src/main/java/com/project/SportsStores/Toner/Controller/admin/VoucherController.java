package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.KhuyenMai;
import com.project.SportsStores.Toner.Repository.VoucherRepository;
import com.project.SportsStores.Toner.Service.VoucherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private VoucherRepository voucherRepository;


    @RequestMapping("/admin/voucher")
    public String voucher() {
        return "admin/voucher/list-voucher";
    }

    @RequestMapping("/admin/add-voucher")
    public String addvoucher(Model model, @RequestParam(value = "id", required = false) Long id) {
        if(id == null){
            model.addAttribute("voucher", new KhuyenMai());
            model.addAttribute("image", "");
        }
        else{
            Optional<KhuyenMai> voucher = voucherService.findById(id);
            if(voucher.isEmpty()){
                return "redirect:voucher";
            }
            model.addAttribute("voucher", voucher.get());
            model.addAttribute("image", voucher.get().getBanner());
        }
        return "admin/voucher/add-voucher";
    }

    @RequestMapping(value = "/admin/add-voucher", method = RequestMethod.POST)
    public String addvoucherAction(@Valid @ModelAttribute("voucher") KhuyenMai voucher, Model model) {
        boolean isValid = false;
        if(voucher.getNgayKetThuc().before(voucher.getNgayBatDau())){
            isValid = true;
            model.addAttribute("errorNgayKetThuc", "Ngày kết thúc không hợp lệ");
        }
        if(voucher.getId() != null){
            if (voucherRepository.findByNameAndId(voucher.getTenKhuyenMai(), voucher.getId()).isPresent()) {
                isValid = true;
                model.addAttribute("errorName", "Tên voucher này đã tồn tại");
            }
        }
        else{
            if (voucherRepository.findByName(voucher.getTenKhuyenMai()).isPresent()) {
                isValid = true;
                model.addAttribute("errorName", "Tên voucher này đã tồn tại");
            }
        }
        if(isValid){
            return "admin/voucher/add-voucher";
        }
        voucher.setNgayTao(LocalDateTime.now());
        voucherService.create(voucher);
        return "redirect:voucher";
    }

//    @DeleteMapping("/admin/delete")
//    public ResponseEntity<?> delete(@RequestParam("id") Long id){
//        voucherService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping("/admin/voucher/findAll-page")
    public ResponseEntity<?> findAll(@RequestParam(value = "start", required = false) Date start,
                                     @RequestParam(value = "end", required = false) Date end,
                                     Pageable pageable){
        Page<KhuyenMai> result = voucherService.findAll(start,end, pageable);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
