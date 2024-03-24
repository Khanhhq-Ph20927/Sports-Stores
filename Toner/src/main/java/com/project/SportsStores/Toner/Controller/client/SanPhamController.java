package com.project.SportsStores.Toner.Controller.client;

import com.project.SportsStores.Toner.Service.SanPhamChiTietService;
import com.project.SportsStores.Toner.Service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/client/product",
        "/product"})
public class SanPhamController {
    @Autowired
    SanPhamService service;
    @Autowired
    SanPhamChiTietService chiTietService;

    @GetMapping()
    public String getAll() {

        return "client/pages/products/grid/product-grid-defualt";
    }
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("sanPham", service.getById(id).get());
        return "client/pages/products/product-details";
    }

}
