package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.DTO.SanPhamDTO;
import com.project.SportsStores.Toner.Model.SanPham;
import com.project.SportsStores.Toner.Repository.NhaCungCapRepository;
import com.project.SportsStores.Toner.Repository.ThuongHieuRepository;
import com.project.SportsStores.Toner.Service.Impl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/api/admin/product")
public class ControllerSanPham {

    @Autowired
    private SanPhamServiceImpl sv;

    @Autowired
    private NhaCungCapRepository rp1;

    @Autowired
    private ThuongHieuRepository rp2;

    @RequestMapping(value = "/product_list", method = RequestMethod.GET)
    private String productList() {
        return "admin/products/product-list";
    }

    @RequestMapping(value = "/create_product", method = RequestMethod.GET)
    private String nextViewCreateProduct() {
        return "admin/products/product-create";
    }

    @RequestMapping(value = "/product_detail", method = RequestMethod.GET)
    private String viewProductDetail() {
        return "admin/products/product-detailed";
    }

    @RequestMapping(value = "/save_product", method = RequestMethod.POST)
    private String saveProduct(@ModelAttribute("product") SanPham sp,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        // Regular expression
        String regexPrice = "^(?=.*[1-9])\\d+$";

        Pattern pattern = Pattern.compile(regexPrice);
        Matcher matcher = pattern.matcher(String.valueOf(sp.getDonGia()));

        System.out.println(sp.getDonGia());

        for (SanPham sanPham : sv.getAll()
        ) {
            sp.setMaSP("SP" + sv.getAll().size());
            if (sp.getMaSP().equalsIgnoreCase(sanPham.getMaSP())) {
                sp.setMaSP("SP" + (sv.getAll().size() + 1));
            }
        }
        boolean isValid = true;
        sp.setNgayTao(LocalDateTime.now());
        if (sp.getDanhMuc() == -1) {
            isValid = false;
            model.addAttribute("errorCollections", "Please Choose Collections!");
        }
        if (sp.getTenSP().isEmpty()) {
            isValid = false;
            model.addAttribute("errorName", "Please Choose Name!");
        }
        if (sp.getThieu() == null) {
            isValid = false;
            model.addAttribute("errorBrand", "Please Choose Brand!");
        }
        if (sp.getNcc() == null) {
            isValid = false;
            model.addAttribute("errorProvider", "Please Choose Provider!");
        }
        if (sp.getDonGia() == null) {
            isValid = false;
            model.addAttribute("errorPrice", "Please Choose Price!");
        }
        if (sp.getDonGia() == null) {
            isValid = false;
            model.addAttribute("errorPrice", "Please Choose Price!");
        }
        if (matcher.matches()) {
            System.out.println(sp.getDonGia().compareTo(new BigDecimal("70000")));
            if (sp.getDonGia().compareTo(new BigDecimal("70000")) < 0) {
                isValid = false;
                model.addAttribute("errorPrice", "The price is invalid or less than 70,000 VND!");
            }
        } else {
            isValid = false;
            model.addAttribute("errorPrice", "Invalid price format!");
        }
        if (isValid) {
            sv.save(sp);
            redirectAttributes.addFlashAttribute("message", true);
            return "redirect:/api/admin/product/create_product";
        } else {
            model.addAttribute("tenSP", sp.getTenSP());
            model.addAttribute("thieu", sp.getThieu().getId());
            model.addAttribute("ncc", sp.getNcc().getId());
            model.addAttribute("danhMuc", sp.getDanhMuc());
            model.addAttribute("trangThai", sp.getTrangThai());
            model.addAttribute("donGia", sp.getDonGia());
            return "admin/products/product-create";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    private ResponseEntity<?> deleteProduct(RedirectAttributes redirectAttributes, @PathVariable("id") String id) {
        sv.deleteById(Long.valueOf(id));
        redirectAttributes.addFlashAttribute("listProduct", sv.getAll());
        redirectAttributes.addFlashAttribute("deleteSuccess", true);
        return ResponseEntity.ok().body(sv.getAll());
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> detailProduct(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(sv.getById(Long.valueOf(id)));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    private String updateProduct(@ModelAttribute("product") SanPhamDTO sanPhamDTO, RedirectAttributes redirectAttributes
            , @PathVariable("id") String id) {
        SanPham sp = sv.getById(Long.valueOf(id)).get();
        sp.setDonGia(sanPhamDTO.getDonGia());
        sp.setTenSP(sanPhamDTO.getTenSP());
        sp.setDanhMuc(sanPhamDTO.getDanhMuc());
        sp.setTrangThai(sanPhamDTO.getTrangThai());
        sp.setNcc(rp1.findById(sanPhamDTO.getNcc()).get());
        sp.setThieu(rp2.findById(sanPhamDTO.getThieu()).get());
        sv.save(sp);
        redirectAttributes.addFlashAttribute("updateSuccess", true);
        return "redirect:/api/admin/product/product_list";
    }
}