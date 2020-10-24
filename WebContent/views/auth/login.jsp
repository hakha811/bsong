<%@page import="models.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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
<body>
	<div>
		<div id="lg-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12 title">
						<h2>Đăng nhập</h2>
					</div>
				</div>
				<%
					if (request.getParameter("msg") != null) {
					int msg = Integer.parseInt(request.getParameter("msg"));
				%>
				<div class="alert alert-danger">
					<strong> 
				<%
				 	switch (msg) {
				 	case 0 :
				 		out.print("Sai tên tài khoản hoặc mật khẩu");
				 		break;
				 	}
				%>
					</strong>
				</div>
				<%
					}
				%>
				<!-- /. ROW  -->
				<hr />
				<div class="row">
					<div class="col-md-12">
						<!-- Form Elements -->
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
										<form role="form"
											action="<%=request.getContextPath()%>/auth/login"
											method="post" id="form">
											<div class="form-group">
												<label for="username">Tên đăng nhập</label> <input
													type="text" id="username" name="username"
													class="form-control" required />
											</div>
											<div class="form-group">
												<label for="password">Mật khẩu</label> <input
													type="password" id="password" name="password"
													class="form-control" required />
											</div>
											<div class="form-group">
												<a href="<%=request.getContextPath() %>/auth/register">Register</a>
											</div>
											<button type="submit" name="submit"
												class="btn btn-success btn-md">Đăng nhập</button>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- End Form Elements -->
					</div>
				</div>
				<!-- /. ROW  -->
			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
</body>