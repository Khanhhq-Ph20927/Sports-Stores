package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.NhaCungCap;
import com.project.SportsStores.Toner.Repository.NCCRepository;
import com.project.SportsStores.Toner.Service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@RequestMapping("/admin/nhacungcap")
public class NhaCungCapController {
    @Autowired
    private NCCRepository nhaCungCapRepository;

    @Autowired
    private NhaCungCapService service;


    @GetMapping()
    public String getPage(Model model, @RequestParam(value = "number", defaultValue = "1") int number) {
        Pageable pageable = PageRequest.of(number - 1, 5);
        service.page(pageable);
        model.addAttribute("number", number);
        model.addAttribute("totalPages", service.page(pageable).getTotalPages());
        model.addAttribute("totalElements", service.page(pageable).getTotalElements());
        model.addAttribute("list", service.page(pageable).getContent());
        return "admin/nhacungcap/ncc-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("nhaCungCap", new NhaCungCap());
        return "admin/nhacungcap/ncc-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("nhaCungCap") NhaCungCap nhaCungCap) {
        service.save(nhaCungCap);
        return "redirect:/admin/nhacungcap/add";
    }
}
