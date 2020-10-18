<%@page import="models.Category"%>
<%@page import="java.util.List"%>
<%@page import="models.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Thêm bài hát</h2>
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
					Song song = (Song) request.getAttribute("song");
					String name = "";
					String preview_text = "";
					String detail_text = "";
					if (song != null) {
						name = song.getName();
						preview_text = song.getPreview_text();
						detail_text = song.getDetail_text();
					}
				%>
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<form role="form" action="<%=request.getContextPath() %>/admin/song/add" method="post" enctype="multipart/form-data"
									id="form">
									<div class="form-group">
										<label for="name">Tên bài hát</label> <input type="text"
											id="name" value="<%=name %>" name="name" class="form-control" required/>
									</div>
									<div class="form-group">
										<label for="category">Danh mục bài hát</label> 
										<select id="category" name="category" class="form-control">
									<%
										if(request.getAttribute("listCat") != null) {
											List<Category> listCat = (List<Category>)request.getAttribute("listCat");
											for(Category cat : listCat) {
									%>
											<option value="<%=cat.getId() %>"><%=cat.getName() %></option>
									<%
											}
										}
									%>
										</select>
									</div>
									<div class="form-group">
										<label for="picture">Hình ảnh</label> <input type="file"
											name="picture" />
									</div>
									<div class="form-group">
										<label for="preview">Mô tả</label>
										<textarea id="preview" class="form-control" rows="3"
											name="preview" required><%=preview_text %></textarea>
									</div>
									<div class="form-group">
										<label for="detail">Chi tiết</label>
										<textarea id="detail" class="form-control" id="detail"
											rows="5" name="detail" required><%=detail_text %></textarea>
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
	document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>