<%@page import="utils.StringUtil"%>
<%@page import="daos.SongDAO"%>
<%@page import="daos.CategoryDAO"%>
<%@page import="models.Song"%>
<%@page import="java.util.List"%>
<%@page import="models.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
  <form id="formsearch" name="formsearch" method="post" action="<%=request.getContextPath()%>/search">
    <span>
    <input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80" placeholder="Tìm kiếm bài hát" type="text" />
    </span>
    <input name="button_search" src="<%=request.getContextPath()%>/templates/public/images/search.jpg" class="button_search" type="image" />
  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="sb_menu">
    <%
	    CategoryDAO catDAO = new CategoryDAO();
		List<Category> listCat = catDAO.getAll();
	  	if(listCat != null) {
	  		for(Category item : listCat) {
	  			String urlSlug = request.getContextPath()+"/danh-muc/"+StringUtil.makeSlug(item.getName())+"-"+item.getId()+".html";
	%>
    		<li><a href="<%=urlSlug %>"><%=item.getName() %></a></li>
	<%
	  		}
	  	}
	%>
  </ul>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
  	<%
	  	SongDAO songDAO = new SongDAO();
		List<Song> listNewSong = songDAO.getItems(6);
	  	if(listNewSong != null) {
	  		for(Song song : listNewSong) {
	  			String urlSlug = request.getContextPath()+"/chi-tiet/"+StringUtil.makeSlug(song.getName())+"-"+song.getId()+".html";
	%>
	    <li><a href="<%=urlSlug %>"><%=song.getName() %></a><br />
	    <%
	   		String preview = song.getPreview_text();
	    	int length = preview.length();
	    	if(length > 30)
	    		length = 30;
	    	out.print(preview.substring(0, length)+"...");
	    %>
	    </li>
    <%
	  		}
	  	}
	%>
  </ul>
</div>