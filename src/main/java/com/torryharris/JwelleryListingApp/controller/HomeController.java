package com.torryharris.JwelleryListingApp.controller;

import com.torryharris.JwelleryListingApp.global.GlobalData;
import com.torryharris.JwelleryListingApp.model.Product;
import com.torryharris.JwelleryListingApp.service.CategoryService;
import com.torryharris.JwelleryListingApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @GetMapping({"/","/home"})
    public String home(Model model){
        model.addAttribute("cartCount",GlobalData.cart.size());
        return "index";
    }
    @GetMapping("/logout")
    public String logout(Model model){
        model.addAttribute("cart",GlobalData.cart.removeAll(GlobalData.cart));
        return "sign";
    }
    @GetMapping("/shop/search")
    public String search(@Param("keyword") String keyword,Model model){
        List<Product> searchresult=productService.search(keyword);
        model.addAttribute("searchresult",searchresult);
        return "searchResult";
    }
    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getAllProduct());
        model.addAttribute("cartCount",GlobalData.cart.size());
        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String shopCategoryById(Model model, @PathVariable int id){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("products",productService.getAllProductByCategoryId(id));
        return "shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable int id){
        model.addAttribute("product",productService.getProductById(id).get());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "viewProduct";
    }

}
