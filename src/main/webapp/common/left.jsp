<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<ul class="nav nav-sidebar">
		<li class="active"><a href="${cp }/login/main">Main <span class="sr-only">(current)</span></a></li>
		
		<li class="active"><a href="${cp }/board/insertBoard">게시판 생성</a></li>

		
		<c:forEach items="${boardIList }" var="boardIList">
			<c:if test="${boardIList.bor_act == 1 }">
				<li class="active"><a href="${cp }/board/pagingBoard?bor_no=${boardIList.bor_no }">${boardIList.bor_name }</a></li>
			</c:if>
		</c:forEach>
		
	</ul>