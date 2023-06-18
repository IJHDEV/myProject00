<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error_page.jsp</title>
</head>
<body>

<h4><c:out value="${myException.getMessage()}"/></h4>

<ul>
    <c:forEach items="${myException.getStackTrace() }" 
               var="stack">
        <li><c:out value="${stack}"></c:out></li>
    </c:forEach>
</ul>

<%-- 웹브라우저에서 오류를 발생시키는 URL
     http://localhost:8080/mypro00/sample/ex04?name=홍길동&age=aa&page=9 --%>

</body>
</html>