package sample.chap02;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


//@Data                 //lombok 어노테이션
//@NoArgsConstructor    //lombok 어노테이션
//@Getter               //lombok 어노테이션
//@Setter               //lombok 어노테이션
//@EqualsAndHashCode    //lombok 어노테이션
//@ToString             //lombok 어노테이션

//@AllArgsConstructor   //lombok 어노테이션
//@Log4j                //lombok 어노테이션

//@Controller: 제어역활을 하는 클래스를 자동으로 빈으로 생성시키는 어노테이션 
//@Service   : 서비스 역활을 하는 클래스를 자동으로 빈으로 생성시키는 어노테이션 
//@Repository: DAO 클래스를 자동으로 빈으로 생성시키는 어노테이션
//@Component : 나머지 모든 클래스를 빈으로 생성시키는 어노테이션

@Component
@Setter
@Getter
public class Chef {
	private String cname ;
	private String cid ;
	
	
	
	public Chef(String cname, String cid) {
		this.cname = cname;
		this.cid = cid;
		System.out.println("Chef의 모든 필드 초기화 생성자 입니다.");
	}

	
	public Chef() {
		this.cname="베트맨" ;
		this.cid="bt" ;
		System.out.println("Chef의 기본 생성자입니다.");
		
	}


}
