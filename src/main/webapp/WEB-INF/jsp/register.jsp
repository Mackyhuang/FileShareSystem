<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html  lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Login - Vali Admin</title>
  </head>
  <body>
    <section class="material-half-bg">
      <div class="cover"></div>
    </section>
    <section class="login-content">
      <div class="logo">
        <h1>欢迎进入云上文件</h1>
      </div>
      <div class="login-box" style="min-height: 450px;">
        <form class="login-form" method="post" action="<%=request.getContextPath()%>/register.do">
          <h3 class="login-head"><i class="fa fa-lg fa-fw fa-user"></i>注册</h3>
          <div class="form-group">
            <label class="control-label">昵称</label>
            <input class="form-control" name="uName" type="text" placeholder="Name" autofocus>
          </div>
          <div class="form-group">
            <label class="control-label">用户名</label>
            <input class="form-control" name="uUsername" type="text" placeholder="Username" autofocus>
          </div>
          <div class="form-group">
            <label class="control-label">密码</label>
            <input class="form-control" name="uPassword" type="password" placeholder="Password">
          </div>
          <div class="form-group btn-container">
            <input type="submit" value="注册" class="btn btn-primary btn-block"/>
              <a class="btn btn-primary btn-block" href="<%=request.getContextPath()%>/login_page.do">返回</a>
          </div>
        </form>
      </div>
    </section>
    <!-- Essential javascripts for application to work-->
    <script src="./js/jquery-3.2.1.min.js"></script>
    <script src="./js/popper.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/main.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script src="./js/plugins/pace.min.js"></script>
    <script type="text/javascript">
      // Login Page Flipbox control
      $('.login-content [data-toggle="flip"]').click(function() {
      	$('.login-box').toggleClass('flipped');
      	return false;
      });
    </script>
  </body>
</html>