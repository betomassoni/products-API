package br.com.robertomassoni.xyinc.dto.mapper;

import br.com.robertomassoni.xyinc.controller.request.ProductRequest;
import br.com.robertomassoni.xyinc.dto.model.ProductDto;
import br.com.robertomassoni.xyinc.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ProductMapper {

    public static ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        if (product != null) {
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setCategory(product.getCategoryId().getName());
        }
        return productDto;
    }

    public static List<ProductDto> toProductDto(List<Product> productList) {
        ArrayList<ProductDto> productDtoList = new ArrayList();

        for (Product product : productList) {
            productDtoList.add(toProductDto(product));
        }

        return productDtoList;
    }

    public static ProductDto toProductDto(ProductRequest productRequest) {
        ProductDto productDto = new ProductDto();
        if (productRequest != null) {
            productDto.setName(productRequest.getName());
            productDto.setDescription(productRequest.getDescription());
            productDto.setPrice(productRequest.getPrice());
            productDto.setCategory(productRequest.getCategory());
        }
        return productDto;
    } 

    public static Page<ProductDto> toPageProductDto(List<ProductDto> productDtoList, Pageable pageable, Page<Product> pageProduct) {        
        return new PageImpl<>(productDtoList, pageable, pageProduct.getTotalElements());
    }


}
