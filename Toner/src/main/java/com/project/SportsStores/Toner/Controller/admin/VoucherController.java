package com.project.SportsStores.Toner.controller.admin;

import com.project.SportsStores.Toner.model.Voucher;
import com.project.SportsStores.Toner.service.VoucherService;
import com.project.SportsStores.Toner.validate.VoucherValidate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @Autowired
    VoucherValidate voucherValidate;

    @RequestMapping("/voucher")
    public String voucher() {
        return "admin/voucher/list-voucher";
    }

    @RequestMapping("/add-voucher")
    public String addvoucher(Model model, @RequestParam(value = "id", required = false) Integer id) {
        if(id == null){
            model.addAttribute("voucher", new Voucher());
            model.addAttribute("image", "");
        }
        else{
            Optional<Voucher> voucher = voucherService.findById(id);
            if(voucher.isEmpty()){
                return "redirect:voucher";
            }
            model.addAttribute("voucher", voucher.get());
            model.addAttribute("image", voucher.get().getBanner());
        }
        return "admin/voucher/add-voucher";
    }

    @RequestMapping(value = "/add-voucher", method = RequestMethod.POST)
    public String addvoucherAction(@Valid @ModelAttribute("voucher") Voucher voucher, BindingResult bindingResult) {
        System.out.println(voucher);
        voucherValidate.validate(voucher, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/voucher/add-voucher";
        }
        voucher.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        voucherService.create(voucher);
        return "redirect:voucher";
    }
}
