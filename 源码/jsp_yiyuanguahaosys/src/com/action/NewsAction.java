package com.action;

/**
 *  上传缩略图
 * 
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
import com.bean.SystemBean;
 
import com.util.Constant;
import com.util.Filter;
import com.util.SmartFile;
import com.util.SmartUpload;
import com.bean.ComBean;
public class NewsAction extends HttpServlet {

	private ServletConfig config;
	/**
	 * Constructor of the object.
	 */
	public NewsAction() {
		super();
	}

	final public void init(ServletConfig config) throws ServletException
    {
        this.config = config;  
    }

    final public ServletConfig getServletConfig()
    {
        return config;
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

		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		response.setContentType(Constant.CONTENTTYPE);
		String sysdir = new SystemBean().getDir();
		HttpSession session = request.getSession();
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		try{
			String username2 = (String)session.getAttribute("user");
			if(username2 == null){
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			else{
				 String method = null;
				 ComBean cb=new ComBean();
				 SmartUpload mySmartUpload = new SmartUpload();//init
				 int count = 0;
				 try{
					 mySmartUpload.initialize(config,request,response);
		             mySmartUpload.upload(); 
		             method = mySmartUpload.getRequest().getParameter("method").trim();
		            if(method.equals("addys")){//增加
		            	//String xm = "";	String ks=""; String pic=""; String fy=""; String zc=""; String nl=""; String content="";String sj=""; 
		            	String xm = mySmartUpload.getRequest().getParameter("xm");
						String ks = mySmartUpload.getRequest().getParameter("ks");
						String fy = mySmartUpload.getRequest().getParameter("fy");
						String zc = mySmartUpload.getRequest().getParameter("zc");
						String nl = mySmartUpload.getRequest().getParameter("nl");
						String content = mySmartUpload.getRequest().getParameter("content1");
						String bh = mySmartUpload.getRequest().getParameter("bh");
						
						String sj = mySmartUpload.getRequest().getParameter("sj"); 
						 
							SmartFile file = mySmartUpload.getFiles().getFile(0);
			            	String fileExt=file.getFileExt();	            
			            	String path="/upload_file/sale";
		                    count = mySmartUpload.save(path);
		                    String str=cb.getString("select id from ys where bh='"+bh+"'");
		                    if(str==null){
		                    	 String sql="insert into ys(xm,ks,pic,fy,zc,nl,content,sj,bh) " +
		                    		"values('"+xm+"','"+ks+"','"+path+"/"+file.getFileName()+"','"+fy+"','"+zc+"','"+nl+"','"+content+"', '"+sj+"','"+bh+"')";
		                    int flag =cb.comUp(sql);
							if(flag == Constant.SUCCESS){
								request.setAttribute("message", "操作成功！");
								request.getRequestDispatcher(sysdir+"/ys/index.jsp").forward(request, response);
							}
							else{
								request.setAttribute("message", "系统维护中，请稍后再试！");
								request.getRequestDispatcher(sysdir+"/ys/index.jsp").forward(request, response);
							}
		                    }
		                    else{
		                    	request.setAttribute("message", "编号重复！");
								request.getRequestDispatcher(sysdir+"/ys/index.jsp").forward(request, response);
		                    }
		                   
					 
		            }
		            //update
		            else if(method.equals("upys")){
		            	String id = mySmartUpload.getRequest().getParameter("id");
		            	String xm = mySmartUpload.getRequest().getParameter("xm");
						String ks = mySmartUpload.getRequest().getParameter("ks");
						String fy = mySmartUpload.getRequest().getParameter("fy");
						String zc = mySmartUpload.getRequest().getParameter("zc");
						String nl = mySmartUpload.getRequest().getParameter("nl");
						String content = mySmartUpload.getRequest().getParameter("content1");
						String bh = mySmartUpload.getRequest().getParameter("bh");
						String sj = mySmartUpload.getRequest().getParameter("sj");  
							SmartFile file = mySmartUpload.getFiles().getFile(0);
			            	String fileExt=file.getFileExt();	            
			            	String path="/upload_file/sale";
		                    count = mySmartUpload.save(path);
		                    String str=cb.getString("select id from ys where bh='"+bh+"' and id!='"+id+"'");
		                    if(str==null){
		                    	 String sql="update ys set xm='"+xm+"',ks='"+ks+"',pic='"+path+"/"+file.getFileName()+"'," +
		                    		"fy='"+fy+"',zc='"+zc+"',nl='"+nl+"',content='"+content+"',sj='"+sj+"',bh='"+bh+"' where id='"+id+"'";
		                    int flag = cb.comUp(sql);
							if(flag == Constant.SUCCESS){
								request.setAttribute("message", "操作成功！");
								request.getRequestDispatcher(sysdir+"/ys/index.jsp").forward(request, response);
							}
							else{
								request.setAttribute("message", "系统维护中，请稍后再试！");
								request.getRequestDispatcher(sysdir+"/ys/index.jsp").forward(request, response);
							}
		                    }
		                    else{
		                    	request.setAttribute("message", "编号重复！");
								request.getRequestDispatcher(sysdir+"/ys/index.jsp").forward(request, response);
		                    }
		                   
						 
		            }
		            else if(method.equals("upys2")){
		            	String username=(String)session.getAttribute("user");
		            	String xm = mySmartUpload.getRequest().getParameter("xm");
						String ks = mySmartUpload.getRequest().getParameter("ks");
						String fy = mySmartUpload.getRequest().getParameter("fy");
						String zc = mySmartUpload.getRequest().getParameter("zc");
						String nl = mySmartUpload.getRequest().getParameter("nl");
						String content = mySmartUpload.getRequest().getParameter("content1");
						
						String sj = mySmartUpload.getRequest().getParameter("sj");  
							SmartFile file = mySmartUpload.getFiles().getFile(0);
			            	String fileExt=file.getFileExt();	            
			            	String path="/upload_file/sale";
		                    count = mySmartUpload.save(path);
		                    String sql="update ys set xm='"+xm+"',ks='"+ks+"',pic='"+path+"/"+file.getFileName()+"'," +
		                    		"fy='"+fy+"',zc='"+zc+"',nl='"+nl+"',content='"+content+"',sj='"+sj+"' where bh='"+username+"'";
		                    int flag = cb.comUp(sql);
							if(flag == Constant.SUCCESS){
								request.setAttribute("message", "操作成功！");
								request.getRequestDispatcher(sysdir+"/ys/gr.jsp").forward(request, response);
							}
							else{
								request.setAttribute("message", "系统维护中，请稍后再试！");
								request.getRequestDispatcher(sysdir+"/ys/gr.jsp").forward(request, response);
							}
						 
		            }
		            else{
		            	request.getRequestDispatcher("error.jsp").forward(request, response);
		            }
		        }catch(Exception ex){
		        	ex.printStackTrace();
		        	//request.getRequestDispatcher("error.jsp").forward(request, response);
		        }
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
