
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	JacobTest3.java
 * | ������test
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-29 ����3:29:33
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-29 ����3:29:33
 * |------------------------------------------------------------------------------------ 
 */
package test;

import net.ib.util.service.impl.JacobWordBeanServiceImpl;


  /**
 * <p>������test.JacobTest3 </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class JacobTest3 {
	

	public	static void main(String [] args){
		JacobWordBeanServiceImpl	wordBean	=new	JacobWordBeanServiceImpl();
		wordBean.OpenFile("d:\\DataTable.doc");
		wordBean.SetVisible(true);
		//JacobWordBeanServiceImpl	wordBean1	=	new	JacobWordBeanServiceImpl();
		//wordBean1.SetVisible(true);
		//wordBean1.OpenFile("d:\\question.doc");
		//String	a=	wordBean1.getTxtFromCell(1, 2, 2);
		//System.out.println(a);
		//wordBean.paste();
		//wordBean1.closeWord();
//		wordBean.getContextFromAnotherFileTableCell("d:\\question.doc",1,2,2);
//		wordBean.moveEnd();
//		wordBean.getContextFromAnotherFileTableCell("d:\\question.doc",1,3,2);
		//System.out.println(wordBean.getTxtFromCell(1, 2, 2));
		int	count	=	wordBean.CountTable();
		for(int i=1;i<=count;i++){
			wordBean.setTableBorder(i,0);
		}
		
	}
}
