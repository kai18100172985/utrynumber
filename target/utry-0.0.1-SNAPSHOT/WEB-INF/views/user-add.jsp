<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"></meta>
    <title>
        添加/修改信息
    </title>
</head>
<body>
<div class="container">
    <div class="col-md-6 col-md-offset-3" id="form-container">
        <h1 class="text-center">
            用 户 添加/修改 信息
        </h1>
        <form action="user/register" class="form-inline" method="post" role="form">
              <div class="form-group" style="padding-bottom: 8px;">
                <label class="col-md-6 text-center" >
                    用户名:
                </label>
                <div class="col-md-6">
                    <input class="form-control" id="userName" name="userName" required="true" type="text"/>                
                    </p>
                </div>
            </div>
            <div class="form-group" style="padding-bottom: 8px;">
                <label class="col-md-6 text-center" >
                    昵称:
                </label>
                <div class="col-md-6">
                    <input class="form-control" id="nikeName" name="nikeName" required="true" type="text"/>                
                    </p>
                </div>
            </div>
            
            <div class="form-group" style="padding-bottom: 8px;">
                <label class="col-md-6 text-center" for="telephone">
                    手 机 号 :
                </label>
                <div class="col-md-6">
                    <input class="form-control" id="telephone" name="telephone" onblur="checkPhone()" required="true" type="text"/>
                    <p id="telephoneError" style="margin-bottom: -2px;color: red;">
                    </p>
                </div>
            </div>
            
             <div class="form-group" style="padding-bottom: 8px;">
                <label class="col-md-6 text-center" for="email">
                    邮      箱 :
                </label>
                <div class="col-md-6">
                    <input class="form-control" id="email" name="email" onblur="checkEmail()" required="true" type="text"/>
                    <p id="emailError" style="margin-bottom: -2px;color: red;">
                    </p>
                </div>
            </div>
            <div class="form-group" style="padding-bottom: 8px;">
                <label class="col-md-6 text-center" for="password">
                    密      码 :
                </label>
                <div class="col-md-6">
                    <input class="form-control" id="password" name="password" onblur="checkPassword()" required="true" type="password"/>
                    <p id="passwordError" style="margin-bottom: -2px;color: red;">
                    </p>
                </div>
            </div>
            <div class="form-group" style="padding-bottom: 8px;">
                <label class="col-md-6 text-center" for="rePassword">
                    重复密码:
                </label>
                <div class="col-md-6">
                    <input class="form-control" id="rePassword" name="rePassword" onblur="checkrePassword()" required="true" type="password"/>
                    <p id="rePasswordError" style="margin-bottom: -2px;color: red;">
                    </p>
                </div>
            </div>

        </form>
        <div style="margin-bottom: 8px;display: inline-block;">
                <button onclick="useradd()">
                       添加
                </button>
 
            </div>
            <div style="margin-bottom: 18px;display: inline-block;">
                <button onclick="userupdate()">
                    修改
                </button>
            </div>
    </div>
</div>
<script  type="text/javascript">
$(function(){

})
function useradd(){

	var params={
			"name" : $("#userName").val(),
			"password" : $("#password").val(),
			"nikeName" : $("#nikeName").val(),
			"telephone":$("#telephone").val(),
			"email" : $("#email").val()
	}
	
	
		$.post("/page/user/add", params, function(data) {
			alert(data);
		
		});
}

function userupdate(){
	var params={
			"name" : $("#userName").val(),
			"password" : $("#password").val(),
			"nikeName" : $("#nikeName").val(),
			"telephone":$("#telephone").val(),
			"email" : $("#email").val()
	}
	$.post("/page/user/update", params, function(data) {
		alert(data);
	
	});
}

</script>
</body>


</html>