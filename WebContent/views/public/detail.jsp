<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
  <%
  		if(request.getAttribute("cat") != null && request.getAttribute("song") != null) {
  			Category cat = (Category)request.getAttribute("cat");
  			Song song = (Song)request.getAttribute("song");
  %>
    <div class="article">
      <h1><%=song.getName() %></h1>
      <div class="clr"></div>
      <p>Ngày đăng: <%=song.getDate_create() %> Lượt xem: <%=song.getCounter() %></p>
      <div class="vnecontent">
         <%=song.getDetail_text() %>
      </div>
    </div>
    <%
  		}
  		if(request.getAttribute("listSongRelated") != null) {
  	%>
      <h2>Bài viết liên quan</h2>
    <%
  			List<Song> listSongRelated = (List<Song>)request.getAttribute("listSongRelated");
  			for(Song item : listSongRelated) {
  				String urlSlug = request.getContextPath()+"/chi-tiet/"+StringUtil.makeSlug(item.getName())+"-"+item.getId()+".html";
  				String picture = "".equals(item.getPicture())?"songdefault.jpg":item.getPicture();
    %>
    <div class="article">
      <div class="clr"></div>
      <div class="comment"> <a href="">
		<img src="<%=request.getContextPath() %>/templates/images/<%=picture %>" width="40" height="40" class="userpic" alt="<%=item.getName() %>"/>
      </a>
        <h2><a href="<%=urlSlug %>"><%=item.getName() %></a></h2>
        <p><%=item.getPreview_text() %></p>
      </div>
    </div>
    <%
  			}
  		}
    %>
  </div>
  <div class="sidebar">
  <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>
