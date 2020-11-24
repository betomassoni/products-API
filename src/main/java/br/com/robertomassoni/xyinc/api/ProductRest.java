/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.robertomassoni.xyinc.api;

import br.com.robertomassoni.xyinc.dto.ProductDto;
import br.com.robertomassoni.xyinc.form.ProductForm;
import br.com.robertomassoni.xyinc.model.Product;
import br.com.robertomassoni.xyinc.repository.CategoryRepository;
import br.com.robertomassoni.xyinc.repository.ProductRepository;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/product")
public class ProductRest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping()
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable("id") String id) {
        return productRepository.findById(Integer.parseInt(id)).get();
    }

    @PostMapping()
    public ResponseEntity<ProductDto> create(@RequestBody ProductForm form, UriComponentsBuilder uriBuilder) {

        Product product = form.toProduct(categoryRepository);
        productRepository.save(product);

        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDto(product));
    }

//    @PutMapping
//    public Product update(@Valid @RequestBody ProductRequest request) {
//        return "";
//    }
//    
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") Integer id) {
//        return "";
//    }
}
