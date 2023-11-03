package com.example.project.controller.admin;

import com.example.project.dto.category.CategoryPageDto;
import com.example.project.dto.notice.AdminNoticePageDto;
import com.example.project.dto.notice.NoticeDto;
import com.example.project.service.admin.NoticeAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminNoticeController {

    private final NoticeAdminService noticeAdminService;

    @GetMapping("/noticeList")
    public String noticeList(final Model model,
                            @RequestParam(defaultValue = "10") int displayUnit,
                            @RequestParam(defaultValue = "1") int curPage){

        final AdminNoticePageDto noticePageDto = noticeAdminService.selectNoticePage(displayUnit, curPage);
        model.addAttribute("noticePageDto", noticePageDto);
        return "admin/notice/noticeList";
    }

    @ResponseBody
    @DeleteMapping("/noticeList")
    public int noticeRemove(@RequestBody List<NoticeDto> noticeDtoList){
        return noticeAdminService.deleteNoticeList(noticeDtoList);
    }

    @GetMapping("/notice")
    public String findNoticeListById(final Model model,
                                       @RequestParam int categoryId,
                                       @RequestParam(defaultValue = "10") int displayUnit,
                                       @RequestParam(defaultValue = "1") int curPage){

        final AdminNoticePageDto noticePageDto = noticeAdminService.selectNoticePageById(categoryId, displayUnit, curPage);

        model.addAttribute("noticePageDto", noticePageDto);
        model.addAttribute("categoryId", categoryId);
        return "admin/notice/detailCategoryNotice";
    }
}
