package com.project.SportsStores.Toner.Controller.admin;


import com.project.SportsStores.Toner.Model.ThuongHieu;
import com.project.SportsStores.Toner.Service.ThuongHieuService;
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
@RequestMapping("/admin/trademark")
public class ControllerThuongHieu {
    @Autowired
    ThuongHieuService thsv;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String show() {
        return "admin/trademark/trademark-list";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> detailSuppliers(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(thsv.getByID(Long.valueOf(id)));
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    private String update(Model model) {
        model.addAttribute("th", new ThuongHieu());
        return "admin/trademark/trademark-create";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String add(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("th") ThuongHieu thuongHieu
    ) {
        boolean isValid = false;
        for (ThuongHieu th : thsv.getAll()) {
            if (thuongHieu.getTen().equalsIgnoreCase(th.getTen())) {
                isValid = true;
                model.addAttribute("errorName", "Số điện thoại trùng !");
            }
            if (thuongHieu.getEmail().equalsIgnoreCase(th.getEmail())) {
                isValid = true;
                model.addAttribute("errorEmail", "Email trùng !");
            }
        }
        if (thuongHieu.getTen().isBlank()) {
            isValid = true;
            model.addAttribute("errorName", "Please Choose Name");
        }
        if (thuongHieu.getEmail().isBlank()) {
            isValid = true;
            model.addAttribute("errorEmail", "Please Choose Email");
        } else if (!thuongHieu.getEmail().matches(".+@.+\\.+.+")) {
            isValid = true;
            model.addAttribute("errorEmail", "Please Regex Email");
        }
        if (thuongHieu.getQuocGia().isBlank()) {
            isValid = true;
            model.addAttribute("errorQuocGia", "Please Choose");
        } else if (thuongHieu.getQuocGia().length() > 255) {
            isValid = true;
            model.addAttribute("errorQuocGia", "Please Choose");
        }
        if (thuongHieu.getMoTa().isEmpty()) {
            isValid = true;
            model.addAttribute("errorMoTa", "Please Choose   ");
        } else if (thuongHieu.getMoTa().length() > 255) {
            isValid = true;
            model.addAttribute("errorMoTa", "Please Choose   ");
        }

        if (isValid == false) {
            thsv.save(thuongHieu);
            redirectAttributes.addFlashAttribute("message", true);
            return "redirect:/admin/trademark";
        } else {
            model.addAttribute("message", false);
            return "admin/trademark/trademark-create";
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes,
                         @ModelAttribute("th") ThuongHieu thuongHieu, Model model) {
        ThuongHieu th = thsv.getByID(id);
         th.setTen(thuongHieu.getTen());
         th.setEmail(thuongHieu.getEmail());
         th.setLogo(thuongHieu.getLogo());
         th.setMoTa(thuongHieu.getMoTa());
         th.setQuocGia(thuongHieu.getQuocGia());
         thsv.update(thuongHieu);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/admin/trademark";
    }
}
