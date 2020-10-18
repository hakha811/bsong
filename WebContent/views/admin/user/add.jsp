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
				case 1: 
					out.print("Có lỗi khi thêm");
					break;
				case 2: 
					out.print("Trùng tên người dùng");
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
                                        <input type="text" id="name" value="<%=name %>" name="name" class="form-control" required/>
                                    </div>
                                    <div class="form-group">
                                        <label for="username">Tên đăng nhập</label>
                                        <input type="text" id="username" value="<%=username %>" name="username" class="form-control" required/>
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Mật khẩu</label>
                                        <input type="password" id="password" value="" name="password" class="form-control" required/>
                                    </div>
                                   <div class="form-group">
                                        <label for="repassword">Nhập lại mật khẩu</label>
                                        <input type="password" id="repassword" value="" name="repassword" class="form-control" required/>
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
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>