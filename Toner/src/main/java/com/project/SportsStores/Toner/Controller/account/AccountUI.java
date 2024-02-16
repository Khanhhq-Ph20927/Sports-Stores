package com.project.SportsStores.Toner.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountUI {
    @RequestMapping("/auth-logout-basic")
    public String logout() {
        return "account/auth-logout-basic";
    }

    @RequestMapping("/auth-pass-reset-basic")
    public String reset() {
        return "account/auth-pass-reset-basic";
    }

    @RequestMapping("/auth-signin-basic")
    public String signin() {
        return "account/auth-signin-basic";
    }

    @RequestMapping("/auth-signup-basic")
    public String signup() {
        return "account/auth-signup-basic";
    }
}
