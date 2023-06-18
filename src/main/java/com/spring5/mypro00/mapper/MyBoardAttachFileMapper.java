package com.spring5.mypro00.mapper;

import java.util.List;

import com.spring5.mypro00.domain.MyBoardAttachFileVO;

public interface MyBoardAttachFileMapper {
	//기본 CRUD 처리 메서드 정의
	
	//특정 게시물의 첨부파일 목록 조회
	public List<MyBoardAttachFileVO> selectAttachFiles(long bno);
	
	
	//특정 게시물의 첨부파일 정보 입력
	public void insertAttachFile(MyBoardAttachFileVO attachFile);
	
	
	//특정 게시물의 첨부파일 하나를 삭제
	public void deleteAttachFile(String uuid);
	
	
	//특정 게시물의 모든 첨부파일 삭제: 
	//게시물 삭제시, 해당 게시물의 모든 첨부파일을 삭제해야 게시물이 삭제됨
	//(on delete cascade 옵션이 없는 F.K인 경우)
	public void deleteAttachFiles(long bno);
	
	
}
