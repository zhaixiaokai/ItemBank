
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	JacobTest.java
 * | 包名：test
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-27 下午3:27:22
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-27 下午3:27:22
 * |------------------------------------------------------------------------------------ 
 */
package test;
import java.io.File;  
import java.text.SimpleDateFormat;  
import java.util.Calendar;  
import java.util.Date;  
import com.jacob.activeX.ActiveXComponent;  
import com.jacob.com.Dispatch;  
import com.jacob.com.Variant;  

  /**
 * <p>类名：test.JacobTest </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class JacobTest {

	    ActiveXComponent oWord = null;  
	    Dispatch selection;  
	    Dispatch document;  
	    Dispatch doc;  
	    /** 
	     * 读取模板，查找替换委托授权内容 
	     *  
	     * @param filePath 
	     * @param authorizedPerson 
	     * @param contractCode 
	     * @param contractName 
	     * @param auditedTime 
	     */  
	    @SuppressWarnings("static-access")  
	    public void setPowerOfAttorneyInfo(String filePath,  
	            String authorizedPerson, String contractCode, String contractName,  
	            Date auditedTime) {  
	        // 获得项目根目录   
	        File docFloder = new File(filePath);  
	        if (docFloder.exists()) {  
	            File[] modelFile = docFloder.listFiles();  
	            for (File file : modelFile) {  
	                if (file.getName().equals("授权委托书.doc")) {  
	                    String realFileName = filePath + "//" + file.getName();  
	                    System.out.println("realFileName::" + realFileName);  
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	                    Calendar cal = Calendar.getInstance();  
	                    // 至授权期限加一个月   
	                    cal.setTime(auditedTime);  
	                    cal.add(cal.MONTH, +1);  
	                    String auditedTimes = sdf.format(cal.getTime());  
	                    try {  
	                        oWord = new ActiveXComponent("Word.Application");  
	                        oWord.setProperty("Visible", new Variant(false));  
	                        document = oWord.getProperty("Documents").toDispatch();  
	                        doc = Dispatch.call(document, "Open", realFileName)  
	                                .toDispatch();  
	                        selection = oWord.getProperty("Selection").toDispatch();  
	                        // 授权时间   
	                        if (this.find("TIME")) {  
	                            Dispatch.put(selection, "Text", sdf.format(auditedTime));  
	                        }  
	                        // 授权期限   
	                        if (this.find("AUDITED")) {  
	                            Dispatch.put(selection, "Text", sdf.format(auditedTime)+" 至 "+auditedTimes);  
	                        }  
	                        // 授权范围   
	                        if (this.find("AUTHORIZESCOPE")) {  
	                            Dispatch.put(selection, "Text", contractCode+" - "+"《"+contractName+"》");  
	                        }  
	                        // 查找被授权人   
	                        if (this.find("AUTHORIZEDPERSON")) {  
	                            Dispatch.put(selection, "Text", authorizedPerson);  
	                        }  
	                        Dispatch.call(doc, "SaveAs", realFileName);  
	                        Dispatch.call(doc, "Close", new Variant(false));  
	                    } catch (Exception e) {  
	                        e.printStackTrace();  
	                    } finally {  
	                        if (oWord != null) {  
	                            oWord.invoke("Quit", new Variant[] {});  
	                        }  
	                    }  
	                }  
	            }  
	        }  
	    }  
	    /** 
	     * 从选定内容或插入点开始查找文本 
	     *  
	     * @param toFindText 
	     *            要查找的文本 
	     * @return boolean true-查找到并选中该文本，false-未查找到文本 
	     */  
	    public boolean find(String toFindText) {  
	        if (!"".equals(toFindText)){  
	            // 从selection所在位置开始查询   
	            Dispatch find = oWord.call(selection, "Find").toDispatch();  
	            // 移到开头   
	            Dispatch.call(selection, "HomeKey", new Variant(6));  
	            // 设置要查找的内容   
	            Dispatch.put(find, "Text", toFindText);  
	            // 向前查找   
	            Dispatch.put(find, "Forward", "True");  
	            // 设置格式   
	            Dispatch.put(find, "Format", "True");  
	            // 大小写匹配   
	            Dispatch.put(find, "MatchCase", "True");  
	            // 全字匹配   
	            Dispatch.put(find, "MatchWholeWord", "True");  
	            // 查找并选中   
	            return Dispatch.call(find, "Execute").getBoolean();  
	        }else{  
	            return false;  
	        }  
	    }  
	}  
