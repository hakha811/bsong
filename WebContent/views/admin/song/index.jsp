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
                                %>
                                    <tr>
                                        <td><%=song.getId() %></td>
                                        <td class="center"><%=song.getName() %></td>
                                        <td class="center"><%=song.getCat().getName() %></td>
                                        <td class="center cenimg">
                                        <%
                                        	if(!"".equals(song.getPicture())) {
                                        %>
											<img src="<%=request.getContextPath() %>/templates/images/<%=song.getPicture() %>" width="300" height="200" alt="<%=song.getName() %>"/>
                                        <%
                                        	} else {
                                        %>
                                        	<img src="<%=request.getContextPath() %>/templates/images/songdefault.jpg" width="300" height="200" alt="Không có hình ảnh"/>
                                        <%
                                        	}
                                        %>
                                        </td>
                                        <td class="center"><%=song.getCounter() %></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/song/edit?id=<%=song.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath() %>/admin/song/del?id=<%=song.getId() %>" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                               	<%
                                		}
                                	}
                               	%>		
                                </tbody>
                            </table>
                            <div class="row">
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