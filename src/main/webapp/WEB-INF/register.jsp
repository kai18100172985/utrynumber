<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"  href="/static/css/bootstrap.min.css"/>
<script type="text/javascript" src="/static/js/bootstrap.min.js" ></script>
    <meta charset="utf-8"></meta>
    <link href="/static/css/bootstrap.css" rel="stylesheet"/>
    <link href="/static/css/style.css" rel="stylesheet"/>
    <title>
        注册
    </title>
</head>
<body>
<div class="container">
    <div class="col-md-6 col-md-offset-3" id="form-container">
        <h1 class="text-center">
            用 户 注 册
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


            
            <div class="col-lg-6" style="margin-bottom: 8px">
                <button class="btn btn-primary btn-block" type="submit"">
                        注册
                    </button>
 
            </div>
            <div class="col-lg-6" style="margin-bottom: 18px">
                <a class="btn btn-primary btn-block" href="login">
                    已有账号
                </a>
            </div>
        </form>
    </div>
</div>
</body>
<script src="/static/js/jquery-3.3.1.min.js" type="text/javascript">
</script>
<script src="/static/js/common.js" type="text/javascript">
</script>
</html>