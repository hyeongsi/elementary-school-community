package com.example.project.dto;


import com.example.project.dto.notice.CommentAllJoinDto;
import com.example.project.dto.page.Page;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPageDTO {

    private Page page;
    private List<UserDTO> userDTOList;
}
