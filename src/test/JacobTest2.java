
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	JacobTest2.java
 * | ������test
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-27 ����4:57:10
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-27 ����4:57:10
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
 * <p>������test.JacobTest2 </p>
 * <p>������Jacob�����������Է���</p>
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
			// word.openWord(true);// �� word ����
			wordBean.SetVisible(true);
			wordBean.CreateNewDocument();// ����һ�����ĵ�
			wordBean.setLocation();// ���ô򿪺󴰿ڵ�λ��
			//wordBean.InsertText("���");// ���ĵ��в����ַ�
			//wordBean.InsertJpeg("D:" + File.separator + "TestBlob.jpg"); // ����ͼƬ
			// ��� ���뱣���ļ�����������
			wordBean.saveFileAs("d:\\a.doc");
			wordBean.closeDocument(0);
			wordBean.closeWord();
		}
		public static void openAnExistsFileTest() {
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			wordBean.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
			wordBean.OpenFile("d:\\a.doc");
			wordBean.InsertJpeg("D:" + File.separator + "TestBlob.jpg"); // ����ͼƬ(ע��մ򿪵�word
			// ����괦�ڿ�ͷ���ʣ�ͼƬ����ǰ������)
			wordBean.save();
			wordBean.closeDocument(0);
			wordBean.closeWord();
		}
		public static void insertFormatStr(String str) {
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			wordBean.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
			wordBean.CreateNewDocument();// ����һ�����ĵ�
			wordBean.InsertFormatStr(str);// ����һ�����䣬�����е��������������
		}
		public static void insertTableTest() {
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			wordBean.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
			wordBean.CreateNewDocument();// ����һ�����ĵ�
			wordBean.setLocation();
			wordBean.InsertTable("����", 3, 2);  //3��2��
			wordBean.saveFileAs("d:\\table.doc");
			wordBean.closeDocument(0);
			wordBean.closeWord();
		}
		public static void mergeTableCellTest() {
			insertTableTest();//����d:\\table.doc
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			wordBean.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
			wordBean.OpenFile("d:\\table.doc");
			wordBean.mergeCellTest();
		}
		
		public static void main(String[] args) {
			// ���в���ǰҪ��֤d:\\TestBlob.jpg ͼƬ�ļ�����
			 //createANewFileTest();//����һ�����ļ�
			 //openAnExistsFileTest();// ��һ������ ���ļ�
			 //insertFormatStr("��ʽ ���ַ���");//���ַ�������һ��������
			//insertTableTest();// ����һ�����
			//insertTableTest();//����d:\\table.doc
			/*JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			// ������������Ϣ���
			String path="";
			UUID newdocuuid = UUID.randomUUID(); 
			String newdocname=newdocuuid.toString();
			wordBean.CreateDoc(newdocname,path);// ����һ�����ļ�
			wordBean.OpenFile("d:\\"+newdocname+".doc");
			wordBean.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
			wordBean.copyTableFromAnotherDoc("d:\\xiaokai.doc", 1);
			wordBean.saveFileAs("d:\\testSave.doc");// �����һ�����
			int rowCount = wordBean.gettableRow();// �������
			int colCount = wordBean.gettableColumn();// �������
			int cols1 = wordBean.getCols(1);// ��һ������
			int cols2 = wordBean.getCols(2);// �ڶ�������
			System.out.println("&&&&&&&&&&"+rowCount+"  "+colCount+"  "+cols1+"  "+cols2);
			if (rowCount != 2 || colCount != 4 || cols1 != 2 || cols2 != 4) {
				return;
			}
			System.out.println("*********");
			String cell12 = wordBean.getTxtFromCell(1, 1, 2);// ʱ��
			String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";// ��֤ʱ���ʽ�Ƿ���ȷ
			Pattern p = Pattern.compile(eL);
			Matcher m = p.matcher(cell12);
			boolean dateFlag = m.matches();
			if (!dateFlag) {
				return;
			}
			System.out.println("%%%%%%%%%%");
			String cell24 = wordBean.getTxtFromCell(1, 2, 4);// �������߹���֤��
			System.out.println(cell24);
			wordBean.closeWord();
			
			JacobWordBeanServiceImpl wordBean1 = new JacobWordBeanServiceImpl();
			UUID contentdocuuid = UUID.randomUUID(); 
			String newcontentname=contentdocuuid.toString();
			wordBean1.CreateDoc(newcontentname,path);// ����һ�����ļ�
			wordBean1.OpenFile("d:\\"+newcontentname+".doc");
			wordBean1.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
			wordBean1.copyTableFromAnotherDoc("d:\\xiaokai.doc", 2);
			UUID docuuid = UUID.randomUUID(); 
			String docname=docuuid.toString();
			wordBean1.saveFileAs("d:\\"+docname+".doc");
			int Table2rowCount = wordBean1.gettableRow();// �������
			int Table2colCount = wordBean1.gettableColumn();// �������
			int Table2cols1 = wordBean1.getCols(1);// ��һ������
			int Table2cols2 = wordBean1.getCols(2);// �ڶ�������
			int Table2cols3 = wordBean1.getCols(3);// ����������
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
			
			//���һ��֤
			/*createANewFileTest();//����һ�����ļ�
			wordBean.OpenFile("d:\\a.doc");
			wordBean.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
			wordBean.copyTableFromAnotherDoc("d:\\xiaokai.doc",1);
			wordBean.saveFileAs("d:\\testSave.doc");
			//�������
			int rowCount=wordBean.gettableRow();
			System.out.println("&&&&&"+rowCount);
			//�������
			int colCount=wordBean.gettableColumn();
			System.out.println("&&&&&"+colCount);
			//��ȡÿ������
			int cols1=wordBean.getCols(1);
			System.out.println("&&&&&��һ��  "+cols1+"��");
			int cols2=wordBean.getCols(2);
			System.out.println("&&&&&�ڶ���  "+cols2+"��");
				//��һ�е�һ������
			String aaa=wordBean.getTxtFromCell(1,1,1);
			System.out.println("��һ�е�һ������  "+aaa);
				//��һ�еڶ�������
			String bbb=wordBean.getTxtFromCell(1,1,2);
			System.out.println("��һ�еڶ�������  "+bbb);
			//��֤ʱ���ʽ�Ƿ���ȷ
			String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
			Pattern p = Pattern.compile(eL);
			Matcher m = p.matcher(bbb);
			boolean dateFlag = m.matches();
			if (!dateFlag) {
				System.out.println("��ʽ����");
			}
			System.out.println("��ʽ��ȷ");
			//�ڶ��е�һ������
			String ccc=wordBean.getTxtFromCell(1,2,1);
			System.out.println("�ڶ��е�һ������  "+ccc);
			//��2�е�4������
			String ddd=wordBean.getTxtFromCell(1,2,4);
			System.out.println("��2�е�4������  "+ddd);*/
			/*String aa=wordBean.getTxtFromCell(1,2,4);
			System.out.println("%%%%%%%"+aa);
			*/
			
	       
		//	Dispatch tables = Dispatch.get(doc2, "Tables").toDispatch();
		//	  Dispatch cols = Dispatch.get(newTable, "Columns").toDispatch();
		//	  int colCount = Dispatch.get(cols, "Count").getInt();
			//wordBean.getTxtFromCell(1,1,1);//��ȡ����Ӧ�ط���Ԫ��
		//	wordBean.paste();//��ӡ�ڿ���̨
			
			
			
	//		 Dispatch Table=wordBean.copyTableFromAnotherDoc("d:\\xiaokai.doc",1);
	//		   Dispatch cols = Dispatch.get(Table, "Columns").toDispatch(); // �˱�������У�   
					
					
					
					
					
				//	wordBean.closeWord();
		/*	wordBean.OpenFile("d:\\xiaokai.doc");
			wordBean.insertEnter();
			wordBean.copyParagraphFromAnotherDoc("d:\\xiaokai.doc",2);
		wordBean.printFile();
			wordBean.saveFileAs("d:\\question.doc");*/
			
			//wordBean.mergeCellTest();//��table ��x=1,y=1 ��x=1,y=2��������Ԫ��ϲ�  
		//	wordBean.moveDown(2);
			//wordBean.moveEnd();
			//wordBean.moveStart();
			//wordBean.replaceAllText("��", "The");

			//wordBean.moveStart();
			//wordBean.replaceTextWithImage("��", "d:\\TestBlob.jpg");
			//wordBean.findTextAndCopy("��");
			//wordBean.findTextAndPaste("��");
			//wordBean.paste("��");
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
			wordBean.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
			wordBean.OpenFile("d:\\table.doc");
			wordBean.getTxtFromCell(1, 1, 1);
		}*/
}
