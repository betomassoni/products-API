/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.robertomassoni.xyinc.api;

import br.com.robertomassoni.xyinc.dto.RequisicaoNovaOferta;
import br.com.robertomassoni.xyinc.model.Product;
import br.com.robertomassoni.xyinc.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductRest {

    @Autowired
    ProductRepository productRepository;

    @GetMapping()
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable("id") String id) {
        return productRepository.findById(Integer.parseInt(id)).get();
    }

    @PostMapping("/{product}")
    public Product create(@PathVariable("product") Product product) {
        return productRepository.save(product);
    }

    @PutMapping
    public Product update(@Valid @RequestBody RequisicaoNovaOferta request) {
        
    }
    
    @DeleteMapping
    public String delete() {
        
    }
}
