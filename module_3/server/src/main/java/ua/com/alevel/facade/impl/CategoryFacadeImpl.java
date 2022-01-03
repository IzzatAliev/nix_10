package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.api.dto.request.CategoryRequestDto;
import ua.com.alevel.api.dto.response.CategoryResponseDto;
import ua.com.alevel.facade.CategoryFacade;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryFacadeImpl implements CategoryFacade {

    private final CategoryService categoryService;

    public CategoryFacadeImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void create(CategoryRequestDto request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setPrice(request.getPrice());
        category.setIncome(request.isIncome());
        categoryService.create(category);
    }

    @Override
    public void update(CategoryRequestDto request, Long id) {
        Category category = new Category();
        category.setName(request.getName());
        category.setPrice(request.getPrice());
        category.setIncome(request.isIncome());
        categoryService.update(category);
    }

    @Override
    public void delete(Long id) {
        categoryService.delete(id);
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        return new CategoryResponseDto(categoryService.findById(id));
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        return categoryService.findAll()
                .stream().map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }
}
