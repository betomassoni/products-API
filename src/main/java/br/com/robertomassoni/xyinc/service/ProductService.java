package br.com.robertomassoni.xyinc.service;

import br.com.robertomassoni.xyinc.dto.model.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    Page<ProductDto> getAllProducts(Pageable pageable);

    ProductDto getProduct(Integer id);

    ProductDto addProduct(ProductDto productDto);
    
    ProductDto deleteProduct(Integer id);
    
    ProductDto updateProduct(Integer id, ProductDto productDto);
}
