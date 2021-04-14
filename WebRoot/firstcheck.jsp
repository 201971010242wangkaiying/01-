<%@page import="com.mysql.jdbc.Driver"%>
<%@page import="java.sql.*"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%!

public class Answer {
private String tempcount;

public String getTempcount() {
	return tempcount;
}
public void setTempcount(String tempcount) {
	this.tempcount = tempcount;
}
public Answer(String tempcount) {
	this.tempcount = tempcount;
}
}
private static Map<String,Answer> db=new HashMap<String,Answer>();
public boolean check(String tempcount){
	if(db.containsKey(tempcount)){
		return false;
	}
	else{
		return true;
	    }
 }
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
   <%
   response.setCharacterEncoding("UTF-8");
   response.setContentType("text/html");
   String tempcount=new String (request.getParameter("tempcount").getBytes("ISO-8859-1"),"UTF-8");
   @SuppressWarnings("unchecked")
   Answer answer=(Answer)application.getAttribute("answer");
   if(tempcount!=null&& !tempcount.trim().equals("")){
   %>
       <jsp:forward page="/success.jsp"></jsp:forward>
   <% 
   }
   else
   {	
       response.sendRedirect("fail.jsp"); 
       return;  
   }
  %>
  </body>
</html>
