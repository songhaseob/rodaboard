<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<%@ include file="/common/common_lib.jsp"%>
	<link href="${cp }/css/dashboard.css" rel="stylesheet">
	<link href="${cp }/css/blog.css" rel="stylesheet">
	
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>	
 	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">

<script>
</script>

</head>
<body>

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
						<h2 class="sub-header">게시글 수정</h2>
						<div class="table-responsive">

							<form action="${cp }/board/modifyBoard" method="post">
								<input type="hidden" name="user_id" value="${S_USER.userid }">
								<input type="hidden" name="bor_no" value="${boardListVo.bor_no }">
								<input type="hidden" name="rep_user_id" value="${boardListVo.user_id }">
								<input type="hidden" name="post_no" value="${boardListVo.post_no }">
							
							제목<input type="text" id="title" name="title"> 
							
							<br><br>
							
							글내용 
							<textarea id="summernote" name="cont"></textarea>
							
							  <script>
							    $(document).ready(function() {
							        $('#summernote').summernote();
							    });
							  </script>
							  
							<br>
<!-- 							첨부파일 <input type="file" id="file" name="file"> -->
								<button type="submit" id="insertBtn" class="btn btn-default pull-right">저장</button>
							</form>
							
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