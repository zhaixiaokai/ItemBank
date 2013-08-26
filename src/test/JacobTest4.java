
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	JacobTest2.java
 * | 包名：test
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-27 下午4:57:10
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-27 下午4:57:10
 * |------------------------------------------------------------------------------------ 
 */
package test;

import java.io.File;

import net.ib.util.service.impl.JacobWordBeanServiceImpl;


  /**
 * <p>类名：test.JacobTest2 </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class JacobTest4 {
		public static void createANewFileTest() {
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			// word.openWord(true);// 打开 word 程序
			wordBean.SetVisible(true);
			wordBean.CreateNewDocument();// 创建一个新文档
			wordBean.setLocation();// 设置打开后窗口的位置
			wordBean.InsertText("你好");// 向文档中插入字符
			wordBean.InsertJpeg("D:" + File.separator + "TestBlob.jpg"); // 插入图片
			// 如果 ，想保存文件，下面三句
			wordBean.saveFileAs("d:\\a.doc");
			wordBean.closeDocument(0);
			wordBean.closeWord();
		}
		public static void openAnExistsFileTest() {
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			wordBean.SetVisible(true); // 是否前台打开word 程序，或者后台运行
			wordBean.OpenFile("d:\\a.doc");
			wordBean.InsertJpeg("D:" + File.separator + "TestBlob.jpg"); // 插入图片(注意刚打开的word
			// ，光标处于开头，故，图片在最前方插入)
			wordBean.save();
			wordBean.closeDocument(0);
			wordBean.closeWord();
		}
		public static void insertFormatStr(String str) {
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			wordBean.SetVisible(true); // 是否前台打开word 程序，或者后台运行
			wordBean.CreateNewDocument();// 创建一个新文档
			wordBean.InsertFormatStr(str);// 插入一个段落，对其中的字体进行了设置
		}
		public static void insertTableTest() {
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			wordBean.SetVisible(true); // 是否前台打开word 程序，或者后台运行
			wordBean.CreateNewDocument();// 创建一个新文档
			wordBean.setLocation();
			wordBean.InsertTable("表名", 3, 2);
			wordBean.saveFileAs("d:\\table.doc");
			wordBean.closeDocument(0);
			wordBean.closeWord();
		}
		public static void mergeTableCellTest() {
			insertTableTest();//生成d:\\table.doc
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			wordBean.SetVisible(true); // 是否前台打开word 程序，或者后台运行
			wordBean.OpenFile("d:\\table.doc");
			wordBean.mergeCellTest();
		}
		public static void main(String[] args) {
			// 进行测试前要保证d:\\TestBlob.jpg 图片文件存在
			 //createANewFileTest();//创建一个新文件
			 //openAnExistsFileTest();// 打开一个存在 的文件
			 //insertFormatStr("格式 化字符串");//对字符串进行一定的修饰
			//insertTableTest();// 创建一个表格
			insertTableTest();//生成d:\\table.doc
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			wordBean.SetVisible(true); // 是否前台打开word 程序，或者后台运行
			wordBean.OpenFile("d:\\table.doc");
			wordBean.mergeCellTest();
			wordBean.moveDown(2);
			wordBean.moveEnd();
			wordBean.moveStart();
			wordBean.replaceAllText("第", "The");

			//wordBean.moveStart();
			//wordBean.replaceTextWithImage("行", "d:\\TestBlob.jpg");
			wordBean.findTextAndCopy("行");
			wordBean.findTextAndPaste("列");
			//wordBean.paste("行");
			for(int	i=0;i<3;i++)
				wordBean.insertEnter();
			wordBean.copyParagraphFromAnotherDoc("d:\\Doc2.doc",2);
			//wordBean.printFile();
			wordBean.saveFileAs("d:\\testSave.doc");
			wordBean.moveEnd();
			wordBean.copyTableFromAnotherDoc("d:\\DataTable.doc", 1);
		}

}
