package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Service.ServiceTheLoai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/sport")
@RestController
public class RestControllerTheLoai {

    @Autowired
    private ServiceTheLoai service;

    @GetMapping("/find-all")
    private ResponseEntity<?> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
