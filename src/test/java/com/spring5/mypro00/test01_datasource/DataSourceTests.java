package com.spring5.mypro00.test01_datasource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/mybatis-context.xml"})
@Log4j
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DataSourceTests {
 
//	private static final Logger log = LoggerFactory.getLogger(DataSourceTests.class);
	
//	private DataSource dataSource ;
   
//	@Autowired
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource ;
//	}
   
//	@Setter(onMethod_ = @Autowired)
//	private DataSource dataSource ;
//   
//	@Test
//	public void testConnection() {
//	   try {
//	      Connection con = dataSource.getConnection() ;
//	      log.info(con.toString());
//	      
//	   } catch (SQLException e) {
//	      // TODO Auto-generated catch block
//	      e.printStackTrace();
//	   }
//	}
   
	
//	@Setter(onMethod_ = { @Autowired } )
//	private SqlSessionTemplate sqlSession ;
//	
//	
//	@Test
//	public void testMyBatis() {
//	   
//	   //SqlSessionTemplate 까지 사용할 때
//	   Connection conn = sqlSession.getConnection() ;
//	   log.info(conn);
//	   log.info(sqlSession);
//	}
	
	
	@Setter(onMethod_ = @Autowired)
	private SqlSessionFactory sqlSessionFactory ;
	
	@Test
	public void testMyBatis2() {
	
	   SqlSession sqlSession = sqlSessionFactory.openSession() ;
	   
	   log.info(sqlSession);
	   
	}
   
   

}