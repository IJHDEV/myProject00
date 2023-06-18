package sample.chap02;

import org.springframework.stereotype.Component;

@Component
public class Restaurant {
	
//	private Chef chef ;
	
	//1. setter를 이용한 주입
	
//	@Autowired
//	public void setChef(Chef chef) {
//		this.chef = chef ;
////		System.out.println("세터이용 주입, chef.cname: " + chef.getCname());
////		System.out.println("세터이용 주입, chef.cid: " + chef.getCid());
//	}
	
//	//2. 생성자를 이용한 주입
//	public Restaurant(Chef chef) {
//		this.chef = chef ;
//		System.out.println("Restaurant 클래스의 생성자입니다.");
//		System.out.println("chef.cname: " + chef.getCname());
//		System.out.println("chef.cid: " + chef.getCid());
//	}
	
//	public Restaurant() {
//		System.out.println("chef.cid: " + chef.getCid());
//	}

}
