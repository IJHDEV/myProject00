package com.spring5.mypro00.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring5.mypro00.common.paging.domain.MyBoardPagingDTO;
import com.spring5.mypro00.domain.MyBoardAttachFileVO;
import com.spring5.mypro00.domain.MyBoardVO;
import com.spring5.mypro00.mapper.MyBoardAttachFileMapper;
import com.spring5.mypro00.mapper.MyBoardMapper;
import com.spring5.mypro00.mapper.MyReplyMapper;

import lombok.AllArgsConstructor;

//@Log4j
@Service // DAO 또는 Mapper 인터페이스의 메서드 호출
		 // 사용되는 DAO 또는 Mapper 인터페이스 타입의 필드 필요
@AllArgsConstructor
public class MyBoardServiceImpl implements MyBoardService{
	
	private MyBoardMapper myBoardMapper;
	private MyReplyMapper myReplyMapper;
	private MyBoardAttachFileMapper myAttachFileMapper;
//	public MyBoardServiceImpl(MyBoardMapper myBoardMapper) {
//		this.myBoardMapper = myBoardMapper;
//	}
	
	//목록 조회
//	@Override
//	public List<MyBoardVO> getBoardList() {
//		return myBoardMapper.selectMyBoardList();
//	}
	
	@Override
	public List<MyBoardVO> getBoardList(MyBoardPagingDTO myBoardPaging) {
		return myBoardMapper.selectMyBoardList(myBoardPaging);
	}
	
	
	//게시물 총수: 페이징시 사용
	@Override
	public long getRowTotal(MyBoardPagingDTO myBoardPaging) {
		return myBoardMapper.selectRowTotal(myBoardPaging);
	}
	
	
	//게시물 등록(selectKey 이용)
	@Transactional
	@Override
	public long registerBoard(MyBoardVO board) {
		myBoardMapper.insertMyBoardSelectKey(board);
		
		if (board.getAttachFileList() == null || 
			board.getAttachFileList().size() == 0) {
			return board.getBno();
		} 
		
		board.getAttachFileList().forEach(
			attachFile -> {
				attachFile.setBno(board.getBno());
				myAttachFileMapper.insertAttachFile(attachFile);
			}
		);
		return board.getBno();
	}
	
		
	//특정 게시물 조회
	@Override
	public MyBoardVO getBoard(long bno) {
//	public List<HashMap<String, String>> getBoard(long bno) {
		MyBoardVO board = myBoardMapper.selectMyBoard(bno);
		myBoardMapper.updateBviewsCnt(bno);
//		List<HashMap<String, String>> board = myBoardMapper.selectMyBoard(bno);
		System.out.println("전달된 map: " + board);
		return board;
	}

	
	//수정 페이지 호출
	@Override
	public MyBoardVO getBoardDetailModify(long bno) {
//	public List<HashMap<String, String>> getBoardDetailModify(long bno) {
		return myBoardMapper.selectMyBoard(bno);
	}
	
	
	//특정 게시물의 첨부파일 목록 조회
	@Override
	public List<MyBoardAttachFileVO> getAttachFiles(long bno) {
		
		return myAttachFileMapper.selectAttachFiles(bno);
	}
		
	
	//특정 게시물 수정
	@Transactional
	@Override
	public boolean modifyBoard(MyBoardVO board) {
		
		long bno = board.getBno();
		
		//기존 게시물의 첨부파일 DB 정보 삭제
		myAttachFileMapper.deleteAttachFiles(bno);
		
		boolean boardModifyResult = myBoardMapper.updateMyBoard(board) == 1;
		
		if(boardModifyResult && board.getAttachFileList().size() > 0) {
			
			board.getAttachFileList().forEach(attachFile -> {
					attachFile.setBno(bno);
					myAttachFileMapper.insertAttachFile(attachFile);
			});
		}
		
		return boardModifyResult;
	}

	
	//게시물의 bdelFlag = 1로 설정
	@Transactional
	@Override
	public boolean setBoardDeleted(long bno) {
		myReplyMapper.updateRdelFlagAllReply(bno);
		myAttachFileMapper.deleteAttachFiles(bno);
		
		return myBoardMapper.updateSetDelFlag(bno) == 1;
	}
	
	@Transactional	
	//특정 게시물 삭제
	@Override
	public boolean removeBoard(long bno) {
		int delCnt = myReplyMapper.deleteAllReply(bno);
		System.out.println("삭제된 댓글 총 수: " + delCnt);
		
		myAttachFileMapper.deleteAttachFiles(bno); //DB에서 삭제
		
		return myBoardMapper.deleteMyBoard(bno) == 1;
	}




		

}
