package com.project.SportsStores.Toner.Controller.client;

import com.project.SportsStores.Toner.Service.Impl.DiaChiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/address")
public class DiaChiRestController {

    @Autowired
    private DiaChiServiceImpl service;

    @RequestMapping("/get-by-customer/{id}")
    private ResponseEntity<?> getByCustomer(@PathVariable("id") String id){
        return new ResponseEntity<>(service.getByIdKH(id),HttpStatus.OK);
    }
}
