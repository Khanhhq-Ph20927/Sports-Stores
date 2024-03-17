package com.project.SportsStores.Toner.Controller.client;

import com.project.SportsStores.Toner.Service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/client/cart", "/api/cart"
})
public class GioHangRestController {
    @Autowired
    GioHangService service;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> getGHByIdKH(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.findByIdKH(id));
    }
}
