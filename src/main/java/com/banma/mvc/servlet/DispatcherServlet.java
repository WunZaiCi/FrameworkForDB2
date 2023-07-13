package com.banma.mvc.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banma.dao.Dao;
import com.banma.mvc.action.SupportAction;
import com.banma.mvc.servlet.ActionXmlParser.Action;
import com.banma.mvc.servlet.ActionXmlParser.Result;
import com.banma.orm.DBSessionFacory.DBSession;

/**
 * 自定义MVC框架的核心控制器
 * 
 * @author WHASSUPMAN
 *
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String charset = "utf-8";
	private HashMap<String, Action> actions;
	private DBSession session;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 读取初始化参数
		charset = config.getInitParameter("charset");

		// classpahth:相对于classes
		String configPath = config.getInitParameter("config");
		if (configPath.startsWith("classpath:")) {
			configPath = configPath.replace("classpath:", "");
			InputStream is = 
					DispatcherServlet.class.getClassLoader().getResourceAsStream(configPath);
			// 解析业务控制器的配置中心xml文件
			try {
				actions = new ActionXmlParser().parse(is);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.读取当前请求的路径
		String path = request.getRequestURI(); // 包含项目名的请求路径
		String basePath = request.getContextPath();// 项目路径
		// 相对于项目路径的请求路径
		path = path.replace(basePath, "");

		// 2.根据请求路径从配置中心获取到请求处理类
		Action action = actions.get(path);
		if(action==null) {//请求资源不存在
			request.getRequestDispatcher("error.html")
					.forward(request, response);
			return;
		}
		
		try {
			Class<? extends SupportAction> actionCls =  (Class<? extends SupportAction>) Class.forName(action.getName());
			//业务控制器的类对象
			SupportAction sAction = actionCls.newInstance();
		
			//向业务控制器中住request和session
			//获取父类中的字段
			Field reqField=actionCls.getSuperclass().getDeclaredField("request");
			reqField.setAccessible(true);
			reqField.set(sAction, request);
			
			Field sessionField=actionCls.getSuperclass().getDeclaredField("session");
			sessionField.setAccessible(true);
			sessionField.set(sAction, request.getSession());
			sAction.parseParams();//解析请求参数的字段
			
			//获取业务控制中字段
			Field[] fs = actionCls.getDeclaredFields();
			for (Field f : fs) {
				f.setAccessible(true);
				//判断字段的类型是否实现Dao接口
				if(f.getType().getInterfaces()!=null&&
						f.getType().getInterfaces().length>0&&
						f.getType().getInterfaces()[0]==Dao.class) {
					//获取Dao的构造方法
					//方法参数的类型是DBSession类
					Constructor<?> constructor = f.getType().getConstructor(DBSession.class);
					
					//通过构造方法实例化类对象
					Object dao = constructor.newInstance(session);
					
					f.set(sAction, dao);
				}else if(f.getType()==String.class){
					//普通字段
					//从请求中获取数据
					f.set(sAction, request.getParameter(f.getName()));
				}else {
					//Form实体类的字段
					f.set(sAction, sAction.parseParams4Form(f.getType()));
				}
			}
			
			
			//执行业务控制器类的方法-execute()
			String resultName = sAction.execute();
			
			//判断业务处理结果数据的类型是否为json
			if(action.getDataType()==null) {
				//不是json数据的情况下
				//根据处理方法返回的结果名称，获取到<result>标签中path
				Result result = action.getResults().get(resultName);
				String resultPath = result.getPath();
				if(result.isRedirect()) {
					response.sendRedirect(resultPath);
				}else {
					request.getRequestDispatcher(resultPath)
					.forward(request, response);
				}
			}else {
				response.setContentType(action.getDataType());
				response.getWriter().append(resultName);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
