package com.spring5.mypro00.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring5.mypro00.common.paging.domain.MyReplyPagingCreatorDTO;
import com.spring5.mypro00.common.paging.domain.MyReplyPagingDTO;
import com.spring5.mypro00.domain.MyReplyVO;
import com.spring5.mypro00.mapper.MyBoardMapper;
import com.spring5.mypro00.mapper.MyReplyMapper;

@Service
//@Log4j
public class MyReplyServiceImpl implements MyReplyService{
	private MyReplyMapper myReplyMapper;
	private MyBoardMapper myBoardMapper;
	
	public MyReplyServiceImpl(MyReplyMapper myReplyMapper, MyBoardMapper myBoardMapper) {
		this.myReplyMapper = myReplyMapper;
		this.myBoardMapper = myBoardMapper;
	}
	
//	@Autowired
//	private MyReplyMapper myReplyMapper;
//	public MyReplyServiceImpl() {
//	}
	
	@Override
	public MyReplyPagingCreatorDTO getReplyList(MyReplyPagingDTO myReplyPaging) {
		
		List<Long> rowTotal = myReplyMapper.selectMyReplyTotCnt(myReplyPaging.getBno());
		int pageNum = myReplyPaging.getPageNum();
		
		MyReplyPagingCreatorDTO myReplyPagingCreator = null;
		
		if (rowTotal.get(1) == 0) {
			myReplyPaging.setPageNum(1);
			System.out.println("댓글:서비스- 댓글이 없는 경우 pageNum = 1");
			myReplyPagingCreator = new MyReplyPagingCreatorDTO(rowTotal, 
															   myReplyPaging, 
															   myReplyMapper.selectMyReplyList(myReplyPaging));
		} else {
			if (pageNum == -1) {
				pageNum = (int) Math.ceil(rowTotal.get(0) / (myReplyPaging.getRowAmountPerPage() * 1.0));
				myReplyPaging.setPageNum(pageNum);
			}
			
			myReplyPagingCreator = new MyReplyPagingCreatorDTO(rowTotal, 
															   myReplyPaging, 
															   myReplyMapper.selectMyReplyList(myReplyPaging));
		}
		
		return myReplyPagingCreator;
	}
	
		
	@Transactional
	@Override
	public Long registerReplyForBoard(MyReplyVO myReply) {
		System.out.println("입력된 행 수: " + myReplyMapper.insertMyReplyForBoard(myReply));
		myBoardMapper.updateBReplyCnt(myReply.getBno(), 1);
		return myReply.getRno();
	}

	
	@Transactional
	@Override
	public Long registerReplyForReply(MyReplyVO myReply) {
		System.out.println("입력된 행 수: " + myReplyMapper.insertMyReplyForReply(myReply));
		myBoardMapper.updateBReplyCnt(myReply.getBno(), 1);
		return myReply.getRno();
	}

	
	@Override
	public MyReplyVO getReply(long bno, long rno) {
		return myReplyMapper.selectMyReply(bno, rno);
	}

	
	@Override
	public int modifyReply(MyReplyVO myReply) {
		return myReplyMapper.updateMyReply(myReply);
	}

	
	@Transactional
	@Override
	public int removeReply(long bno, long rno) {
		int delCnt = myReplyMapper.deleteMyReply(bno, rno);
		myBoardMapper.updateBReplyCnt(bno, -1);
		
		return delCnt;
	}
	
	
	@Transactional
	@Override
	public int modifyRdelFlagReply(long bno, long rno) {
		int delCnt = myReplyMapper.updateRdelFlagMyReply(bno, rno);
		myBoardMapper.updateBReplyCnt(bno, -1);
		
		return delCnt;
	}
	
	
//	@Override
//	public int removeAllReply(long bno) {
//		return myReplyMapper.deleteAllReply(bno);
//	}

	
//	@Override
//	public int modifyRdelFlagAllReply(long bno) {
//		return myReplyMapper.updateRdelFlagAllReply(bno);
//	}
	
}
