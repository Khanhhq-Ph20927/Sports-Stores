package com.project.SportsStores.Toner.Controller.client;


import com.project.SportsStores.Toner.Service.Impl.ServiceTheLoaiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/client/sports")
@RestController
public class MonTheThaoRestController {

    @Autowired
    ServiceTheLoaiImpl service;

    @GetMapping("/find-all")
    private ResponseEntity<?> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
