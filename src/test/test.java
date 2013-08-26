package test;

import com.jacob.com.*;
import com.jacob.activeX.*;
 
public class test {
	/**
	 * �ĵ�ת������
	 * @param docfile
	 *            word�ĵ��ľ���·�����ļ���(������չ��)
	 * @param htmlfile
	 *            ת�����html�ļ�����·�����ļ���(������չ��)
	 */
	public static void change(String docfile, String htmlfile) {
		ActiveXComponent app = new ActiveXComponent("Word.Application"); // ����word
		try {
			app.setProperty("Visible", new Variant(false));
			// ����word���ɼ�
			Object docs = app.getProperty("Documents").toDispatch();
			Dispatch doc = Dispatch.invoke((Dispatch) docs,"Open",Dispatch.Method,new Object[] { docfile, new Variant(false),new Variant(true) }, new int[1]).toDispatch();
			// ��word�ļ�
			Dispatch.invoke((Dispatch) doc, "SaveAs", Dispatch.Method,new Object[] { htmlfile, new Variant(8) }, new int[1]);
			// ��Ϊhtml��ʽ���浽��ʱ�ļ�
			Variant f = new Variant(false);
			Dispatch.call(doc, "Close", f); 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			app.invoke("Quit", new Variant[] {});
		}
	}

	public static void main(String[] strs) {
		test.change("d:\\e0249195-d418-4488-be5c-a85872b7d5a6.doc", "d:\\11111.html");
		System.out.println("wancheng");
	}
}

