<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<jsp:useBean id="sn" scope="page" class="com.bean.SystemBean" /> 
<jsp:useBean id="cb" scope="page" class="com.bean.ComBean" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dir=sn.getDir();
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<HTML><HEAD><TITLE>后台操作区</TITLE>
<LINK href="<%=basePath %><%=dir %>/images/Admin_Style.css" type=text/css rel=stylesheet>
<LINK href="<%=basePath %><%=dir %>/images/style.css" type=text/css rel=stylesheet> 
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
</STYLE>
</HEAD>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
	<link rel="stylesheet" href="<%=basePath %>editor/plugins/code/prettify.css" />
	<script charset="utf-8" src="<%=basePath %>editor/kindeditor.js"></script>
	<script charset="utf-8" src="<%=basePath %>editor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=basePath %>editor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : '<%=basePath %>editor/plugins/code/prettify.css',
				uploadJson : '<%=basePath %>editor/jsp/upload_json.jsp',
				fileManagerJson : '<%=basePath %>editor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['form1'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['form1'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
<script type="text/javascript">
function sub()
{//String xm = "";	String ks=""; String pic=""; String fy=""; String zc=""; String nl=""; String content="";String sj=""; 
	if(document.form1.xm.value.replace(/\s+$|^\s+/g,"").length<=0
	||document.form1.ks.value.replace(/\s+$|^\s+/g,"").length<=0
	||document.form1.pic.value.replace(/\s+$|^\s+/g,"").length<=0
	||document.form1.fy.value.replace(/\s+$|^\s+/g,"").length<=0
	||document.form1.zc.value.replace(/\s+$|^\s+/g,"").length<=0
	||document.form1.nl.value.replace(/\s+$|^\s+/g,"").length<=0||document.form1.sj.value.replace(/\s+$|^\s+/g,"").length<=0)
	{
		alert("所有项目必须填写！");
		return false;
	}
	if(document.form1.pic.value.replace(/\s+$|^\s+/g,"").length>0)
	{
		var fileext=form1.pic.value.substring(form1.pic.value.length-4,form1.pic.value.length); 
		fileext=fileext.toLowerCase(); 
		if(!(fileext=='.jpg')&&!(fileext=='.gif')) 
		{
			alert("对不起，文件格式不对,必须为jpg或gif格式文件!"); 
			form1.pic.focus(); 
			return false; 
		} 
	}
	 
	 
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
		String xm = "";	String ks=""; String pic=""; String fy=""; String zc=""; String nl=""; String content="";String sj=""; String bh="";
		String id=""; 
		if(method.equals("upys")){
			id=request.getParameter("id") ;
			List newsList=cb.get1Com("select * from ys where id='"+id+"'",10);
			xm=newsList.get(1).toString();
			ks=newsList.get(2).toString();
			pic=newsList.get(3).toString();			
			fy=newsList.get(4).toString();
			zc=newsList.get(5).toString();
			nl=newsList.get(6).toString();
			content=newsList.get(7).toString();
			sj=newsList.get(8).toString();htmlData=content;

			bh=newsList.get(9).toString();
		}		

%>
<BODY >
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top ><form name="form1" action="<%=basePath %>News"  enctype="multipart/form-data"  onsubmit="return sub()">
<table width='100%' cellspacing='1' cellpadding='3' bgcolor='#CCCCCC' class="tablewidth">
  <tr bgcolor='#FFFFFF'> 
    <td width='30%'><div align="right">医生工号：</div></td>
    <td><input name="bh" type="text"  size="40" maxlength="100" value="<%=bh %>" > </TD>
  </tr> 
  <tr bgcolor='#FFFFFF'> <input type="hidden" name="method" value="<%=method %>"> <input type="hidden" name="id" value="<%=id %>">
    <td width='30%'><div align="right">医生姓名：</div></td>
    <td ><input name="xm" type="text" size="40" maxlength="100" value="<%=xm %>"></td>
  </tr>
  <tr bgcolor='#FFFFFF'> 
    <td width='30%'><div align="right">科室类别：</div></td>
    <td ><select name="ks">
    <%if(method.equals("upys")){ %><option value=<%=ks %>><%=ks %></option><%} %>
    <%List flist=cb.getCom("select * from fenlei order by id desc",2);if(!flist.isEmpty()){for(int i=0;i<flist.size();i++){List list2=(List)flist.get(i);%>
    <option value=<%=list2.get(1).toString() %>><%=list2.get(1).toString() %></option>
    <%}} %>
    </select> </TD>
  </tr>
  <tr bgcolor='#FFFFFF'> 
    <td width='30%'><div align="right">上传照片：</div></td>
    <td ><input name="pic" type="file"   size="40" maxlength="150" > </td>
  </tr>
  <tr bgcolor='#FFFFFF'>  
    <td width='30%'><div align="right">挂号费用：</div></td>
    <td ><input name="fy" type="text"  size="15" maxlength="100" value="<%=fy %>"  onkeyup="if(!(/^[\d]+\.?\d*$/.test(this.value))){alert('您的输入有误');this.value='';this.focus();}"> 元</td>
  </tr>
  <tr bgcolor='#FFFFFF'> 
    <td width='30%'><div align="right">医生职称：</div></td>
    <td ><input name="zc" type="text"   size="40" maxlength="100" value="<%=zc %>"></td>
  </tr>
  
  <tr bgcolor='#FFFFFF'> 
    <td width='30%'><div align="right">医生年龄：</div></td>
    <td ><input name="nl" type="text"  size="40" maxlength="100" value="<%=nl %>" > </TD>
  </tr>
  <tr bgcolor='#FFFFFF' align=center> 
  <td width='30%'><div align="right">医生介绍：</div></td>
    <td  align=left><textarea name="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea></td>
    </tr>
   
   <tr bgcolor='#FFFFFF'> 
    <td width='30%'><div align="right">上班时间：</div></td>
    <td><input name="sj" type="text"  size="40" maxlength="100" value="<%=sj %>" > </TD>
  </tr> 
  <tr bgcolor='#FFFFFF'> 
      <td colspan="2" align="center"> 
        <input class=mmcinb type='submit' name='button' value='提交数据' >
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

<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>