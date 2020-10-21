<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa người dùng</h2>
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
                %>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" action="<%=request.getContextPath() %>/admin/user/edit" method="post" id="form">
                                    <div class="form-group">
                                        <label for="id">ID</label>
                                        <input type="text" id="id" value="<%=user.getId() %>" name="id" class="form-control" readonly required/>
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Tên người dùng</label>
                                        <input type="text" id="name" value="<%=user.getFullname() %>" name="name" class="form-control" required/>
                                    </div>
                                    <div class="form-group">
										<label for="role">Vai trò</label> 
									<select id="role" name="role" class="form-control">
										<%
											if(request.getAttribute("role").equals("admin")) {
										%>
										<option value="admin" selected>admin</option>
										<option value="editor">editor</option>
										<%
											} else {
										%>
										<option value="admin">admin</option>
										<option value="editor" selected>editor</option>
										<%
											}
										%>
									</select>
									</div>
                                    <div class="form-group">
                                        <label for="username">Tên đăng nhập</label>
                                        <input type="text" id="username" value="<%=user.getUsername() %>" name="username" class="form-control" readonly required/>
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Mật khẩu</label>
                                        <input type="password" id="password" value="********" name="password" class="form-control" readonly/>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                    <a href="<%=request.getContextPath() %>/admin/user/changepass?id=<%=user.getId() %>" title="" class="btn btn-danger">Đổi mật khẩu</a>
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