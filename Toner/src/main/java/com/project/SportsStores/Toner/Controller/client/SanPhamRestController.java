package com.project.SportsStores.Toner.Controller.client;

import com.project.SportsStores.Toner.Model.CustomModel.FilterRequest;
import com.project.SportsStores.Toner.Model.SanPham;
import com.project.SportsStores.Toner.Service.SanPhamChiTietService;
import com.project.SportsStores.Toner.Service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/client/product",
        "/api/product"})
public class SanPhamRestController {
    @Autowired
    SanPhamService service;
    @Autowired
    SanPhamChiTietService chiTietService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    private ResponseEntity<?> getALL() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/priceMax", method = RequestMethod.GET)
    private ResponseEntity<?> PriceMax() {
        return new ResponseEntity<>(service.priceMax(), HttpStatus.OK);
    }


    @RequestMapping(value = "/page/{pageNumber}/{priceStart}/{priceEnd}", method = RequestMethod.POST)
    private ResponseEntity<?> paginationAndFilter(@PathVariable("pageNumber") String pageNumber,
                                                  @PathVariable("priceStart") String priceStart,
                                                  @PathVariable("priceEnd") String priceEnd,
                                                  @RequestBody FilterRequest filterRequest,
                                                  @RequestParam("sort") int sort,
                                                  @RequestParam("brand") String brand,
                                                  @RequestParam("sport") String sport) {
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber), 8, Sort.by("ngayTao").descending());
        Page<SanPham> page = null;
        if (sort == 1) {
            Sort sortCriteria = Sort.by(
                    Sort.Order.asc("tenSP"),
                    Sort.Order.desc("ngayTao")
            );
            pageable = PageRequest.of(Integer.parseInt(pageNumber), 8, sortCriteria);
            System.out.println("sort A-Z");
        } else if (sort == 2) {
            Sort sortCriteria = Sort.by(
                    Sort.Order.desc("tenSP"),
                    Sort.Order.desc("ngayTao")
            );
            pageable = PageRequest.of(Integer.parseInt(pageNumber), 8, sortCriteria);
            System.out.println("sort Z-A");
        } else if (sort == 3) {
            Sort sortCriteria = Sort.by(
                    Sort.Order.asc("donGia"),
                    Sort.Order.desc("ngayTao")

            );
            pageable = PageRequest.of(Integer.parseInt(pageNumber), 8, sortCriteria);
            System.out.println("sort price desc");
        } else if (sort == 4) {
            Sort sortCriteria = Sort.by(
                    Sort.Order.desc("donGia"),
                    Sort.Order.desc("ngayTao")
            );
            pageable = PageRequest.of(Integer.parseInt(pageNumber), 8, sortCriteria);
            System.out.println("sort price asc");
        } else {
            System.out.println("sort default");
        }
        //default

        if ((Integer.parseInt(priceStart) == 0 && Integer.parseInt(priceEnd) == service.priceMax()) && (filterRequest.getListColors() == null && filterRequest.getListSizes() == null)
        && (sport.equalsIgnoreCase("-1") && brand.equalsIgnoreCase("-1"))) {
            page = service.pageClient(pageable);
            System.out.println("Case Default");
        }
        //color
        else if ((Integer.parseInt(priceStart) == 0 && Integer.parseInt(priceEnd) == service.priceMax()) && filterRequest.getListColors() != null && filterRequest.getListSizes() == null
                && (sport.equalsIgnoreCase("-1") && brand.equalsIgnoreCase("-1"))) {
            page = service.filterColor(pageable, filterRequest.getListColors());
            System.out.println("Case " + 2);
        }
        //size
        else if ((Integer.parseInt(priceStart) == 0 && Integer.parseInt(priceEnd) == service.priceMax()) && filterRequest.getListColors() == null && filterRequest.getListSizes() != null
                && (sport.equalsIgnoreCase("-1") && brand.equalsIgnoreCase("-1"))) {
            page = service.filterSize(pageable, filterRequest.getListSizes());
            System.out.println("Case " + 3);
        }
        //brand
        else if ((Integer.parseInt(priceStart) == 0 && Integer.parseInt(priceEnd) == service.priceMax()) && filterRequest.getListColors() == null && filterRequest.getListSizes() == null
                && (sport.equalsIgnoreCase("-1") && !brand.equalsIgnoreCase("-1"))) {
            page = service.filterBrand(pageable, brand);
            System.out.println("Case " + 2);
        }
        //sport
        else if ((Integer.parseInt(priceStart) == 0 && Integer.parseInt(priceEnd) == service.priceMax()) && filterRequest.getListColors() == null && filterRequest.getListSizes() == null
                && (!sport.equalsIgnoreCase("-1") && brand.equalsIgnoreCase("-1"))) {
            page = service.filterSport(pageable, sport);
            System.out.println("Case " + 2);
        }
        //color+size
        else if ((Integer.parseInt(priceStart) == 0 && Integer.parseInt(priceEnd) == service.priceMax()) && filterRequest.getListColors() != null && filterRequest.getListSizes() != null
                && (sport.equalsIgnoreCase("-1") && brand.equalsIgnoreCase("-1"))) {
            page = service.filterColorAndSize(pageable, filterRequest.getListColors(), filterRequest.getListSizes());
            System.out.println("Case " + 4);
        }
        //price
        else if ((Integer.parseInt(priceStart) != 0 && Integer.parseInt(priceEnd) != service.priceMax() || Integer.parseInt(priceStart) == 0 && Integer.parseInt(priceEnd) != service.priceMax()
                || Integer.parseInt(priceStart) != 0 && Integer.parseInt(priceEnd) == service.priceMax()) && filterRequest.getListColors() == null && filterRequest.getListSizes() == null
                && (sport.equalsIgnoreCase("-1") && brand.equalsIgnoreCase("-1"))) {
            page = service.price(pageable, priceStart, priceEnd);
            System.out.println("Case " + 5);
        }
        //price+color
        else if ((Integer.parseInt(priceStart) != 0 && Integer.parseInt(priceEnd) != service.priceMax() || Integer.parseInt(priceStart) == 0 && Integer.parseInt(priceEnd) != service.priceMax()
                || Integer.parseInt(priceStart) != 0 && Integer.parseInt(priceEnd) == service.priceMax()) && filterRequest.getListColors() != null && filterRequest.getListSizes() == null
                && (sport.equalsIgnoreCase("-1") && brand.equalsIgnoreCase("-1"))) {
            page = service.priceAndFilterColor(pageable, priceStart, priceEnd, filterRequest.getListColors());
            System.out.println("Case " + 6);
        }
        //price+size
        else if ((Integer.parseInt(priceStart) != 0 && Integer.parseInt(priceEnd) != service.priceMax() || Integer.parseInt(priceStart) == 0 && Integer.parseInt(priceEnd) != service.priceMax()
                || Integer.parseInt(priceStart) != 0 && Integer.parseInt(priceEnd) == service.priceMax()) && filterRequest.getListColors() == null && filterRequest.getListSizes() != null
                && (sport.equalsIgnoreCase("-1") && brand.equalsIgnoreCase("-1"))) {
            page = service.priceAndFilterSize(pageable, priceStart, priceEnd, filterRequest.getListSizes());
            System.out.println("Case " + 7);
        }
        //price+color+size
        else if ((Integer.parseInt(priceStart) != 0 && Integer.parseInt(priceEnd) != service.priceMax() || Integer.parseInt(priceStart) == 0 && Integer.parseInt(priceEnd) != service.priceMax()
                || Integer.parseInt(priceStart) != 0 && Integer.parseInt(priceEnd) == service.priceMax()) && filterRequest.getListColors() != null && filterRequest.getListSizes() != null
                && (sport.equalsIgnoreCase("-1") && brand.equalsIgnoreCase("-1"))) {
            page = service.priceAndFilterColorAndSize(pageable, priceStart, priceEnd, filterRequest.getListColors(), filterRequest.getListSizes());
            System.out.println("Case " + 8);
        } else {
            page = service.pageClient(pageable);
            System.out.println("Error??");
        }

        return new ResponseEntity<>(page, HttpStatus.OK);
    }


}
