
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	JacobTest3.java
 * | 包名：test
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-29 下午3:29:33
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-29 下午3:29:33
 * |------------------------------------------------------------------------------------ 
 */
package test;

import net.ib.util.service.impl.JacobWordBeanServiceImpl;


  /**
 * <p>类名：test.JacobTest3 </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
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
