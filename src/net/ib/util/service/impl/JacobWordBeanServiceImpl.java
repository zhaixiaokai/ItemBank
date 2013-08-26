
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	JacobWordBean.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-27 ����5:03:02
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-27 ����5:03:02
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import net.ib.config.WordBeanProperty;

import org.apache.log4j.Logger;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


  /**
 * <p>������net.ib.util.service.impl.JacobWordBean </p>
 * <p>���������ඨ����Jacob����Word�Ļ�������</p>
 * <p></p>
 */
public class JacobWordBeanServiceImpl {
	private static Logger logger = Logger.getLogger(JacobWordBeanServiceImpl.class);
	private ActiveXComponent MsWordApp = null;   
    // ������д����word �ĵ�   
    private Dispatch document = null;   
    public JacobWordBeanServiceImpl() {   
        // Open Word if we\'ve not done it already   
        if (MsWordApp == null) {   
            MsWordApp = new ActiveXComponent("Word.Application");   
        }   
    }
    /**
     * 
     * <p>���ƣ�SetVisible</p>
     * <p>˵���������Ƿ���ǰ̨�� word ����</p>
     * <p>������@param visible �趨�ļ� true:��ǰ̨��word����  false���ں�̨����word����</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param visible</p>
     */
    public void SetVisible(boolean visible) {   
        MsWordApp.setProperty("Visible", new Variant(visible));   
        // ��һ��������ͬ   
        // Dispatch.put(MsWordApp, "Visible", new Variant(visible));   
    }   
    /**
     * 
     * <p>���ƣ�CreateNewDocument</p>
     * <p>˵��������һ�����ĵ�</p>
     * <p>������ �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p></p>
     */
    public void CreateNewDocument() {   
        // Find the Documents collection object maintained by Word   
        // documents��ʾword�������ĵ����ڣ���word�Ƕ��ĵ�Ӧ�ó���   
        Dispatch documents = Dispatch.get(MsWordApp, "Documents").toDispatch();   
        document = Dispatch.call(documents, "Add").toDispatch();   
    }   
    /**
     * 
     * <p>���ƣ�OpenFile</p>
     * <p>˵������һ�����ڵ�word�ĵ�,����document������  </p>
     * <p>������@param wordFilePath �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param wordFilePath</p>
     */
    public void OpenFile(String wordFilePath) {   
        Dispatch documents = Dispatch.get(MsWordApp, "Documents").toDispatch();   
        document = Dispatch.call(documents, "Open", wordFilePath,   
        		//�Ƿ����ת��ConfirmConversions 
                new Variant(true),   
                //�Ƿ�ֻ��
                new Variant(false)).toDispatch();   
    }   
    /**
     * 
     * <p>���ƣ�InsertText</p>
     * <p>˵������ document �в����ı�����  </p>
     * <p>������@param textToInsert �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param textToInsert</p>
     */
    public void InsertText(String textToInsert) {   
        // Get the current selection within Word at the moment.   
        // a new document has just been created then this will be at   
        // the top of the new doc ���ѡ �е����ݣ������һ���´������ļ��������������ݣ�����Ӧ�����ļ���ͷ��   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch();   
        // ȡ��ѡ��,Ӧ�þ����ƶ���� ������ ����ӵ����ݻḲ��ѡ�е�����   
        Dispatch.call(selection, "MoveRight", new Variant(1), new Variant(1));   
        // Put the specified text at the insertion point   
        Dispatch.put(selection, "Text", textToInsert);   
        // ȡ��ѡ��,Ӧ�þ����ƶ����   
        Dispatch.call(selection, "MoveRight", new Variant(1), new Variant(1));   
    }   
    /**
     * 
     * <p>���ƣ�InsertJpeg</p>
     * <p>˵�������ĵ������һ��ͼƬ</p>
     * <p>������@param jpegFilePath �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param jpegFilePath</p>
     */
    public void InsertJpeg(String jpegFilePath) {   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch();   
        Dispatch image = Dispatch.get(selection, "InLineShapes").toDispatch();   
        Dispatch.call(image, "AddPicture", jpegFilePath);   
    }   
    /**
     * 
     * <p>���ƣ�InsertFormatStr</p>
     * <p>˵��������Ĵ���,�����ʽ�����ı�</p>
     * <p>������@param text �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param text</p>
     */
	public void InsertFormatStr(String text) {
		// ȡ��word�ļ�������
		Dispatch wordContent = Dispatch.get(document, "Content").toDispatch();
		// ����һ�����䵽���
		Dispatch.call(wordContent, "InsertAfter", text);
		// ���ж���
		Dispatch paragraphs = Dispatch.get(wordContent, "Paragraphs")
				.toDispatch();
		// һ���Ķ�����
		int paragraphCount = Dispatch.get(paragraphs, "Count").getInt();
		// �ҵ�������Ķ��䣬���ø�ʽ
		Dispatch lastParagraph = Dispatch.call(paragraphs, "Item",
				new Variant(paragraphCount)).toDispatch(); // ���һ�Σ�Ҳ���Ǹղ���ģ�
		// Range �����ʾ�ĵ��е�һ��������Χ����һ����ʼ�ַ�λ�ú�һ����ֹ�ַ�λ�ö���
		Dispatch lastParagraphRange = Dispatch.get(lastParagraph, "Range")
				.toDispatch();
		Dispatch font = Dispatch.get(lastParagraphRange, "Font").toDispatch();
		Dispatch.put(font, "Bold", new Variant(true)); // ����Ϊ����
		Dispatch.put(font, "Italic", new Variant(false)); // ����Ϊб��
		Dispatch.put(font, "Name", new Variant("����")); //
		Dispatch.put(font, "Size", new Variant(12)); // С��
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch();
		Dispatch.call(selection, "TypeParagraph");// ����һ������
		Dispatch alignment = Dispatch.get(selection, "ParagraphFormat")
				.toDispatch();// �����ʽ
		Dispatch.put(alignment, "Alignment", "1"); // (1:���� 2:���� 3:����)
	}

    /**
     * 
     * <p>���ƣ�InsertTable</p>
     * <p>˵����������</p>
     * <p>������@param tableTitle
     * <p>������@param row
     * <p>������@param column �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param tableTitle
     * <p>@param row
     * <p>@param column</p>
     */
    // word ���ڶԱ����б�����ʱ�� �������к��� ��column ��cell   
    // �����±��1��ʼ  
    public void InsertTable(String tableTitle, int row, int column) {   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
        Dispatch.call(selection, "TypeText", tableTitle); // д��������� // �������   
        Dispatch.call(selection, "TypeParagraph"); // ��һ�ж���   
        Dispatch.call(selection, "TypeParagraph"); // ��һ�ж���   
        Dispatch.call(selection, "MoveDown"); // �α�����һ��   
        // �������   
        Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
        Dispatch range = Dispatch.get(selection, "Range").toDispatch();// /��ǰ���λ�û���ѡ�е�����   
        Dispatch newTable = Dispatch.call(tables, "Add", range,   
                new Variant(row), new Variant(column), new Variant(1))   
                .toDispatch(); // ����row,column,��������   
        Dispatch cols = Dispatch.get(newTable, "Columns").toDispatch(); // �˱�������У�   
        int colCount = Dispatch.get(cols, "Count").getInt();// һ���ж����� ʵ���������==column   
        logger.debug(colCount + "��");   
        for (int i = 1; i <= colCount; i++) { // ѭ��ȡ��ÿһ��   
            Dispatch col = Dispatch.call(cols, "Item", new Variant(i))   
                    .toDispatch();   
            Dispatch cells = Dispatch.get(col, "Cells").toDispatch();// ��ǰ���е�Ԫ��   
            int cellCount = Dispatch.get(cells, "Count").getInt();// ��ǰ���е�Ԫ���� ʵ�������������row   
            for (int j = 1; j <= cellCount; j++) {// ÿһ���еĵ�Ԫ����   
                // Dispatch cell = Dispatch.call(cells, "Item", new   
                // Variant(j)).toDispatch(); //��ǰ��Ԫ��   
                // Dispatch cell = Dispatch.call(newTable, "Cell", new   
                // Variant(j) , new Variant(i) ).toDispatch(); //ȡ��Ԫ�����һ�ַ���   
                // Dispatch.call(cell, "Select");//ѡ�е�ǰ��Ԫ��   
                // Dispatch.put(selection, "Text",   
                // "��"+j+"�У���"+i+"��");//��ѡ�е���������ֵ��Ҳ��������ǰ��Ԫ����ֵ   
                PutTxtToCell(newTable, j, i, "��" + j + "�У���" + i + "��");// �������ľ��������ͬ   
            }   
        }   
    } 
    /**
     * 
     * <p>���ƣ�addTableRowBefore</p>
     * <p>˵������ָ���ı����ָ����ǰ��������</p>
     * <p>������@param tableIndex		�ļ��еĵ�N �ű�(��1 ��ʼ)
     * <p>������@param rowIndex 		ָ���е����(��1 ��ʼ)</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param tableIndex
     * <p>@param rowIndex</p>
     */
	public void addTableRowBefore(int tableIndex, int rowIndex) {
		// ���б��
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// Ҫ���ı��
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// ����������
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.call(rows, "Item", new Variant(rowIndex))
				.toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}
	/**
	 * 
	 * <p>���ƣ�addTableRowBeforeFirst</p>
	 * <p>˵�����ڵ�1 ��ǰ����һ��</p>
	 * <p>������@param tableIndex 		�ĵ��еĵ�N �ű�(��1 ��ʼ)</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param tableIndex</p>
	 */
	
	public void addTableRowBeforeFirst(int tableIndex) {
		// ���б��
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// Ҫ���ı��
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// ����������
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.get(rows, "First").toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}
	/**
	 * 
	 * <p>���ƣ�addTableRowBeforeLast</p>
	 * <p>˵���������1 ��ǰ����һ��</p>
	 * <p>������@param tableIndex 	word �ĵ��еĵ�N �ű�(��1 ��ʼ)</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param tableIndex</p>
	 */
	public void addTableRowBeforeLast(int tableIndex) {
		// ���б��
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// Ҫ���ı��
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// ����������
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.get(rows, "Last").toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}
	/**
	 * 
	 * <p>���ƣ�addRow</p>
	 * <p>˵��������һ��</p>
	 * <p>������@param tableIndex 	�ĵ��еĵ�N �ű�(��1 ��ʼ)</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param tableIndex</p>
	 */
	public void addRow(int tableIndex) {
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// Ҫ���ı��
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// ����������
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch.call(rows, "Add");
	}
	/**
	 * 
	 * <p>���ƣ�addCol</p>
	 * <p>˵��������һ��</p>
	 * <p>������@param tableIndex 	�ĵ��еĵ�N �ű�(��1 ��ʼ)</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param tableIndex</p>
	 */
	public void addCol(int tableIndex) {
		// ���б��
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// Ҫ���ı��
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// ����������
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch.call(cols, "Add").toDispatch();
		Dispatch.call(cols, "AutoFit");
	}
	/**
	 * 
	 * <p>���ƣ�addTableColBefore</p>
	 * <p>˵������ָ����ǰ�����ӱ�����</p>
	 * <p>������@param tableIndex		�ĵ��еĵ�N �ű�(��1 ��ʼ)
	 * <p>������@param colIndex 		ָ���е����(��1 ��ʼ)</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param tableIndex
	 * <p>@param colIndex</p>
	 */
	public void addTableColBefore(int tableIndex, int colIndex) {
		// ���б��
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// Ҫ���ı��
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// ����������
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		logger.debug(Dispatch.get(cols, "Count"));
		Dispatch col = Dispatch.call(cols, "Item", new Variant(colIndex))
				.toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}
	/**
	 * 
	 * <p>���ƣ�addTableColBeforeFirst</p>
	 * <p>˵�����ڵ�1 ��ǰ����һ��</p>
	 * <p>������@param tableIndex �ĵ��еĵ�N �ű�(��1 ��ʼ)</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param tableIndex</p>
	 */
	public void addTableColBeforeFirst(int tableIndex) {
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// Ҫ���ı��
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// ����������
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch col = Dispatch.get(cols, "First").toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}
	/**
	 * 
	 * <p>���ƣ�addLastTableCol</p>
	 * <p>˵���������һ��ǰ����һ��</p>
	 * <p>������@param tableIndex �ĵ��еĵ�N �ű�(��1 ��ʼ)</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param tableIndex</p>
	 */
	public void addLastTableCol(int tableIndex) {
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// Ҫ���ı��
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// ����������
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch col = Dispatch.get(cols, "Last").toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}	
	/**
	 * 
	 * <p>���ƣ�autoFitTable</p>
	 * <p>˵��������Զ���Ӧ</p>
	 * <p>������ �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p></p>
	 */
	public void autoFitTable() {
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		int count = Dispatch.get(tables, "Count").toInt();
		for (int i = 0; i < count; i++) {
			Dispatch table = Dispatch.call(tables, "Item", new Variant(i + 1))
					.toDispatch();
			Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
			Dispatch.call(cols, "AutoFit");
		}
	}

    /**
     * 
     * <p>���ƣ�PutTxtToCell</p>
     * <p>˵������ָ���ĵ�Ԫ������д����</p>
     * <p>������@param table
     * <p>������@param cellRowIdx
     * <p>������@param cellColIdx
     * <p>������@param txt �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     */
    public void PutTxtToCell(Dispatch table, int cellRowIdx, int cellColIdx,   
            String txt) {   
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),   
                new Variant(cellColIdx)).toDispatch();   
        Dispatch.call(cell, "Select");   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
        Dispatch.put(selection, "Text", txt);   
    }   
    /**
     * 
     * <p>���ƣ�PutTxtToCell</p>
     * <p>˵������ָ���ĵ�Ԫ������д����</p>
     * <p>������@param table
     * <p>������@param cellRowIdx
     * <p>������@param cellColIdx
     * <p>������@param txt �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param table
     * <p>@param cellRowIdx
     * <p>@param cellColIdx
     * <p>@param txt</p>
     */ 
    public void putTxtToCell(int tableIndex, int cellRowIdx, int cellColIdx,   
            String txt) {   
        // ���б��   
        Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
        // Ҫ���ı��   
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))   
                .toDispatch();   
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),   
                new Variant(cellColIdx)).toDispatch();   
        Dispatch.call(cell, "Select");   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
        Dispatch.put(selection, "Text", txt);   
    }   
    /**
     * 
     * <p>���ƣ�getTxtFromCell</p>
     * <p>˵������ȡָ�������ָ��λ�õı���е����ݣ����Ҹ��Ƶ����а�</p>
     * <p>������@param tableIndex
     * <p>������@param cellRowIdx
     * <p>������@param cellColIdx
     * <p>������@return �趨�ļ�</p>
     * <p>����ֵ��String ��������</p>
     */
	public String getTxtFromCell(int tableIndex, int cellRowIdx, int cellColIdx) {
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// Ҫ���ı��
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),new Variant(cellColIdx)).toDispatch();
		Dispatch Range = Dispatch.get(cell, "Range").toDispatch();
//		System.out.println(Dispatch.get(Range, "Text").toString().substring(0, Dispatch.get(Range, "Text").toString().length()-2));
		String a=Dispatch.get(Range, "Text").toString().substring(0, Dispatch.get(Range, "Text").toString().length()-2);
		return a;

	}

    /**
     * 
     * <p>���ƣ�mergeCell</p>
     * <p>˵�����ϲ�������Ԫ��</p>
     * <p>������@param cell1
     * <p>������@param cell2 �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param cell1
     * <p>@param cell2</p>
     */
    public void mergeCell(Dispatch cell1, Dispatch cell2) {   
        Dispatch.call(cell1, "Merge", cell2);   
    }   
    /**
     * 
     * <p>���ƣ�mergeCell</p>
     * <p>˵�����ϲ�(row1,col1)(row2,col2)������ȷ���ľ��θ��ǵ����е�Ԫ��</p>
     * <p>������@param table
     * <p>������@param row1	��һ����Ԫ��row
     * <p>������@param col1	��һ����Ԫ��col
     * <p>������@param row2	�ڶ�����Ԫ��row
     * <p>������@param col2 	�ڶ�����Ԫ��col�趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     */
    public void mergeCell(Dispatch table, int row1, int col1, int row2, int col2) {   
        Dispatch cell1 = Dispatch.call(table, "Cell", new Variant(row1),   
                new Variant(col1)).toDispatch();   
        Dispatch cell2 = Dispatch.call(table, "Cell", new Variant(row2),   
                new Variant(col2)).toDispatch();   
        mergeCell(cell1, cell2);   
    }   
    /**
     * 
     * <p>���ƣ�mergeCellTest</p>
     * <p>˵�������Ժϲ���Ԫ���ʵ������</p>
     * <p>������ �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p></p>
     */
    public void mergeCellTest() {   
        Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
        int tableCount = Dispatch.get(tables, "Count").getInt(); // document�еı������   
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableCount))   
                .toDispatch();// �ĵ������һ��table   
        mergeCell(table, 1, 1, 1, 2);// ��table ��x=1,y=1 ��x=1,y=2��������Ԫ��ϲ�   
    }   
    /**
     * 
     * <p>���ƣ�moveUp</p>
     * <p>˵������������ƶ�</p>
     * <p>������@param pos �����ƶ��ľ����趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param pos</p>
     */
    public void moveUp(int pos) {   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
        for (int i = 0; i < pos; i++) {   
            Dispatch.call(selection, "MoveUp");   
        }   
    }
    /**
     * 
     * <p>���ƣ�moveDown</p>
     * <p>˵������������ƶ�</p>
     * <p>������@param pos �����ƶ��ľ����趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param pos</p>
     */
    public void moveDown(int pos) {   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
        for (int i = 0; i < pos; i++) {   
            Dispatch.call(selection, "MoveDown");   
        }   
    } 
    /**
     * 
     * <p>���ƣ�moveLeft</p>
     * <p>˵������������ƶ�</p>
     * <p>������@param pos �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param pos</p>
     */
    public void moveLeft(int pos) {
    	Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
        for (int i = 0; i < pos; i++) {   
            Dispatch.call(selection, "MoveLeft");   
        } 
    }
    /**
     * 
     * <p>���ƣ�moveRight</p>
     * <p>˵������������ƶ�</p>
     * <p>������@param pos �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param pos</p>
     */
    public void moveRight(int pos) {
    	Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
        for (int i = 0; i < pos; i++) {   
            Dispatch.call(selection, "MoveRight");   
        } 
    }
    /**
     * 
     * <p>���ƣ�moveStart</p>
     * <p>˵��������ƶ����ļ��ײ�</p>
     * <p>������ �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p></p>
     */
    public void moveStart() {
    	Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
    	Dispatch.call(selection, "HomeKey", new Variant(6));
    }
    /**
     * 
     * <p>���ƣ�moveEnd</p>
     * <p>˵��������ƶ����ļ��ײ�</p>
     * <p>������ �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p></p>
     */
    public void moveEnd() {
    	Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
    	Dispatch.call(selection, "EndKey", new Variant(6));
    }
    /** */  
    /**  
     * ��ѡ�����ݻ����㿪ʼ�����ı�  
     *  
     * @param toFindText  
     *            Ҫ���ҵ��ı�  
     * @return boolean true-���ҵ���ѡ�и��ı���false-δ���ҵ��ı�  
     */  
    public boolean find(String toFindText) {   
        if (toFindText == null || toFindText.equals(""))   
            return false;   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
        // ��selection����λ�ÿ�ʼ��ѯ   
        Dispatch find = Dispatch.call(selection, "Find").toDispatch();   
        // ����Ҫ���ҵ�����   
        Dispatch.put(find, "Text", toFindText);   
        // ��ǰ����   
        Dispatch.put(find, "Forward", "True");   
        // ���ø�ʽ   
        Dispatch.put(find, "Format", "false");   
        // ��Сдƥ��   
        Dispatch.put(find, "MatchCase", "True");   
        // ȫ��ƥ��   
        Dispatch.put(find, "MatchWholeWord", "True");   
        // ���Ҳ�ѡ��   
        return Dispatch.call(find, "Execute").getBoolean();   
    }  
    /**
     * 
     * <p>���ƣ�replaceText</p>
     * <p>˵������ѡ��ѡ�������趨Ϊ�滻�ı�</p>
     * <p>������@param toFindText �����ַ���
     * <p>������@param newText	Ҫ�滻������  
     * <p>������@return �趨�ļ�</p>
     * <p>����ֵ��boolean ��������   true:�滻�ɹ�;false:δ�ҵ���Ӧ�ַ���</p>
     */
    public boolean replaceText(String toFindText, String newText) {   
        if (!find(toFindText))   
            return false;   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
        Dispatch.put(selection, "Text", newText);   
        return true;   
    }   
    /**
     * 
     * <p>���ƣ�replaceAllText</p>
     * <p>˵�����ҵ������������ı������滻����Ϊ�µ��ı�</p>
     * <p>������@param toFindText	Դ�ı�
     * <p>������@param newText 	Ŀ���ı�	�趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param toFindText
     * <p>@param newText</p>
     */
    public void replaceAllText(String toFindText, String newText) {
    	Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
    	while (find(toFindText)) {
    		Dispatch.put(selection, "Text",newText);
    		Dispatch.call(selection, "MoveRight");
    	}
    }
    /**
     * 
     * <p>���ƣ�replaceTextWithImage</p>
     * <p>˵������ָ��·����ͼƬ�滻�ĵ��е�ǰλ��֮���ҵ��ĵ�һ���ı�����</p>
     * <p>������@param toFindText
     * <p>������@param imagePath
     * <p>������@return �趨�ļ�</p>
     * <p>����ֵ��boolean ��������</p>
     * <p>@param toFindText
     * <p>@param imagePath
     * <p>@return</p>
     */
	public boolean replaceTextWithImage(String toFindText, String imagePath) {
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���
		Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
				"AddPicture", imagePath);
		return true;
	}
	/**
	 * 
	 * <p>���ƣ�replaceAllTextWithImageAfterCurrentPos</p>
	 * <p>˵������ָ��·����ͼƬ�滻�ĵ��е����λ��֮�����в��ҵ����ı�����</p>
	 * <p>������@param toFindText
	 * <p>������@param imagePath �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param toFindText
	 * <p>@param imagePath</p>
	 */
	public void replaceAllTextWithImageAfterCurrentPos(String toFindText, String imagePath) {
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���
		while (find(toFindText)) {
			Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
					"AddPicture", imagePath);
			Dispatch.call(selection, "MoveRight");
		}
	}
	/**
	 * 
	 * <p>���ƣ�insertImage</p>
	 * <p>˵�����ڵ�ǰλ�ò���ͼƬ</p>
	 * <p>������@param imagePath 	ͼƬ·��	�趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param imagePath</p>
	 */
	public void insertImage(String imagePath) {
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���
		Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
				"AddPicture", imagePath);
	}
	/**
	 * 
	 * <p>���ƣ�findTextAndCopy</p>
	 * <p>˵�����ӹ�굱ǰλ���ҵ���һ���ı����ҽ��临�Ƶ�������</p>
	 * <p>������@param toCopyText �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param toCopyText</p>
	 */
	public void findTextAndCopy(String toCopyText) {
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���
		moveStart();
		if (this.find(toCopyText)) {
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			Dispatch.call(textRange, "Copy");
		}
	}
	/**
	 * 
	 * <p>���ƣ�findTextAndPaste</p>
	 * <p>˵�����ҵ�ָ���ı������ü��а���ı��滻</p>
	 * <p>������@param pos �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param pos</p>
	 */
	public void findTextAndPaste(String pos) {
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���
		moveStart();
		if (this.find(pos)) {
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			Dispatch.call(textRange, "Paste");
		}
	}
	
	public	void	paste(){
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���
		Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
		Dispatch.call(textRange, "Paste");
	}
	/**
	 * 
	 * <p>���ƣ�insertEnter</p>
	 * <p>˵��������س�</p>
	 * <p>������ �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p></p>
	 */
	public	void	insertEnter(){
		InsertText("\n");
	}
	
	/**
	 * 
	 * <p>���ƣ�copyParagraphFromAnotherDoc</p>
	 * <p>˵�����ڵ�ǰ�ĵ�ĩβ����������һ���ĵ��еĶ���</p>
	 * <p>������@param anotherDocPath	��һ���ĵ��Ĵ���·��
	 * <p>������@param paragraphIndex �������Ķ�������һ���ĵ��е����(��1��ʼ)</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param anotherDocPath
	 * <p>@param paragraphIndex</p>
	 */
	public void copyParagraphFromAnotherDoc(String anotherDocPath,
			int paragraphIndex) {
		Dispatch wordContent = Dispatch.get(document, "Content").toDispatch(); // ȡ�õ�ǰ�ĵ�������
		Dispatch.call(wordContent, "InsertAfter", "$selection$");// �����������λ�����
		this.moveStart();
		if(this.find("$selection$")){
			copyParagraphFromAnotherDoc(anotherDocPath, paragraphIndex,
					"selection");
		}

	}
	/**
	 * 
	 * <p>���ƣ�copyParagraphFromAnotherDoc</p>
	 * <p>˵�����ڵ�ǰ�ĵ�ָ����λ�ÿ���������һ���ĵ��еĶ���</p>
	 * <p>������@param anotherDocPath	��һ���ĵ��Ĵ���·��
	 * <p>������@param paragraphIndex	�������Ķ�������һ���ĵ��е����(��1��ʼ)
	 * <p>������@param pos ��ǰ�ĵ�ָ����λ��</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param anotherDocPath
	 * <p>@param paragraphIndex
	 * <p>@param pos</p>
	 */
	public void copyParagraphFromAnotherDoc(String anotherDocPath,
			int paragraphIndex, String pos) {
		Dispatch doc2 = null;
		Dispatch documents	=	this.MsWordApp.getProperty("Documents").toDispatch();
		Dispatch selection 	= 	Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���
		try {
			doc2 = Dispatch.call(documents, "Open", anotherDocPath)
					.toDispatch();
			Dispatch paragraphs = Dispatch.get(doc2, "Paragraphs").toDispatch();
			Dispatch paragraph = Dispatch.call(paragraphs, "Item",
					new Variant(paragraphIndex)).toDispatch();
			Dispatch range = Dispatch.get(paragraph, "Range").toDispatch();
			Dispatch.call(range, "Copy");
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			Dispatch.call(textRange, "Paste");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Dispatch.call(doc2,"Close", new Variant(0));
		}
	}
	/**
	 * 
	 * <p>���ƣ�copyTableFromAnotherDoc</p>
	 * <p>˵�����ڵ�ǰ�ĵ�ָ����λ�ÿ���������һ���ĵ��еı��</p>
	 * <p>������@param anotherDocPath		��һ���ĵ��Ĵ���·��
	 * <p>������@param tableIndex			�������ı������һ���ĵ��е����(��1��ʼ)
	 * <p>������@param pos �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 */
	public void copyTableFromAnotherDoc(String anotherDocPath, int tableIndex) {
		Dispatch doc2 = null;
		Dispatch documents	=	this.MsWordApp.getProperty("Documents").toDispatch();
		Dispatch selection 	= 	Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���
		try {
			doc2 = Dispatch.call(documents, "Open", anotherDocPath)
					.toDispatch();
			Dispatch tables = Dispatch.get(doc2, "Tables").toDispatch();
			Dispatch table = Dispatch.call(tables, "Item",
					new Variant(tableIndex)).toDispatch();
			Dispatch range = Dispatch.get(table, "Range").toDispatch();
			Dispatch.call(range, "Copy");
				Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
				Dispatch.call(textRange, "Paste");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (doc2 != null) {
				Dispatch.call(doc2, "Close", new Variant(0));
				doc2 = null;
			}
		}
	}
	

	
	/**
	 * 
	 * <p>���ƣ�setFont</p>
	 * <p>˵�������õ�ǰѡ�����ݵ�����</p>
	 * <p>������@param bold		����
	 * <p>������@param italic		б��
	 * <p>������@param underLine	�»���
	 * <p>������@param colorSize	������ɫ
	 * <p>������@param size		�����С
	 * <p>������@param name 		��������</p>
	 * <p>����ֵ��void ��������</p>
	 */
	public void setFont(boolean bold, boolean italic, boolean underLine,
			String colorSize, String size, String name) {
		Dispatch selection 	= 	Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���
		Dispatch font = Dispatch.get(selection, "Font").toDispatch();
		Dispatch.put(font, "Name", new Variant(name));
		Dispatch.put(font, "Bold", new Variant(bold));
		Dispatch.put(font, "Italic", new Variant(italic));
		Dispatch.put(font, "Underline", new Variant(underLine));
		Dispatch.put(font, "Color", colorSize);
		Dispatch.put(font, "Size", size);
	}
	/**
	 * 
	 * <p>���ƣ�printFile</p>
	 * <p>˵������ӡ�ļ�</p>
	 * <p>������ �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p></p>
	 */
	public void printFile() {   
        Dispatch.call(document, "PrintOut");   
    }   
	/**
	 * 
	 * <p>���ƣ�save</p>
	 * <p>˵�������浱ǰ�ĵ�</p>
	 * <p>������ �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p></p>
	 */
    public void save() {   
        Dispatch.call(document, "Save");   
    }   
    /**
     * 
     * <p>���ƣ�saveFileAs</p>
     * <p>˵�����ļ����Ϊ</p>
     * <p>������@param filename �ļ����Ϊ���ļ������ɴ�·����</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param filename</p>
     */
    public void saveFileAs(String filename) {   
        Dispatch.call(document, "SaveAs", filename);   
    }   
    /**
     * 
     * <p>���ƣ�closeDocument</p>
     * <p>˵�����ر��ļ�</p>
     * <p>������@param Para 	0��������ر�	1:����ر�		other:��ʾ�Ƿ񱣴�</p>
     * <p>����ֵ��void ��������</p>
     * <p>@param Para</p>
     */
    public void closeDocument(int	Para) {   
    	if(Para!=0&&Para!=-1&&Para!=-2){
    		Para	=	0;
    	}
        // Close the document without saving changes   
        // 0 = wdDoNotSaveChanges   
        // -1 = wdSaveChanges   
        // -2 = wdPromptToSaveChanges   
        Dispatch.call(document, "Close", new Variant(Para));   
        document = null;   
    }   
    /**
     * 
     * <p>���ƣ�closeWord</p>
     * <p>˵�����ر�word����</p>
     * <p>������ �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p></p>
     */
    public void closeWord() {   
        Dispatch.call(MsWordApp, "Quit");   
        MsWordApp = null;   
        document = null;   
    }   
    /**
     * 
     * <p>���ƣ�setLocation</p>
     * <p>˵��������wordApp�򿪺󴰿ڵ�λ��   </p>
     * <p>������ �趨�ļ�</p>
     * <p>����ֵ��void ��������</p>
     * <p></p>
     */
    public void setLocation() {   
        Dispatch activeWindow = Dispatch.get(MsWordApp, "Application")   
                .toDispatch();   
        Dispatch.put(activeWindow, "WindowState", new Variant(1)); // 0=default   
        // 1=maximize   
        // 2=minimize   
        Dispatch.put(activeWindow, "Top", new Variant(0));   
        Dispatch.put(activeWindow, "Left", new Variant(0));   
        Dispatch.put(activeWindow, "Height", new Variant(600));   
        Dispatch.put(activeWindow, "width", new Variant(800));   
    }   
    
    /**
    * 
    * <p>���ƣ�gettableColumn</p>
    * <p>˵������ȡ�������</p>
    * <p>������@param anotherDocPath
    * <p>������@param tableIndex �趨�ļ�</p>
    * <p>����ֵ��void ��������</p>
    * <p>@param anotherDocPath
    * <p>@param tableIndex</p>
    * <p>author:lijuan</p>
    */
   	public int  gettableColumn() {
           Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
           int tableCount = Dispatch.get(tables, "Count").getInt(); // document�еı������   
           Dispatch table = Dispatch.call(tables, "Item", new Variant(tableCount))   
                   .toDispatch();// �ĵ������һ��table
           Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
   		int colCount = Dispatch.get(cols, "Count").getInt();
           return colCount;
         
   	}
   	
   /**
    * 	
    * <p>���ƣ�gettableRow</p>
    * <p>˵����TODO(������һ�仰�����������������)</p>
    * <p>������@return �趨�ļ�</p>
    * <p>����ֵ��int ��������</p>
    * <p>@return</p>
    * author:lijuan
    */
   	
   	public int  gettableRow() {
           Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
           int tableCount = Dispatch.get(tables, "Count").getInt(); // document�еı������   
           Dispatch table = Dispatch.call(tables, "Item", new Variant(tableCount))   
                   .toDispatch();// �ĵ������һ��table
           Dispatch rows = Dispatch.get(table, "Rows").toDispatch(); // �˱�������У�   
           int rowCount = Dispatch.get(rows, "Count").getInt();
           return rowCount;
           }
   	
   	
   	/**
   	 * 
   	 * <p>���ƣ�getCols</p>
   	 * <p>˵������ȡĳһ�е�����</p>
   	 * <p>������@return �趨�ļ�</p>
   	 * <p>����ֵ��int ��������</p>
   	 * <p>@return</p>
   	 * author:lijuan
   	 */
   	public int getCols(int a){
        Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
        int tableCount = Dispatch.get(tables, "Count").getInt(); // document�еı������   
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableCount))   
                .toDispatch();// �ĵ������һ��table
        Dispatch rows = Dispatch.get(table, "Rows").toDispatch(); // �˱��������
        int cellCount=0;
            Dispatch col = Dispatch.call(rows, "Item", new Variant(a))   
                    .toDispatch();   
            Dispatch cells = Dispatch.get(col, "Cells").toDispatch();// ��ǰ���е�Ԫ��   
            cellCount = Dispatch.get(cells, "Count").getInt();// ��ǰ���е�Ԫ���� ʵ�������������row
   		return cellCount;
   	}
 
   	/**
   	 * 
   	 * <p>���ƣ�getRowContent</p>
   	 * <p>˵������ȡ�е�����</p>
   	 * <p>������@param a
   	 * <p>������@return �趨�ļ�</p>
   	 * <p>����ֵ��String ��������</p>
   	 * <p>@param a
   	 * <p>@return</p>
   	 * author��lijuan
   	 */
   	public String getRowContent(int a){
   	   Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
       int tableCount = Dispatch.get(tables, "Count").getInt(); // document�еı������   
       Dispatch table = Dispatch.call(tables, "Item", new Variant(tableCount))   
               .toDispatch();// �ĵ������һ��table
       Dispatch rows = Dispatch.get(table, "Rows").toDispatch(); // �˱��������
       int rowCount = Dispatch.get(rows, "Count").getInt();// һ���ж����� ʵ���������==column   
       int cellCount=0;
           Dispatch col = Dispatch.call(rows, "Item", new Variant(a))   
                   .toDispatch();   
           Dispatch cells = Dispatch.get(col, "Cells").toDispatch();// ��ǰ���е�Ԫ��   
           cellCount = Dispatch.get(cells, "Count").getInt();// ��ǰ���е�Ԫ���� ʵ�������������row
           String content="";
   	   for (int j = 1; j <= cellCount; j++) {// ÿһ���еĵ�Ԫ����   
    //       Dispatch cell = Dispatch.call(cells, "Item", new  Variant(j)).toDispatch(); //��ǰ��Ԫ��   
      //     Dispatch.call(cell, "Select");//ѡ�е�ǰ��Ԫ��   
       	Dispatch cell = Dispatch.call(table, "Cell", new Variant(a),new Variant(j)).toDispatch();
		Dispatch Range = Dispatch.get(cell, "Range").toDispatch();
		content=Dispatch.get(Range,"Item").getString();
       }   
   		return content;
   		
   	} 
   	
   	/**
   	 * 
   	 * <p>���ƣ�CreateDoc</p>
   	 * <p>˵���������µ�word�ĵ�</p>
   	 * <p>������ �趨�ļ�</p>
   	 * <p>����ֵ��void ��������</p>
   	 * <p></p>
   	 * author:lijuan
   	 */
   	public void CreateDoc(String name,String path){
   		JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
		wordBean.SetVisible(WordBeanProperty.WORD_VISABLE);
		wordBean.CreateNewDocument();// ����һ�����ĵ�
		wordBean.setLocation();// ���ô򿪺󴰿ڵ�λ��
		wordBean.saveFileAs(path+'\\'+name+".doc");
		wordBean.closeDocument(0);
		wordBean.closeWord();
   	}
   	
   	
   	/**
   	 * 
   	 * <p>���ƣ�CountTable</p>
   	 * <p>˵��������������TODO(������һ�仰�����������������)</p>
   	 * <p>������@param DocPath
   	 * <p>������@return �趨�ļ�</p>
   	 * <p>����ֵ��String ��������</p>
   	 * <p>@param DocPath
   	 * <p>@return</p>
   	 *  author:lijuan
   	 */
   	public int CountTable(){
   	   Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
       int tableCount = Dispatch.get(tables, "Count").getInt(); 
   		return tableCount;
   	}
   	
   	/**
   	 * 
   	 * <p>���ƣ�wordToHtml</p>
   	 * <p>˵������wordת��ΪhtmlTODO(������һ�仰�����������������)</p>
   	 * <p>������@param docfile
   	 * <p>������@param htmlfile �趨�ļ�</p>
   	 * <p>����ֵ��void ��������</p>
   	 * <p>@param docfile
   	 * <p>@param htmlfile</p>
   	 *   author:lijuan
   	 */
	public  void wordToHtml(String docfile, String htmlfile) {
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
	
	/**
	 * 
	 * <p>���ƣ�getContextFromAnotherFileTableCell</p>
	 * <p>˵��������һ���ĵ��еĵ�tableIndex������еĵ�row�е�col�и��Ʊ�����ݲ���ճ������ǰ�ĵ�</p>
	 * <p>������@param anotherFileName
	 * <p>������@param tableIndex
	 * <p>������@param row
	 * <p>������@param col �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param anotherFileName
	 * <p>@param tableIndex
	 * <p>@param row
	 * <p>@param col</p>
	 */
	public	void getContextFromAnotherFileTableCell(String anotherFileName,int	tableIndex,int row,int col){
		Dispatch doc2 = null;
		Dispatch documents	=	this.MsWordApp.getProperty("Documents").toDispatch();
		Dispatch selection 	= 	Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���
		try {
			doc2 = Dispatch.call(documents, "Open", anotherFileName)
					.toDispatch();
			
			
			Dispatch tables = Dispatch.get(doc2, "Tables").toDispatch();
			// Ҫ���ı��
			Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
			Dispatch cell = Dispatch.call(table, "Cell", new Variant(row),new Variant(col)).toDispatch();
			Dispatch Range = Dispatch.get(cell, "Range").toDispatch();
//			System.out.println(Dispatch.get(Range, "Text").toString().substring(0, Dispatch.get(Range, "Text").toString().length()-2));
			//String a=Dispatch.get(Range, "Text").toString().substring(0, Dispatch.get(Range, "Text").toString().length()-2);
			//Dispatch Text=Dispatch.get(Range, "Text").toDispatch();
			Dispatch.call(Range, "Copy");
				Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
				Dispatch.call(textRange, "Paste");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Dispatch.call(doc2,"Close", new Variant(0));
		}
	}
	
	/**
	 * 
	 * <p>���ƣ�disableTableBorder</p>
	 * <p>˵��������index�����ı߿�����Ϊwidth</p>
	 * <p>������@param index
	 * <p>������@param width �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param index
	 * <p>@param width</p>
	 */
	public void setTableBorder(int index,int width){

		MsWordApp.setProperty("Visible", new Variant(true));

		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		Dispatch table = Dispatch.call(tables, "Item", new Variant(index)).toDispatch();
		      for(int b=1;b<=6;b++){ 
		             Dispatch oBorders = Dispatch.call(table, "Borders", 0-b).toDispatch();
		             Dispatch.put(oBorders, "LineStyle", new Variant(width));
		      } 

	}
}
