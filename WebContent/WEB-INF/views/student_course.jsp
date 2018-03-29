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
  <style type="text/css">
.wrap {
    width:30px;
    margin-bottom:10px;
    position:relative;
}
.img {
font-size:20px;
    width:30px;
    height:30px;
    border:0px solid #000;
}
.msg {
    width:18px;
    height:18px;
    line-height:20px;
    font-size:12px;
    color:#fff;
    text-align:center;
    background-color:#f00;
    border-radius:50%;
    position:absolute;
    right:-10px;
    top:-10px;
}
td{
width:14%;
}
th{
font-size:15px;
height:30px;
color:#0c80ba;
text-align:center;
}

table{
vertical-align:middle;
text-align:center;
font-size:14px;
}
  </style>
  <script type="text/javascript">
  var courseMap=null;
  var currentDate=null;
  $(function(){
	  getStudentCourses();
	  currentDate = ${day};
	  chooseDate(currentDate);
	})
	
  function chooseDate(date){
	  currentDate = date;
	  var year = $("#year").html();
	  var month = $("#month").html();
	  $("#date").html(year+"-"+month+"-"+date);
	  var courseList = courseMap[date];
	  var courseListHtml ="";
      if(courseList!=null){
    	  for(var i=0;i<courseList.length;i++){
    		  var course = courseList[i];
    		  courseListHtml+="<tr id='"+course.id+"'><td width='30%'>"+course.startTime+"-"+course.endTime+"</td><td width='30%'><a href=javascript:getClassStudent("+course.classId+",'"+course.className+"') >"+course.className+"</a></td><td width='20%'><button type='button' class='am-btn am-btn-danger' onclick='deleteCourse("+course.id+")'>删除</button></td></tr>";
    	  }
      }
	  
	  $(".wrap").attr('style','color:black;');
	  $("#d"+date).attr('style','color:blue;');	  
      $("#courseList").html(courseListHtml);
  }
	
    function getStudentCourses(){
        var year = $("#year").html();
        var month = $("#month").html();

    	$.ajax({
    	    type: "get",
    	    url:"<%=basePath%>course/studentCourses.htmls?year="+year+"&month="+month,
    	    contentType:"application/json",
    	    dataType: "json",
    	    async: false,
    	    success: function(data){
    	    	       courseMap = data; 
    	    	       for(key in courseMap){
    	    	    	  $("#msg"+key).attr("class","msg");
    	    	    	  $("#msg"+key).html(courseMap[key].length);
    	    	       }
    	             }
    	    });
    }
          
  </script>
 
</head>
<body  style="overflow:auto; ">
<!-- Header -->
 <header data-am-widget="header" class="am-header am-header-default">
   <a href='<%=basePath%>user/student.htmls' style='color:white;'>返回</a> 
</header>
<div style="width:100%;height:30px;text-align:center;font-size:20px;background-color:#F0F0F0;"><i></i>&nbsp;&nbsp;&nbsp;&nbsp;<span id="year">${year}</span>&nbsp;&nbsp;年&nbsp;&nbsp;<span id="month">${month}</span>&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;<i></i></div>
<div >
  <table style="width:100%;">
    <thead>
        <tr>
            <th>一</th>
            <th>二</th>
            <th>三</th>
            <th>四</th>
            <th>五</th>
            <th>六</th>
            <th>日</th>
        </tr>
    </thead>
    <tbody>
        <tr>
        <c:if test="${(weekDay-1)>0}">
          <c:forEach begin="1" end="${weekDay-1}">
           <td align="center">
             <div class="wrap">
                <div class="img"></div>
             </div>
           </td>
          </c:forEach>
        </c:if>
       <c:forEach begin="0" end="${monthDays-1}" varStatus="s">
           <td align="center">
             <div class="wrap" onclick="chooseDate('${s.index+1}')" id="d${s.index+1}" >
              <c:if test="${(s.index+1)==day}">
                <div class="img" style="color:#0c80ba;">${s.index+1}</div>
              </c:if>
              <c:if test="${(s.index+1)!=day}">
                <div class="img">${s.index+1}</div>
              </c:if>
                <div id="msg${s.index+1}"></div>
             </div>
           </td>
         <c:if test="${(s.index+weekDay)%7==0}">
           </tr>
         </c:if>
     </c:forEach> 
           
    </tbody>
</table>
</div>
<div>
  <table class="am-table  am-table-radius am-table-striped">
    <thead>
        <tr>
            <th width="40%">时间</th>
            <th width="40%">班级</th>
            <th width="20%">&nbsp;</th>
        </tr>
    </thead>
    <tbody id="courseList">                   
    </tbody>
</table>
</div>

</body>
</html>