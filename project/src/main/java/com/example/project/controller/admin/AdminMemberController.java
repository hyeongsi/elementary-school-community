package com.example.project.controller.admin;

import com.example.project.dto.UserDTO;
import com.example.project.dto.UserPageDTO;
import com.example.project.dto.notice.CommentDto;
import com.example.project.dto.notice.CommentPageDto;
import com.example.project.service.admin.CommentAdminService;
import com.example.project.service.admin.MemberAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberAdminService memberAdminService;

    @GetMapping("/memberList")
    public String findMemberList(final Model model,
                                  @RequestParam(defaultValue = "10") int displayUnit,
                                  @RequestParam(defaultValue = "1") int curPage){

        final UserPageDTO userPageDTO = memberAdminService.selectMemberPage(displayUnit, curPage);
        model.addAttribute("userPageDto", userPageDTO);
        return "admin/member/memberList";
    }

    @ResponseBody
    @DeleteMapping("/memberList")
    public int memberRemove(@RequestBody List<UserDTO> userDTOList){
        return memberAdminService.deleteMemberList(userDTOList);
    }
}
