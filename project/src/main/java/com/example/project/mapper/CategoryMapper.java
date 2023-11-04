package com.example.project.mapper;

import com.example.project.dto.board.BoardDto;
import com.example.project.dto.page.FindCategoryByBoardIdPageDto;
import com.example.project.dto.page.PageDto;
import com.example.project.dto.category.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    public List<CategoryDto> selectCategoryList();
    public int categoryTotalCnt();
    public List<CategoryDto> selectCategoryPage(PageDto pageDto);
    public List<CategoryDto> selectCategoryListById(FindCategoryByBoardIdPageDto findCategoryByBoardIdPageDto);
    public int categoryTotalCtnById(int boardId);
    public int insertCategory(CategoryDto categoryDto);
    public int deleteCategoryList(List<CategoryDto> categoryDtoList);
}
