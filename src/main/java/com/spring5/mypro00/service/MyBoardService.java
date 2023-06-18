package com.spring5.mypro00.service;

import java.util.List;

import com.spring5.mypro00.common.paging.domain.MyBoardPagingDTO;
import com.spring5.mypro00.domain.MyBoardAttachFileVO;
import com.spring5.mypro00.domain.MyBoardVO;

public interface MyBoardService {
		
	//목록조회
//	public List<MyBoardVO> getBoardList();
	public List<MyBoardVO> getBoardList(MyBoardPagingDTO myBoardPaging);
	
	
	//특정 게시물 조회(조회수 증가 고려)
	//게시물 SELECT 및 bviewsCnt 컬럼에 + 1 업데이트 고려
	public MyBoardVO getBoard(long bno);
//	public List<HashMap<String, String>> getBoard(long bno);
	
	
	//게시물 총수: 페이징시 사용
	public long getRowTotal(MyBoardPagingDTO myBoardPaging);
	
	
	//게시물 수정페이지 호출
	public MyBoardVO getBoardDetailModify(long bno);
//	public List<HashMap<String, String>> getBoardDetailModify(long bno);
	
	
	//특정 게시물의 첨부파일 목록 조회
	public List<MyBoardAttachFileVO> getAttachFiles(long bno);
	
	
	//특정 게시물 수정
	public boolean modifyBoard(MyBoardVO board);
	
	
	//특정 게시물 삭제
	public boolean removeBoard(long bno);
	
	
	//게시물 삭제 요청: bdelFlag = 1로 설정
	public boolean setBoardDeleted(long bno);
	
	
	//게시물 등록(selectKey 이용)
	public long registerBoard(MyBoardVO board);
	
}
