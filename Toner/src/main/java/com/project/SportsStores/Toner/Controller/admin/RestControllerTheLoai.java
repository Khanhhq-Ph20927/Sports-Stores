package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Service.ServiceTheLoai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/admin/sport")
@RestController
public class RestControllerTheLoai {

    @Autowired
    private ServiceTheLoai service;

    @GetMapping("/find-all")
    private ResponseEntity<?> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    private ResponseEntity<?> findById(@PathVariable("id") String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
