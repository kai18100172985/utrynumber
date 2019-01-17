<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退出系统</title>
</head>
<body>
     <button id="exit" onclick="exit()">退出登录</button>
     <script type="text/javascript">
     function exit(){
    		alert("确认退出该系统");
    		var param={
    				
    		}
    		$.get("/page/user/exit", param, function(data) {
    		 alert("11");
    		});
    		window.location.href="http://localhost:8071/page/login";
    		
    	}
     </script>
</body>
</html>