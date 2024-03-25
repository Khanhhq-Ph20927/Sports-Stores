package com.project.SportsStores.Toner.Controller.client;

import com.project.SportsStores.Toner.Model.CustomModel.ResponseCustom;
import com.project.SportsStores.Toner.Service.Impl.AnhSanPhamServiceImpl;
import com.project.SportsStores.Toner.Service.Impl.SanPhamChiTietServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/client/product_detail", "/api/product_detail"
})
public class SanPhamChiTietRestController {
    @Autowired
    private SanPhamChiTietServiceImpl sv;
    @Autowired
    private AnhSanPhamServiceImpl serviceASP;

    @RequestMapping(value = "", method = RequestMethod.GET)
    private ResponseEntity<?> getAll() {
        return new ResponseEntity<>(sv.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/detailPD/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> getByid(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(sv.getListByIdSp(id));
    }
    @RequestMapping(value = "/detail/{id}/{color}", method = RequestMethod.GET)
    private ResponseEntity<?> findIdProductAndColor(@PathVariable("id") String id,@PathVariable("color") String color) {
        return ResponseEntity.ok().body(sv.findListProductByColor(id,color));
    }

    @RequestMapping(value = "/picture/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> getPictureByIdProductDetail(@PathVariable("id") String id) {
        if (serviceASP.getByIdProduct(id).size() == 0) {
            System.out.println("Product Picture is null");
            ResponseCustom responseCustom = new ResponseCustom();
            responseCustom.setStatusText("failure");
            responseCustom.setMessage("List Picture Is Null");
            return ResponseEntity.ok().body(responseCustom);
        }
        System.out.println(serviceASP.getByIdProduct(id).toString());
        return ResponseEntity.ok().body(serviceASP.getByIdProduct(id));
    }

    @RequestMapping(value = "/picture-detail/{id}/{color}", method = RequestMethod.GET)
    private ResponseEntity<?> getPictureByIdProductAndColor(@PathVariable("id") String id,
                                                            @PathVariable("color") String color) {
        if (serviceASP.getByIdProductAndColor(id,color).size() == 0) {
            System.out.println("Product Picture is null");
            ResponseCustom responseCustom = new ResponseCustom();
            responseCustom.setStatusText("failure");
            responseCustom.setMessage("List Picture Is Null");
            return ResponseEntity.ok().body(responseCustom);
        }
        System.out.println(serviceASP.getByIdProductAndColor(id,color).toString());
        return ResponseEntity.ok().body(serviceASP.getByIdProductAndColor(id,color));
    }
    @RequestMapping(value = "/detailSL/{id}/{color}/{size}", method = RequestMethod.GET)
    private ResponseEntity<?> findIdProductAndColorAndSize(@PathVariable("id") String id, @PathVariable("color") String color, @PathVariable("size") String size) {
        return ResponseEntity.ok().body(sv.findIdProductByColorAndSize(id, color, size));
    }


}
