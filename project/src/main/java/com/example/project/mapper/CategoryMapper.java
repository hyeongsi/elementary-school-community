package com.example.project.mapper;

import com.example.project.dto.PageDto;
import com.example.project.dto.category.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    public List<CategoryDto> selectCategoryList();
    public int categoryTotalCnt();
    public List<CategoryDto> selectCategoryPage(PageDto pageDto);
    public List<CategoryDto> selectCategoryListById(final int boardId);
}
