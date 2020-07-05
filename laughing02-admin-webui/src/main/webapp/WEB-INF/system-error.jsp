<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="GB18030">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>

    <link rel="stylesheet" href="statics/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="statics/css/font-awesome.min.css">
    <link rel="stylesheet" href="statics/css/login.css">
    <script type="text/javascript"src="statics/jquery/jquery-2.1.1.min.js"> </script>
    <script type="text/javascript"src="statics/layer/layer.js"></script>
    <script type="text/javascript">
        $(function(){
            $("button").click(function(){
                // 相当于浏览器的后退按钮
                window.history.back();
            });
        });
    </script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">


        <h3 class="btn btn-lg btn-success btn-block" onclick="" >
            出错了${requestScope.exception.message}</h3>
    <button style="width: 150px;margin: 50px auto 0px auto;" class="btn btn-lg btn-success btn-block">点我返回上一步</button>

</div>
<script src="statics/jquery/jquery-2.1.1.min.js"></script>
<script src="statics/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
