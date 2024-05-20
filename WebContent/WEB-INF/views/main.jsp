<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	session = request.getSession();
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<script type="text/javascript">
	function doLogout() {
		window.location.href = './logout.do';
	}
	</script>
</head>

<body>
	memberId = <%= session.getAttribute("memberId") %>
	
	<input type="button" value="logout" onClick="javascript:doLogout()"> 
</body>

</html>