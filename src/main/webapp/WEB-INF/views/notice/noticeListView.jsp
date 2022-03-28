<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript" scr="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">


</script>
</head>
<body>

<hr>
<h1 align="center">공지 사항</h1>
<!-- 로그인한 횐원이 관리자인 경우 공지사항 등록 버튼 보이게함 -->


<!-- 검색 항목 -->


<!-- 목록 출력 -->
<br>
<table align="center" width="500" border="1" cellspacing="0" cellpadding="1">
	<tr><th>번호</th><th>제목</th><th>작성자</th><th>첨부파일</th><th>날짜</th></tr>
	<c:forEach items="${ requestScope.list }" var="n">
		<tr align="center">
			<td>${ n.noticeno }</td>
			<!-- 공지제목클릭시 상세보기로 넘어가게처리 -->
			<c:url var="ndt" value="/ndetail.do">
				<c:param name="noticeno" value="${ n.noticeno }" />
			</c:url>
			<td><a href="${ ndt }">${ n.noticetitle }</a></td>
			<td>${ n.noticewriter }</td>
			<td>
				<c:if test="${ !empty n.original_filepath }">◎</c:if>
				<c:if test="${ empty n.original_filepath }">&nbsp;</c:if>
			</td>
			<td><fmt:formatDate value="${ n.noticedate }" pattern="yyyy-mm-dd" /></td>
			
		</tr>
	</c:forEach>
</table>

<hr>
<c:import url="/WEB-INF/views/common/footer.jsp" />
</body>
</html>