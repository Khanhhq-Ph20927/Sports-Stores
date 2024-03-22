package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.KhuyenMai;
import com.project.SportsStores.Toner.Model.TheLoai;
import com.project.SportsStores.Toner.Repository.TheLoaiRepository;
import com.project.SportsStores.Toner.Service.TheLoaiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller
public class TheLoaiController {

    @Autowired
    private TheLoaiRepository theLoaiRepository;

    @Autowired
    private TheLoaiService theLoaiService;

    @RequestMapping("/admin/theloai")
    public String category() {
        return "admin/category/list-category";
    }

    @RequestMapping("/admin/add-theloai")
    public String addTheLoaiView(Model model, @RequestParam(value = "id", required = false) Long id) {
        if(id == null){
            model.addAttribute("theloai", new TheLoai());
            model.addAttribute("image", "");
        }
        else{
            Optional<TheLoai> theLoai = theLoaiService.findById(id);
            if(theLoai.isEmpty()){
                return "redirect:theloai";
            }
            model.addAttribute("theloai", theLoai.get());
            model.addAttribute("image", theLoai.get().getHinhAnh());
        }
        return "admin/category/add-category";
    }

    @RequestMapping(value = "/admin/add-category", method = RequestMethod.POST)
    public String addTheLoai(@Valid @ModelAttribute("theloai") TheLoai theLoai, Model model) {
        boolean isValid = false;
        if(theLoai.getId() != null){
            if (theLoaiRepository.findByNameAndId(theLoai.getTen(), theLoai.getId()).isPresent()) {
                isValid = true;
                model.addAttribute("errorName", "Tên thể loại này đã tồn tại");
            }
        }
        else{
            if (theLoaiRepository.findByName(theLoai.getTen()).isPresent()) {
                isValid = true;
                model.addAttribute("errorName", "Tên thể loại này đã tồn tại");
            }
        }
        Pattern regex = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        if (regex.matcher(theLoai.getTen()).find()) {
            isValid = true;
            model.addAttribute("errorName", "Tên thể loại không được chứa ký tự đặc biệt");
        }
        if(isValid){
            return "admin/category/add-category";
        }
        theLoaiService.create(theLoai);
        return "redirect:theloai";
    }

    @DeleteMapping("/admin/delete-theloai")
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        theLoaiService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/theloai/findAll-page")
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<TheLoai> result = theLoaiService.findAll(pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/public/theloai/find-by-id")
    public ResponseEntity<?> findById(@RequestParam("id") Long id){
        Optional<TheLoai> theLoai = theLoaiService.findById(id);
        return new ResponseEntity<>(theLoai.get(),HttpStatus.OK);
    }
}
