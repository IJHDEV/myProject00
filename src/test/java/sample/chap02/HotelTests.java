package sample.chap02;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@Log4j
public class HotelTests {
	
	private SampleHotel hotel ;

	@Autowired
	public void setHotel(SampleHotel hotel) {
		this.hotel = hotel;
	}
	
	//테스트 클래스에서 생성자를 통해 빈 주입은 테스트 않됩니다.
	//Setter를 이용한 빈 주입을 사용해야 합니다.
//	public HotelTests(SampleHotel hotel) {
//		this.hotel = hotel;
//	}
	
	
	
	@Test
	public void testExist() {
		
		assertNotNull(hotel);
		
		log.info(hotel);
		log.info("==========================");
		log.info(hotel.getChef());
	}


	
	

}
