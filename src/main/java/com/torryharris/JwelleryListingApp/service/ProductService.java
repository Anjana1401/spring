package com.torryharris.JwelleryListingApp.service;

import com.torryharris.JwelleryListingApp.model.Product;
import com.torryharris.JwelleryListingApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> search(String keyword){
        return productRepository.search(keyword);
    }
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public void addProduct(Product product){
        productRepository.save(product);
    }
    public int removeProductById(long id){
        productRepository.deleteById(id);
        return 0;
    }
    public Optional<Product> getProductById(long id){
        return productRepository.findById(id);
    }
    public List<Product> getAllProductByCategoryId(int id){
        return productRepository.findAllByCategory_Id(id);
    }
    public int getStockDec(int id)
    {
        return productRepository.StockDecrement(id);
    } //decrement


    public List<Product> all()
    {
     return productRepository.findAll();
    }
    public int getStockInc(int id) {
        return productRepository.StockIncrement(id);
    }

}
