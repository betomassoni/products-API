package br.com.robertomassoni.xyinc.dto.mapper;

import br.com.robertomassoni.xyinc.dto.util.PageableDto;
import br.com.robertomassoni.xyinc.dto.model.ProductDto;
import org.springframework.data.domain.Page;


public class PageableMapper {
    public static PageableDto toPageableDto(Page<ProductDto> pageProductDto) {
        PageableDto pageableDto = new PageableDto();
        if (pageProductDto != null) {
            pageableDto.setSize(pageProductDto.getSize());
            pageableDto.setTotalElements(pageProductDto.getTotalElements());
            pageableDto.setTotalPages(pageProductDto.getTotalPages());
            pageableDto.setNumber(pageProductDto.getNumber());            
        }
        return pageableDto;
    }
}
