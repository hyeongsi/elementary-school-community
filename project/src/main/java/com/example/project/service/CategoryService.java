package com.example.project.service;

import com.example.project.dto.page.FindCategoryByBoardIdPageDto;
import com.example.project.dto.page.PageDto;
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

    public CategoryPageDto selectCategoryListById(final int boardId, final int displayUnit, final int curPage){

        final int totalCnt = categoryMapper.categoryTotalCtnById(boardId);
        final Page page = new Page(displayUnit, curPage, totalCnt);

        final PageDto pageDto = new PageDto(page.getStartNum(), page.getEndNum());
        final FindCategoryByBoardIdPageDto findCategoryByBoardIdPageDto =
                new FindCategoryByBoardIdPageDto(pageDto, boardId);

        final List<CategoryDto> categoryDtoList = categoryMapper.selectCategoryListById(findCategoryByBoardIdPageDto);

        final CategoryPageDto categoryPageDto = new CategoryPageDto(page, categoryDtoList);
        return categoryPageDto;
    }

    public int insertCategory(final CategoryDto categoryDto){
        return categoryMapper.insertCategory(categoryDto);
    }

    public int deleteCategoryList(List<CategoryDto> categoryDtoList){
        return categoryMapper.deleteCategoryList(categoryDtoList);
    }
}
