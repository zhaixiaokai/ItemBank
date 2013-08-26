
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	CurseServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：wuzexi
 * | 创建日期：2012-12-6 下午8:22:24
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-6 下午8:22:24
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.CurseService;
import net.ib.util.service.Service;


  /**
 * <p>类名：net.ib.util.service.impl.CurseServiceImpl </p>
 * <p>描述：课程管理的服务实现类</p>
 * <p></p>
 */
public class CurseServiceImpl implements CurseService {

	private static Logger logger = Logger.getLogger(CurseServiceImpl.class);
	private	Dao	dao;
	private	Service	service;
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
	/* (非-Javadoc)
	 * <p>名称: addCurse</p>
	 * <p>说明: </p>
	 * @param getSpecial
	 * @param curseId
	 * @param curseName
	 * @param curseCredit
	 * @return
	 * @see net.ib.util.service.CurseService#addCurse(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addCurse(String getSpecial,String curseId,String curseName,String curseCredit,String method,String curseRemarks) {
		// TODO Auto-generated method stub
		String result = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		
		String	CheckExistSql	=	"select * from SYS_IB_CLASSIFICATION_TREE where ID='"+getSpecial+"'";
		List<Map>	CheckList	=	dao.executeQuery(CheckExistSql);
		if(CheckList.size()==0){
			logger.info("添加课程过程中未找到提交的专业");
			result = "{\"result\":\"failedId\",\"text\":\"该专业不存在\"}";
			try {
				PrintWriter pw = response.getWriter();
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//通过id、name查询是否存在与当前所要添加的项有重复的项
		String	Sql	=	"select CURSE_ID,CURSE_NAME,SPECIAL_FIELD_ID from SYS_CURSE where CURSE_ID='"+curseId
				+"' or CURSE_NAME='"+curseName+"'";
		List<Map>	list	=	dao.executeQuery(Sql);
		
		//如果有重复项
		if(list.size()!=0){
			String	TempId="";
			String	TempName="";
			Map map = new HashMap();
			for(int	i=0;i<list.size();i++){
				map=list.get(i);
				TempId	=	(String) map.get("curse_id");
				TempName=	(String) map.get("curse_name");
				logger.debug(TempName);
				logger.debug(TempId);
				if(TempId.equals(curseId)){
					logger.info("添加课程过程中课程唯一标识符重复");
					result = "{\"result\":\"failedId\",\"text\":\"课程编号已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(curseName)){
					logger.info("添加课程过程中课程名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"课程名称已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		//没有重复项
		else{
			Sql="insert into SYS_CURSE (CURSE_ID,CURSE_NAME,SPECIAL_FIELD_ID,CURSE_CREDIT,ASSESSMENT_METHOD,REMARKS) values " +
					"('"+curseId+"','"+curseName+"','"+getSpecial+"','"+curseCredit+"','"+method+"','"+curseRemarks+"')";
			if(1==dao.execute(Sql)){
				result = "{\"result\":\"succ\",\"text\":\"添加课程成功\"}";
				try {
					PrintWriter pw = response.getWriter();
					pw.print(result);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	@Override
	public String updateCurse(String oldCurseId,String curseName,String	curseID,String	curseCredit,String radio,String curseRemarks)
	{
		// TODO Auto-generated method stub
		logger.debug(oldCurseId);
		logger.debug(curseName);
		logger.debug(curseID);
		logger.debug(curseCredit);
		logger.debug(radio);
		logger.debug(curseRemarks);
		curseRemarks=curseRemarks.trim();
		if (radio.trim().toUpperCase().equals("KAOSHI")) {
			radio = "1";
		} else {
			radio = "0";
		}
		String sql = "select curse_id,curse_name from sys_curse where (curse_id='"
				+ curseID + "' or curse_name='" + curseName + "') and curse_id!='"+oldCurseId+"'";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<Map>	list	=	dao.executeQuery(sql);
		String	result="";
		
		if (list.size() == 0) {
		
			//开始插入数据
			sql = "update sys_curse set curse_id='" + curseID
					+ "',curse_name='" + curseName + "',curse_credit='"
					+ curseCredit+ "',assessment_method='" + radio
					+ "',remarks='" + curseRemarks
					+ "' where curse_id='" + oldCurseId + "'";

			logger.debug(sql);
			dao.execute(sql);
			result = "{\"result\":\"succ\",\"text\":\"修改课程成功\"}";
			try {
				PrintWriter pw = response.getWriter();
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		//有重复
		else{
			Map map = new HashMap();
			String ClassId = "";
			String ClassName = "";
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				ClassId = (String) map.get("curse_id");
				ClassName = (String) map.get("curse_name");
				// 如果课程Id重复
				if (curseID.equals(ClassId)) {
					logger.info("修改课程过程中Id重复");
					result = "{\"result\":\"failedId\",\"text\":\"课程编号已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}
				// 班级
				else if (curseName.equals(ClassName)) {
					logger.info("修改课程过程中名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"课程名称已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				} 

				else {
					logger.info("修改课程发生异常");
				}
			}			
		}
		return null;
	}
	@Override
	public String deleteCurse(String deleteId) {
		// TODO Auto-generated method stub
		logger.debug(deleteId);
		String	sql	=	"delete from sys_curse where CURSE_ID='"+deleteId+"'";
		logger.debug(sql);
		String	result="";
		if(1==dao.execute(sql)){
			result	=	"{\"result\":\"删除课程成功\"}";
		}
		else{
			result	=	"{\"result\":\"删除课程失败\"}";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String bulkDeleteCurse(String	deleteIds) {
		// TODO Auto-generated method stub
		logger.debug(deleteIds);
		//收到的DelIds格式为   id1 id2 id3 id4 ....
		String[] array=deleteIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		String	Sql	=	"delete from sys_curse where";
		String	result="";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
			}
			Sql+=" curse_id='"+array[i]+"'";
		}
		logger.debug(Sql);
		if(1==dao.execute(Sql)){
			result="{\"result\":\"success\",\"text\":\"删除成功\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"删除成功\"}";
		}
		
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(result);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}