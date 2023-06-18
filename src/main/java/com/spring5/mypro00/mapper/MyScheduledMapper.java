package com.spring5.mypro00.mapper;

import java.util.List;

import com.spring5.mypro00.domain.MyBoardAttachFileVO;

public interface MyScheduledMapper {
	
	public List<MyBoardAttachFileVO> selectAttachFilesBerforeOneDay1();

	
	public List<MyBoardAttachFileVO> selectAttachFilesBerforeOneDay2();

	
	public List<String> selectDirs();
	
	
	

}
