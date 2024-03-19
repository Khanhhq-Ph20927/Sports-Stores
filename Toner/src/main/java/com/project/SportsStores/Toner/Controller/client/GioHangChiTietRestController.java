package com.project.SportsStores.Toner.Controller.client;

import com.project.SportsStores.Toner.Model.CustomModel.ResponseCustom;
import com.project.SportsStores.Toner.Model.DTO.ChiTietSanPhamDTO;
import com.project.SportsStores.Toner.Model.GioHang;
import com.project.SportsStores.Toner.Model.GioHangChiTiet;
import com.project.SportsStores.Toner.Model.KhachHang;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Service.*;
import com.project.SportsStores.Toner.Service.Impl.GioHangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/api/client/cart_detail", "/api/cart_detail"})
public class GioHangChiTietRestController {

    @Autowired
    GioHangChiTietService service;
    @Autowired
    DonHangService donHangservice;
    @Autowired
    DonHangChiTietService donHangChiTietservice;
    @Autowired
    KhachHangService khachHangService;
    @Autowired
    SanPhamChiTietService sanPhamChiTietService;
    @Autowired
    GioHangService gioHangService;
    @Autowired
    GioHangChiTietService gioHangChiTietService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    private ResponseEntity<?> paginationCart() {
        Pageable pageable = PageRequest.of(0, 4, Sort.by("ngaySua").descending());
        GioHang gioHang = gioHangService.findByIdKH(Long.valueOf(3));
        Page<GioHangChiTiet> page = service.getByIdGH(String.valueOf(gioHang.getId()), pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/page/{pageNumber}/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> pagination(@PathVariable("pageNumber") int pageNumber, @PathVariable("id") String id) {
        Pageable pageable = PageRequest.of(pageNumber, 4, Sort.by("ngaySua").descending());
        GioHang gioHang = gioHangService.findByIdKH(Long.valueOf(id));
        Page<GioHangChiTiet> page = service.getByIdGH(String.valueOf(gioHang.getId()), pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> findAll(@PathVariable("id") String id) {
        GioHang gioHang = gioHangService.findByIdKH(Long.valueOf(id));
        List<GioHangChiTiet> list = service.getByIdGHList(String.valueOf(gioHang.getId()));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    private ResponseEntity<?> add(@RequestBody ChiTietSanPhamDTO dto, @PathVariable("id") String id) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.findIdProductByColorAndSize(dto.getIdSP(), dto.getMs(), dto.getSize());
        GioHang gioHang = gioHangService.findByIdKH(Long.parseLong(id));
        if (gioHang == null) {
            KhachHang kh = khachHangService.getByID(Long.parseLong(id));
            gioHang.setKh(kh);
            gioHangService.save(gioHang);
            gioHang = gioHangService.findByIdKH(Long.parseLong(id));
        }
        System.out.println(gioHang.toString());

        boolean isCheck = false;
        GioHangChiTiet ghctTemp = null;
        for (GioHangChiTiet ghct : gioHangChiTietService.getByIdGHList(String.valueOf(gioHang.getId()))) {
            if (ghct.getSpct().getId() == sanPhamChiTiet.getId()) {
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

    @RequestMapping(value = "/delete-a-lot/{userId}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteALot(@RequestBody List<String> list, @PathVariable("userId") String userId) {
        try {
            if (list.size() == 0) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            } else {
                for (String id : list
                ) {
                    GioHang gh = gioHangService.findByIdKH(Long.parseLong(userId));
                    String idGhct = null;
                    if (gioHangChiTietService.getByIdGHList(String.valueOf(gh.getId())).size() == 0) {
                        System.out.println("ghct null");
                    }
                    for (GioHangChiTiet ghct : gioHangChiTietService.getByIdGHList(String.valueOf(gh.getId()))) {
                        if (String.valueOf(ghct.getSpct().getId()).equalsIgnoreCase(id)) {
                            idGhct = String.valueOf(ghct.getId());
                            break;
                        }
                    }
                    System.out.println("idGhct" + idGhct);
                    if (idGhct != null) {
                        service.delete(idGhct);
                        System.out.println("is Delete");
                    }
                }
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete items", e);
        }
    }

    @RequestMapping(value = "/minus-quantity/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> minusQuantity(@PathVariable("id") String id) {
        GioHangChiTiet ghct = gioHangChiTietService.getById(id);
        ResponseCustom response = new ResponseCustom();
        if (ghct.getSoLuong() <= 1) {
            response.setStatusText("success");
            response.setMessage("The product has been removed from the cart!");
            gioHangChiTietService.delete(id);
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

    @RequestMapping(value = "/add-to-invoice/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> addToInvoice(@RequestBody List<String> listProduct, @PathVariable("id") String idUser) {
        List<GioHangChiTiet> listGHCT = new ArrayList<>();
        if (listProduct.size() == 0) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            for (String id : listProduct
            ) {
                GioHang gh = gioHangService.findByIdKH(Long.parseLong(idUser));
                if (gioHangChiTietService.getByIdGHList(String.valueOf(gh.getId())).size() == 0) {
                    System.out.println("ghct null");
                }
                for (GioHangChiTiet ghct : gioHangChiTietService.getByIdGHList(String.valueOf(gh.getId()))) {
                    if (String.valueOf(ghct.getSpct().getId()).equalsIgnoreCase(id)) {
                        listGHCT.add(ghct);
                        break;
                    }
                }

            }
        }
        return new ResponseEntity<>(listGHCT,HttpStatus.OK);
    }


}
