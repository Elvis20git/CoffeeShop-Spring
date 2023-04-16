package com.Employee3.controller;

import com.Employee3.model.Product;
import com.Employee3.repository.ProductRepository;
import com.Employee3.service.Implementation.ProductNotFoundException;

import com.Employee3.service.Implementation.ProductServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductRepository repo;
    private final ProductServiceImplementation productService;

    public ProductController(ProductServiceImplementation productService) {
        this.productService = productService;
//        this.productService = productService;
    }

//    @GetMapping("/")
//    public String Display(Model px){
//        px.addAttribute("Product_List", productService);
//        return "Display";
//    }


    @GetMapping("/registerProduct")
    public String getRegister_Page(Model model) {
        model.addAttribute("productRequest", new Product());
        model.addAttribute("RegistrationPage", "Add new Product");
        return "product_page";
    }

    @GetMapping("products")
    public String productList(Model model){
        List<Product> ListOfProduct = productService.Productlist();
        model.addAttribute("ListOfProduct", ListOfProduct);

       return "Display";
    }

    @PostMapping("/registerProduct")
    public String register(@ModelAttribute Product product) {
        System.out.println("register request :" + product);
        Product registeredProduct = productService.registerProduct(product);

        return registeredProduct == null ? "error_page" : "redirect:/products";
    }


    @GetMapping("/UpdateProduct/{id}")
    public String UpdateProduct(@PathVariable("id") Integer id, Model model){
        Optional<Product> procts = repo.findFirstById(id);
        Product product = procts.get();
        model.addAttribute("product", product);

        return"EditPage";

    }





    @GetMapping("DeleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        repo.deleteById(id);

        return"redirect:/products";

    }


}
