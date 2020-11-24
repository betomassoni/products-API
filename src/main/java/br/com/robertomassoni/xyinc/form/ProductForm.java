package br.com.robertomassoni.xyinc.form;

import br.com.robertomassoni.xyinc.model.Category;
import br.com.robertomassoni.xyinc.model.Product;
import br.com.robertomassoni.xyinc.repository.CategoryRepository;

public class ProductForm {

    private String name;
    private String description;
    private String category;
    private double price;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product toProduct(CategoryRepository categoryRepository) {
        Category categoryFound = categoryRepository.findByName(this.category);
        if (categoryFound == null) {
            Category newCategory = new Category();
            newCategory.setName(this.category);
            categoryRepository.save(newCategory);            
            categoryFound = newCategory;
        }         
        return toProduct(categoryFound);
    }
    
    public Product toProduct(Category category) {        
        Product product = new Product();
        product.setName(this.name);
        product.setDescription(this.description);
        product.setCategoryId(category);
        return product;
    }

}
