<%@page import="java.util.Date"%>
<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/common_lib.jsp"%>
<link href="${cp }/css/dashboard.css" rel="stylesheet">
<link href="${cp }/css/blog.css" rel="stylesheet">

<script>

</script>

</head>

<body>

	<form >
	
	</form>

	<!-- header부분 include -->
	<%@ include file="/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<!-- left 부분 include -->
				<%@ include file="/common/left.jsp"%>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">게시판 등록</h2>
						<div class="table-responsive">
						
						<form action="${cp }/board/insertBoard" method="post">
							게시판이름 <input type="text" id="bor_name" name="bor_name"> 
								<select id='bor_act' name="bor_act">
									<option value="t">사용</option>
									<option value="f">미사용</option>
								</select>
								<button type="submit" id="insertBtn">생성</button>
						</form>
							
						<hr>
						
						<c:forEach items="${boardIList }" var="boardIList">
							<form action="${cp }/board/updateBoard" method="post">
							게시판이름 <input type="text" name="bor_name" value="${boardIList.bor_name }">
							<c:choose>
								<c:when test="${boardIList.bor_act == 1 }">
									<select id='bor_act' name="bor_act">
										<option value="t" selected>사용</option>
										<option value="f">미사용</option>
									</select>
								</c:when>
								<c:otherwise>
									<select id='bor_act' name="bor_act">
										<option value="t">사용</option>
										<option value="f" selected>미사용</option>
									</select>
								</c:otherwise>
							</c:choose>
								<button type="submit" id="updateBtn">수정</button><br>
							</form>
						</c:forEach>

						</div>

						<div class="text-center">

						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>

</html>