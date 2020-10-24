<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Đổi mật khẩu</h2>
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
					out.print("Có lỗi xảy ra!");
					break;
				case 1: 
					out.print("Sai mật khẩu!");
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
                                <form role="form" action="<%=request.getContextPath() %>/admin/user/changepass" method="post" id="form">
                                    <div class="form-group">
                                        <label for="password">Mật khẩu cũ</label>
                                        <input type="password" id="password" value="" name="password" class="form-control" required/>
                                    </div>
                                    <div class="form-group">
                                        <label for="newpassword">Mật khẩu mới</label>
                                        <input type="password" id="newpassword" value="" name="newpassword" class="form-control" required/>
                                    </div>
                                    <div class="form-group">
                                        <label for="repassword">Nhập lại mật khẩu mới</label>
                                        <input type="password" id="repassword" value="" name="repassword" class="form-control" required/>
                                    </div>
                                    <div class="form-group hidden">
                                        <label for="id">ID</label>
                                        <input type="text" id="id" value="<%=request.getParameter("id") %>" name="id" class="form-control" readonly/>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Đổi mật khẩu</button>
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
			password : {
				required : true,
				minlength: 8
			},
			newpassword : {
				required : true,
				minlength: 8
			},
			repassword : {
				equalTo : "#newpassword",
				minlength: 8
			}
		},
		messages: {
			password : {
				required : " (Vui lòng nhập mật khẩu)",
				minlength: " (Hãy nhập ít nhất 8 ký tự)"
			},
			newpassword : {
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