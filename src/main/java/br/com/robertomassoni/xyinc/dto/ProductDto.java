package br.com.robertomassoni.xyinc.dto;


import br.com.robertomassoni.xyinc.model.Product;



public class ProductDto {
    
    private int id;
    private String name;
    private double price;
    private String category;
    
    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
//        this.price = product.getPrice();
        this.category = product.getCategoryId().getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }        
}
