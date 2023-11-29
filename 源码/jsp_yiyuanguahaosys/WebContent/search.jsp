<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" %>
<%@ include file="iframe/head.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<LINK href="images/default.css" type=text/css rel=stylesheet>
<LINK href="images/css.css" type=text/css rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
 
<META content="MSHTML 6.00.2900.3268" name=GENERATOR>
</HEAD>
<script type="text/javascript">
 
function f111()
{
 
 if(document.form1.xm.value=="")
 {
 	alert("请输入查询内容，可以为关键字！");
 	document.form1.xm.focus();
 	return false;
 }
 
 form1.submit();
}
</script>
<BODY text=#000000 bgColor=#ffffff leftMargin=0 topMargin=0>
 
     <TABLE class=dragTable cellSpacing=0 cellPadding=0 width="100%" border=0>
		<TBODY>
        <TR>
          <TD class=head >
			<SPAN class=TAG>医生查询</SPAN> 
			
		  </TD>
		</TR>
		<TR>
		<TD  class=middle align="center" >
		<form action="<%=basePath %>searchinfo.jsp" name="form1" >
         <TABLE   cellSpacing=0 cellPadding=0 width="100%" border=0>
         <TBODY>
                    
        <TR>
          <TD width=350 align=right>科室类别：</TD><TD align=left><select name="ks">
    <%List flist=cb.getCom("select * from fenlei order by id desc",2);if(!flist.isEmpty()){for(int i=0;i<flist.size();i++){List list2=(List)flist.get(i);%>
    <option value=<%=list2.get(1).toString() %>><%=list2.get(1).toString() %></option>
    <%}} %>
    </select></TD>
		</TR>
		<TR>
          <TD  align=right>医生姓名关键字：</TD><TD align=left><input type=text name=xm size=30 ></TD>
		</TR> 
		<TR>
          <TD  align=center colspan=2><input type=button onclick="f111()" value="查询信息"></TD>
		</TR>
				</TBODY>
			</TABLE>
			</form>			
			</TD>
		</TR>
        </TBODY>
	  </TABLE>
 
 

<%@ include file="iframe/foot.jsp"%>