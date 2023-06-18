package com.spring5.mypro00.service;

import com.spring5.mypro00.common.paging.domain.MyReplyPagingCreatorDTO;
import com.spring5.mypro00.common.paging.domain.MyReplyPagingDTO;
import com.spring5.mypro00.domain.MyReplyVO;

//@Transactional
public interface MyReplyService {

//	@Transactional
	public MyReplyPagingCreatorDTO getReplyList(MyReplyPagingDTO myReplyPaging);
	
//	@Transactional
	public Long registerReplyForBoard(MyReplyVO myReply);
	
	public Long registerReplyForReply(MyReplyVO myReply);
	
	public MyReplyVO getReply(long bno, long rno);
	
	public int modifyReply(MyReplyVO myReply);
	
	public int removeReply(long bno, long rno);
	
	public int modifyRdelFlagReply(long bno, long rno);
	
//	public int removeAllReply(long bno);
	
//	public int modifyRdelFlagAllReply(long bno);
	
}
