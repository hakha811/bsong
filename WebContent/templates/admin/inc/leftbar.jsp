<%@page import="utils.AuthUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	if(session.getAttribute("username") != null) {
%>
<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li class="text-center">
                <img src="<%=request.getContextPath()%>/templates/admin/assets/img/find_user.png" class="user-image img-responsive" />
            </li>
            <li>
                <a id="index" href="<%=request.getContextPath()%>/admin"><i class="fa fa-dashboard fa-3x"></i> Trang chủ</a>
            </li>
            <li>
                <a id="category" href="<%=request.getContextPath()%>/admin/cat"><i class="fa fa-list fa-3x"></i> Quản lý danh mục</a>
            </li>
            <li>
                <a id="song" href="<%=request.getContextPath()%>/admin/song"><i class="fa fa-music fa-3x"></i> Quản lý bài hát</a>
            </li>
            <%
            	if(AuthUtil.checkRole(request, response) == 2) {
            %>
            <li>
                <a id="user" href="<%=request.getContextPath()%>/admin/user"><i class="fa fa-user fa-3x"></i> Quản lý người dùng</a>
            </li>
            <li>
                <a id="contact" href="<%=request.getContextPath()%>/admin/contact"><i class="fa fa-envelope fa-3x"></i> Quản lý liên hệ</a>
            </li>
            <%
            	}
            %>
            <li>
                <a id="picture" href="<%=request.getContextPath()%>/admin/picture"><i class="fa fa-picture-o fa-3x"></i> Quản lý hình ảnh</a>
            </li>
        </ul>
    </div>
</nav>
<%
	}
%>
<!-- /. NAV SIDE  -->