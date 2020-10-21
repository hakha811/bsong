<%@page import="java.util.List"%>
<%@page import="models.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
  <%
  	int numSong = 0;
  	if(request.getAttribute("listSong") != null) {
  		List<Song> listSong = (List<Song>)request.getAttribute("listSong");
  		for(Song song : listSong) {
  			String urlSlug = request.getContextPath()+"/chi-tiet/"+StringUtil.makeSlug(song.getName())+"-"+song.getId()+".html";
			String picture = "".equals(song.getPicture())?"songdefault.jpg":song.getPicture();
  %>
  	<div class="article">
      <h2><a href="<%=urlSlug %>" title="<%=song.getName() %>"><%=song.getName() %></a></h2>
      <p class="infopost">Ngày đăng: <%=song.getDate_create() %> Lượt xem: <%=song.getCounter() %> <a href="<%=request.getContextPath()%>/detail?id=<%=song.getId()%>" class="com"><span><%=++numSong %></span></a></p>
      <div class="clr"></div>
      <div class="img">
		<img src="<%=request.getContextPath() %>/templates/images/<%=picture %>" width="177" height="213" alt="<%=song.getName() %>"/>
      </div>
      <div class="post_content">
        <p><%=song.getPreview_text() %></p>
        <p class="spec"><a href="<%=urlSlug %>" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
  <%
  		}
  	}
  	if(request.getParameter("msg") != null && request.getParameter("msg").equals("success")) {
  %>
  <h3>Not Found!!!</h3>
  <%
  	}
  %>
  </div>
  <div class="sidebar">
      <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>
