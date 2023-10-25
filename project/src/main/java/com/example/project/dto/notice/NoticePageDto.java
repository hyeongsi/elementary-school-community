package com.example.project.dto.notice;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.example.project.dto.Page.Page;

@Alias("NoticePageDto")
public class NoticePageDto {

	private Page page;
	private List<NoticeDto> noticeDtoList;
	
	public NoticePageDto(Page page, List<NoticeDto> noticeDtoList) {
		this.page = page;
		this.noticeDtoList = noticeDtoList;
	}

	public Page getPage() {
		return page;
	}

	public List<NoticeDto> getNoticeDtoList() {
		return noticeDtoList;
	}
	
	
	
}
