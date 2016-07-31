<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=8" />
    <title>登陆页</title>
    <link href="./pageres/css/StyleSheet.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./pageres/jquery-1.10.1.js"></script>
    <style type="text/css">
        body{ background:#031A55 url(./pageres/images/bg.png) repeat-x;}
    </style>
</head>
<body>
    <div class="lg_center">
    <form action="./user/login.jhtml" method="post">
        <div class="lg_user_con">
            <img alt="#" src="./pageres/images/user.png" />
            <p>用户名：</p>
            <input class="lg_input" type="text" />
        </div>
        <div class="lg_user_con lg_key_con">
            <img alt="#" src="./pageres/images/key.png" />
            <p>密　码：</p>
            <input class="lg_input" type="text" />
        </div>
        <input type="submit"  class="lg_btn" value=""/>
        </form>
        <div class="lg_foot">
            <p>版权所有：</p>
            <p>E-MAIL：sdzffzw@163.com  电话：0531-82566969  地址：济南市省府前街1号  鲁ICP备06002095</p>
        </div>
    </div>      
</body>
</html>
