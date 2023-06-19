package com.spring5.mypro00.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring5.mypro00.common.paging.domain.MyReplyPagingCreatorDTO;
import com.spring5.mypro00.common.paging.domain.MyReplyPagingDTO;
import com.spring5.mypro00.domain.MyReplyVO;
import com.spring5.mypro00.service.MyReplyService;

@RestController
@RequestMapping(value = {"/replies"})
public class MyReplyController {
	
	private MyReplyService myReplyService;
	
	public MyReplyController(MyReplyService myReplyService) {
		this.myReplyService = myReplyService;
	}
	
	//게시물에 대한 댓글 목록 조회: GET /replies/{bno}/page={pageNum}
	//Ajax에서의 요청 URI: /mypro00/replies/234567
	@GetMapping(value = "/{bno}/page={pageNum}", 
				produces = {"application/json; charset=utf-8", 
							"application/xml; charset=utf-8"})
	public ResponseEntity<MyReplyPagingCreatorDTO> showReplyList(@PathVariable("bno") long bno,
														 		 @PathVariable("pageNum") Integer pageNum) {
		MyReplyPagingCreatorDTO myReplyPagingCreator = 
				myReplyService.getReplyList(new MyReplyPagingDTO(bno, pageNum));
		
		return new ResponseEntity<MyReplyPagingCreatorDTO>(myReplyPagingCreator, HttpStatus.OK);
	}
	
		
	//게시물에 대한 댓글 등록(rno 반환): POST /replies/{bno}/new
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/{bno}/new",
				 consumes = {"application/json; charset=utf-8"},
				 produces = {"text/plain; charset=utf-8"})
	public ResponseEntity<String> registerReplyForBoard(@PathVariable("bno") Long bno, 
														@RequestBody MyReplyVO myReply) {
		Long registeredRno = myReplyService.registerReplyForBoard(myReply);
		System.out.println("등록된 댓글 번호: " + registeredRno);
		return registeredRno != null ? new ResponseEntity<String>("CommentRegisterSuccess", HttpStatus.OK) 
									 : new ResponseEntity<String>("CommentRegisterFail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//게시물에 대한 댓글의 답글 등록(rno 반환): POST /replies/{bno}/{prno}/new
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/{bno}/{prno}/new",
				 consumes = {"application/json; charset=utf-8"},
				 produces = {"text/plain; charset=utf-8"})
	public ResponseEntity<String> registerReplyForReply(@PathVariable("bno") Long bno, 
														@PathVariable("prno") Long prno,
														@RequestBody MyReplyVO myReply) {
		Long registeredRno = myReplyService.registerReplyForReply(myReply);
		System.out.println("등록된 댓글 번호: " + registeredRno);
		return registeredRno != null ? new ResponseEntity<String>("ReplyRegisterSuccess", HttpStatus.OK) 
									 : new ResponseEntity<String>("ReplyRegisterFail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//게시물에 대한 특정 댓글 조회: GET /replies/{bno}/{rno}
	@GetMapping(value = "/{bno}/{rno}", 
				produces = "application/json; charset=utf-8")
	public ResponseEntity<MyReplyVO> showReply(@PathVariable("bno") Long bno, 
											   @PathVariable("rno") Long rno) {
		
		return new ResponseEntity<MyReplyVO>(myReplyService.getReply(bno, rno), HttpStatus.OK);
	}
	
	
	//게시물에 대한 특정 댓글 수정: PUT:PATCH /replies/{bno}/{rno}
	@PreAuthorize("isAuthenticated() && principal.username == #myReply.rwriter")
	@RequestMapping(value = "/{bno}/{rno}", 
//					method = {RequestMethod.PUT, RequestMethod.PATCH},
					method = {RequestMethod.PUT},
					consumes = "application/json; charset=utf-8",
					produces = "text/plain; charset=utf-8")
	public ResponseEntity<String> modifyReply(@PathVariable("bno") Long bno, 
											  @PathVariable("rno") Long rno, 
											  @RequestBody MyReplyVO myReply) {
		System.out.println("컨트롤러 myReply: " + myReply);
//		myReply.setBno(bno);
//		myReply.setRno(rno);
		return myReplyService.modifyReply(myReply) == 1 
				? new ResponseEntity<String>("ReplyModifySuccess", HttpStatus.OK)
				: new ResponseEntity<String>("ReplyModifyFail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//게시물에 대한 특정 댓글 삭제: DELETE: /replies/{bno}/{rno}
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value = "/{bno}/{rno}", 
				   produces = "text/plain; charset=utf-8")
	public ResponseEntity<String> removeReply(@PathVariable("bno") Long bno, 
											  @PathVariable("rno") Long rno,
											  @RequestBody MyReplyVO myReply) {
		
		return myReplyService.removeReply(bno, rno) == 1 
				? new ResponseEntity<String>("ReplyRemoveSuccess", HttpStatus.OK)
				: new ResponseEntity<String>("ReplyRemoveFail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//게시물의 특정 댓글 삭제 요청
	@PreAuthorize("isAuthenticated() && principal.username == #myReply.rwriter")
	@PatchMapping(value = "/{bno}/{rno}",
				  consumes = "application/json; charset=utf-8",
				  produces = "text/plain; charset=utf-8")
	public ResponseEntity<String> modifyRdelFlagReply(@PathVariable("bno") Long bno, 
													  @PathVariable("rno") Long rno,
													  @RequestBody MyReplyVO myReply) {
		
		System.out.println("rwriter : " + myReply.getRwriter());
		
		return myReplyService.modifyRdelFlagReply(bno, rno) == 1 
				? new ResponseEntity<String>("ReplyRemoveSuccess", HttpStatus.OK)
				: new ResponseEntity<String>("ReplyRemoveFail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
//	//게시물의 모든 댓글 삭제
//	@DeleteMapping(value = "/{bno}", 
//				   produces = "text/plain; charset=utf-8")
//	public ResponseEntity<String> removeAllReply(@PathVariable("bno") Long bno) {
//		
//		return new ResponseEntity<> (String.valueOf(myReplyService.removeAllReply(bno)), HttpStatus.OK);
//	}
	
	
//	//게시물의 모든 댓글 삭제 요청
//	@PatchMapping(value = "/{bno}", 
//			produces = "text/plain; charset=utf-8")
//	public ResponseEntity<String> modifyRdelFlagAllReply(@PathVariable("bno") Long bno) {
//		
//		return new ResponseEntity<> (String.valueOf(myReplyService.modifyRdelFlagAllReply(bno)), HttpStatus.OK);
//	}	
	
	
	
	
}
