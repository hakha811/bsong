<%@page import="models.Picture"%>
<%@page import="models.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Thêm hình ảnh</h2>
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
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<form role="form" action="<%=request.getContextPath() %>/admin/picture/add" method="post" enctype="multipart/form-data"
									id="form">
									<div class="form-group">
										<label for="picture">Hình ảnh</label> <input type="file"
											name="picture" />
									</div>
									<button type="submit" name="submit"
										class="btn btn-success btn-md">Thêm</button>
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
	document.getElementById("picture").classList.add('active-menu');
</script>
<script type="text/javascript">
$(document).ready(function() {
	$("#form").validate({
		errorPlacement: function(error, element) {
			$(element).closest("form").find('label[for="' + $(element).attr('id') + '"]').append(error);
		},
		errorElement: "span",
		rules : {
			picture : {
				required : true,
			}
		},
		messages: {
			picture : {
				required : " (Vui lòng thêm file hình ảnh)",
			}
		}
	})
});
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>