
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

import net.ib.util.service.impl.JacobWordBeanServiceImpl;


  /**
 * <p>������test.JacobTest2 </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class JacobTest4 {
		public static void createANewFileTest() {
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			// word.openWord(true);// �� word ����
			wordBean.SetVisible(true);
			wordBean.CreateNewDocument();// ����һ�����ĵ�
			wordBean.setLocation();// ���ô򿪺󴰿ڵ�λ��
			wordBean.InsertText("���");// ���ĵ��в����ַ�
			wordBean.InsertJpeg("D:" + File.separator + "TestBlob.jpg"); // ����ͼƬ
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
			wordBean.InsertTable("����", 3, 2);
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
			insertTableTest();//����d:\\table.doc
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			wordBean.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
			wordBean.OpenFile("d:\\table.doc");
			wordBean.mergeCellTest();
			wordBean.moveDown(2);
			wordBean.moveEnd();
			wordBean.moveStart();
			wordBean.replaceAllText("��", "The");

			//wordBean.moveStart();
			//wordBean.replaceTextWithImage("��", "d:\\TestBlob.jpg");
			wordBean.findTextAndCopy("��");
			wordBean.findTextAndPaste("��");
			//wordBean.paste("��");
			for(int	i=0;i<3;i++)
				wordBean.insertEnter();
			wordBean.copyParagraphFromAnotherDoc("d:\\Doc2.doc",2);
			//wordBean.printFile();
			wordBean.saveFileAs("d:\\testSave.doc");
			wordBean.moveEnd();
			wordBean.copyTableFromAnotherDoc("d:\\DataTable.doc", 1);
		}

}
