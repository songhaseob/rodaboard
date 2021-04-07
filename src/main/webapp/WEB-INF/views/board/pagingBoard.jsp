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
<!-- Font Awesome Icons -->
	<link rel="stylesheet" href="${cp }/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
	<!-- Theme style -->
<%-- 	<link rel="stylesheet" href="${cp }/resources/bootstrap/dist/css/adminlte.min.css"> --%>
<%@ include file="/common/common_lib.jsp"%>
<link href="${cp }/css/dashboard.css" rel="stylesheet">
<link href="${cp }/css/blog.css" rel="stylesheet">

<script>
	//문서 로딩이 완료되고 나서 실행되는 영역
	$(function() {
		$(".bor").on("click", function(){
			var post_del = $(this).data("post_del");
			if(post_del == 0 ){
				alert("삭제된 게시글 입니다.");
				return false;
			}
			var bor_no = $(this).data("bor_no");
			var post_no = $(this).data("post_no");
			var user_id = $(this).data("user_id");
			$("#borno").val(bor_no);
			$("#postno").val(post_no);
			$("#userid").val(user_id);
			$("#frm").submit();
		});
		
	});
</script>

</head>

<body>
<!-- 	<form id="frm" action="/board/board.jsp"> -->
	<form id="frm" action="${cp }/board/boardDetail">
		<input type="hidden" id="borno" name="bor_no" value=""/>
		<input type="hidden" id="postno" name="post_no" value=""/>
		<input type="hidden" id="userid" name="user_id" value=""/>
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
						<h2 class="sub-header">게시판</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>게시글 번호</th>
									<th>제목</th>
									<th>작성자 아이디</th>
									<th>작성일시</th>
								</tr>
								
								<c:forEach items="${pagingList }" var="borList" varStatus="statas">
									<tr class="bor"  data-bor_no="${borList.bor_no }" data-post_no="${borList.post_no }" data-user_id="${borList.user_id }" data-post_del="${borList.post_del }">
										<td>${statas.count }</td>
										<td>
											<c:forEach begin="1" end="${borList.boardLevel }" var="i">
												<c:if test="${i > 1 }">
													&nbsp;&nbsp;&nbsp;&nbsp;
												</c:if>
											</c:forEach>
											<c:if test="${borList.boardLevel > 1 }">
												↳										
											</c:if>
										${borList.title }</td>
										<td>${borList.user_id }</td>
										<td><fmt:formatDate value="${borList.reg_dt }" pattern="yyyy.MM.dd"/></td>
									</tr>
								</c:forEach>
								
								</table>
						</div>

						<a class="btn btn-default pull-right" href="${cp }/board/insertBoardRep?bor_no=${borno}">새글 등록</a>
<!-- 						<a class="btn btn-default pull-right" href="/board/insertBoardRep.jsp">새글등록</a> -->

						<div class="card-footer" style="text-align: center;">
							<nav aria-label="member list Navigation">
								<ul class="pagination justify-content-center m-0">
									<li class="page-item">
										<a class="page-link" href="${cp }/board/pagingBoard?page=1&pageSize=${pageVo.getPageSize() }&bor_no=${borno}">
											<i class="fas fa-angle-double-left"></i>
										</a>
									</li>
									
									<c:choose>
										<c:when test="${pageVo.getPage()-1 <= 1 }">
											<li class="page-item">
												<a class="page-link" href="${cp }/board/pagingBoard?page=1&pageSize=${pageVo.getPageSize() }&bor_no=${borno}">
												<i class="fas fa-angle-left"></i></a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="page-item">
												<a class="page-link" href="${cp }/board/pagingBoard?page=${pageVo.getPage() -1 }&pageSize=${pageVo.getPageSize() }&bor_no=${borno}">
												<i class="fas fa-angle-left"></i></a>
											</li>
										</c:otherwise>									
									</c:choose>

									<c:forEach begin="1" end="${pagination }" var="i">
                           				<c:choose>
                              				<c:when test="${pageVo.getPage() == i }">
                           						<li class="page-item active">
                           							<a class="page-link" href="#">${i }</a>
                           						</li>
                           					</c:when>
                              				<c:otherwise>
                           						<li class="page-item">
                           							<a class="page-link" href="${cp }/board/pagingBoard?page=${i }&pageSize=${pageVo.getPageSize() }&bor_no=${borno}">${i }</a>
                           						</li>
                           					</c:otherwise>
                           				</c:choose>
                           			</c:forEach>

									<c:choose>
										<c:when test="${pageVo.getPage() == pagination }">
											<li class="page-item">
												<a class="page-link" href="${cp }/board/pagingBoard?page=${pagination }&pageSize=${pageVo.getPageSize() }&bor_no=${borno}">
												<i class="fas fa-angle-right"></i></a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="page-item">
												<a class="page-link" href="${cp }/board/pagingBoard?page=${pageVo.getPage() +1 }&pageSize=${pageVo.getPageSize() }&bor_no=${borno}">
												<i class="fas fa-angle-right"></i></a>
											</li>
										</c:otherwise>									
									</c:choose>
									
									<li class="page-item">
										<a class="page-link" href="${cp }/board/pagingBoard?page=${pagination }&pageSize=${pageVo.getPageSize() }&bor_no=${borno}">
											<i class="fas fa-angle-double-right"></i>
										</a>
									</li>
								</ul>
							</nav>
						</div>
						
						
						
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script src="./resources/bootstrap/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="./resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="./resources/bootstrap/dist/js/adminlte.min.js"></script>
</body>

</html>