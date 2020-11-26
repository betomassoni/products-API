package br.com.robertomassoni.xyinc.dto.mapper;

import br.com.robertomassoni.xyinc.dto.model.CategoryDto;
import br.com.robertomassoni.xyinc.model.Category;
import java.util.ArrayList;
import java.util.List;


public class CategoryMapper {
    
    public static CategoryDto toCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }
    
    public static List<CategoryDto> toCategoryDto(List<Category> categoryList) {
        ArrayList<CategoryDto> categoryDtoList = new ArrayList();
        
        for (Category category : categoryList) {
            categoryDtoList.add(toCategoryDto(category));
        }
        
        return categoryDtoList;
    }
}
