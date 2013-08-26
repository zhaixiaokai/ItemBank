package net.ib.base;

import java.io.IOException;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import license.License;
import net.ib.base.utils.LicenseCheck;
import net.ib.common.BeanLoader;
import net.ib.base.utils.LicenseUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class InitServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(InitServlet.class);
	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	private static final long serialVersionUID = 1L;
	private ServletContext context;

	public void init() throws ServletException {	
		context=this.getServletContext();
		
		//执行License合法性检查
		String fileURL="/license/public.key";
		PublicKey publicKey=LicenseUtils.readPublicKeyFromJar(fileURL);
		String licPath=context.getRealPath("/WEB-INF/license.xml");
		License license=(License)LicenseUtils.parseLicenseFromFile(licPath);
		if(LicenseUtils.verify(publicKey,LicenseUtils.getLicenseContent(license),license.getSignatuer())){
			logger.debug("License合法性检查通过。。。。。。");
		}else{
			logger.error("License合法性检查未通过，系统终止启动。");
			System.exit(0);
		}
		if(LicenseCheck.Check(license)){
			logger.debug("License授权项检查通过。。。。。。");
		}else{
			logger.error("License授权项检查未通过，系统终止启动。");
			System.exit(0);
		}		
		
		//初始化缓存框架，并缓存系统配置参数
		CacheManager cacheManager=(CacheManager)BeanLoader.getBean("cacheManager");
		Cache objectCache=cacheManager.getCache("objectCache");
		Element element = new Element("SYSTEM_NAME","通用试题库系统");
		Element licStatus = new Element("LICENSE_STATUC","VALID");
		objectCache.put(element);
		objectCache.put(licStatus);
		
		//初始化内存数据库，创建测试表，并插入测试数据
		logger.debug("初始化内存数据库！");
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:mem:configdb;DB_CLOSE_DELAY=-1","sa","123");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("CREATE TABLE TEST_MEM(ID INT PRIMARY KEY,NAME VARCHAR(255));");
			stmt.executeUpdate("INSERT INTO TEST_MEM VALUES(1,'Hello_First');");
			stmt.executeUpdate("INSERT INTO TEST_MEM VALUES(2,'Hello_Second');");
			stmt.executeUpdate("INSERT INTO TEST_MEM VALUES(3,'Hello_Third');");
			ResultSet rs = stmt.executeQuery("SELECT * FROM TEST_MEM");  
		    while(rs.next()) {   
		    	logger.debug(rs.getInt("ID")+","+rs.getString("NAME"));
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Constructor of the object.
	 */
	public InitServlet() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
