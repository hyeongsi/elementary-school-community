package com.example.project.controller.admin;

import com.example.project.dto.board.BoardDto;
import com.example.project.dto.category.CategoryDto;
import com.example.project.dto.category.CategoryPageDto;
import com.example.project.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String findCategoryListById(final Model model,
                                       @RequestParam int boardId,
                                       @RequestParam(defaultValue = "10") int displayUnit,
                                       @RequestParam(defaultValue = "1") int curPage){

        final CategoryPageDto categoryPageDto = categoryService.selectCategoryListById(boardId, displayUnit, curPage);
        model.addAttribute("categoryPageDto", categoryPageDto);
        model.addAttribute("boardId", boardId);
        return "admin/detailBoardCategory";
    }

    @ResponseBody
    @PostMapping("/categoryList")
    public int categoryAdd(@RequestBody CategoryDto categoryDto){
        return categoryService.insertCategory(categoryDto);
    }

    @ResponseBody
    @DeleteMapping ("/categoryList")
    public int categoryRemove(@RequestBody List<CategoryDto> categoryDtoList){
        return categoryService.deleteCategoryList(categoryDtoList);
    }
}
