notice_init_detail_page = function(id){
	   var elements=["head","silder","content"];
	    lib_destory_element(elements);
	    var notice = notice_getNoticeDetail(id);
	    var headHtml = "<header data-am-widget='header' class='am-header am-header-default'><a href='' style='color:white;'>返回</a><h1 class='am-header-title'><a href='#title-link'>Amaze UI</a></h1></header>";
    	var noticeTitleHtml="<div class='am-u-sm-12 am-article' style='margin:0 auto;text-align:center'><h1 class='am-article-title'>"+notice.title+"</h1><p class='am-article-meta'>"+notice.senderName+"</p><p class='am-article-meta'>"+notice.sendTime+"</p></div><hr class='am-article-divider'/>";
    	var noticeDetailHtml="<div class='am-g am-g-fixed'><div class='am-u-md-9 am-u-md-push-3'><div class='am-g'><div class='am-u-sm-11 am-u-sm-centered'><div class='am-cf am-article'>"+notice.content+"</div></div></div></div></div>";
    	$("#head").append(headHtml);
    	$("#content").append(noticeTitleHtml);
	    $("#content").append(noticeDetailHtml);
    }

notice_getNoticeDetail = function(id){
	var notice="";
	$.ajax({
    type: "get",
    url: server_ip+"/iClass/api/noticeRes/notice/"+id,
    contentType:"application/json",
    dataType: "json",
    async: false,
    success: function(data){
                notice = data.returnContent;  
             }
    });
	return notice;
}

notice_getByClassId = function(classId){
	  $.ajax({
        type: "get",
        url: server_ip+"/iClass/api/noticeRes/notices/class/"+classId,
        contentType:"application/json",
        dataType: "json",
        success: function(data){
                    var noticeList = new Array();
                    noticeList = data.returnContent;
                    var noticeHtml="";
                    for(var i=0;i<noticeList.length;i++){
                  	  noticeHtml = noticeHtml+"<li class='am-g am-list-item-dated'><a href='javascript:notice_init_detail_page("+noticeList[i].id+")' class='am-list-item-hd'>"+noticeList[i].title+"</a><span class='am-list-date'>"+noticeList[i].sendTime+"</span></li>";
                    }
                    
                    $("#noticeList").html(noticeHtml);
                 }
    });
}

notice_init_list_page = function(classId){
	var elements=["head","silder","content"];
    lib_destory_element(elements);
    var headHtml = "<header data-am-widget='header' class='am-header am-header-default'><a href='' style='color:white;'>返回</a><h1 class='am-header-title'><a href='#title-link'>Amaze UI</a></h1></header>";
    var noticeHtml="<div class='am-list-news-bd'><ul class='am-list' id='noticeList'>";
    $.ajax({
        type: "get",
        url: server_ip+"/iClass/api/noticeRes/notices/class/"+classId,
        contentType:"application/json",
        dataType: "json",
        async: false,
        success: function(data){
                    var noticeList = new Array();
                    noticeList = data.returnContent;
                    for(var i=0;i<noticeList.length;i++){
                  	  noticeHtml = noticeHtml+"<li class='am-g am-list-item-dated'><a href='javascript:notice_init_detail_page("+noticeList[i].id+")' class='am-list-item-hd'>&nbsp;&nbsp;"+noticeList[i].title+"</a><span class='am-list-date'>"+noticeList[i].sendTime+"</span></li>";
                    }
                 }
    });
    $("#head").append(headHtml);
    $("#content").append(noticeHtml+"</ul></div>");
}