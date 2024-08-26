package ru.DTF98.ewm.category.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.DTF98.ewm.category.mapper.CategoryMapper;
import ru.DTF98.ewm.category.model.Category;
import ru.DTF98.ewm.category.repository.CategoryRepository;
import ru.DTF98.ewm.category.service.CategoryService;
import ru.DTF98.ewm.error.NotFoundException;
import ru.DTF98.ewm.utils.Pagination;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category updateCategory(long id, Category updater) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Category.class, id));
        categoryMapper.update(category, updater);
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategory(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Category.class, id));
    }

    @Override
    public List<Category> getCategories(int from, int size) {
        Pageable page = Pagination.getPage(from, size);
        return categoryRepository.findAll(page);
    }

    @Override
    @Transactional
    public void deleteCategory(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Category.class, id));
        categoryRepository.delete(category);
    }
}
