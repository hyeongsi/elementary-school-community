package com.example.project.controller.admin;

import com.example.project.dto.category.CategoryDto;
import com.example.project.dto.notice.CommentDto;
import com.example.project.dto.notice.CommentPageDto;
import com.example.project.service.admin.CommentAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminCommentController {

    private final CommentAdminService commentAdminService;

    @GetMapping("/commentList")
    public String findCommentList(final Model model,
                                   @RequestParam(defaultValue = "10") int displayUnit,
                                   @RequestParam(defaultValue = "1") int curPage){

        final CommentPageDto commentPageDto = commentAdminService.selectCommentPage(displayUnit, curPage);
        model.addAttribute("commentPageDto", commentPageDto);
        return "admin/commentList";
    }

    @ResponseBody
    @DeleteMapping("/commentList")
    public int commentRemove(@RequestBody List<CommentDto> commentDtoList){
        return commentAdminService.deleteCommentList(commentDtoList);
    }
}
