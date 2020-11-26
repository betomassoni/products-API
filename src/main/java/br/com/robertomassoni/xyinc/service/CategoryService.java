package br.com.robertomassoni.xyinc.service;

import br.com.robertomassoni.xyinc.dto.model.CategoryDto;
import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategory(Integer id);
    
    CategoryDto getCategory(String name);

    CategoryDto addCategoryIfNotExist(String name);
    
    CategoryDto addCategory(CategoryDto categoryDto);
}
