package com.project.SportsStores.Toner.Controller.client;

import com.project.SportsStores.Toner.Model.CustomModel.ResponseCustom;
import com.project.SportsStores.Toner.Model.DTO.ChiTietSanPhamDTO;
import com.project.SportsStores.Toner.Model.GioHang;
import com.project.SportsStores.Toner.Model.GioHangChiTiet;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Service.GioHangChiTietService;
import com.project.SportsStores.Toner.Service.GioHangService;
import com.project.SportsStores.Toner.Service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping({"/api/client/cart_detail", "/api/cart_detail"
})
public class GioHangChiTietRestController {
    @Autowired
    GioHangChiTietService service;
    @Autowired
    SanPhamChiTietService sanPhamChiTietService;
    @Autowired
    GioHangService gioHangService;
    @Autowired
    GioHangChiTietService gioHangChiTietService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    private ResponseEntity<?> paginationCart() {
        Pageable pageable = PageRequest.of(0, 4, Sort.by("ngaySua").descending());
        GioHang gioHang = gioHangService.findByIdKH(Long.valueOf(3)).get();
        Page<GioHangChiTiet> page = service.getByIdGH(String.valueOf(gioHang.getId()), pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
    private ResponseEntity<?> pagination(@PathVariable("pageNumber") int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 4, Sort.by("ngaySua").descending());
        GioHang gioHang = gioHangService.findByIdKH(Long.valueOf(3)).get();
        Page<GioHangChiTiet> page = service.getByIdGH(String.valueOf(gioHang.getId()), pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private ResponseEntity<?> add(@RequestBody ChiTietSanPhamDTO dto) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.findIdProductByColorAndSize(dto.getIdSP(), dto.getMs(), dto.getSize());
        GioHang gioHang = gioHangService.getById("2");
        boolean isCheck = false;
        GioHangChiTiet ghctTemp = null;
        for (GioHangChiTiet ghct : gioHangChiTietService.getByIdGHList("2")) {
            if (ghct.getSpct().getId().equals(sanPhamChiTiet.getId())) {
                isCheck = true;
                ghctTemp = ghct;
                break;
            }
        }
        if (!isCheck) {
            GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setGh(gioHang);
            gioHangChiTiet.setSoLuong(1);
            gioHangChiTiet.setNgaySua(LocalDateTime.now());
            gioHangChiTiet.setSpct(sanPhamChiTiet);
            System.out.println("isCheck" + 1);
            service.save(gioHangChiTiet);
            return ResponseEntity.ok().body(HttpStatus.OK);
        } else {
            ghctTemp.setSoLuong(ghctTemp.getSoLuong() + 1);
            System.out.println("isCheck" + 2);
            service.save(ghctTemp);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(String.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/minus-quantity/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> minusQuantity(@PathVariable("id") String id) {
        GioHangChiTiet ghct = gioHangChiTietService.getById(id);
        ResponseCustom response = new ResponseCustom();
        if (ghct.getSoLuong() <= 1) {
            response.setStatusText("success");
            response.setMessage("The product has been removed from the cart!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ghct.setSoLuong(ghct.getSoLuong() - 1);
            gioHangChiTietService.save(ghct);
            response.setStatusText("success");
            response.setMessage("success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add-quantity/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> addQuantity(@PathVariable("id") String id) {
        GioHangChiTiet ghct = gioHangChiTietService.getById(id);
        SanPhamChiTiet spct = sanPhamChiTietService.getById(String.valueOf(ghct.getSpct().getId()));
        ResponseCustom response = new ResponseCustom();
        if (spct.getSoLuong() == 1) {
            response.setStatusText("failure");
            response.setMessage("The product is out of stock");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (ghct.getSoLuong() == (spct.getSoLuong() - 1)) {
            response.setStatusText("failure");
            response.setMessage("Limited number of products");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ghct.setSoLuong(ghct.getSoLuong() + 1);
            gioHangChiTietService.save(ghct);
            response.setStatusText("success");
            response.setMessage("success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/edit-quantity/{id}/{quantity}", method = RequestMethod.GET)
    public ResponseEntity<?> editQuantity(@PathVariable("id") String id, @PathVariable("quantity") String quantity) {
        GioHangChiTiet ghct = gioHangChiTietService.getById(id);
        SanPhamChiTiet spct = sanPhamChiTietService.getById(String.valueOf(ghct.getSpct().getId()));
        String regexQuantity = "\\d+";
        ResponseCustom response = new ResponseCustom();
        if (quantity.matches(regexQuantity)) {
            int quantityInteger = Integer.parseInt(quantity);
            if (quantityInteger < 1) {
                response.setStatusText("failure");
                response.setMessage("Quantity must be greater than 0");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                if (quantityInteger >= (spct.getSoLuong() + ghct.getSoLuong())) {
                    response.setStatusText("failure");
                    response.setMessage(spct.getSoLuong() + "");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    ghct.setSoLuong(quantityInteger);
                    response.setStatusText("success");
                    response.setMessage("success");
                    gioHangChiTietService.save(ghct);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
        } else {
            response.setStatusText("failure");
            response.setMessage("Format error");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity<?> deleteALl() {
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
