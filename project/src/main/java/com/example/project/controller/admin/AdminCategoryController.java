package com.example.project.controller.admin;

import com.example.project.dto.category.CategoryDto;
import com.example.project.dto.category.CategoryPageDto;
import com.example.project.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminCategoryController {

    final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categoryList")
    public String findCategoryList(final Model model,
                                   @RequestParam(defaultValue = "10") int displayUnit,
                                   @RequestParam(defaultValue = "1") int curPage){

        final CategoryPageDto categoryPageDto = categoryService.selectCategoryPage(displayUnit, curPage);
        model.addAttribute("categoryPageDto", categoryPageDto);
        return "admin/categoryList";
    }

    @GetMapping("/category")
    public String findCategoryListById(final Model model, @RequestParam int boardId){

        final List<CategoryDto> categoryList = categoryService.selectCategoryListById(boardId);
        model.addAttribute("categoryList", categoryList);
        return "admin/detailBoardCategory";
    }
}
