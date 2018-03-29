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
   <a href='<%=basePath%>user/studentIndex.htmls' style='color:white;'>返回</a>
</header>

<div data-am-widget='list_news' class='am-list-news am-list-news-default' >
<div class='am-list-news-hd am-cf'>
  <strong class='am-text-primary am-text-lg'>通知公告</strong>
  </div>
   <div class='am-list-news-bd'>
     <ul class='am-list' id='noticeList'>
      <c:forEach items="${noticeList}" var="notice">
        <li class='am-g am-list-item-dated'><a href="<%=basePath%>notice/detail.htmls?id=${notice.id}" class='am-list-item-hd'>&nbsp;&nbsp;${notice.title}</a><span class='am-list-date'>${notice.sendTime}</span></li>
      </c:forEach>
     </ul>
    </div>
</div>



</body>
</html>