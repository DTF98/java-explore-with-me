package ru.DTF98.ewm.category.mapper;

import org.mapstruct.*;
import ru.DTF98.ewm.category.dto.CategoryDto;
import ru.DTF98.ewm.category.dto.NewCategoryDto;
import ru.DTF98.ewm.category.model.Category;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    Category toModel(NewCategoryDto newCategoryDto);

    Category toModel(CategoryDto categoryDto);

    List<CategoryDto> toCategoryDto(List<Category> categories);

    CategoryDto toCategoryDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Category category, Category updated);
}
