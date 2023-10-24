package com.example.project.mapper;

import com.example.project.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    public List<CategoryDto> selectCategoryList();
    public List<CategoryDto> selectCategoryListById(final int boardId);
}
