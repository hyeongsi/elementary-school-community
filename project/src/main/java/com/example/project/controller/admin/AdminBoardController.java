package com.example.project.controller.admin;

import com.example.project.dto.board.BoardDto;
import com.example.project.dto.board.BoardPageDto;
import com.example.project.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminBoardController {

    final BoardService boardService;

    public AdminBoardController(final BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boardList")
    public String boardList(final Model model,
                            @RequestParam(defaultValue = "10") int displayUnit,
                            @RequestParam(defaultValue = "1") int curPage){

        final BoardPageDto boardPageDto = boardService.selectBoardPage(displayUnit, curPage);
        model.addAttribute("boardPageDto", boardPageDto);
        return "admin/board/boardList";
    }

    @ResponseBody
    @PostMapping ("/boardList")
    public int boardAdd(@RequestBody BoardDto boardDto){
        return boardService.insertBoard(boardDto);
    }

    @ResponseBody
    @DeleteMapping ("/boardList")
    public int boardRemove(@RequestBody List<BoardDto> boardDtoList){
        return boardService.deleteBoardList(boardDtoList);
    }

}
