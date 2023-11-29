<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="com.fredck.FCKeditor.*" %> 
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK" %> 
<jsp:useBean id="sn" scope="page" class="com.bean.SystemBean" /> 
<jsp:useBean id="cb" scope="page" class="com.bean.ComBean" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dir=sn.getDir();
%>
<HTML><HEAD><TITLE>后台操作区</TITLE>
<LINK href="<%=basePath %><%=dir %>/images/Admin_Style.css" type=text/css rel=stylesheet>
<LINK href="<%=basePath %><%=dir %>/images/style.css" type=text/css rel=stylesheet>
<SCRIPT language=JavaScript src="<%=basePath %>admin/images/calendar.js"></SCRIPT>
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
</STYLE>
</HEAD>
<script type="text/javascript">
function sub()
{
	if(document.form1.ys.value.replace(/\s+$|^\s+/g,"").length<=0||document.form1.ks.value.replace(/\s+$|^\s+/g,"").length<=0||document.form1.sj.value.replace(/\s+$|^\s+/g,"").length<=0 )
	{
		alert("所有项目必须填写！");
		return false;
	}
	 
	 
	form1.submit();
}
</script>
<%
String message = (String)request.getAttribute("message");
	if(message == null){
		message = "";
	}
	if (!message.trim().equals("")){
		out.println("<script language='javascript'>");
		out.println("alert('"+message+"');");
		out.println("</script>");
	}
	request.removeAttribute("message");
%>
<%
	String username=(String)session.getAttribute("user");
	if(username==null){
		response.sendRedirect(path+"/error.jsp");
	}
	else{
		String method=request.getParameter("method");
		 
		String ys = "";	 
		String ks=""; 
		String sj="";String xx="";
		 String id="";
		if(method.equals("uppb")){
			id=request.getParameter("id").trim();
			List newsList=cb.get1Com("select * from pb where id='"+id+"'",5);
			ys=newsList.get(1).toString(); 
			ks=newsList.get(2).toString();sj=newsList.get(3).toString();xx=newsList.get(4).toString();
		}		

%>
<BODY >
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top ><form name="form1" action="<%=basePath %>ComServlet"   >
<table width='100%' cellspacing='1' cellpadding='3' bgcolor='#CCCCCC' class="tablewidth">
  <tr bgcolor='#FFFFFF'> <input type="hidden" name="method" value="<%=method %>"> <input type="hidden" name="id" value="<%=id %>">
    <td width='40%'><div align="right">排班专家：</div></td>
    <td ><select name="ys">
    <%if(method.equals("uppb")){ %><option value=<%=ys %>><%=ys %></option><%} %>
    <%List flsist=cb.getCom("select * from ys order by id desc",2);if(!flsist.isEmpty()){for(int i=0;i<flsist.size();i++){List list2=(List)flsist.get(i);%>
    <option value=<%=list2.get(1).toString() %>><%=list2.get(1).toString() %></option>
    <%}} %>
    </select></td>
  </tr> 
  <tr bgcolor='#FFFFFF'>  
    <td width='40%'><div align="right">排班科室：</div></td>
    <td ><select name="ks">
    <%if(method.equals("uppb")){ %><option value=<%=ks %>><%=ks %></option><%} %>
    <%List flist=cb.getCom("select * from fenlei order by id desc",2);if(!flist.isEmpty()){for(int i=0;i<flist.size();i++){List list2=(List)flist.get(i);%>
    <option value=<%=list2.get(1).toString() %>><%=list2.get(1).toString() %></option>
    <%}} %>
    </select></td>
  </tr> 
  <tr bgcolor='#FFFFFF'> 
    <td width='40%'><div align="right">排班日期：</div></td>
    <td ><input name="sj" type="text" size="40" maxlength="100" value="<%=sj %>" readonly onclick="SelectDate(this,'yyyy-MM-dd')"  this.Txt_Date.Attributes["onclick"] = "SelectDate(this,'yyyy-MM-dd')";></td>
  </tr> 
  <tr bgcolor='#FFFFFF'> 
    <td width='40%'><div align="right">排班时间：</div></td>
    <td ><select name=xx>
      <%if(method.equals("uppb")){ %><option value="<%=xx%>"><%=xx%></option> <%} %> 
        <option value="上午班">上午班</option>
        <option value="下午班">下午班</option>
        <option value="晚班">晚班</option> 
        </select></td>
  </tr> 
  <tr bgcolor='#FFFFFF'> 
      <td colspan="2" align="center"> 
        <input class=mmcinb type='button' name='button' value='提交数据' onclick='sub()'>
      </td>
    </tr>
	
</table></form>
   </TD>
	</TR>
  </TBODY>
</TABLE>
</BODY>
<%} %>
</HTML>
