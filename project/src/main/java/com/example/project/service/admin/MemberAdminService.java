package com.example.project.service.admin;

import com.example.project.dao.UserDAO;
import com.example.project.dto.UserDTO;
import com.example.project.dto.UserPageDTO;
import com.example.project.dto.notice.CommentAllJoinDto;
import com.example.project.dto.notice.CommentPageDto;
import com.example.project.dto.page.Page;
import com.example.project.dto.page.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberAdminService {

    private final UserDAO userDAO;

    public UserPageDTO selectMemberPage(final int displayUnit, final int curPage) {

        final int totalCnt = userDAO.memberTotalCnt();
        final Page page = new Page(displayUnit, curPage, totalCnt);

        final PageDto pageDto = new PageDto(page.getStartNum(), page.getEndNum());
        final List<UserDTO> userDTOList = userDAO.selectMemberListPage(pageDto);

        final UserPageDTO userPageDTO = new UserPageDTO(page, userDTOList);
        return userPageDTO;
    }

    public int deleteMemberList(List<UserDTO> userDTOList) {
        return userDAO.deleteMemberList(userDTOList);
    }

    public int increaseGrade() {
        return userDAO.increaseGrade();
    }
}
