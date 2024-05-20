<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.pf.www.forum.message.MessageEnum" %>
<%
String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<!-- Coding By CodingNepal - codingnepalweb.com -->
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- ===== Iconscout CSS ===== -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

    <!-- ===== CSS 주의할점 : /resources/css/style.css resource안에 css파일 안에 style.css를 넣는다.  ===== -->
    <link rel="stylesheet" href="./resources/css/style.css">
         
    <title>Login & Registration Form</title>
    
</head>

<body>
    
    <div class="container">
        <div class="forms">
            <div class="form login">
                <span class="title">Login</span>

				<!-- 액션이 # 인경우는 아무것도 안하겠다는 의미  -->
                <form action="./login.do" method="POST" > 
                    <div class="input-field">
                        <input type="text" placeholder="Enter your email" name ="email" required>
                        <i class="uil uil-envelope icon"></i>
                    </div>
                    <div class="input-field">
                        <input type="password" class="password" name = "passwd" placeholder="Enter your password" required>
                        <i class="uil uil-lock icon"></i>
                        <i class="uil uil-eye-slash showHidePw"></i>
                    </div>

                    <div class="checkbox-text">
                        <div class="checkbox-content">
                            <input type="checkbox" id="logCheck">
                            <label for="logCheck" class="text">Remember me</label>
                        </div>
                        
                        <a href="#" class="text">Forgot password?</a>
                    </div>

                    <div class="input-field button">
                        <input type="button" value="Login" onClick="submit();">
                    </div>
                </form>

                <div class="login-signup">
                    <span class="text">Not a member?
                        <a href="#" class="text signup-link">Signup Now</a>
                    </span>
                </div>
            </div>

            <!-- Registration Form -->
            <div class="form signup">
                <span class="title">Registration</span>

                <form action="./join.do">
                    <div class="input-field">
                        <input type="text" placeholder="Enter your name"  name = "memberId"  required>
                        <i class="uil uil-user"></i>
                    </div>
                    <div class="input-field">
                        <input type="text" placeholder="Enter your email" name = "email" required>
                        <i class="uil uil-envelope icon"></i>
                    </div>
                    <div class="input-field">
                        <input type="password" id = "passwd1" class="password" placeholder="Create a password" name="passwd" required>
                        <i class="uil uil-lock icon"></i>
                    </div>
                    <div class="input-field">
                        <input type="password" id= "passwd2" class="password" placeholder="Confirm a password" required>
                        <i class="uil uil-lock icon"></i>
                        <i class="uil uil-eye-slash showHidePw"></i>
                    </div>

                    <div class="checkbox-text">
                        <div class="checkbox-content">
                            <input type="checkbox" id="termCon">
                            <label for="termCon" class="text">I accepted all terms and conditions</label>
                        </div>
                    </div>

                    <div class="input-field button">
                        <input type="button" value="Signup" onClick="submit();">
                    </div>
                </form>

                <div class="login-signup">
                    <span class="text">Already a member?
                        <a href="#" class="text login-link">Login Now</a>
                    </span>
                </div>
            </div>
        </div>
    </div>
    
<!-- 주의할점 : /resources/js/script.js설정시 resources안에 js파일안에 script.js 넣을 것 -->
     <script src="./resources/js/script.js"></script> 
     
   	<script type="text/javascript">
		var ctx = '<%= request.getContextPath() %>';
	</script>		
	
    <script type="text/javascript">
    	window.onload=function(){
    		var code = '${code}';
    		var msg = '${msg}';

    		if(code != '' && code != '<%= MessageEnum.SUCCESS.getCode() %>'){
    			alert(msg);
				location.href='<%=ctx%>/loginPage.do'
    		}
    	}
    </script> 
    
</body>
</html>