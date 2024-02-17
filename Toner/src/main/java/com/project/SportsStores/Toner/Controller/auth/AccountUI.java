package com.project.SportsStores.Toner.Controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AccountUI {
    @RequestMapping("/auth-logout-basic")
    public String logout() {
        return "auth/auth-logout-basic";
    }

    @RequestMapping("/auth-pass-reset-basic")
    public String reset() {
        return "auth/auth-pass-reset-basic";
    }

    @RequestMapping("/auth-signin-basic")
    public String signin() {
        return "auth/auth-signin-basic";
    }

    @RequestMapping("/auth-signup-basic")
    public String signup() {
        return "auth/auth-signup-basic";
    }

    @RequestMapping("/403-error")
    public String error403() {
        return "auth/auth-404";
    }
}
