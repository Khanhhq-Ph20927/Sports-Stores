package com.project.SportsStores.Toner.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/sell-off")
public class BanHangTaiQuayController {
    @RequestMapping("")
    public String satc() {
        return "admin/satc";
    }
}
