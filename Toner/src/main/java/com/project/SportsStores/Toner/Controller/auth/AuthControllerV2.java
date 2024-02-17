package com.project.SportsStores.Toner.Controller.auth;

import com.project.SportsStores.Toner.Model.CustomModel.AuthRequest;
import com.project.SportsStores.Toner.Model.CustomModel.AuthResponse;
import com.project.SportsStores.Toner.Repository.KhachHangRepository;
import com.project.SportsStores.Toner.Repository.NhanVienRepository;
import com.project.SportsStores.Toner.Service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v2/auth")
public class AuthControllerV2 {

    private static final String secret_key = "123";

    @Autowired
    NhanVienRepository nvrp;
    @Autowired
    KhachHangRepository khrp;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@ModelAttribute("authRequest") AuthRequest authRequest, HttpSession session) {
        AuthResponse response = authenticationService.authenticate(authRequest);
        System.out.println(authRequest.getUsername() + " " + authRequest.getPassword());
        session.setAttribute("access_token", response.getToken());
        System.out.println("token " + response.getToken());
        if (khrp.getByEmail(authRequest.getUsername()).isPresent()) {
            session.setAttribute("username", khrp.getByEmail(authRequest.getUsername()).get().getHoTen());
            return "redirect:/index/home";
        } else {
            if (nvrp.getByEmail(authRequest.getUsername()).isPresent() &&
                    nvrp.getByEmail(authRequest.getUsername()).get().getCv().getId() == 2) {
                session.setAttribute("username", nvrp.getByEmail(authRequest.getUsername()).get().getHoTen());
                return "redirect:/admin/index";
            } else {
                //tạm thời chưa có UI cho nhân viên, chưa thống nhất có lên làm thêm roles nhân viên hay không?
                return "redirect:/admin/index";
            }
        }
    }

    @GetMapping("/log-out")
    private String logout(HttpSession session){
        session.removeAttribute("access_token");
        session.removeAttribute("username");
        return "redirect:/auth/auth-signin-basic";
    }
}
