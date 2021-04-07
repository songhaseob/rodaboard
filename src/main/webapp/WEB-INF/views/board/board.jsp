<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/common/common_lib.jsp"%>

<!-- Custom styles for this template -->

<link href="${cp }/css/dashboard.css"
	rel="stylesheet">
<link href="${cp }/css/blog.css" rel="stylesheet">

<script>
// 	사용자 수정 : method : get / action = /userModify
// 	사용자 삭제 : method : post / action = /deleteUser
// 	파라미터는 둘다 userid 하나만 있으면 가능
	//문서 로딩이 완료 되었을때
	$(function(){
		// 수정버든
		$('#modifyBtn').on('click', function(){
			$('#frm').attr("method", "get");
			$('#frm').attr("action", "${cp }/board/modifyBoard");
			$('#frm').submit();
		});
		
		// 삭제버튼
		$('#deleteBtn').on('click', function(){
			$('#frm').attr("method", "post");
			$('#frm').attr("action", "${cp }/board/modifydelBoard");
			$('#frm').submit();
		});
		
		//답글
		$('#replyBtn').on('click', function(){
			$('#frm').attr("method", "get");
			$('#frm').attr("action", "${cp }/board/insertComm");
			$('#frm').submit();
		});
		
		//댓글
		$('#insertBtn').on('click', function(){
			$('#frm').attr("method", "post");
			$('#frm').attr("action", "${cp }/board/insertPostCom");
			$('#frm').submit();
		});
	});
</script>

</head>
<body>
	
	<%@ include file="/common/header.jsp" %>
	
	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/common/left.jsp" %>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자 상세조회</h2>
						
				<form class="form-horizontal" id="frm" role="form">
					<input type="hidden" name="user_id" value="${boardDetail.user_id }"/>
					<input type="hidden" name="bor_no" value="${boardDetail.bor_no }"/>
					<input type="hidden" name="post_no" value="${boardDetail.post_no }"/>
					<input type="hidden" name="com_user_id" value="${S_USER.userid }"/>
				</form>
					
					<div class="form-group">
						<label for="userid" class="col-sm-2 control-label">제목</label>
						<div class="col-sm-10">
							<label class="control-label">${boardDetail.title }</label>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">글내용</label>
						<div class="col-sm-10">
							<label class="control-label">${boardDetail.cont }</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">첨부파일</label>
						<div class="col-sm-10">
							<label class="control-label">첨부파일....</label>
								<button type="button" id="modifyBtn">수정</button>
								<button type="button" id="deleteBtn">삭제</button>
								<button type="button" id="replyBtn">답글</button>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">댓글</label>
						<div class="col-sm-10">
							<c:forEach items="${post_comList }" var="postcom">
								<c:choose>
									<c:when test="${postcom.com_del == 1 }">
										<form action="${cp }/board/deleteComment" method="post">
											${postcom.com_con } [${postcom.com_user_id }/<fmt:formatDate value="${boardDetail.reg_dt }" pattern="yyyy-MM-dd"/>]
											<c:if test="${S_USER.userid == postcom.com_user_id }">
												<input type="submit" value="삭제"/>
												<input type="hidden" name="com_no" value="${postcom.com_no }"/>
												<input type="hidden" name="bor_no" value="${boardDetail.bor_no }"/>
												<input type="hidden" name="post_no" value="${boardDetail.post_no }"/>
												<input type="hidden" name="ruser_id" value="${boardDetail.user_id }"/>
											</c:if>
											<br>
										</form>
									</c:when>
									<c:otherwise>
										삭제된 댓글입니다.<br>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<br>
						</div>
						<div>
							<form action="${cp }/board/registComent" method="post">
								<input type="hidden" name="user_id" value="${S_USER.userid }"/>
								<input type="hidden" name="bor_no" value="${boardDetail.bor_no }"/>
								<input type="hidden" name="post_no" value="${boardDetail.post_no }"/>
								<input type="hidden" name="com_user_id" value="${boardDetail.user_id }"/>
								<textarea rows="5" cols="100" name="com_con"></textarea>
								<input type="submit" value="댓글저장"/>
							</form>
						</div>
					</div>
			</div>
		</div>
	</div>
	</div>
	</div>

</body>
</html>