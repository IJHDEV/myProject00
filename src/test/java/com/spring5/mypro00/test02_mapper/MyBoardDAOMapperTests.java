package com.spring5.mypro00.test02_mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring5.mypro00.dao.MyBoardDAO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/mybatis-context.xml")
@Log4j
public class MyBoardDAOMapperTests {
	
    @Setter(onMethod_ = @Autowired)
    private MyBoardDAO myBoardDAO;
    
    
    //게시물 목록 조회 테스트 <-- 테스트 후 메서드 주석처리
	@Test
	public void testSelectDAOMyBoardList() {
	    myBoardDAO.selectDAOMyBoardList().forEach(myBoard -> log.info(myBoard));
	}

  
//  //게시물 등록 테스트 - selectKey 사용 안함
//    @Test
//    public void testInsertDAOMyBoard() {
//
////	MyBoardVO myBoard = new MyBoardVO(0L, "메퍼 테스트-입력제목", "매퍼 테스트-입력내용", "test", 0, 0, 0, null, null );
//	MyBoardVO myBoard = new MyBoardVO();
//	myBoard.setBtitle("메퍼 테스트-입력제목");
//	myBoard.setBcontent("매퍼 테스트-입력내용");
//	myBoard.setBwriter("test");
//	
//	boardDAO.insertDAOMyBoard(myBoard);
//	log.info(myBoard);
//    }


//  //게시물 조회 테스트(by bno)
//	@Test
//	public void testSelectDAOMyBoard() {
//	    // 존재하는 게시물 번호로 테스트
//	    log.info(boardDAO.selectDAOMyBoard(1L));
//	}

  
}