<%@page import="java.util.List"%>
<%@page import="models.Song"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý bài hát</h2>
            </div>
        </div>
        <%
	        if(request.getParameter("msg")!=null){
				int msg = Integer.parseInt(request.getParameter("msg"));
        %>
        <div class="alert alert-success">
		  	<strong>
		  	<% 
				switch(msg){
				case 0: 
					out.print("Có lỗi xảy ra!");
					break;
				case 1: 
					out.print("Thêm thành công");
					break;
				case 2: 
					out.print("Sửa thành công");
					break;
				case 3: 
					out.print("Xóa thành công");
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
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath() %>/admin/song/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath() %>/admin/song/search">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="name" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên bài hát</th>
                                        <th>Tên danh mục</th>
                                        <th>Hình ảnh</th>
                                        <th>Lượt xem</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	if(request.getAttribute("listSong") != null) {
                                		List<Song> listSong = (List<Song>)request.getAttribute("listSong");
                                		for(Song song : listSong) {
                                			String picture = "".equals(song.getPicture())?"songdefault.jpg":song.getPicture();
                                %>
                                    <tr>
                                        <td><%=song.getId() %></td>
                                        <td class="center"><%=song.getName() %></td>
                                        <td class="center"><%=song.getCat().getName() %></td>
                                        <td class="center cenimg">
										<img src="<%=request.getContextPath() %>/templates/images/<%=picture %>" width="300" height="200" alt="<%=song.getName() %>"/>
                                        </td>
                                        <td class="center"><%=song.getCounter() %></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/song/edit?id=<%=song.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath() %>/admin/song/del?id=<%=song.getId() %>" onclick="return confirm('Bạn có muốn xóa?')" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                               	<%
                                		}
                                	}
                               	%>		
                                </tbody>
                            </table>
                            <div class="row">
                           	<%
                           		if(request.getAttribute("listSong") != null && request.getAttribute("search") == null) {
                           			int currentPage = (int)request.getAttribute("currentPage");
                           			int numOfPages = (int)request.getAttribute("numOfPages");
                           	%>
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Trang <%=currentPage %> trên <%=numOfPages %></div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                        <%
                                        	if(currentPage > 1) {
                                        %>
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath() %>/admin/song?page=<%=currentPage-1 %>">Trang trước</a></li>
                                       	<%
                                        	}
                                        	if(numOfPages-currentPage <= 2) {
                                        		if(numOfPages <= 3) {
		                                        	for(int i = 1; i <= numOfPages; i++) {
		                                        		if(currentPage==i) {
                                       	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/song?page=<%=i %>"><%=i %></a></li>
										<%
	                                        			} else {
										%>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/song?page=<%=i %>"><%=i %></a></li>
										<%
		                                        		}
		                                        	}
	                                        	} else {
	                                        		for(int i = currentPage; i <= numOfPages; i++) {
		                                        		if(currentPage==i) {
                                       	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/song?page=<%=currentPage %>"><%=currentPage %></a></li>
										<%
	                                        			} else {
										%>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/song?page=<%=i %>"><%=i %></a></li>
										<%
		                                        		}
	                                        		}
	                                        	}
                                        	} else {
										%>
											<li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/song?page=<%=currentPage %>"><%=currentPage %></a></li>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/song?page=<%=currentPage+1 %>"><%=currentPage+1 %></a></li>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath() %>/admin/song?page=<%=currentPage+2 %>"><%=currentPage+2 %></a></li>
										<%
                                        	}
											if(currentPage < numOfPages) {
										%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath() %>/admin/song?page=<%=currentPage+1 %>">Trang tiếp</a></li>
                                        <%
                                        	}
                           		}
                                        %>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>