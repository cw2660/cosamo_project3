<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>web</title>
<script type="text/javascript">
function validate(){
	var pwd1 = document.getElementById("userpwd").value;
	var pwd2 = document.getElementById("userpwd2").value;
	if(pwd1 !== pwd2){
		alert("암호와 암호확인 일치안함 \n 다시입력하시오");
		document.getElementById("userpwd").select();
	}

}

</script>
</head>
<body>
<hr>
<h1 align="center">내 정보 보기</h1>
<br>
<form method="post" action="mupdate.do">
	<input type="hidden" name="origin_userpwd" value="${ member.userpwd }">
<table id="outer" align="center" width="700" cellspacing="5" cellpadding="0">
	<tr>
		<th width="120">이 름</th>
		<td><input type="text" name="username" value="${ requestScope.member.username }" readonly></td>
	</tr>
	<tr>
		<th width="120">아이디</th>
		<td><input type="text" name="userid" value="${ member.userid }" readonly></td>
	</tr>
	<tr>
		<th width="120">암 호</th>
		<td><input type="password" name="userpwd" id="userpwd" value=""></td>
	</tr>
	<tr>
		<th width="120">암호확인</th>
		<td><input type="password" id="userpwd2" onblur="validate();"></td>
		<!-- onblur: foucs가 사라질때 작동되는이벤트 -->
	</tr>
	<tr>
		<th width="120">성 별</th>
		<td>
		<c:if test="${ member.gender eq 'M' }">
			<input type="radio" name="gender" value="M" checked>남자
			<input type="radio" name="gender" value="F">여자
		</c:if>
		<c:if test="${ member.gender eq 'F' }">
			<input type="radio" name="gender" value="M">남자
			<input type="radio" name="gender" value="F" checked>여자
		</c:if>
		</td>
	</tr>
	<tr>
		<th width="120">나 이</th>
		<td><input type="number" name="age" min="19" max="100" value="${ member.age }"></td>
	</tr>
	<tr>
		<th width="120">전화번호</th>
		<td><input type="tel" name="phone" value="${ member.phone }"></td>
	</tr>
	<tr>
		<th width="120">이메일</th>
		<td><input type="email" name="email" value="${ member.email }"></td>
	</tr>
	<tr>
		<th width="120">취 미</th>
		<td>
			<!-- 취미 문자열을 각각의 문자열로 분리하면서 취미에 적용할 변수9개 만듦 -->
			<c:forTokens items="${ member.hobby }" delims="," var="hb">
				<c:if test="${ hb eq 'game' }">
					<c:set var="checked1" value="checked" />
				</c:if>
				<c:if test="${ hb eq 'reading' }">
					<c:set var="checked2" value="checked" />
				</c:if>
				<c:if test="${ hb eq 'climb' }">
					<c:set var="checked3" value="checked" />
				</c:if>
				<c:if test="${ hb eq 'sport' }">
					<c:set var="checked4" value="checked" />
				</c:if>
				<c:if test="${ hb eq 'music' }">
					<c:set var="checked5" value="checked" />
				</c:if>
				<c:if test="${ hb eq 'movie' }">
					<c:set var="checked6" value="checked" />
				</c:if>
				<c:if test="${ hb eq 'travle' }">
					<c:set var="checked7" value="checked" />
				</c:if>
				<c:if test="${ hb eq 'cook' }">
					<c:set var="checked8" value="checked" />
				</c:if>
				<c:if test="${ hb eq 'etc' }">
					<c:set var="checked9" value="checked" />
				</c:if>
			</c:forTokens>
			<table width="350">
			<tr>
				<td><input type="checkbox" name="hobby" value="game" ${ checked1 }> 게임</td>
				<td><input type="checkbox" name="hobby" value="reading" ${ checked2 }> 독서</td>
				<td><input type="checkbox" name="hobby" value="climb" ${ checked3 }> 등산</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="hobby" value="sport" ${ checked4 }> 운동</td>
				<td><input type="checkbox" name="hobby" value="music" ${ checked5 }> 음악듣기</td>
				<td><input type="checkbox" name="hobby" value="movie" ${ checked6 }> 영화보기</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="hobby" value="travel" ${ checked7 }> 여행</td>
				<td><input type="checkbox" name="hobby" value="cook" ${ checked8 }> 요리</td>
				<td><input type="checkbox" name="hobby" value="etc" ${ checked9 }> 기타</td>
			</tr>
			</table>
		</td>
	</tr>
	<tr>
		<th width="120">추가사항</th>
		<td><textarea style="resize: none;" name="etc" rows="5" cols="45">${ member.etc }</textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<a href="javascript:history.go(-1)">이전페이지로</a> &nbsp;
			<input type="submit" value="수정하기"> &nbsp;
			<input type="reset" value="수정취소"> &nbsp;
			
			<a href="main.do">시작페이지로</a> &nbsp;&nbsp;
			
			<!-- 탈퇴하기 요청 처리용 -->
			<c:url var="mdelete" value="mdel.do">
				<c:param name="userid" value="${ member.userid }" />
			</c:url>
			<a href="${ mdelete }">탈퇴하기</a>
		</th>
	</tr>
</table>
</form>


<hr>
<c:import url="/WEB-INF/views/common/footer.jsp" />
</body>
</html>