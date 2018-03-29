$(function(){
	init_index_page();
})
  
init_index_page = function(){
	  user_init_student_page();
	  notice_getByClassId(loginUser.classId);
}

function user_init_data(){
	if(loginUser==null){
		var url = window.location.href;
		 var params = url.split("?uid=");
		 if(params.length<2)  window.location.href=server_ip+"/iClass/ui/login.html";
		 var uid = params[1];
		 if(uid==null||uid=="") window.location.href=server_ip+"/iClass/ui/login.html";
		 $.ajax({
	         type: "get",
	         url: server_ip+"/iClass/api/userRes/user/"+uid,
	         contentType:"application/json",
	         async: false,
	         dataType: "json",
	         success: function(data){
	                     var user = data.returnContent;
	                     loginUser= user;
	                  }
	     });
	}	 
}

user_init_student_page = function(){
	user_init_data();
	var headHtml = "<header data-am-widget='header' class='am-header am-header-default'><h1 class='am-header-title'><a href='#title-link'>Amaze UI</a></h1></header>";
	var menuHtml ="<ul class='am-avg-sm-4 am-avg-md-4 am-margin am-padding am-text-center'>"
        +"<li><a href='#' class='am-text-success'><span class='am-icon-btn am-icon-file-text'></span><br/>我的班级</a></li>"
        +"<li><a href='#' class='am-text-warning'><span class='am-icon-btn am-icon-briefcase'></span><br/>我的课程</a></li>"
        +"<li><a href='#' class='am-text-danger'><span class='am-icon-btn am-icon-recycle'></span><br/>我的成绩</a></li>"
        +"<li><a href='#' class='am-text-secondary'><span class='am-icon-btn am-icon-user-md'></span><br/>我的考勤</a></li></ul>";
	var noticeHtml="<div data-am-widget='list_news' class='am-list-news am-list-news-default' ><div class='am-list-news-hd am-cf'><a href='javascript:notice_init_list_page("+loginUser.classId+")' class=''><strong class='am-text-primary am-text-lg'>通知公告</strong><span class='am-list-news-more am-fr'>更多 &raquo;</span></a></div>" 
		+"<div class='am-list-news-bd'><ul class='am-list' id='noticeList'></ul></div></div>";
	var silderHtml = "<ul class='am-slides'><li><img src='http://s.amazeui.org/media/i/demos/bing-1.jpg'></li><li><img src='http://s.amazeui.org/media/i/demos/bing-2.jpg'></li><li><img src='http://s.amazeui.org/media/i/demos/bing-3.jpg'></li><li><img src='http://s.amazeui.org/media/i/demos/bing-4.jpg'></li></ul>"
	$("#head").append(headHtml);
	$("#silder").append(silderHtml);
	$("#content").append(menuHtml);
	$("#content").append(noticeHtml);
}

user_init_list_page = function(type){
	var elements=["head","silder","content"];
    lib_destory_element(elements);
    var headHtml = "<header data-am-widget='header' class='am-header am-header-default'><a href='' style='color:white;'>返回</a><h1 class='am-header-title'><a href='#title-link'>Amaze UI</a></h1></header>";
    var userHtml="<div class='am-list-news-bd'><ul class='am-list' id='noticeList'>";
    $.ajax({
        type: "get",
        url: server_ip+"/iClass/api/userRes/users/type/"+classId,
        contentType:"application/json",
        dataType: "json",
        async: false,
        success: function(data){
                    var noticeList = new Array();
                    userList = data.returnContent;
                    for(var i=0;i<noticeList.length;i++){
                    	userHtml = userHtml+"<li class='am-g am-list-item-dated'><a href='javascript:notice_init_detail_page("+userList[i].id+")' class='am-list-item-hd'>&nbsp;&nbsp;"+userList[i].title+"</a><span class='am-list-date'>"+userList[i].sendTime+"</span></li>";
                    }
                 }
    });
}
