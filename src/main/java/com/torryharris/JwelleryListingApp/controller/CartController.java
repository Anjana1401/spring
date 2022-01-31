package com.torryharris.JwelleryListingApp.controller;

import com.torryharris.JwelleryListingApp.global.GlobalData;
import com.torryharris.JwelleryListingApp.model.Product;
import com.torryharris.JwelleryListingApp.repository.ProductRepository;
import com.torryharris.JwelleryListingApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id,Model model){
          GlobalData.cart.add(productService.getProductById(id).get()); //get entire d
          model.addAttribute("stock",productService.getStockDec(id)); //stock--> stock-1
         // System.out.print(model.addAttribute("stock",productService.getStockDec(id)));
          return "redirect:/shop";
    }
    @GetMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cartCount",GlobalData.cart.size()); //cartvalue
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum()); //price
        model.addAttribute("cart",GlobalData.cart); //details
        model.addAttribute("all",productService.all());
        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index, Model model){ //remove from cart product(id,name...)

        GlobalData.cart.remove(index);

      //  model.addAttribute("stock",productService.getStockInc(GlobalData.cart.indexOf(index)));
       // System.out.println(model.addAttribute("stock",productService.getStockInc(GlobalData.cart.indexOf(index))));//stock+1
        return "redirect:/cart";
    }


//    @GetMapping("/cart/cancel/{id}")
//    public String CartDec(@PathVariable int id,Model model)
//    {
//        model.addAttribute("product",productService.getAllProduct());
//        model.addAttribute("stock",productService.getStockDec(id));
//        return "redirect:/shop";
//    }

    @GetMapping("/checkout")
    public String checkOut(Model model){
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }
}
