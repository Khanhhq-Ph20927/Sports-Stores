package com.project.SportsStores.Toner.controller.admin;

import com.project.SportsStores.Toner.model.NhaCungCap;
import com.project.SportsStores.Toner.service.NhaCungCapService;
import com.project.SportsStores.Toner.validate.NCCValidate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class NhaCungCapController {

    @Autowired
    private NhaCungCapService nccService;

    @Autowired
    NCCValidate nccValidate;

    @RequestMapping("/ncc")
    public String ncc() {
        return "admin/ncc/list-ncc";
    }

    @RequestMapping("/add-ncc")
    public String addncc(Model model, @RequestParam(value = "id", required = false) Integer id) {
        if(id == null){
            model.addAttribute("ncc", new NhaCungCap());
        }
        else{
            Optional<NhaCungCap> ncc = nccService.findById(id);
            if(ncc.isEmpty()){
                return "redirect:ncc";
            }
            model.addAttribute("ncc", ncc.get());
        }
        return "admin/ncc/add-ncc";
    }

    @RequestMapping(value = "/add-ncc", method = RequestMethod.POST)
    public String addnccAction(@Valid @ModelAttribute("ncc") NhaCungCap ncc, BindingResult bindingResult) {
        System.out.println(ncc);
        nccValidate.validate(ncc, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/ncc/add-ncc";
        }
        nccService.create(ncc);
        return "redirect:ncc";
    }
}
