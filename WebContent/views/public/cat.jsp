<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
   <%
    	Category cat = (Category)request.getAttribute("cat");
   %>
    <div class="article">
		<h1><%=cat.getName() %></h1>
    </div>
    <%
	  	int numOfPages = (int)request.getAttribute("numOfPages");
	  	int currentPage = (int)request.getAttribute("currentPage");
	  	int numSong = (currentPage-1)*4;
	  	List<Song> listSong = (List<Song>)request.getAttribute("listSong");
	  	if(listSong != null && listSong.size() > 0) {
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
		  	} else {
	  %>
	  <div class="article">
	  	Chưa có bài hát nào
	  </div>
	  <%
		  	}
	  %>
    <%
    	
    	if(listSong != null && listSong.size() > 0) {
    %>
    	<p class="pages"><small>Trang <%=currentPage %> của <%=numOfPages %></small>
    <%
			for (int i = 1; i <= numOfPages; i++) {
				if(i == currentPage) {
	%>
				<span><%=i%></span>
	<%
				} else {
	%>
				<a href="<%=request.getContextPath()%>?<%=cat.getId() %>page=<%=i%>"><%=i%></a>
	<%
				}
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