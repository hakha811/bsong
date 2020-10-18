<%@page import="models.Contact"%>
<%@page import="models.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm liên hệ</h2>
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
					out.print("Trùng email");
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
					Contact contact = (Contact) request.getAttribute("contact");
					String name = "";
					String email = "";
					String website = "";
					String message = "";
					if (contact != null) {
						name = contact.getName();
						email = contact.getEmail();
						website = contact.getWebsite();
						message = contact.getMessage();
					}
				%>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" method="post" id="form">
                                    <div class="form-group">
                                        <label for="name">Tên liên hệ</label>
                                        <input type="text" id="name" value="<%=name %>" name="name" class="form-control" required/>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email</label>
                                        <input type="text" id="email" value="<%=email %>" name="email" class="form-control" pattern="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$" required/>
                                    </div>
                                    <div class="form-group">
                                        <label for="website">Website</label>
                                        <input type="text" id="website" value="<%=website %>" name="website" class="form-control" required/>
                                    </div>
                                    <div class="form-group">
										<label for="message">Message</label>
										<textarea id="message" class="form-control" rows="3"
											name="message" required><%=message %></textarea>
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
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>