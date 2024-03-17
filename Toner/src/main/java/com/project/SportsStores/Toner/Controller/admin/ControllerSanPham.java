package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.DTO.SanPhamDTO;
import com.project.SportsStores.Toner.Model.CustomModel.ResponseCustom;
import com.project.SportsStores.Toner.Model.SanPham;
import com.project.SportsStores.Toner.Repository.NhaCungCapRepository;
import com.project.SportsStores.Toner.Repository.ThuongHieuRepository;
import com.project.SportsStores.Toner.Service.Impl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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

    @RequestMapping(value = "/product_detail", method = RequestMethod.GET)
    private String viewProductDetail() {
        return "admin/products/product-detailed";
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
    private ResponseEntity<?> updateProduct(@RequestBody SanPhamDTO sanPhamDTO
            , @PathVariable("id") String id) {

        // Regular expression
        String regexPrice = "^(?=.*[1-9])\\d+$";

        String regexName = "^[a-zA-ZÀ-Ỹà-ỹ][a-zA-Z0-9À-Ỹà-ỹ ]{9,50}$";

        Pattern pattern = Pattern.compile(regexPrice);
        Matcher matcher = pattern.matcher(String.valueOf(sanPhamDTO.getDonGia()));

        Pattern patternName = Pattern.compile(regexName);
        Matcher matcherName = patternName.matcher(sanPhamDTO.getTenSP());

        List<ResponseCustom> responseList = new ArrayList<>();

        SanPham sp = sv.getById(Long.valueOf(id)).isPresent() ? sv.getById(Long.valueOf(id)).get() : null;

        boolean isValid = true;

        List<SanPham> getAllProduct = sv.getAll();
        // loại bỏ phần tử muốn update khỏi danh sách
        getAllProduct.remove(sp);
        for (SanPham product : getAllProduct) {
            if (product.getTenSP().equalsIgnoreCase(sanPhamDTO.getTenSP())) {
                ResponseCustom response = new ResponseCustom();
                response.setMessage("errorDuplicateName");
                response.setStatusText("failure");
                responseList.add(response);
                isValid = false;
                break;
            }
        }

        if (sanPhamDTO.getDanhMuc() == -1) {
            isValid = false;
        }
        if (sanPhamDTO.getTenSP().isEmpty()) {
            isValid = false;
        }
        if (!matcherName.matches()) {
            isValid = false;
            ResponseCustom responseCustom = new ResponseCustom();
            responseCustom.setStatusText("failure");
            responseCustom.setMessage("errorFormatName");
            responseList.add(responseCustom);
        }
        if (sanPhamDTO.getThieu() == null || rp2.findById(sanPhamDTO.getThieu()).isEmpty()) {
            isValid = false;
        }
        if (sanPhamDTO.getNcc() == null || rp1.findById(sanPhamDTO.getNcc()).isEmpty()) {
            isValid = false;
        }
        if (sanPhamDTO.getDonGia() == null) {
            isValid = false;
        }
        if (sanPhamDTO.getDonGia() == null) {
            isValid = false;

        }
        if (matcher.matches()) {
            if (BigDecimal.valueOf(Long.parseLong(sanPhamDTO.getDonGia())).compareTo(new BigDecimal("70000")) < 0) {
                isValid = false;
                ResponseCustom responseCustom = new ResponseCustom();
                responseCustom.setStatusText("failure");
                responseCustom.setMessage("errorPriceLessThan");
                responseList.add(responseCustom);
            }
            if (BigDecimal.valueOf(Long.parseLong(sanPhamDTO.getDonGia())).compareTo(new BigDecimal("5000000")) > 0) {
                isValid = false;
                ResponseCustom responseCustom = new ResponseCustom();
                responseCustom.setStatusText("failure");
                responseCustom.setMessage("errorPriceMoreThan");
                responseList.add(responseCustom);
            }
        } else {
            isValid = false;
            ResponseCustom responseCustom = new ResponseCustom();
            responseCustom.setStatusText("failure");
            responseCustom.setMessage("errorFormatPrice");
            responseList.add(responseCustom);
        }
        if (isValid) {
            sp.setTenSP(sanPhamDTO.getTenSP());
            sp.setDanhMuc(sanPhamDTO.getDanhMuc());
            sp.setTrangThai(sanPhamDTO.getTrangThai());
            sp.setDonGia(BigDecimal.valueOf(Long.parseLong(sanPhamDTO.getDonGia())));
            sp.setThieu(rp2.findById(sanPhamDTO.getThieu()).get());
            sp.setNcc(rp1.findById(sanPhamDTO.getNcc()).get());
            sv.save(sp);
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok(responseList);
        }
    }
}