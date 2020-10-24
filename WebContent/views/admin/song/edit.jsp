<%@page import="models.Category"%>
<%@page import="java.util.List"%>
<%@page import="models.Song"%>
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
                	Song song = (Song)request.getAttribute("song");
                %>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" action="<%=request.getContextPath() %>/admin/song/edit" enctype="multipart/form-data" method="post" id="form">
                                    <div class="form-group">
                                        <label for="id">ID</label>
                                        <input type="text" id="id" value="<%=song.getId() %>" name="id" class="form-control" readonly required/>
                                    </div>
                                    <div class="form-group">
										<label for="name">Tên bài hát</label> <input type="text"
											id="name" value="<%=song.getName() %>" name="name" class="form-control" required/>
									</div>
                                    <div class="form-group">
										<label for="category">Danh mục bài hát</label> <select
											id="category" name="category" class="form-control">
									<%
										if(request.getAttribute("listCat") != null) {
											List<Category> listCat = (List<Category>)request.getAttribute("listCat");
											for(Category cat : listCat) {
												if(cat.getId() == song.getCat().getId()) {
									%>
											<option value="<%=cat.getId() %>" selected><%=cat.getName() %></option>
									<%
												} else {
									%>
											<option value="<%=cat.getId() %>"><%=cat.getName() %></option>
									<%
												}
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
											name="preview" required><%=song.getPreview_text() %></textarea>
									</div>
									<div class="form-group">
										<label for="detail">Chi tiết</label>
										<textarea id="detail" class="form-control" id="detail"
											rows="5" name="detail" required><%=song.getDetail_text() %></textarea>
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
    document.getElementById("song").classList.add('active-menu');
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
			preview : {
				required : true,
			},
			detail : {
				required : true,
			}
		},
		messages: {
			name : {
				required : " (Vui lòng nhập tên bài hát)",
			},
			preview : {
				required : " (Vui lòng nhập giới thiệu bài hát)",
			},
			detail : {
				required : " (Vui lòng nhập mô tả bài hát)",
			}
		}
	})
});
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>