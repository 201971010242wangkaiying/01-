<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.jdbc.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  
  <body> 
    <%
    // 连接数据库并读取数据
    PreparedStatement pst=null;
    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbkp01", "root", "root");
    Statement st=conn.createStatement();
    String querySql="select * from table_kp01";
    ResultSet rs=st.executeQuery(querySql);
    
     %>
    <!-- //构造显示数据的表格，放到一个form中 -->
    
    <form action="dbServlet" method="post">
      <table>   <!--  表开始 -->
      <!--  表头 -->
    <tr>
      <th width="100"> </th> <th width="100"> 序号</th>  <th width="100"> 名称</th> <th width="100"> 价格</th> <th width="100">描述</th> <th width="100">操作</th>
    </tr> 
      <!--  查询数据库，将查询结果填写到表格中 --> 
     <%
     while(rs.next()){
     int id=rs.getInt("id");
     String name=rs.getString("name");
     int price=rs.getInt("price");
     String description=rs.getString("description");
     out.println("<tr>");// 程序生成行开始标签
     out.println("<td width='100'> <input type=checkbox  name=id value="+id+"></td>");// 复选框列
     out.println("<td width='100'>"+id+"</td>");// 商品序号列
     out.println("<td width='100'>"+name+"</td>");
     out.println("<td width='100'>"+price+"</td>");
     out.println("<td width='100'>"+description+"</td>");
     
     out.println("<td>");//最后一列，显示“删除”、“修改”超链接
    		//<a href="../operateServlet?sid=100&action=delete" onclick="return confirm(\"确定删除此条记录?\")"">>
    		
    		out.println("<a href='dbServlet?id="+id+"&action=delete"+"'"
    		+"onclick='return confirm(\"确定删除此条记录?\")'> 删除</a>");
    		
    		out.println("<a href='dbServlet?action=edit&id="+id+"'"
    		+"> 修改</a>");// 如果是修改，跳转到operateServlet，目标页面根据id查找相关行的数据
    		
    		out.println("</td>");
     
     // 删除和修改先不写
       
     }
     
      %> 
      </table>   
      
      

    
    
    
    
    
    </form>
    
    
    
  </body>
</html>
