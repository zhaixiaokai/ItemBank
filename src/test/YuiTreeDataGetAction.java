
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	YuiTreeDataGetAction.java
 * | 包名：test
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-21 下午2:35:45
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-21 下午2:35:45
 * |------------------------------------------------------------------------------------ 
 */
package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.Service;


  /**
 * <p>类名：test.YuiTreeDataGetAction </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class YuiTreeDataGetAction {
	Dao	dao;
	Service service;
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public	String	getData(){
		List<Map> list	=	new	ArrayList<Map>();
		String	sql	=	"select id,tree_id,text label,pid,sno,node_path,node_series from SYS_IB_CLASSIFICATION_TREE where tree_id='jibenfenleitixi' order by node_path asc";
		list	=	dao.executeQuery(sql);
		String	Json=service.DataListToTreeJson(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(Json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
