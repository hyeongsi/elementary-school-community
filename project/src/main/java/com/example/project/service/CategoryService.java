package com.example.project.service;

import com.example.project.dto.CategoryDto;
import com.example.project.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    final CategoryMapper categoryMapper;

    public CategoryService(final CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> selectCategoryList(){
        return categoryMapper.selectCategoryList();
    }

    public List<CategoryDto> selectCategoryListById(final int boardId){
        return categoryMapper.selectCategoryListById(boardId);
    }
}
