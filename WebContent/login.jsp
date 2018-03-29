<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String path=request.getContextPath();
   String basePath=request.getScheme()+"://"
		   +request.getServerName()+":"+request.getServerPort()
		   +path+"/";
%>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>优佳教育</title>  
  <link rel="icon" type="image/png" href="<%=basePath%>assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="<%=basePath%>assets/i/favicon.png">
  <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
  <style type="text/css">
  body {
  background: #eee !important;
}

.wrapper {
  margin-top: 0px;
  margin-bottom: 0px;
}

.form-signin {
  max-width: 850px;
  padding: 15px 35px 45px;
  margin: 0 auto;
  background-color: #fff;
  border: 1px solid rgba(0, 0, 0, 0.1);
}

.form-signin .form-control {
  position: relative;
  font-size: 16px;
  height: 100px;
  padding: 10px;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 20px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
#img{
height:100%; 
width:100%; 
background-position:center; 
background-repeat:no-repeat; 
background-image: url(http://tupian.aladd.net/photo2/1611.jpg);
}
  
  </style>
 <script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
 <script type="text/javascript" src="<%=basePath%>js/md5.js"></script>
 <script type="text/javascript">
 var localStorage  =  window.localStorage;
 $(function(){	
	var account = localStorage["account"];
	var password = localStorage["password"];
	if(account!=null&&password!=null){
		toLogin(account,password);	
	}
	setTimeout(function(){open()},1500);
})

    function open(){
	 $("#img").attr("style","display:none;");
	 $(".wrapper").attr("style","display:block;");
 }
    function toLogin(acc,pass){
	 var account = null;
 	 var password = null;
	 if(acc==null||pass==null){
		 account = $("#account").val();
	     password = hex_md5($("#password").val());
	 }else{
		 account = acc;
		 password = pass;
	 }
    	$.ajax({
    	    type: "post",
    	    url:"<%=basePath%>user/login.htmls?account="+account+"&password="+password,
    	    contentType:"application/json",
    	    dataType: "json",
    	    async: false,
    	    success: function(data){
    	    	        var msg = data.msg;
    	    	        if(msg=="success"){
    	    	        	localStorage.account = account;
    	    	        	localStorage.password = password;
    	    	        	window.location.href="<%=basePath%>"+data.url; 
    	    	        }else{
    	    	        	alert("账号或密码错误，请重新输入");
    	    	        }
    	                
    	             }
    	    });
    }
 </script>
</head>

<body style="text-align:center;background-color:#ffffff;">
 <div id="img"> 
   <img alt="" src="<%=basePath%>img/start.jpg" width="100%" height="100%">
 </div>
    <div class="wrapper" style="display:none;">
    <form class="form-signin" action="<%=basePath%>user/login.htmls" method="post" id="loginForm">   
    <div style="width:100%;text-align:center"><img  src="<%=basePath%>img/logo.png" width="450px"/>   </div> 
      <input type="text" class="form-control" name="account" id=account placeholder="请输入您的账号" style="font-size:30px;"/><br>
      <input type="password" class="form-control" name="password" id="password" placeholder="请输入您的密码" style="font-size:30px;"/>      

      <button class="btn btn-lg btn-primary btn-block" type="button" onclick="toLogin()" style="height:100px;font-size:40px;">Login</button> 
      <button class="btn btn-lg btn-success btn-block" type="button" style="height:100px;font-size:40px;">Register</button>   
    </form>
  </div>
</body>

</html>