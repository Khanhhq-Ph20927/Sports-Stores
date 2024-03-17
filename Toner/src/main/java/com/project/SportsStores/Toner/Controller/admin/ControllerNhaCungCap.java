package com.project.SportsStores.Toner.Controller.admin;


import com.project.SportsStores.Toner.Model.NhaCungCap;
import com.project.SportsStores.Toner.Service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/admin/supplier")
public class ControllerNhaCungCap {
    @Autowired
    NhaCungCapService nccsv;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String show() {
        return "admin/suppliers/suppliers-list";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> detailSuppliers(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(nccsv.getByID(Long.valueOf(id)));
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    private String update(Model model) {
        model.addAttribute("ncc", new NhaCungCap());
        return "admin/suppliers/suppliers-create";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String add(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("ncc") NhaCungCap nhaCungCap
    ) {
        boolean isValid = false;
       for (NhaCungCap ncc : nccsv.getAll()){
            if (nhaCungCap.getSdt().equalsIgnoreCase(ncc.getSdt())) {
                isValid = true;
                model.addAttribute("errorPhone", "Số điện thoại trùng !");
            }
            if (nhaCungCap.getEmail().equalsIgnoreCase(ncc.getEmail())) {
                isValid = true;
                model.addAttribute("errorEmail", "Email trùng !");
            }
        }
        if (nhaCungCap.getTen().isBlank()) {
            isValid = true;
            model.addAttribute("errorName", "Please Choose Name");
        }
        if (nhaCungCap.getSdt().isEmpty()) {
            isValid = true;
            model.addAttribute("errorPhone", "Please Choose Phone Number");
        } else if (!nhaCungCap.getSdt().matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b")) {
            isValid = true;
            model.addAttribute("errorPhone", "Please Regex Phone Number");
        }
        if (nhaCungCap.getEmail().isBlank()) {
            isValid = true;
            model.addAttribute("errorEmail", "Please Choose Email");
        } else if (!nhaCungCap.getEmail().matches(".+@.+\\.+.+")) {
            isValid = true;
            model.addAttribute("errorEmail", "Please Regex Email");
        }
        if (nhaCungCap.getDiaChi().isBlank()) {
            isValid = true;
            model.addAttribute("errorDiaChi", "Please Choose");
        } else if (nhaCungCap.getDiaChi().length() > 255) {
            isValid = true;
            model.addAttribute("errorDiaChi", "Please Choose");
        }
        if (nhaCungCap.getMoTa().isBlank()) {
            isValid = true;
            model.addAttribute("errorMoTa", "Please Choose   ");
        } else if (nhaCungCap.getMoTa().length() > 255) {
            isValid = true;
            model.addAttribute("errorMoTa", "Please Choose   ");
        }if (nhaCungCap.getThongTinKhac().length() > 255) {
            isValid = true;
            model.addAttribute("errorTT", "Please Choose   ");
        }

        if (isValid == false) {
            nccsv.save(nhaCungCap);
            redirectAttributes.addFlashAttribute("message", true);
            return "redirect:/admin/supplier";
        } else {
            model.addAttribute("message", false);
            return "admin/suppliers/suppliers-create";
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes,
                         @ModelAttribute("ncc") NhaCungCap nhaCungCap, Model model) {
        NhaCungCap nhaCC = nccsv.getByID(id);
        nhaCC.setTen(nhaCungCap.getTen());
        nhaCC.setEmail(nhaCungCap.getEmail());
        nhaCC.setMoTa(nhaCungCap.getMoTa());
        nhaCC.setDiaChi(nhaCungCap.getDiaChi());
        nhaCC.setSdt(nhaCungCap.getSdt());
        nhaCC.setThongTinKhac(nhaCungCap.getThongTinKhac());
        nccsv.update(nhaCungCap);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/admin/supplier";
    }
}
