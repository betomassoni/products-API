package br.com.robertomassoni.xyinc.service;

import br.com.robertomassoni.xyinc.dto.model.ProductDto;
import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getProduct(Integer id);

    ProductDto addProduct(ProductDto productDto);
    
    ProductDto deleteProduct(Integer id);
    
    ProductDto updateProduct(Integer id, ProductDto productDto);
}
