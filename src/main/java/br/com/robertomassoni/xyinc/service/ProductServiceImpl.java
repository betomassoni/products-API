package br.com.robertomassoni.xyinc.service;

import br.com.robertomassoni.xyinc.dto.mapper.ProductMapper;
import br.com.robertomassoni.xyinc.dto.model.CategoryDto;
import br.com.robertomassoni.xyinc.dto.model.ProductDto;
import br.com.robertomassoni.xyinc.enumerator.EntityType;
import br.com.robertomassoni.xyinc.enumerator.ExceptionType;
import br.com.robertomassoni.xyinc.exception.XyincException;
import br.com.robertomassoni.xyinc.model.Category;
import br.com.robertomassoni.xyinc.model.Product;
import br.com.robertomassoni.xyinc.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> pageProduct = productRepository.findAll(pageable);  
        if (!pageProduct.isEmpty()) {
            List<ProductDto> productDtoList = ProductMapper.toProductDto(pageProduct.getContent());
            return ProductMapper.toPageProductDto(productDtoList, pageable, pageProduct);
        }
        throw XyincException.throwException(EntityType.PRODUCT, ExceptionType.ENTITY_NOT_FOUND, null);
    }

    @Override
    public ProductDto getProduct(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ProductMapper.toProductDto(product.get());
        } else {
            throw XyincException.throwException(EntityType.PRODUCT, ExceptionType.ENTITY_NOT_FOUND, id.toString());
        }
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        CategoryDto categoryDto = categoryService.addCategoryIfNotExist(productDto.getCategory());
        Product product;
        try {
            product = new Product();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setCategoryId(new Category(categoryDto.getId()));
            product = productRepository.save(product);
        } catch (Exception e) {
            throw XyincException.throwException(EntityType.PRODUCT, ExceptionType.ENTITY_EXCEPTION, productDto.getName());
        }
        return ProductMapper.toProductDto(product);
    }

    @Override
    public ProductDto deleteProduct(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return ProductMapper.toProductDto(product.get());
        } else {
            throw XyincException.throwException(EntityType.PRODUCT, ExceptionType.ENTITY_NOT_FOUND, id.toString());
        }
    }

    @Override
    public ProductDto updateProduct(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            CategoryDto categoryDto = categoryService.addCategoryIfNotExist(productDto.getCategory());

            Product product = new Product();
            product.setId(id);
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setCategoryId(new Category(categoryDto.getId()));
            product = productRepository.save(product);
            return ProductMapper.toProductDto(product);
        } else {
            throw XyincException.throwException(EntityType.PRODUCT, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(productDto.getId()));
        }
    }

}
