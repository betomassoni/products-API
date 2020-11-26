package br.com.robertomassoni.xyinc.controller.api;

import br.com.robertomassoni.xyinc.controller.request.ProductRequest;
import br.com.robertomassoni.xyinc.dto.mapper.ProductMapper;
import br.com.robertomassoni.xyinc.dto.response.Response;
import br.com.robertomassoni.xyinc.model.Product;
import br.com.robertomassoni.xyinc.repository.CategoryRepository;
import br.com.robertomassoni.xyinc.repository.ProductRepository;
import br.com.robertomassoni.xyinc.service.ProductService;
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

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping()
    public Response getAll() {
        return Response.ok().setPayload(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable("id") Integer id) {
        return Response.ok().setPayload(productService.getProduct(id));
    }

    @PostMapping()
    public Response create(@RequestBody ProductRequest productRequest) {
        return Response.ok().setPayload(productService.addProduct(ProductMapper.toProductDto(productRequest)));
    }
    
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") Integer id, @RequestBody ProductRequest productRequest) {
        return Response.ok().setPayload(productService.updateProduct(id, ProductMapper.toProductDto(productRequest)));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Integer id) {
        return Response.ok().setPayload(productService.deleteProduct(id));
    }
}
