<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
      <h2><span>Liên hệ</span></h2>
      <div class="clr"></div>
      <p>Khi bạn có thắc mắc, vui lòng gửi liên hệ, chúng tôi sẽ phản hồi trong thời gian sớm nhất.</p>
    </div>
    <div class="article">
      <h2>Gửi liên hệ đến chúng tôi</h2>
      <div class="clr"></div>
      <%
	      	String name = request.getAttribute("name") == null ? "" : (String)request.getAttribute("name");
			String email = request.getAttribute("email") == null ? "" : (String)request.getAttribute("email");
			String website = request.getAttribute("website") == null ? "" : (String)request.getAttribute("website");
			String message = request.getAttribute("message") == null ? "" : (String)request.getAttribute("message");
      %>
      <form action="<%=request.getContextPath() %>/contact" method="post" id="sendemail">
        <ol>
          <li>
            <label for="name">Họ tên (required)</label>
            <input id="name" value="<%=name %>" name="name" class="text" required/>
          </li>
          <li>
            <label for="email">Email (required)</label>
            <input id="email" value="<%=email %>" name="email" class="text" pattern="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$" required/>
          </li>
          <li>
            <label for="website">Website</label>
            <input id="website" value="<%=website %>" name="website" class="text" />
          </li>
          <li>
            <label for="message">Nội dung</label>
            <textarea id="message" name="message" rows="8" cols="50" required><%=message %></textarea>
          </li>
          <li>
            <input type="image" name="imageField" id="imageField" src="<%=request.getContextPath() %>/templates/public/images/submit.gif" class="send" />
            <div class="clr"></div>
            <%
			    if(request.getParameter("msg")!=null){
					int msg = Integer.parseInt(request.getParameter("msg"));
					if(msg == 0) {
		    %>
            <h3 style="color:red">Thêm contact thành công</h3>
		    <%
			    	} else if(msg == 1) {
		    %>
            <h3 style="color:red">Thêm contact thất bại</h3>
		    <%
		    		}
			    }
		    %>
          </li>
        </ol>
      </form>
    </div>
  </div>
  <div class="sidebar">
  <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>
