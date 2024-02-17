package com.project.SportsStores.Toner.Controller;

import com.project.SportsStores.Toner.Model.CustomModel.AuthRequest;
import com.project.SportsStores.Toner.Model.CustomModel.AuthResponse;
import com.project.SportsStores.Toner.Model.NhanVien;
import com.project.SportsStores.Toner.Repository.KhachHangRepository;
import com.project.SportsStores.Toner.Repository.NhanVienRepository;
import com.project.SportsStores.Toner.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    @Autowired
    NhanVienRepository nvrp;
    @Autowired
    KhachHangRepository khrp;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @GetMapping("/test")
    public List<NhanVien> test() {
        return nvrp.findAll();
    }

    @GetMapping("/test2/{email}")
    public NhanVien test2(@PathVariable("email") String email) {
        System.out.println(email);
        return nvrp.getByEmail(email).get();
    }


}

