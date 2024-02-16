package com.project.SportsStores.Toner.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminUI {
    @RequestMapping("/index")
    public String home() {
        return "admin/index";
    }

    @RequestMapping("/account")
    public String account() {
        return "admin/profile/auth";
    }

    @RequestMapping("/account-settings")
    public String accountSeetting() {
        return "admin/profile/auth-settings";
    }

    @RequestMapping("/invoices-create")
    public String invoicesCreate() {
        return "admin/invoice/invoices-create";
    }

    @RequestMapping("/invoices-details")
    public String invoicesDetails() {
        return "admin/invoice/invoices-details";
    }

    @RequestMapping("/invoices-list")
    public String invoicesList() {
        return "admin/invoice/invoices-list";
    }

    @RequestMapping("/orders-list-view")
    public String ordersList() {
        return "admin/orders/orders-list-view";
    }

    @RequestMapping("/orders-overview")
    public String ordersOverview() {
        return "admin/orders/orders-overview";
    }

    @RequestMapping("/categories")
    public String categories() {
        return "admin/products/categories";
    }

    @RequestMapping("/product-create")
    public String productCreate() {
        return "admin/products/product-create";
    }

    @RequestMapping("/product-grid")
    public String productGrid() {
        return "admin/products/product-grid";
    }

    @RequestMapping("/product-list")
    public String productList() {
        return "admin/products/product-list";
    }

    @RequestMapping("/product-overview")
    public String productOverview() {
        return "admin/products/product-overview";
    }

    @RequestMapping("/sub-categories")
    public String subCategories() {
        return "admin/products/sub-categories";
    }

    @RequestMapping("/seller-overview")
    public String sellerOverview() {
        return "admin/sellers/seller-overview";
    }

    @RequestMapping("/sellers-grid-view")
    public String sellersGrid() {
        return "admin/sellers/sellers-grid-view";
    }

    @RequestMapping("/sellers-list-view")
    public String sellersList() {
        return "admin/sellers/sellers-list-view";
    }

    @RequestMapping("/shipments")
    public String shipments() {
        return "admin/shipping/shipments";
    }

    @RequestMapping("/shipping-list")
    public String shippingList() {
        return "admin/shipping/shipping-list";
    }

    @RequestMapping("/brands")
    public String brands() {
        return "admin/brands";
    }

    @RequestMapping("/calendar")
    public String calendar() {
        return "admin/calendar";
    }

    @RequestMapping("/compact")
    public String compact() {
        return "admin/compact";
    }

    @RequestMapping("/coupons")
    public String coupons() {
        return "admin/coupons";
    }

    @RequestMapping("/currency-rates")
    public String currencyRates() {
        return "admin/currency-rates";
    }

    @RequestMapping("/detached")
    public String detached() {
        return "admin/detached";
    }

    @RequestMapping("/horizontal")
    public String horizontal() {
        return "admin/horizontal";
    }

    @RequestMapping("/reviews-ratings")
    public String reviewsRatings() {
        return "admin/reviews-ratings";
    }

    @RequestMapping("/statistics")
    public String statistics() {
        return "admin/statistics";
    }

    @RequestMapping("/transactions")
    public String transactions() {
        return "admin/transactions";
    }

    @RequestMapping("/two-column")
    public String twoColumn() {
        return "admin/two-column";
    }

    @RequestMapping("/users-list")
    public String usersList() {
        return "admin/users-list";
    }
}
