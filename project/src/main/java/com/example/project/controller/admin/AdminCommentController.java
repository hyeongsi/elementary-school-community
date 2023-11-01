package com.example.project.controller.admin;

import com.example.project.dto.category.CategoryPageDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminCommentController {

    @GetMapping("/commentList")
    public String findCommentList(final Model model,
                                   @RequestParam(defaultValue = "10") int displayUnit,
                                   @RequestParam(defaultValue = "1") int curPage){

//        final CategoryPageDto categoryPageDto = categoryService.selectCategoryPage(displayUnit, curPage);
//        model.addAttribute("categoryPageDto", categoryPageDto);
        return "admin/commentList";
    }
}
