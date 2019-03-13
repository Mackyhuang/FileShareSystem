<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description"
          content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <!-- Twitter meta-->
    <meta property="twitter:card" content="summary_large_image">
    <meta property="twitter:site" content="@pratikborsadiya">
    <meta property="twitter:creator" content="@pratikborsadiya">
    <!-- Open Graph Meta-->
    <meta property="og:type" content="website">
    <meta property="og:site_name" content="Vali Admin">
    <meta property="og:title" content="Vali - Free Bootstrap 4 admin theme">
    <meta property="og:url" content="http://pratikborsadiya.in/blog/vali-admin">
    <meta property="og:image" content="http://pratikborsadiya.in/blog/vali-admin/hero-social.png">
    <meta property="og:description"
          content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <title>个人云盘</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="./css/webLoader.css" rel="stylesheet">
    <!--引入JS-->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://cdn.staticfile.org/webuploader/0.1.0/webuploader.min.js"></script>
</head>
<body class="app sidebar-mini rtl">
<!-- Navbar-->
<header class="app-header"><a class="app-header__logo" href="<%=request.getContextPath()%>/index.do">File</a>
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                    aria-label="Hide Sidebar"></a>
    <!-- Navbar Right Menu-->
    <ul class="app-nav">
        <!-- User Menu-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i
                class="fa fa-user fa-lg"></i></a>
            <ul class="dropdown-menu settings-menu dropdown-menu-right">
                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/logout.do"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
            </ul>
        </li>
    </ul>
</header>
<!-- Sidebar menu-->
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user">
        <div>
            <img style="height: 50px" class="app-sidebar__user-avatar" src="./img/pdx.gif" alt="User Image">
        </div>
        <div>
            <p class="app-sidebar__user-name">欢迎您：</p>
            <p class="app-sidebar__user-designation">${user.uName}</p>
        </div>
    </div>
    <ul class="app-menu">
        <li><a class="app-menu__item active" href="<%=request.getContextPath()%>/index.do"><i class="app-menu__icon fa fa-dashboard"></i><span class="app-menu__label">主页</span></a></li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-th-list"></i><span class="app-menu__label">用户专区</span><i class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="<%=request.getContextPath()%>/user_list.do"><i class="icon fa fa-circle-o"></i>用户列表</a></li>
            </ul>
        </li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-th-list"></i><span class="app-menu__label">文件专区</span><i class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li><a class="treeview-item" href="<%=request.getContextPath()%>/list_private.do"><i class="icon fa fa-circle-o"></i>我的文件</a></li>
                <li><a class="treeview-item" href="<%=request.getContextPath()%>/list_shared.do"><i class="icon fa fa-circle-o"></i>共享文件</a></li>
            </ul>
        </li>
    </ul>
</aside>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i>个人云盘</h1>
            <p>为您私人定制的网上文件管理系统</p>
        </div>
        <div id="uploader" class="wu-example" style="float: right;margin-left: 12px;">
            <!--用来存放文件信息-->
            <div id="thelist" class="uploader-list" style="float: left;"></div>
            <div class="btns" style="float: right;">
                <div id="picker" style="float: left;margin-left: 12px;">选择文件</div>
                <button style="width: 84px;height: 43px;display: inline-block; float: right;margin: 0 12px;" id="ctlBtn"
                        class="btn btn-default">开始上传
                </button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <table class="table table-hover table-bordered" id="sampleTable">
                        <thead>
                        <tr>
                            <th>文件名</th>
                            <th>文件大小</th>
                            <th>状态</th>
                            <th>拥有者</th>
                            <th>上传时间</th>
                            <th>操作</th>
                            <th>下载路径</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty res.list}">
                            <tr>
                                <td colspan="100">暂无数据行</td>
                            </tr>
                        </c:if>
                        <c:if test="${!empty res.list}">
                            <c:forEach var="rs" items="${res.list}">
                                <tr>
                                    <td>${rs.fileName}</td>
                                    <td>${rs.fileSize} KB</td>
                                    <td>
                                        <c:if test="${rs.fState eq 1}">私有</c:if>
                                        <c:if test="${rs.fState eq 2}">共享</c:if>
                                    </td>
                                    <td>${rs.fUsername}</td>
                                    <td><fmt:formatDate value="${rs.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>
                                        <a class="" href="<%=request.getContextPath()%>/share.do?id=${rs.fId}" style="margin-right: 10px"><i class="icon fa fa-circle-o"></i>共享</a>
                                        <a class="" href="<%=request.getContextPath()%>/delete.do?id=${rs.fId}"><i class="icon fa fa-circle-o"></i>删除</a>
                                    </td>
                                    <td><a href="<%=request.getContextPath()%>${rs.filePath}">点击下载</a></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</main>
<!-- Essential javascripts for application to work-->
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
<!-- The javascript plugin to display page loading on top-->
<script src="js/plugins/pace.min.js"></script>
<!-- Page specific javascripts-->
<!-- Data table plugin-->
<script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/plugins/dataTables.bootstrap.min.js"></script>
<script type="text/javascript">$('#sampleTable').DataTable();</script>
<!-- Google analytics script-->
<script type="text/javascript">
    if (document.location.hostname == 'pratikborsadiya.in') {
        (function (i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r;
            i[r] = i[r] || function () {
                (i[r].q = i[r].q || []).push(arguments)
            }, i[r].l = 1 * new Date();
            a = s.createElement(o),
                m = s.getElementsByTagName(o)[0];
            a.async = 1;
            a.src = g;
            m.parentNode.insertBefore(a, m)
        })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
        ga('create', 'UA-72504830-1', 'auto');
        ga('send', 'pageview');
    }
</script>
<script>


    // 文件上传
    jQuery(function () {
        var $ = jQuery,
            $list = $('#thelist'),
            $btn = $('#ctlBtn'),
            state = 'pending',
            uploader;

        uploader = WebUploader.create({

            // swf文件路径
            swf: "./js/Uploader.swf",

            // 文件接收服务端。
            server: '<%=request.getContextPath() %>/upload.do',
            //文件上传方式
            method: 'POST',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker',
            fileNumLimit: 1,
        });

        // 当有文件添加进来的时候
        uploader.on('fileQueued', function (file) {
            $list.append('<div id="' + file.id + '" class="item">' +
                '<h4 class="info">' + file.name + '</h4>' +
                '<p class="state">等待上传...</p>' +
                '</div>');
        });

        // 文件上传过程中创建进度条实时显示。
        uploader.on('uploadProgress', function (file, percentage) {
            var $li = $('#' + file.id),
                $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if (!$percent.length) {
                $percent = $('<div class="progress progress-striped active">' +
                    '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                    '</div>' +
                    '</div>').appendTo($li).find('.progress-bar');
            }

            $li.find('p.state').text('上传中');

            $percent.css('width', percentage * 100 + '%');
        });

        uploader.on('uploadSuccess', function (file) {
            $('#' + file.id).find('p.state').text('已上传');
            setTimeout(function () {
                window.location.href = "<%=request.getContextPath() %>/list_private.do"
            }, 1000);
        });

        uploader.on('uploadError', function (file) {
            $('#' + file.id).find('p.state').text('上传出错');
        });

        uploader.on('uploadComplete', function (file) {
            $('#' + file.id).find('.progress').fadeOut();
        });

        uploader.on('all', function (type) {
            if (type === 'startUpload') {
                state = 'uploading';
            } else if (type === 'stopUpload') {
                state = 'paused';
            } else if (type === 'uploadFinished') {
                state = 'done';
            }

            if (state === 'uploading') {
                $btn.text('暂停上传');
            } else {
                $btn.text('开始上传');
            }
        });

        $btn.on('click', function () {
            if (state === 'uploading') {
                uploader.stop();
            } else {
                uploader.upload();
            }
        });
    });
</script>
</body>
</html>