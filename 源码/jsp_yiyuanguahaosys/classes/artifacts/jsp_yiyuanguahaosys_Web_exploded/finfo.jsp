<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" %>
<jsp:directive.page import="com.util.Common"/>
<%@ include file="iframe/head.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<LINK href="images/default.css" type=text/css rel=stylesheet>
<LINK href="images/css.css" type=text/css rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
 
<META content="MSHTML 6.00.2900.3268" name=GENERATOR>
</HEAD>
<BODY text=#000000 bgColor=#ffffff leftMargin=0 topMargin=0>
 
     <TABLE class=dragTable cellSpacing=0 cellPadding=0 width="100%" border=0>
		<TBODY>
        <TR>
          <TD class=head colspan="2">
			<SPAN class=TAG>科室分类</SPAN> 
			
		  </TD>
		</TR>
		<TR>
		<TD  class=middle align="left" >
               <TABLE class=xsnr id=tb1_nr1  cellSpacing=0 cellPadding=0 width="100%" border=0>
                    <TBODY>
                    <%
                    String type=request.getParameter("id");
                    type=Common.toChineseAndTrim(type);
                    List hotlist=cb.getCom("select * from ys where ks='"+type+"' order by id desc ",10);
                    if(!hotlist.isEmpty()){
					for(int alln1=0;alln1<hotlist.size();alln1++){
						List allnew=(List)hotlist.get(alln1);
			%>
          <TR>
          <TD width=20% align=left>
		  <a href="<%=basePath %>ysinfo.jsp?id=<%=allnew.get(0).toString()%>"><IMG height=150 src="<%=basePath+allnew.get(3).toString()%>" width=120 onload=makesmallpic(this,150,120); border=0></A>
		   </TD>
		  <TD align=left>
		  医生工号：<%=allnew.get(9).toString()%> 医生姓名：<%=allnew.get(1).toString()%> <br>
		  科室类别：<%=allnew.get(2).toString()%>  &nbsp;&nbsp;&nbsp;挂号费用：<%=allnew.get(4).toString()%><br>
		  医生职称：<%=allnew.get(5).toString()%>  <br> 
		  医生年龄：<%=allnew.get(6).toString()%> <br>
		  上班时间：<%=allnew.get(8).toString()%>   
		  &nbsp;&nbsp;<a href="<%=basePath %>ysinfo.jsp?id=<%=allnew.get(0).toString()%>">点击查看详细...</a>
		  </TD>
		</TR>
		<%}} %>
				</TBODY>
			</TABLE>			
			</TD>
		</TR>
        </TBODY>
	  </TABLE>

 

<%@ include file="iframe/foot.jsp"%>