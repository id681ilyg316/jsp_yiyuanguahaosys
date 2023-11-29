package com.action;
/**
 * 管理员登陆 增加 修改 删除 删除登陆日志
 */
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.AdminBean;
import com.bean.ComBean;
import com.bean.SystemBean;
import com.util.Constant;
import com.util.MD5;

public class AdminAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AdminAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(Constant.CONTENTTYPE);
		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		try{
			String method=request.getParameter("method").trim();
			AdminBean loginbean = new AdminBean();
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(1200);
			SystemBean systembean = new SystemBean();
			String sysdir = systembean.getDir();
			if(method.equals("one")){//admin登录
				String username = request.getParameter("username");
				String password = request.getParameter("password");String sf = request.getParameter("sf");
				if(username == null||username.trim().equals("")){
					request.setAttribute("message", "请正确输入用户名！");
					request.getRequestDispatcher(sysdir+"/login.jsp").forward(request, response);
				}
				else if(password == null||password.trim().equals("")){
					request.setAttribute("message", "请输入密码！");
					request.getRequestDispatcher(sysdir+"/login.jsp").forward(request, response);
				}
				else{
					ComBean cb=new ComBean();
					 String str=cb.getString("select id from admin where username='"+username+"' and password='"+password+"'  and sf='"+sf+"'");
					 if(str!=null){  
						 session.setAttribute("user", username);session.setAttribute("sf", sf);
						request.getRequestDispatcher("admin/index.jsp").forward(request, response);  
					 }
					 else{
						 request.setAttribute("message", "登录信息错误！");
							request.getRequestDispatcher("admin/login.jsp").forward(request, response);  
					 }
				}
			}
			else if(method.equals("editpwd")){//admin edit password
				String username2 = (String)session.getAttribute("user");
				if(username2 == null){
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
				else{
					String oldpwd = MD5.MD5(request.getParameter("oldpwd").trim());
					String newpwd = MD5.MD5(request.getParameter("newpwd").trim());
					String username = (String)session.getAttribute("user");
					int flag = loginbean.editPassword(username, oldpwd, newpwd);
					switch (flag){
						case Constant.SUCCESS:
							request.setAttribute("message", "密码修改成功！");
							request.getRequestDispatcher(sysdir+"/system/editpwd.jsp").forward(request, response);
							break;
						case Constant.PASSWORD_ERROR:
							request.setAttribute("message", "原始密码错误，请确认权限！");
							request.getRequestDispatcher(sysdir+"/system/editpwd.jsp").forward(request, response);
							break;
						case Constant.SYSTEM_ERROR:
							request.setAttribute("message", "系统维护中，请稍后再试！");
							request.getRequestDispatcher(sysdir+"/system/editpwd.jsp").forward(request, response);
							break;
					}
				}		
			}
			else if(method.equals("exit")){//admin exit
				 
					session.removeAttribute("user");
					session.removeAttribute("sf");
					System.gc();
					request.getRequestDispatcher("index.jsp").forward(request, response);
				 
			}
			else if(method.equals("manager")){//add,update manager
				 
						String username = request.getParameter("bh").trim();
						String password = MD5.MD5(request.getParameter("password").trim());
						
						
						int flag = loginbean.addManager(username, password, "2", "1");
						if(flag == Constant.SUCCESS){
							request.setAttribute("message", "增加成功！");
							request.getRequestDispatcher(sysdir+"/system/user.jsp").forward(request, response);
						}
						else if(flag == Constant.SAME_NAME){
							request.setAttribute("username", username);
							request.setAttribute("message", "该用户名已经存在！");
							request.getRequestDispatcher(sysdir+"/system/user.jsp").forward(request, response);
						}
						else{
							request.setAttribute("message", "系统维护中，请稍后再试！");
							request.getRequestDispatcher(sysdir+"/system/user.jsp").forward(request, response);
						}	 
			}
			else if(method.equals("delm")){//delete manager
				String username2 = (String)session.getAttribute("user");
				if(username2 == null){
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
				else{
					int id = Integer.parseInt(request.getParameter("id").trim());
					if(id == 1){
						request.setAttribute("message", "不能删除原始帐号！");
						request.getRequestDispatcher(sysdir+"/system/user.jsp").forward(request, response);
					}
					else{
						int flag = loginbean.delManager(id);
						if(flag == Constant.SUCCESS){
							request.setAttribute("message", "删除成功！");
							request.getRequestDispatcher(sysdir+"/system/user.jsp").forward(request, response);
						}	
						else{
							request.setAttribute("message", "系统维护中，请稍后再试！");
							request.getRequestDispatcher(sysdir+"/system/user.jsp").forward(request, response);
						}	
					}
				}			
			}
			else if(method.equals("dellog")){//delete login note
				String username2 = (String)session.getAttribute("user");
				if(username2 == null){
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
				else{
					String check[] = request.getParameterValues("checkit");
					if(check == null){
						request.setAttribute("message", "请选择要删除的记录！");
						request.getRequestDispatcher(sysdir+"/system/log.jsp").forward(request, response);
					}
					else{
						int id[]= new int[check.length];
						for(int i = 0;i<check.length;i++){
							int s = Integer.parseInt(check[i]);				
							id[i] = s;
						}
						int flag = loginbean.delLog(id);
						if(flag == Constant.SUCCESS){
							request.setAttribute("message", "删除记录成功！");
							request.getRequestDispatcher(sysdir+"/system/log.jsp").forward(request, response);
						}
						else{
							request.setAttribute("message", "系统维护中，请稍后再试！");
							request.getRequestDispatcher(sysdir+"/system/log.jsp").forward(request, response);
						}
					}
				}			
			}
			else{//无参数传入转到错误页面
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
