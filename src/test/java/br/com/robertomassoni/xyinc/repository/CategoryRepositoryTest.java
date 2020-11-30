package br.com.robertomassoni.xyinc.repository;

import br.com.robertomassoni.xyinc.dto.model.CategoryDto;
import br.com.robertomassoni.xyinc.service.CategoryService;
import java.util.List;
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
public class CategoryRepositoryTest {
    
    @Autowired
    CategoryService categoryService;    
    
    @Test 
    public void testAaddCategoryIfNotExist() {
        CategoryDto newCategory = categoryService.addCategoryIfNotExist("Nova Categoria");
        Assert.assertNotNull(newCategory);       
    }
    
    @Test 
    public void testBgetCategoryById() {
        CategoryDto newCategory = categoryService.getCategory(1);
        Assert.assertNotNull(newCategory);  
    }
    
    @Test 
    public void testCgetCategoryByName() {
        CategoryDto newCategory = categoryService.getCategory("Nova Categoria");
        Assert.assertNotNull(newCategory);  
    }
    
    @Test 
    public void testDgetAllCategories() {
        List<CategoryDto> categoryList = categoryService.getAllCategories();
        Assert.assertNotNull(categoryList);
        Assert.assertTrue(categoryList.size() > 0);
    }
    
}
