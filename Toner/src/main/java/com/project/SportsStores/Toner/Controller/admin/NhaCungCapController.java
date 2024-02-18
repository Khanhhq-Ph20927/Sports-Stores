package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.NhaCungCap;
import com.project.SportsStores.Toner.Repository.NCCRepository;
import com.project.SportsStores.Toner.Service.NhaCungCapService;
import com.project.SportsStores.Toner.validate.NCCValidate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class NhaCungCapController {
    @Autowired
    private NCCRepository nhaCungCapRepository;

    @Autowired
    private NhaCungCapService nccService;

    @Autowired
    NCCValidate nccValidate;


    @GetMapping("/ncc")
    public String listNhaCungCap(Model model) {
        List<NhaCungCap> listNhaCungCap = nccService.findAll();
        model.addAttribute("nccList", listNhaCungCap);
        return "admin/ncc/list-ncc";
    }

    @RequestMapping("/add-ncc")
    public String addncc(Model model, @RequestParam(value = "id", required = false) Integer id) {
        // Lấy danh sách nhà cung cấp
        List<NhaCungCap> nccList = nccService.findAll();
        model.addAttribute("nccList", nccList);

        if (id == null) {
            model.addAttribute("ncc", new NhaCungCap());
        } else {
            Optional<NhaCungCap> ncc = nccService.findById(id);
            if (ncc.isEmpty()) {
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
