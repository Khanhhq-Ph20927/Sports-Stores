package com.project.SportsStores.Toner.Controller.client;

import com.project.SportsStores.Toner.Model.CustomModel.ResponseCustom;
import com.project.SportsStores.Toner.Model.DTO.DiaChiDTO;
import com.project.SportsStores.Toner.Model.DiaChi;
import com.project.SportsStores.Toner.Service.Impl.DiaChiServiceImpl;
import com.project.SportsStores.Toner.Service.Impl.KhachHangServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/client/address")
public class DiaChiRestController {

    @Autowired
    private DiaChiServiceImpl service;

    @Autowired
    private KhachHangServiceImpl Customerservice;

    @RequestMapping(value = "/get-by-customer/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> getByCustomer(@PathVariable("id") String id) {
        return new ResponseEntity<>(service.getByIdKH(id), HttpStatus.OK);
    }

    @PostMapping("/save/{id}")
    private ResponseEntity<?> save(@PathVariable("id") String id, @RequestBody DiaChiDTO diaChi) {
        DiaChi dc = new DiaChi();
        dc.setKh(Customerservice.getByID(Long.parseLong(id)));
        dc.setDiaChiCuThe(diaChi.getDcct());
        List<DiaChi> list = service.getByIdKH(id);
        boolean isDefault = true;
        for (DiaChi address : list
        ) {
            if (address.isDefault()) {
                isDefault = false;
                break;
            }
        }
        dc.setDefault(isDefault);
        dc.setSdt(diaChi.getSdt());
        dc.setTtp(diaChi.getTtp());
        dc.setQh(diaChi.getQh());
        dc.setXp(diaChi.getXp());
        dc.setNgayTao(LocalDateTime.now());
        service.save(dc);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PostMapping("/update/{id}")
    private ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody DiaChiDTO diaChi) {
        DiaChi dc = service.getById(id);
        dc.setDiaChiCuThe(diaChi.getDcct());
        dc.setTtp(diaChi.getTtp());
        dc.setQh(diaChi.getQh());
        dc.setSdt(diaChi.getSdt());
        dc.setXp(diaChi.getXp());
        dc.setNgayTao(LocalDateTime.now());
        service.save(dc);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/validation/{id}")
    private ResponseEntity<?> validation(@PathVariable("id") String id, @RequestBody DiaChiDTO diaChi) {
        List<DiaChi> list = service.getByIdKH(id);
        List<ResponseCustom> listResponse = new ArrayList<>();
        String regexPhoneNumber = "^(\\+?84|0)(3[2-9]|5[2689]|7[06-9]|8[0-9]|9[0-9])\\d{7}$";
        String regexAddressDetail = "^(?=.*[\\p{L}])[\\p{L}\\p{N}][\\p{L}\\p{N}\\p{P}\\p{Z}]{3,253}[\\p{L}\\p{N}\\p{P}]$";
        boolean isValid = false;
        if (!diaChi.getSdt().matches(regexPhoneNumber)) {
            ResponseCustom response = new ResponseCustom();
            response.setStatusText("failure");
            response.setMessage("Error Number Phone");
            listResponse.add(response);
            isValid = true;
        }
        if (!diaChi.getDcct().matches(regexAddressDetail)) {
            ResponseCustom response = new ResponseCustom();
            response.setStatusText("failure");
            response.setMessage("Error Address Detail");
            listResponse.add(response);
            isValid = true;
        }
        for (DiaChi dc : list
        ) {
            if (diaChi.getTtp().equalsIgnoreCase(dc.getTtp())) {
                if (diaChi.getQh().equalsIgnoreCase(dc.getQh())) {
                    if (diaChi.getXp().equalsIgnoreCase(dc.getXp())) {
                        if (diaChi.getDcct().equalsIgnoreCase(dc.getDiaChiCuThe()) &&
                                diaChi.getSdt().equalsIgnoreCase(dc.getSdt())) {
                            if (diaChi.getSdt().equalsIgnoreCase(dc.getSdt())) {
                                ResponseCustom response = new ResponseCustom();
                                response.setMessage("Duplicate address");
                                response.setStatusText("failure");
                                listResponse.add(response);
                                isValid = true;
                            }
                        }
                    }
                }
            }
        }
        if (!isValid) {
            ResponseCustom response = new ResponseCustom();
            response.setStatusText("success");
            response.setMessage("No Error");
            listResponse.add(response);
        }
        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }

    @PostMapping("/validation-update/{id}/{idAddress}")
    private ResponseEntity<?> validationUpdate(@PathVariable("id") String id,
                                               @PathVariable("idAddress") String idAddress,
                                               @RequestBody DiaChiDTO diaChi) {
        List<DiaChi> list = service.getByIdKH(id);
        DiaChi diaChiUpdate = service.getById(idAddress);
        list.remove(diaChiUpdate);
        List<ResponseCustom> listResponse = new ArrayList<>();
        String regexPhoneNumber = "^(\\+?84|0)(3[2-9]|5[2689]|7[06-9]|8[0-9]|9[0-9])\\d{7}$";
        String regexAddressDetail = "^(?=.*[\\p{L}])[\\p{L}\\p{N}][\\p{L}\\p{N}\\p{P}\\p{Z}]{3,253}[\\p{L}\\p{N}\\p{P}]$";
        boolean isValid = false;
        if (!diaChi.getSdt().matches(regexPhoneNumber)) {
            ResponseCustom response = new ResponseCustom();
            response.setStatusText("failure");
            response.setMessage("Error Number Phone");
            listResponse.add(response);
            isValid = true;
        }
        if (!diaChi.getDcct().matches(regexAddressDetail)) {
            ResponseCustom response = new ResponseCustom();
            response.setStatusText("failure");
            response.setMessage("Error Address Detail");
            listResponse.add(response);
            isValid = true;
        }
        for (DiaChi dc : list
        ) {
            if (diaChi.getTtp().equalsIgnoreCase(dc.getTtp())) {
                if (diaChi.getQh().equalsIgnoreCase(dc.getQh())) {
                    if (diaChi.getXp().equalsIgnoreCase(dc.getXp())) {
                        if (diaChi.getDcct().equalsIgnoreCase(dc.getDiaChiCuThe()) &&
                                diaChi.getSdt().equalsIgnoreCase(dc.getSdt())) {
                            if (diaChi.getSdt().equalsIgnoreCase(dc.getSdt())) {
                                ResponseCustom response = new ResponseCustom();
                                response.setMessage("Duplicate address");
                                response.setStatusText("failure");
                                listResponse.add(response);
                                isValid = true;
                            }
                        }
                    }
                }
            }
        }
        if (!isValid) {
            ResponseCustom response = new ResponseCustom();
            response.setStatusText("success");
            response.setMessage("No Error");
            listResponse.add(response);
        }
        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }
}
