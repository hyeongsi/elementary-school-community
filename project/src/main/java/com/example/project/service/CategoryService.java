package com.example.project.service;

import com.example.project.dto.PageDto;
import com.example.project.dto.category.CategoryDto;
import com.example.project.dto.category.CategoryPageDto;
import com.example.project.dto.page.Page;
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

    public CategoryPageDto selectCategoryPage(final int displayUnit, final int curPage){

        final int totalCnt = categoryMapper.categoryTotalCnt();
        final Page page = new Page(displayUnit, curPage, totalCnt);

        final PageDto pageDto = new PageDto(page.getStartNum(), page.getEndNum());
        final List<CategoryDto> categoryDtoList = categoryMapper.selectCategoryPage(pageDto);

        final CategoryPageDto categoryPageDto = new CategoryPageDto(page, categoryDtoList);
        return categoryPageDto;
    }

    public List<CategoryDto> selectCategoryListById(final int boardId){
        return categoryMapper.selectCategoryListById(boardId);
    }
}
