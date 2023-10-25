package com.example.project.dto.category;

import com.example.project.dto.board.BoardDto;
import com.example.project.dto.page.Page;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("CategoryPageDto")
public class CategoryPageDto {

    private Page page;
    private List<CategoryDto> categoryDtoList;

    public CategoryPageDto(Page page, List<CategoryDto> categoryDtoList) {
        this.page = page;
        this.categoryDtoList = categoryDtoList;
    }

    public Page getPage() {
        return page;
    }

    public List<CategoryDto> getCategoryDtoList() {
        return categoryDtoList;
    }
}
