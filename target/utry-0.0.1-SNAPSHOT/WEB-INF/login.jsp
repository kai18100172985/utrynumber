<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<link rel="stylesheet" type="text/css"  href="/static/css/bootstrap.min.css"/>
	<script type="text/javascript" src="/static/js/bootstrap.min.js" ></script>
    <link href="/static/css/bootstrap.css" rel="stylesheet"/>
    <link href="/static/css/style.css" rel="stylesheet"/>
        <meta charset="utf-8"></meta>
    <title>
        登录
    </title>
</head>
<body>
<div class="container">
    <div class="col-md-4 col-md-offset-4" id="form-container">
        <h1 class="text-center">
            用 户 登 录
        </h1>
        <form id="loginForm" class="form-inline" method="post" role="form" action="user/login">
            <div class="form-group" style="padding-bottom: 8px;">
                <label class="col-md-4" for="telephone">
                    用户名:
                </label>
                <div class="col-md-8">
                    <input class="form-control" id="userName" name="userName" required="true" type="text"/>                
                    </p>
                </div>
            </div>
            <div class="form-group" style="padding-bottom: 8px;">
                <label class="col-md-4" for="password">
                    密   码:
                </label>
                <div class="col-md-8">
                    <input class="form-control" id="password" name="password" onblur="checkPassword()" required="true" type="password"/>
                    <p id="passwordError" style="margin-bottom: -2px;color: red;">
                    </p>
                </div>
            </div>
            <div class="form-group" style="margin-bottom: 15px;">
                <div class="col-md-3 col-md-offset-3">
                    <button class="btn btn-primary btn-lg" type="submit"">
                        登录
                    </button>
                </div>
                <div class="col-md-3 col-md-offset-3">
                    <a class="btn btn-primary btn-lg" href="register">
                        注册
                    </a>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="/static/js/jquery-3.3.1.min.js" ></script>
<script type="text/javascript" src="/static/js/common.js"></script>
</html>