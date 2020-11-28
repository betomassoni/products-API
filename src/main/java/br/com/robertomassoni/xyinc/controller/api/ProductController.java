package br.com.robertomassoni.xyinc.controller.api;

import br.com.robertomassoni.xyinc.controller.request.ProductRequest;
import br.com.robertomassoni.xyinc.dto.mapper.PageableMapper;
import br.com.robertomassoni.xyinc.dto.mapper.ProductMapper;
import br.com.robertomassoni.xyinc.dto.model.ProductDto;
import br.com.robertomassoni.xyinc.dto.response.Response;
import br.com.robertomassoni.xyinc.repository.CategoryRepository;
import br.com.robertomassoni.xyinc.repository.ProductRepository;
import br.com.robertomassoni.xyinc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Response getAll(Pageable pageable) {
        Page<ProductDto> pageProductDto = productService.getAllProducts(pageable);               
        return Response.ok().setContent(pageProductDto.getContent()).setPageable(PageableMapper.toPageableDto(pageProductDto));
    }

    @GetMapping(value="/{id}")
    public Response get(@PathVariable("id") Integer id) {
        return Response.ok().setContent(productService.getProduct(id));
    } 

    @PostMapping()
    public Response create(@RequestBody ProductRequest productRequest) {
        return Response.ok().setContent(productService.addProduct(ProductMapper.toProductDto(productRequest)));
    }
    
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") Integer id, @RequestBody ProductRequest productRequest) {
        return Response.ok().setContent(productService.updateProduct(id, ProductMapper.toProductDto(productRequest)));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Integer id) {
        return Response.ok().setContent(productService.deleteProduct(id));
    }
}
