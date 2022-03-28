<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Register</title>

    <!-- Custom fonts for this template-->
    <link href="${ pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${ pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	//전송버튼눌렷을때 입력값들대한 유효성 검사
	function validate(){
		//validation 체크검사 코드 구현
		//서버 컨트롤러로 전송할 값들이 유효한 값인지 검사하는거
		
		//암호 암호확인 검사 같은값인지확인작업
		var pwdValue = document.getElementById("userpwd").value;
		var pwdValue2 = document.getElementById("userpwd2").val.ue;
		
		if(pwdValue !== pwdValue2){
			alert("암호확인이 일치하지 않습니다");
			document.getElementById("userpwd").select();
			return false; //전송취소
		}
		
		return true; //검사문제없어서 여기까지오면 전송해라
	}
	
	function dupCheckId(){
		$.ajax({
			url: "idchk.do",
			type: "post",
			data: { userid: $("#userid").val() },
			success: function(data){
				console.log("success : "+ data );
				if(data == "ok"){
					alert("사용가능한 아이디입니다");
					$("#userpwd").focus();
				}else{
					alert("이미 사용중인 아이디입니다\n 다시입력하시오");
					$("#userid").select();
				}
			},
			error: function(jqXHR, textstatus, errorthrown){
				console.log("error : "+jqXHR + ", "+textstatus+", "+errorthrown);
			}
			
		});
		
	}
</script>
</head>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            <form class="user" action="enroll.do" method="post" onsubmit="return validate();">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" name="username" class="form-control form-control-user" id="exampleFirstName"
                                            placeholder="Name" required>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" name="userid" class="form-control form-control-user" id="userid"
                                            placeholder="Account" required>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                <input type="button" value="Duplicate Check" onclick="return dupCheckId();">
                                </div>
                                
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="password" name="userpwd" class="form-control form-control-user" id="exampleFirstName"
                                            placeholder="Password" required>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control form-control-user" id="exampleLastName"
                                            placeholder="Repeat Password">
                                    </div>
                                </div>
                                
                                <div class="form-group">&nbsp; &nbsp;
									<th width="120">Gender</th>&nbsp; &nbsp; &nbsp;
									<td><input type="radio" name="gender" value="M" checked>Male &nbsp; &nbsp; &nbsp;
									<input type="radio" name="gender" value="F">Female
									</td>
								</div>
                                
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="number" name="age" min="19" max="100" value="20" class="form-control form-control-user" id="exampleFirstName">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="tel" name="phone" class="form-control form-control-user" id="exampleLastName" placeholder="Phone Number" required>
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <input type="email" name="email" class="form-control form-control-user" id="exampleInputEmail"
                                        placeholder="Email Address" required>
                                </div>
                                
                                <div class="form-group">
                                    <table width="350" align="center">
									<tr>
										<td><input type="checkbox" name="hobby" value="game"> 게임</td>
										<td><input type="checkbox" name="hobby" value="reading"> 독서</td>
										<td><input type="checkbox" name="hobby" value="climb"> 등산</td>
									</tr>
									<tr>
										<td><input type="checkbox" name="hobby" value="sport"> 운동</td>
										<td><input type="checkbox" name="hobby" value="music"> 음악듣기</td>
										<td><input type="checkbox" name="hobby" value="movie"> 영화보기</td>
									</tr>
									<tr>
										<td><input type="checkbox" name="hobby" value="travel"> 여행</td>
										<td><input type="checkbox" name="hobby" value="cook"> 요리</td>
										<td><input type="checkbox" name="hobby" value="etc" checked> 기타</td>
									</tr>
									</table>
                                </div>
                                
                                
                               	<tr>
									<td><textarea style="resize: none;" name="etc" rows="5" cols="60" placeholder="etc.."></textarea></td>
								</tr>
                                
                                
                                
                                <input class="btn btn-primary btn-user btn-block" type="submit" value="Register Account">
                                
                                <hr>
                                <a href="#" class="btn btn-google btn-user btn-block">
                                    <i class="fab fa-google fa-fw"></i> Register with Google
                                </a>
                                <a href="#" class="btn btn-facebook btn-user btn-block">
                                    <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook
                                </a>
                            </form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="forgot-password.html">Forgot Password?</a>
                            </div>
                            <div class="text-center">
                                <a class="small" href="loginPage.do">Already have an account? Login!</a>
                            </div>
                            <div class="text-center">
                                <a class="small" href="main.do">Go to Mainpage</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="${ pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${ pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${ pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${ pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

</body>

</html>