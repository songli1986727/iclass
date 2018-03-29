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
<header data-am-widget="header"
          class="am-header am-header-default">
      <div class="am-header-left am-header-nav">
          <a href="#left-link" class="">

                <i class="am-header-icon am-icon-home"></i>
          </a>
      </div>

      <h1 class="am-header-title">
          <a href="#title-link" class="">
            优佳教育
          </a>
      </h1>

      <div class="am-header-right am-header-nav">
          <a href="#right-link" class="">

                <i class="am-header-icon am-icon-bars"></i>
          </a>
      </div>
  </header>
                     
<!-- Slider -->
<div data-am-widget="slider" class="am-slider am-slider-a1" data-am-slider='{"directionNav":false}' id="silder">
<ul class='am-slides'>
    <li><img src='<%=basePath%>img/banner1.png' height="10%"></li>
    <li><img src='http://s.amazeui.org/media/i/demos/bing-2.jpg'></li>
    <li><img src='http://s.amazeui.org/media/i/demos/bing-3.jpg'></li>
    <li><img src='http://s.amazeui.org/media/i/demos/bing-4.jpg'></li></ul>
 </div> 
 
<%--<div>
   <ul class='am-avg-sm-4 am-avg-md-4 am-margin am-padding am-text-center'>
      <li><a href='<%=basePath%>course/calendar.htmls' class='am-text-success'><span class='am-icon-btn am-icon-file-text'></span><br/>我的课程</a></li>
      <li><a href='#' class='am-text-warning'><span class='am-icon-btn am-icon-briefcase'></span><br/>我的课程</a></li>
      <li><a href='#' class='am-text-danger'><span class='am-icon-btn am-icon-recycle'></span><br/>我的成绩</a></li>
      <li><a href='#' class='am-text-secondary'><span class='am-icon-btn am-icon-user-md'></span><br/>我的考勤</a></li></ul>
</div>--%>

<div data-am-widget='list_news' class='am-list-news am-list-news-default' >
<div data-am-widget="titlebar" class="am-titlebar am-titlebar-multi" >
    <h2 class="am-titlebar-title ">
        通知公告
    </h2>
    <nav class="am-titlebar-nav">
        <a href="<%=basePath%>notice/list.htmls" class="">more &raquo;</a>
    </nav>
</div>
   <div class='am-list-news-bd'>
     <ul class='am-list' id='noticeList'>
      <c:forEach items="${noticeList}" var="notice">
        <li class='am-g am-list-item-dated'><a href="<%=basePath%>notice/detail.htmls?id=${notice.id}" class='am-list-item-hd'>&nbsp;&nbsp;${notice.title}</a><span class='am-list-date'>${notice.sendTime}</span></li>
      </c:forEach>
     </ul>
    </div>
</div>


  <div data-am-widget="list_news" class="am-list-news am-list-news-default" >
   <div data-am-widget="titlebar" class="am-titlebar am-titlebar-multi" >
    <h2 class="am-titlebar-title ">
       学习经验
    </h2>
    <nav class="am-titlebar-nav">
        <a href="<%=basePath%>notice/list.htmls" class="">more &raquo;</a>
    </nav>
  </div>
  <div class="am-list-news-bd">
  <ul class="am-list">
      <li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">

        <div class="am-list-main">
            <h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11614662/" class="">【新人报道】来分享一点个人的英语学习经验吧~</a></h3>

        </div>
      </li>
      <li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">


        <div class="am-list-main">
            <h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11624755/" class="">【经验交流】我是如何从英语学渣逆袭成为英语大神的？</a></h3>



        </div>
      </li>
      <li class="am-g am-list-item-desced">

        <div class=" am-list-main">
            <h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11645411/" class="">【新人报道】跪求怎么提高英语成绩</a></h3>
        </div>
      </li>

    
    </ul>
  </div>

  </div>

</body>
</html>