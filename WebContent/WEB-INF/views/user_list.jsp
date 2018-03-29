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
        $('.btn-loading-example').click(function () {
            var $btn = $(this)
            var userName = $("#userName").val();
            var phone = $("#phone").val();
            $btn.button('loading');
            $.ajax({
                type: "post",
                url:"<%=basePath%>user/saveStudent.htmls?userName="+encodeURI(encodeURI(userName))+"&phone="+phone,
                contentType:"application/json",
                dataType: "json",
                async: false,
                success: function(data){
                    var result = data.result;
                    if(result==1){
                       var user="<tr>" +
                           "<td><input type=\"checkbox\" /></td>\n" +
                           " <td>1</td>\n" +
                           "<td>"+userName+"</td>\n" +
                           " <td>\n" +
                           " <div class=\"am-btn-toolbar\">\n" +
                           " <div class=\"am-btn-group am-btn-group-xs\">\n" +
                           " <button class=\"am-btn am-btn-default am-btn-xs am-text-secondary\"><span class=\"am-icon-pencil-square-o\"></span> 编辑</button>\n" +
                           " <button class=\"am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only\" onclick=\"deleteClass(${cramClass.id})\"><span class=\"am-icon-trash-o\"></span> 删除</button>\n" +
                           " </div>\n" +
                           " </div>\n" +
                           " </td>\n" +
                           "</tr>"
                        var userList = $("#userList").html();
                           userList = user+userList;
                        $("#userList").html(userList);
                        var $modal = $("#doc-modal-1");
                        $modal.modal("close");
                    }else if(result==0){
                        alert("姓名重复");
                        $btn.button('reset');
                        return;
                    }else{
                        alert("保存失败,请稍后再尝试");
                        $btn.button('reset');
                        return;
                    }

                }
            });
        });
    </script>

</head>
<body  style="overflow:auto; ">
<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf">
            <c:if test="${userType==0}">
                <strong class="am-text-primary am-text-lg">学生管理</strong> / <small>Student</small>
            </c:if>
            <c:if test="${userType==1}">
                <strong class="am-text-primary am-text-lg">教师管理</strong> / <small>Teacher</small>
            </c:if>
        </div>
    </div>

    <hr>

    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button type="button" class="am-btn am-btn-default"  data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 400, height: 225}"><span class="am-icon-plus"></span> 新增</button>
                </div>
            </div>
        </div>

        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <input type="text" class="am-form-field">
                <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="button">搜索</button>
          </span>
            </div>
        </div>
    </div>

    <div class="am-g">
        <div class="am-u-sm-12">
            <form class="am-form">
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th class="table-check"><input type="checkbox" /></th><th class="table-id">序号</th><th class="table-title">姓名</th><th class="table-set">操作</th>
                    </tr>
                    </thead>
                    <tbody id="userList">
                    <c:forEach items="${userList}" var="user" varStatus="c">
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>${c.index+1}</td>
                            <td>${user.userName}</td>
                            <td>
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
                                        <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="deleteClass(${cramClass.id})"><span class="am-icon-trash-o"></span> 删除</button>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="am-cf">
                    共 15 条记录
                    <div class="am-fr">
                        <ul class="am-pagination">
                            <li class="am-disabled"><a href="#">«</a></li>
                            <li class="am-active"><a href="#">1</a></li>
                            <li><a href="#">»</a></li>
                        </ul>
                    </div>
                </div>

            </form>
        </div>

    </div>
</div>

<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">学生信息
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd">
            <div class="am-input-group am-input-group-primary">
                <span class="am-input-group-label"><i class="am-icon-user am-icon-fw"></i></span>
                <input type="text" class="am-form-field" placeholder="学生姓名" id="userName">
            </div>
            <br>
            <div class="am-input-group am-input-group-primary">
                <span class="am-input-group-label"><i class="am-icon-user am-icon-fw"></i></span>
                <input type="text" class="am-form-field" placeholder="联系电话" id="phone">
            </div>
            <br>
            <button type="button" class="am-btn am-btn-primary btn-loading-example">Submit</button>
        </div>
    </div>
</div>

<footer class="admin-content-footer">
    <hr>
    <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
</footer>

<!-- content end -->

</body>
</html>