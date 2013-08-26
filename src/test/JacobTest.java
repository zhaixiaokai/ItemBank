
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	JacobTest.java
 * | ������test
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-27 ����3:27:22
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-27 ����3:27:22
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
 * <p>������test.JacobTest </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class JacobTest {

	    ActiveXComponent oWord = null;  
	    Dispatch selection;  
	    Dispatch document;  
	    Dispatch doc;  
	    /** 
	     * ��ȡģ�壬�����滻ί����Ȩ���� 
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
	        // �����Ŀ��Ŀ¼   
	        File docFloder = new File(filePath);  
	        if (docFloder.exists()) {  
	            File[] modelFile = docFloder.listFiles();  
	            for (File file : modelFile) {  
	                if (file.getName().equals("��Ȩί����.doc")) {  
	                    String realFileName = filePath + "//" + file.getName();  
	                    System.out.println("realFileName::" + realFileName);  
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	                    Calendar cal = Calendar.getInstance();  
	                    // ����Ȩ���޼�һ����   
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
	                        // ��Ȩʱ��   
	                        if (this.find("TIME")) {  
	                            Dispatch.put(selection, "Text", sdf.format(auditedTime));  
	                        }  
	                        // ��Ȩ����   
	                        if (this.find("AUDITED")) {  
	                            Dispatch.put(selection, "Text", sdf.format(auditedTime)+" �� "+auditedTimes);  
	                        }  
	                        // ��Ȩ��Χ   
	                        if (this.find("AUTHORIZESCOPE")) {  
	                            Dispatch.put(selection, "Text", contractCode+" - "+"��"+contractName+"��");  
	                        }  
	                        // ���ұ���Ȩ��   
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
	     * ��ѡ�����ݻ����㿪ʼ�����ı� 
	     *  
	     * @param toFindText 
	     *            Ҫ���ҵ��ı� 
	     * @return boolean true-���ҵ���ѡ�и��ı���false-δ���ҵ��ı� 
	     */  
	    public boolean find(String toFindText) {  
	        if (!"".equals(toFindText)){  
	            // ��selection����λ�ÿ�ʼ��ѯ   
	            Dispatch find = oWord.call(selection, "Find").toDispatch();  
	            // �Ƶ���ͷ   
	            Dispatch.call(selection, "HomeKey", new Variant(6));  
	            // ����Ҫ���ҵ�����   
	            Dispatch.put(find, "Text", toFindText);  
	            // ��ǰ����   
	            Dispatch.put(find, "Forward", "True");  
	            // ���ø�ʽ   
	            Dispatch.put(find, "Format", "True");  
	            // ��Сдƥ��   
	            Dispatch.put(find, "MatchCase", "True");  
	            // ȫ��ƥ��   
	            Dispatch.put(find, "MatchWholeWord", "True");  
	            // ���Ҳ�ѡ��   
	            return Dispatch.call(find, "Execute").getBoolean();  
	        }else{  
	            return false;  
	        }  
	    }  
	}  
