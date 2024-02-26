package com.project.SportsStores.Toner.Controller.admin;


import com.project.SportsStores.Toner.Model.KhachHang;
import com.project.SportsStores.Toner.Repository.KhachHangRepository;
import com.project.SportsStores.Toner.Service.Impl.KhachHangServiceImpl;
import com.project.SportsStores.Toner.Service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/invoice")
public class ControllerKhachHang {
    @Autowired
    private KhachHangService khsv;


    @RequestMapping(value = "" ,method = RequestMethod.GET)
    public String show(){

        return "admin/invoice/invoices-list";
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    private ResponseEntity<?> detailInvoice(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(khsv.getByID(Long.valueOf(id)));
    }
    @RequestMapping(value = "/update/{id}" ,method = RequestMethod.POST)
    private String update(@ModelAttribute("khachhang")KhachHang kh,@PathVariable("id") String id){
        kh.setTrangThai(kh.getTrangThai());
        khsv.update(kh);
        return "redirect:/admin/invoice";
    }
}
