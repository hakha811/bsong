<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm người dùng</h2>
            </div>
        </div>
        <%
	        if(request.getParameter("msg")!=null){
				int msg = Integer.parseInt(request.getParameter("msg"));
        %>
        <div class="alert alert-danger">
		  	<strong>
		  	<% 
				switch(msg){
				case 0: 
					out.print("Tên tài khoản đã tồn tại!!!");
					break;
				case 1: 
					out.print("Có lỗi xảy ra!!!");
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
                <%
                	User user = (User)request.getAttribute("user");
                	String name = "";
                	String username = "";
                	if(user != null) {
                		name = user.getFullname();
                		username = user.getUsername();
                	}
                %>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" action="<%=request.getContextPath() %>/admin/user/add" method="post" id="form">
                                    <div class="form-group">
                                        <label for="name">Tên người dùng</label>
                                        <input type="text" id="name" value="<%=name %>" name="name" class="form-control"/>
                                    </div>
                                    <div class="form-group">
										<label for="role">Vai trò</label> 
									<select id="role" name="role" class="form-control">
										<option value="admin">admin</option>
										<option value="editor">editor</option>
									</select>
									</div>
                                    <div class="form-group">
                                        <label for="username">Tên đăng nhập</label>
                                        <input type="text" id="username" value="<%=username %>" name="username" class="form-control"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Mật khẩu</label>
                                        <input type="password" id="password" value="" name="password" class="form-control"/>
                                    </div>
                                   <div class="form-group">
                                        <label for="repassword">Nhập lại mật khẩu</label>
                                        <input type="password" id="repassword" value="" name="repassword" class="form-control"/>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
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
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
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
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>