package ru.DTF98.ewm.category.service;

import ru.DTF98.ewm.category.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);

    Category updateCategory(long id, Category updater);

    Category getCategory(long id);

    List<Category> getCategories(int from, int size);

    void deleteCategory(long id);
}
