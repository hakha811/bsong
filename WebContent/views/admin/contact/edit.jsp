<%@page import="models.Contact"%>
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
	        if(request.getAttribute("msg")!=null){
				int msg = (int)request.getAttribute("msg");
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
                	Contact contact = (Contact)request.getAttribute("contact");
                %>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" action="<%=request.getContextPath() %>/admin/contact/edit" method="post" id="form">
                                    <div class="form-group">
                                        <label for="id">Mã liên hệ</label>
                                        <input type="text" id="id" value="<%=contact.getId() %>" name="id" class="form-control" readonly required/>
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Tên liên hệ</label>
                                        <input type="text" id="name" value="<%=contact.getName() %>" name="name" class="form-control" required/>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email</label>
                                        <input type="text" id="email" value="<%=contact.getEmail() %>" name="email" class="form-control" pattern="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$" required/>
                                    </div>
                                    <div class="form-group">
                                        <label for="website">Website</label>
                                        <input type="text" id="website" value="<%=contact.getWebsite() %>" name="website" class="form-control" required/>
                                    </div>
                                    <div class="form-group">
										<label for="message">Message</label>
										<textarea id="message" class="form-control" rows="3"
											name="message" required><%=contact.getMessage() %></textarea>
									</div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
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
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>