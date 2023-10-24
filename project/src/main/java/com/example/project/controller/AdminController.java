package com.example.project.controller;

import com.example.project.dto.Board.BoardDto;
import com.example.project.dto.Board.BoardPageDto;
import com.example.project.dto.CategoryDto;
import com.example.project.service.BoardService;
import com.example.project.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    final BoardService boardService;
    final CategoryService categoryService;

    public AdminController(final BoardService boardService, final CategoryService categoryService) {
        this.boardService = boardService;
        this.categoryService = categoryService;
    }

    @GetMapping("/boardList")
    public String boardList(final Model model,
                            @RequestParam(defaultValue = "10") int displayUnit,
                            @RequestParam(defaultValue = "1") int curPage){

        final BoardPageDto boardPageDto = boardService.selectBoardPage(displayUnit, curPage);
        model.addAttribute("boardPageDto", boardPageDto);
        return "admin/boardList";
    }

    @ResponseBody
    @PostMapping ("/boardList")
    public int boardAdd(@RequestParam String boardName){

        final BoardDto boardDto = new BoardDto(null, boardName);
        return boardService.insertBoard(boardDto);
    }

    @GetMapping("/categoryList")
    public String findCategoryList(final Model model){

        final List<CategoryDto> categoryList = categoryService.selectCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "admin/categoryList";
    }

    @GetMapping("/category")
    public String findCategoryListById(final Model model, @RequestParam int boardId){

        final List<CategoryDto> categoryList = categoryService.selectCategoryListById(boardId);
        model.addAttribute("categoryList", categoryList);
        return "admin/detailBoardCategory";
    }

}
