package com.example.project.mapper;

import com.example.project.dto.category.CategoryDto;
import com.example.project.dto.category.FindCategoryByBoardIdPageDto;
import com.example.project.dto.page.PageDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {

    public List<CategoryDto> selectCategoryList();
    
    public int categoryTotalCnt();
    
    public List<CategoryDto> selectCategoryPage(PageDto pageDto);
    
    public List<CategoryDto> selectCategoryListById(FindCategoryByBoardIdPageDto findCategoryByBoardIdPageDto);
    
    public int categoryTotalCtnById(int boardId);
    
    public int insertCategory(CategoryDto categoryDto);
    
    public int deleteCategoryList(List<CategoryDto> categoryDtoList);
}
