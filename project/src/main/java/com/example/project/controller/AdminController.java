package com.example.project.controller;

import com.example.project.dto.BoardDto;
import com.example.project.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {

//    final BoardService boardService;
//
//    public AdminController(final BoardService boardService) {
//        this.boardService = boardService;
//    }
//
//    @GetMapping("admin/boardList")
//    public String boardList(final Model model){
//
//        List<BoardDto> boardList = boardService.selectBoardList();
//        model.addAttribute("boardList", boardList);
//        System.out.println(boardList);
//        return "admin/boardList";
//    }
//
//    @ResponseBody
//    @PostMapping ("admin/boardList")
//    public int boardAdd(@RequestParam String boardName,@RequestParam String boardCategory){
//
//        BoardDto boardDto = new BoardDto(null, boardName, boardCategory, null);
//        System.out.println(boardDto);
//        return boardService.insertBoard(boardDto);
//    }

}
