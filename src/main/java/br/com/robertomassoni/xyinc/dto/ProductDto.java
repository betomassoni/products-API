package br.com.robertomassoni.xyinc.dto;

import br.com.robertomassoni.xyinc.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDto {

    private final int id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final String category;
    
    
    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategoryId().getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public static List<ProductDto> toProduct(List<Product> productList) {
        ArrayList<ProductDto> productDtoList = new ArrayList();
        
        productList.stream().map((product) -> new ProductDto(product)).forEach((productDto) -> {
            productDtoList.add(productDto);
        });
        
        return productDtoList;
    }
  
}
