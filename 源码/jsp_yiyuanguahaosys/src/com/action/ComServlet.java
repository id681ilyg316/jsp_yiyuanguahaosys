package com.action;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.ComBean;
import com.util.Constant;

public class ComServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ComServlet() {
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
		HttpSession session = request.getSession();
		ComBean cBean = new ComBean();
		String date2=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String method = request.getParameter("method");
		if(method.equals("addFenLei")){  //增加科室 ------代码注释
			 String fl = request.getParameter("bj"); 
			 String str=cBean.getString("select id from fenlei where fl='"+fl+"'");
			 if(str==null){
				 int flag = cBean.comUp("insert into fenlei(fl)  values('"+fl+"')");
					if(flag == Constant.SUCCESS){ 
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher("admin/ys/fenlei.jsp").forward(request, response); 
					}
					else { 
						request.setAttribute("message", "操作失败！");
						request.getRequestDispatcher("admin/ys/fenlei.jsp").forward(request, response); 
					} 
			 }
			 else { 
					request.setAttribute("message", "科室重复！");
					request.getRequestDispatcher("admin/ys/fenlei.jsp").forward(request, response); 
				} 
		}
		else if(method.equals("delFenLei")){  //删除科室
			//hm xm dh zz  zj sj sjzj lx bz 
			String id = request.getParameter("id");  
			 int flag = cBean.comUp("delete from fenlei where id='"+id+"'");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/ys/fenlei.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/ys/fenlei.jsp").forward(request, response); 
				} 
		}
		else if(method.equals("delmember")){  //删除注册用户
			//hm xm dh zz  zj sj sjzj lx bz 
			String id = request.getParameter("id");  
			 int flag = cBean.comUp("delete from member where id='"+id+"'");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/member/person.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/member/person.jsp").forward(request, response); 
				} 
		}
		else if(method.equals("addpb")){  //增加排班信息
			String ys = request.getParameter("ys"); 
			 String ks = request.getParameter("ks"); 
			 String sj = request.getParameter("sj"); 
			 String xx = request.getParameter("xx"); 
			String str=cBean.getString("select id from pb where ys='"+ys+"' and xx='"+xx+"'"); 
			if(str==null){
				int flag = cBean.comUp("insert into pb(ys,ks,sj,xx)  values('"+ys+"','"+ks+"','"+sj+"','"+xx+"')");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/pb/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/pb/index.jsp").forward(request, response); 
				} 
			}
			else{
				request.setAttribute("message", "时间冲突！");
				request.getRequestDispatcher("admin/pb/index.jsp").forward(request, response); 
			} 
		}
		else if(method.equals("uppb")){  //修改排班信息 
			String id = request.getParameter("id");  
			String ys = request.getParameter("ys"); 
			 String ks = request.getParameter("ks"); 
			 String sj = request.getParameter("sj"); 
			 String xx = request.getParameter("xx"); 
			 String str=cBean.getString("select ks from pb where ys='"+ys+"' and xx='"+xx+"' and id!='"+id+"'"); 
			if(str==null){
				int flag = cBean.comUp("update pb set ys='"+ys+"',ks='"+ks+"',sj='"+sj+"',xx='"+xx+"' where id='"+id+"'");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/pb/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/pb/index.jsp").forward(request, response); 
				} 
			}
			else{
				request.setAttribute("message", "时间冲突！");
				request.getRequestDispatcher("admin/pb/index.jsp").forward(request, response); 
			} 
		}
		 
		else if(method.equals("delpb")){  //删除排班信息 
			String id = request.getParameter("id");  
			 int flag = cBean.comUp("delete from pb where id='"+id+"'");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/pb/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/pb/index.jsp").forward(request, response); 
				} 
		}
		else if(method.equals("delys")){  //删除 医生
			//hm xm dh zz  zj sj sjzj lx bz 
			String id = request.getParameter("id");  
			 int flag = cBean.comUp("delete from ys where id='"+id+"'");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/ys/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/ys/index.jsp").forward(request, response); 
				} 
		}
		
		 
		 
		//////////////////////////////////////////////////////////预约  
		else if(method.equals("gh")){  //加入预约
			String member=(String)session.getAttribute("member");
			 
				String ysid = request.getParameter("ysid");  
				String sj = request.getParameter("sj");  
				String xs = request.getParameter("xs");  
				String lxr = request.getParameter("lxr");  
				String lxfs = request.getParameter("lxfs");  
				String xm=cBean.getString("select xm from ys where id='"+ysid+"'");
				String ks=cBean.getString("select ks from ys where id='"+ysid+"'"); 
				String fy=cBean.getString("select fy from ys where id='"+ysid+"'"); 
				String ysbh=cBean.getString("select bh from ys where id='"+ysid+"'"); 
				long bh=System.currentTimeMillis();
					int flag = cBean.comUp("insert into gh(ysid,xm,ks,sj,lxr,lxfs,tjsj,member,bh,fy,xs,ysbh) " +
							"values('"+ysid+"','"+xm+"','"+ks+"','"+sj+"','"+lxr+"','"+lxfs+"','"+date2+"','"+member+"','"+bh+"','"+fy+"','"+xs+"','"+ysbh+"')");
					if(flag == Constant.SUCCESS){ 
						request.setAttribute("message", "操作成功，请在用户中心查看预约编号！");
						request.getRequestDispatcher("ys.jsp").forward(request, response); 
					}
					else { 
						request.setAttribute("message", "操作失败！");
						request.getRequestDispatcher("ys.jsp").forward(request, response); 
					}  
		} 
		 
		else if(method.equals("delgh")){  //删除预约
			 
			String id = request.getParameter("id");  
			 	int flag = cBean.comUp("delete from gh where id='"+id+"'");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("member/gh/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("member/gh/index.jsp").forward(request, response); 
				} 
		} 
		else if(method.equals("delgh2")){  //删除预约
			 
			String id = request.getParameter("id");  
			 	int flag = cBean.comUp("delete from gh where id='"+id+"'");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/gh/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/gh/index.jsp").forward(request, response); 
				} 
		} 
		else if(method.equals("delgh3")){  //删除预约
			 
			String id = request.getParameter("id");  
			 	int flag = cBean.comUp("delete from gh where id='"+id+"'");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/gh/index2.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/gh/index2.jsp").forward(request, response); 
				} 
		} 
		else if(method.equals("wcgh")){  //删除预约
			 
			String id = request.getParameter("id");  
			 	int flag = cBean.comUp("update gh set zt='已完成' where id='"+id+"'");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/gh/index2.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/gh/index2.jsp").forward(request, response); 
				} 
		} 
		else if(method.equals("hmd")){  //删除预约
			 
			String id = request.getParameter("id"); 
			String member=cBean.getString("select member from gh where id='"+id+"'");
			 	int flag = cBean.comUp("update member set hmd='是' where username='"+member+"'");
			 	System.out.println("update member set hmd='是' where username='"+member+"'");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/gh/index2.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/gh/index2.jsp").forward(request, response); 
				} 
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
