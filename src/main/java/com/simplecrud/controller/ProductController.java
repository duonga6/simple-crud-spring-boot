package com.simplecrud.controller;

import com.simplecrud.model.Product;
import com.simplecrud.model.ResponseObject;
import com.simplecrud.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductRepository _productRepository;

    @GetMapping
    public ResponseEntity<ResponseObject> GetAll() {
        return ResponseEntity.ok().body(
                new ResponseObject("ok", "Get products success", _productRepository.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> GetById(@PathVariable String id) {
        var productFind = _productRepository.findById(id);

        return productFind.isPresent() ?
                ResponseEntity.ok(
                        new ResponseObject("ok", "Get product success", productFind)
                ) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ResponseObject> Create(@RequestBody Product newProduct) {
        return ResponseEntity.ok(
                new ResponseObject("ok", "Create product success", _productRepository.save(newProduct))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> Update(@PathVariable String id, @RequestBody Product updateProduct) {
        var updatedProduct = _productRepository.findById(id).map(product -> {
            product.setName(updateProduct.getName());
            product.setImage(updateProduct.getImage());
            product.setPrice(updateProduct.getPrice());
            product.setQuantity(updateProduct.getQuantity());
            return _productRepository.save(product);
        });

        return updatedProduct.isPresent() ?
            ResponseEntity.ok().body(
                    new ResponseObject("ok", "Update product success", updatedProduct)
            ) :
            ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> Delete(@PathVariable String id) {
        var existProduct = _productRepository.existsById(id);
        if (existProduct) {
            _productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
