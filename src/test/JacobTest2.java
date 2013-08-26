
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

import net.ib.common.ConnPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jacob.com.Dispatch;

import net.ib.util.service.impl.JacobWordBeanServiceImpl;


  /**
 * <p>类名：test.JacobTest2 </p>
 * <p>描述：Jacob基础函数测试方法</p>
 * <p></p>
 */
public class JacobTest2 {
    public Dispatch getDocument() {
		return document;
	}
	public void setDocument(Dispatch document) {
		this.document = document;
	}

	private static Dispatch document = null;   
		public static void createANewFileTest() {
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			// word.openWord(true);// 打开 word 程序
			wordBean.SetVisible(true);
			wordBean.CreateNewDocument();// 创建一个新文档
			wordBean.setLocation();// 设置打开后窗口的位置
			//wordBean.InsertText("你好");// 向文档中插入字符
			//wordBean.InsertJpeg("D:" + File.separator + "TestBlob.jpg"); // 插入图片
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
			wordBean.InsertTable("表名", 3, 2);  //3行2列
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
			//insertTableTest();//生成d:\\table.doc
			/*JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			// 检查试题基本信息表格
			String path="";
			UUID newdocuuid = UUID.randomUUID(); 
			String newdocname=newdocuuid.toString();
			wordBean.CreateDoc(newdocname,path);// 创建一个新文件
			wordBean.OpenFile("d:\\"+newdocname+".doc");
			wordBean.SetVisible(true); // 是否前台打开word 程序，或者后台运行
			wordBean.copyTableFromAnotherDoc("d:\\xiaokai.doc", 1);
			wordBean.saveFileAs("d:\\testSave.doc");// 保存第一个表格
			int rowCount = wordBean.gettableRow();// 表格行数
			int colCount = wordBean.gettableColumn();// 表格列数
			int cols1 = wordBean.getCols(1);// 第一行列数
			int cols2 = wordBean.getCols(2);// 第二行列数
			System.out.println("&&&&&&&&&&"+rowCount+"  "+colCount+"  "+cols1+"  "+cols2);
			if (rowCount != 2 || colCount != 4 || cols1 != 2 || cols2 != 4) {
				return;
			}
			System.out.println("*********");
			String cell12 = wordBean.getTxtFromCell(1, 1, 2);// 时间
			String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";// 验证时间格式是否正确
			Pattern p = Pattern.compile(eL);
			Matcher m = p.matcher(cell12);
			boolean dateFlag = m.matches();
			if (!dateFlag) {
				return;
			}
			System.out.println("%%%%%%%%%%");
			String cell24 = wordBean.getTxtFromCell(1, 2, 4);// 试题作者工作证号
			System.out.println(cell24);
			wordBean.closeWord();
			
			JacobWordBeanServiceImpl wordBean1 = new JacobWordBeanServiceImpl();
			UUID contentdocuuid = UUID.randomUUID(); 
			String newcontentname=contentdocuuid.toString();
			wordBean1.CreateDoc(newcontentname,path);// 创建一个新文件
			wordBean1.OpenFile("d:\\"+newcontentname+".doc");
			wordBean1.SetVisible(true); // 是否前台打开word 程序，或者后台运行
			wordBean1.copyTableFromAnotherDoc("d:\\xiaokai.doc", 2);
			UUID docuuid = UUID.randomUUID(); 
			String docname=docuuid.toString();
			wordBean1.saveFileAs("d:\\"+docname+".doc");
			int Table2rowCount = wordBean1.gettableRow();// 表格行数
			int Table2colCount = wordBean1.gettableColumn();// 表格列数
			int Table2cols1 = wordBean1.getCols(1);// 第一行列数
			int Table2cols2 = wordBean1.getCols(2);// 第二行列数
			int Table2cols3 = wordBean1.getCols(3);// 第三行列数
			System.out.println("&&&&&&&&&&"+Table2rowCount+"  "+Table2colCount+"  "+Table2cols1+"  "+Table2cols2+" "+Table2cols3 );
			if (Table2rowCount != 3 || Table2colCount != 2 || Table2cols1 != 1
					|| Table2cols2 != 2 || Table2cols3 != 2) {
				return;
			}
			System.out.println("^^^^^^^^^^^^^" );
			*/
			
			
		/*	Connection connection = null;
			Statement statement = null;
			try {
				connection = ConnPool.getConnection();
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select * from sys_user_teacher where school_id='"+ cell24 + "'");
				System.out.println("select * from sys_user_teacher where school_id='"+ cell24 + "'");
				if(rs.next()){
				System.out.println(rs.getString("school_id"));}
				if (!rs.next()) {
					return;
				}
				System.out.println("&&&&&&&&&&&&&&&");
				
				}catch (Exception e) {
					e.printStackTrace();
				}*/
			
			//表格一验证
			/*createANewFileTest();//创建一个新文件
			wordBean.OpenFile("d:\\a.doc");
			wordBean.SetVisible(true); // 是否前台打开word 程序，或者后台运行
			wordBean.copyTableFromAnotherDoc("d:\\xiaokai.doc",1);
			wordBean.saveFileAs("d:\\testSave.doc");
			//表格行数
			int rowCount=wordBean.gettableRow();
			System.out.println("&&&&&"+rowCount);
			//表格列数
			int colCount=wordBean.gettableColumn();
			System.out.println("&&&&&"+colCount);
			//获取每行列数
			int cols1=wordBean.getCols(1);
			System.out.println("&&&&&第一行  "+cols1+"列");
			int cols2=wordBean.getCols(2);
			System.out.println("&&&&&第二行  "+cols2+"列");
				//第一行第一列内容
			String aaa=wordBean.getTxtFromCell(1,1,1);
			System.out.println("第一行第一列内容  "+aaa);
				//第一行第二列内容
			String bbb=wordBean.getTxtFromCell(1,1,2);
			System.out.println("第一行第二列内容  "+bbb);
			//验证时间格式是否正确
			String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
			Pattern p = Pattern.compile(eL);
			Matcher m = p.matcher(bbb);
			boolean dateFlag = m.matches();
			if (!dateFlag) {
				System.out.println("格式错误");
			}
			System.out.println("格式正确");
			//第二行第一列内容
			String ccc=wordBean.getTxtFromCell(1,2,1);
			System.out.println("第二行第一列内容  "+ccc);
			//第2行第4列内容
			String ddd=wordBean.getTxtFromCell(1,2,4);
			System.out.println("第2行第4列内容  "+ddd);*/
			/*String aa=wordBean.getTxtFromCell(1,2,4);
			System.out.println("%%%%%%%"+aa);
			*/
			
	       
		//	Dispatch tables = Dispatch.get(doc2, "Tables").toDispatch();
		//	  Dispatch cols = Dispatch.get(newTable, "Columns").toDispatch();
		//	  int colCount = Dispatch.get(cols, "Count").getInt();
			//wordBean.getTxtFromCell(1,1,1);//获取表格对应地方的元素
		//	wordBean.paste();//打印在控制台
			
			
			
	//		 Dispatch Table=wordBean.copyTableFromAnotherDoc("d:\\xiaokai.doc",1);
	//		   Dispatch cols = Dispatch.get(Table, "Columns").toDispatch(); // 此表的所有列，   
					
					
					
					
					
				//	wordBean.closeWord();
		/*	wordBean.OpenFile("d:\\xiaokai.doc");
			wordBean.insertEnter();
			wordBean.copyParagraphFromAnotherDoc("d:\\xiaokai.doc",2);
		wordBean.printFile();
			wordBean.saveFileAs("d:\\question.doc");*/
			
			//wordBean.mergeCellTest();//将table 中x=1,y=1 与x=1,y=2的两个单元格合并  
		//	wordBean.moveDown(2);
			//wordBean.moveEnd();
			//wordBean.moveStart();
			//wordBean.replaceAllText("第", "The");

			//wordBean.moveStart();
			//wordBean.replaceTextWithImage("行", "d:\\TestBlob.jpg");
			//wordBean.findTextAndCopy("行");
			//wordBean.findTextAndPaste("列");
			//wordBean.paste("行");
			//	for(int	i=0;i<3;i++)
			//	wordBean.insertEnter();
			//wordBean.copyParagraphFromAnotherDoc("d:\\Doc2.doc",2);
			//wordBean.printFile();
			//wordBean.saveFileAs("d:\\testSave.doc");
		//	wordBean.moveEnd(); 
		//	wordBean.copyTableFromAnotherDoc("d:\\DataTable.doc", 1);
		}

/*		public	static	void	main(String [] args){
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			wordBean.SetVisible(true); // 是否前台打开word 程序，或者后台运行
			wordBean.OpenFile("d:\\table.doc");
			wordBean.getTxtFromCell(1, 1, 1);
		}*/
}
