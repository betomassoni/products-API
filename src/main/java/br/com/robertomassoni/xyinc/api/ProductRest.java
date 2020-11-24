/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.robertomassoni.xyinc.api;

import br.com.robertomassoni.xyinc.dto.ProductDto;
import br.com.robertomassoni.xyinc.request.ProductRequest;
import br.com.robertomassoni.xyinc.model.Product;
import br.com.robertomassoni.xyinc.repository.CategoryRepository;
import br.com.robertomassoni.xyinc.repository.ProductRepository;
import java.net.URI;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/api/products")
public class ProductRest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping()
    public List<ProductDto> getAll() {
        List<Product> productList = productRepository.findAll();
        return ProductDto.toProduct(productList);
    }

    @GetMapping("/{id}")
    public ProductDto get(@PathVariable("id") Integer id) {
        Product product = productRepository.findById(id).get();                
        return new ProductDto(product);
    }

    @PostMapping()
    public ResponseEntity<ProductDto> create(@RequestBody ProductRequest form, UriComponentsBuilder uriBuilder) {
        Product product = form.toProduct(form.createIfNotExist(categoryRepository));
        productRepository.save(product);
        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDto(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Integer id, @RequestBody ProductRequest form) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            form.update(optional.get(), productRepository, categoryRepository);
            return ResponseEntity.ok(new ProductDto(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }
//    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
