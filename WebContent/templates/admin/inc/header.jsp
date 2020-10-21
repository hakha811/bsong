<%@page import="utils.AuthUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>AdminCP | VinaEnter Edu</title>
<!-- BOOTSTRAP STYLES-->
<link
	href="<%=request.getContextPath()%>/templates/admin/assets/css/bootstrap.css"
	rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link
	href="<%=request.getContextPath()%>/templates/admin/assets/css/font-awesome.css"
	rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link
	href="<%=request.getContextPath()%>/templates/admin/assets/css/custom.css"
	rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
</head>
<%
	if (!AuthUtil.checkLogin(request, response)) {
		response.sendRedirect(request.getContextPath() + "/auth/login");
		return;
	}
	String urlContact = request.getContextPath() +"/views/admin/contact";
	String urlUser = request.getContextPath() +"/views/admin/user";
	String url = request.getContextPath() + request.getServletPath();
	if(AuthUtil.checkRole(request, response) != 2 && (url.startsWith(urlContact) || url.startsWith(urlUser))) {
		response.sendRedirect(request.getContextPath() + "/404");
		return;
	}
%>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-cls-top " role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=request.getContextPath()%>/admin">VinaEnter
					Edu</a>
			</div>
			<div
				style="color: white; padding: 15px 50px 5px 50px; float: right; font-size: 16px;">
				Xin chào, <b><%=session.getAttribute("username")%></b> &nbsp; <a
					href="<%=request.getContextPath()%>/auth/logout"
					class="btn btn-danger square-btn-adjust">Đăng xuất</a>
			</div>
		</nav>
		<!-- /. NAV TOP  -->