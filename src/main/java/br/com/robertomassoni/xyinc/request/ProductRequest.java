package br.com.robertomassoni.xyinc.request;

import br.com.robertomassoni.xyinc.model.Category;
import br.com.robertomassoni.xyinc.model.Product;
import br.com.robertomassoni.xyinc.repository.CategoryRepository;
import br.com.robertomassoni.xyinc.repository.ProductRepository;
import java.math.BigDecimal;

public class ProductRequest {

    private String name;
    private String description;
    private String category;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
 
    public Product toProduct(Category category) {        
        Product product = new Product();
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setCategoryId(category);
        return product;
    }
    
    public Category createIfNotExist(CategoryRepository categoryRepository) {
        Category categoryFound = categoryRepository.findByName(this.category);
        if (categoryFound == null) {
            Category newCategory = new Category();
            newCategory.setName(this.category);
            categoryRepository.save(newCategory);            
            categoryFound = newCategory;
        }      
        return categoryFound;
    }
    
    public Product update(Product productToUpdate, ProductRepository productRepository, CategoryRepository categoryRepository) {
        productToUpdate.setName(this.name);
        productToUpdate.setDescription(this.description);
        productToUpdate.setPrice(this.price);
        productToUpdate.setCategoryId(this.createIfNotExist(categoryRepository));
        productRepository.save(productToUpdate);
        return productToUpdate;
    }

}
