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
  <script type="text/javascript">
    function save(){
    	var content = $("#content").val();
    	$("#memoContent").val(encodeURI(content));
       $("#memo").submit();
    }
  </script>

</head>
<body  style="overflow:auto; ">
<!-- Header -->
 <header data-am-widget="header" class="am-header am-header-default">
   <a href='<%=basePath%>user/teacher.htmls' style='color:white;'>返回</a> 
</header>

<div data-am-widget='list_news' class='am-list-news am-list-news-default' >
  <div class='am-list-news-hd am-cf'>
  <strong class='am-text-primary am-text-lg'>新建备忘</strong>
  </div>
   <div class='am-list-news-bd'>
      <div class="am-g am-margin-top">
     
           
   </div>
</div>
   <div class="am-g am-margin-top">
              <div class="am-u-sm-8 am-u-md-4 am-text-right">
              <form action="<%=basePath%>memo/save.htmls" id="memo">
              <input type="hidden" name="content" id="memoContent">
               <textarea rows="10" cols="35" id="content"></textarea>
               </form>
              </div>
             
              
            </div>

             <div class="am-g am-margin-top">
              <div class="am-u-sm-4 am-u-md-2 am-text-right">
                <button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="save()">提交保存</button>
              </div>
              <div class="am-u-sm-8 am-u-md-4">
                 <button type="button" class="am-btn am-btn-primary am-btn-xs">放弃保存</button>
              </div>
             
            </div>
</div>
</body>
</html>