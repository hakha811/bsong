<%@page import="models.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>AdminCP | VinaEnter Edu</title>
<!-- BOOTSTRAP STYLES-->
<link href="<%=request.getContextPath()%>/templates/admin/assets/css/bootstrap.css"
	rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="<%=request.getContextPath()%>/templates/admin/assets/css/font-awesome.css"
	rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="<%=request.getContextPath()%>/templates/admin/assets/css/custom.css"
	rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<!-- JQUERY -->
<script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery-3.2.1.js"></script>
<!-- JQUERY VALIDATION -->
<script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.validate.min.js"></script>
</head>
<body>
	<div>
		<div id="lg-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12 title">
						<h2>Đăng ký</h2>
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
						 case 0:
						 	out.print("Có lỗi xảy ra!");
						 	break;
						 case 1:
						 	out.print("Mật khẩu không trùng!");
						 	break;
						 case 2:
						 	out.print("Tên đăng nhập đã tồn tại!");
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
											action="<%=request.getContextPath()%>/auth/register"
											method="post" id="form">
											<div class="form-group">
												<label for="name">Tên người dùng</label> <input type="text"
													id="name" name="name" class="form-control" />
											</div>
											<div class="form-group">
												<label for="username">Tên đăng nhập</label> <input
													type="text" id="username" name="username"
													class="form-control" />
											</div>
											<div class="form-group">
												<label for="password">Mật khẩu</label> <input
													type="password" id="password" name="password"
													class="form-control" />
											</div>
											<div class="form-group">
												<label for="repassword">Nhập lại mật khẩu</label> <input
													type="password" id="repassword" name="repassword"
													class="form-control" />
											</div>
											<button type="submit" name="submit"
												class="btn btn-success btn-md">Đăng ký</button>
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
	<script type="text/javascript">
$(document).ready(function() {
	$("#form").validate({
		errorPlacement: function(error, element) {
			$(element).closest("form").find('label[for="' + $(element).attr('id') + '"]').append(error);
		},
		errorElement: "span",
		rules : {
			name : {
				required : true,
			},
			username : {
				required : true,
			},
			password : {
				required : true,
				minlength: 8
			},
			repassword : {
				equalTo : "#password",
				minlength: 8
			}
		},
		messages: {
			name : {
				required : " (Vui lòng nhập họ tên)",
			},
			username : {
				required : " (Vui lòng nhập tên người dùng)",
			},
			password : {
				required : " (Vui lòng nhập mật khẩu)",
				minlength: " (Hãy nhập ít nhất 8 ký tự)"
			},
			repassword : {
				equalTo: " (Mật khẩu không khớp)",
				minlength: " (Hãy nhập ít nhất 8 ký tự)"
			}
		}
	})
});
</script>
</body>