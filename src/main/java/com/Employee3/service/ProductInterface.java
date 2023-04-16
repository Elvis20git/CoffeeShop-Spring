package com.Employee3.service;

import com.Employee3.model.Product;

import java.util.List;

public interface ProductInterface {

    Product registerProduct(Product prod);
    Product updateProduct(Product prod);
    void deleteProductById(Product prod);
    List<Product> Productlist();

    Product findProductById(Product prod);
}
