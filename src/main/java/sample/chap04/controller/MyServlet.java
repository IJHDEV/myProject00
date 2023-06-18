package sample.chap04.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/sample3/ex04")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		System.out.println("MyServlet, name: " + name);
		System.out.println("MyServlet, age: " + age);
	} // http://localhost:8080/mypro00/sample3/ex04?name=홍길동&age=24

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	// sample.chap04.controller 의 ex04()와 mapping 주소 같지만 Servlet이 우선순위 높게 처리됨
	// 사용자 요청 톰캣이 잡고 자신에게 등록되어있는 url pattern에 없고 jsp파일 요청이 없으면 Dispatcher Servlet(Spring) 호출
	// ex04.jsp 실습: /WEB-INF/ 안의 파일은 url 직접 호출 불가
}
