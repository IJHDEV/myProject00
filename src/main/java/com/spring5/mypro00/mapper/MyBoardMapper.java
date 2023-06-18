package com.spring5.mypro00.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring5.mypro00.common.paging.domain.MyBoardPagingDTO;
import com.spring5.mypro00.domain.MyBoardVO;

public interface MyBoardMapper {
	//기본 CRUD 처리 메서드 정의
	
//	//목록조회(R, SELECT) -1, paging 없음 
//	public List<MyBoardVO> selectMyBoardList();

	//목록조회(R, SELECT) -2, paging 고려 
	public List<MyBoardVO> selectMyBoardList(MyBoardPagingDTO myBoardPaging);
	
	//게시물 총수: 페이징시 사용
	public long selectRowTotal(MyBoardPagingDTO myBoardPaging);
	
	//게시물 등록1(C, INSERT: selectKey 이용X)
	public void insertMyBoard(MyBoardVO board);
		
	//게시물 등록2(C, INSERT: selectKey 이용)
	public long insertMyBoardSelectKey(MyBoardVO board);
	
	//특정 게시물 조회(R, SELECT)
	public MyBoardVO selectMyBoard(long bno);
	
	//특정 게시물 조회(첨부파일 포함): JOIN, HashMap 반환
//	public List<HashMap<String, String>> selectMyBoard(long bno);
		
	//특정 게시물 수정(U, UPDATE)
	public int updateMyBoard(MyBoardVO board);
	
	//게시물의 bdelFlag = 1로 설정
	public int updateSetDelFlag(long bno);

	//특정 게시물 삭제(D, DELETE)
	public int deleteMyBoard(long bno);
	
	//게시물 조회수 +1 증가
	public int updateBviewsCnt(long bno);
	
	//댓글 수 변경: 댓글추가 시에 amount에 1, 댓글삭제 시 amount에 -1 이 각각 전달됨
	public void updateBReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
	
	
	
}
