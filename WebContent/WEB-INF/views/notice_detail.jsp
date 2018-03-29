<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
   String path=request.getContextPath();
   String basePath=request.getScheme()+"://"
		   +request.getServerName()+":"+request.getServerPort()
		   +path+"/";
%><!DOCTYPE html>
<html class="no-js fixed-layout">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>优佳教育</title>
  <meta name="description" content="这是一个 index 页面">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="<%=basePath%>assets/i/favicon.ico">
  <link rel="apple-touch-icon-precomposed" href="<%=basePath%>assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="<%=basePath%>assets/css/admin.css">
  <script src="<%=basePath%>assets/js/jquery.min.js"></script>
  <script src="<%=basePath%>assets/js/amazeui.min.js"></script>

</head>
<body  style="overflow:auto; ">
<!-- Header -->
 <header data-am-widget="header" class="am-header am-header-default">
   <a href='<%=basePath%>user/student.htmls' style='color:white;'>返回</a> 
</header>

<div class='am-u-sm-12 am-article' style='margin:0 auto;text-align:center'>
  <h1 class='am-article-title'>${notice.title}</h1>
  <p class='am-article-meta'>${notice.senderName}</p>
  <p class='am-article-meta'>${notice.sendTime}</p>
</div>
<hr class='am-article-divider'/>

<div class='am-g am-g-fixed'>
  <div class='am-u-md-9 am-u-md-push-3'>
    <div class='am-g'>
      <div class='am-u-sm-11 am-u-sm-centered'>
        <div class='am-cf am-article'>${notice.content}</div>
        </div>
      </div>
    </div>
  </div>

</body>
</html>