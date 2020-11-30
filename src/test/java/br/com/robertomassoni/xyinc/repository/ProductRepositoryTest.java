package br.com.robertomassoni.xyinc.repository;

import br.com.robertomassoni.xyinc.dto.model.ProductDto;
import br.com.robertomassoni.xyinc.service.ProductService;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductRepositoryTest {

    @Autowired
    ProductService productService;

    private static Integer newProductId = null;

    @Test
    public void testAaddProducts() {     
        ProductDto newProduct = productService.addProduct(this.createNewProduct());
        if (newProduct != null) {
            ProductRepositoryTest.newProductId = newProduct.getId();
        }
        Assert.assertNotNull(newProduct);
    }
    
    @Test 
    public void testBupdateProduct() {
        ProductDto productDto = productService.updateProduct(ProductRepositoryTest.newProductId, this.updateNewProduct());
        Assert.assertNotNull(productDto);       
    }
    
    @Test 
    public void testCgetProduct() {
        ProductDto productDto = productService.getProduct(ProductRepositoryTest.newProductId);
        Assert.assertNotNull(productDto);
    }
    
    @Test 
    public void testDdeleteProduct() {
        ProductDto productDto = productService.deleteProduct(ProductRepositoryTest.newProductId);
        Assert.assertNotNull(productDto);       
    }
        
    private ProductDto createNewProduct() {
        ProductDto product = new ProductDto();
        product.setName("Produto Teste");
        product.setDescription("Descrição do Produto Teste");
        product.setCategory("Categoria Teste");
        product.setPrice(new BigDecimal(1.99));
        return product;
    }
    
    private ProductDto updateNewProduct() {
        ProductDto product = new ProductDto();
        product.setName("Produto Teste Alterado");
        product.setDescription("Descrição do Produto Teste");
        product.setCategory("Categoria Teste");
        product.setPrice(new BigDecimal(99999.99));
        return product;
    }
}
