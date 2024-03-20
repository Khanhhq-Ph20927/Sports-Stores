package com.project.SportsStores.Toner.Controller;

import com.project.SportsStores.Toner.Model.CustomModel.AuthRequest;
import com.project.SportsStores.Toner.Model.CustomModel.AuthResponse;
import com.project.SportsStores.Toner.Model.KhachHang;
import com.project.SportsStores.Toner.Model.NhanVien;
import com.project.SportsStores.Toner.Repository.KhachHangRepository;
import com.project.SportsStores.Toner.Repository.NhanVienRepository;
import com.project.SportsStores.Toner.Service.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/getStaff/{email}")
    public NhanVien getStaff(@PathVariable("email") String email, HttpSession session) {
        System.out.println(email);
        if(nvrp.getByEmail(email).isPresent()) {
            session.setAttribute("idUser",String.valueOf(nvrp.getByEmail(email).get().getId()));
            return nvrp.getByEmail(email).get();
        } else {
            return null;
        }
    }
    @PostMapping("/getCustomer/{email}")
    public KhachHang getCustomer(@PathVariable("email") String email,HttpSession session) {
        System.out.println(email);
        if(khrp.findByEmail(email).isPresent()) {
            session.setAttribute("idUser",String.valueOf(khrp.getByEmail(email).get().getId()));
            return khrp.getByEmail(email).get();
        } else {
            return null;
        }
    }

}

