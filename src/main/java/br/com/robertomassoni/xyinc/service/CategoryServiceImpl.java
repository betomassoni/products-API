package br.com.robertomassoni.xyinc.service;

import br.com.robertomassoni.xyinc.dto.mapper.CategoryMapper;
import br.com.robertomassoni.xyinc.dto.model.CategoryDto;
import br.com.robertomassoni.xyinc.enumerator.EntityType;
import br.com.robertomassoni.xyinc.enumerator.ExceptionType;
import br.com.robertomassoni.xyinc.exception.XyincException;
import br.com.robertomassoni.xyinc.model.Category;
import br.com.robertomassoni.xyinc.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return CategoryMapper.toCategoryDto(categoryList);
    }

    @Override
    public CategoryDto getCategory(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return CategoryMapper.toCategoryDto(category.get());
        }
        throw XyincException.throwException(EntityType.PRODUCT, ExceptionType.ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public CategoryDto getCategory(String name) {
        Optional<Category> category = Optional.ofNullable(categoryRepository.findByName(name));
        if (category.isPresent()) {
            return CategoryMapper.toCategoryDto(category.get());
        }
        throw XyincException.throwException(EntityType.CATEGORY, ExceptionType.ENTITY_NOT_FOUND, name);
    }

    @Override
    public CategoryDto addCategoryIfNotExist(String name) {
        Category category = null;
        try {
            Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByName(name));
            if (optionalCategory.isPresent()) {
                category = optionalCategory.get();                
            } else {
                Category newCategory = new Category();
                newCategory.setName(name);
                category = categoryRepository.save(newCategory);
            }
        } catch (Exception e) {
            throw XyincException.throwException(EntityType.CATEGORY, ExceptionType.ENTITY_EXCEPTION, name);
        }
        return CategoryMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
