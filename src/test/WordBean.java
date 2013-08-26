
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	WordBean.java
 * | ������test
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-27 ����3:51:41
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-27 ����3:51:41
 * |------------------------------------------------------------------------------------ 
 */
package test;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


  /**
 * <p>������test.WordBean </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class WordBean {
	private ActiveXComponent MsWordApp = null;   
    // ������д����word �ĵ�   
    private Dispatch document = null;   
    public WordBean() {   
        // Open Word if we\'ve not done it already   
        if (MsWordApp == null) {   
            MsWordApp = new ActiveXComponent("Word.Application");   
        }   
    }
 // �����Ƿ���ǰ̨�� word ���� ��   
    public void setVisible(boolean visible) {   
        MsWordApp.setProperty("Visible", new Variant(visible));   
        // ��һ��������ͬ   
        // Dispatch.put(MsWordApp, "Visible", new Variant(visible));   
    }   
    // ����һ�����ĵ�   
    public void createNewDocument() {   
        // Find the Documents collection object maintained by Word   
        // documents��ʾword�������ĵ����ڣ���word�Ƕ��ĵ�Ӧ�ó���   
        Dispatch documents = Dispatch.get(MsWordApp, "Documents").toDispatch();   
        // Call the Add method of the Documents collection to create   
        // a new document to edit   
        document = Dispatch.call(documents, "Add").toDispatch();   
    }   
    // ��һ�����ڵ�word�ĵ�,����document ���� ������   
    public void openFile(String wordFilePath) {   
        // Find the Documents collection object maintained by Word   
        // documents��ʾword�������ĵ����ڣ���word�Ƕ��ĵ�Ӧ�ó���   
        Dispatch documents = Dispatch.get(MsWordApp, "Documents").toDispatch();   
        document = Dispatch.call(documents, "Open", wordFilePath,   
                new Variant(true)/* �Ƿ����ת��ConfirmConversions */,   
                new Variant(false)/* �Ƿ�ֻ�� */).toDispatch();   
        // document = Dispatch.invoke(documents, "Open", Dispatch.Method,   
        // new Object[] { wordFilePath, new Variant(true),   
        // new Variant(false)   
        // }, new int[1]).toDispatch();   
    }   
    // �� document �в����ı�����   
    public void insertText(String textToInsert) {   
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
    // ���ĵ������ һ��ͼƬ��   
    public void insertJpeg(String jpegFilePath) {   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch();   
        Dispatch image = Dispatch.get(selection, "InLineShapes").toDispatch();   
        Dispatch.call(image, "AddPicture", jpegFilePath);   
    }   
 // ����Ĵ���,�����ʽ�����ı�   
    public void insertFormatStr(String text) {   
        Dispatch wordContent = Dispatch.get(document, "Content").toDispatch(); // ȡ��word�ļ�������   
        Dispatch.call(wordContent, "InsertAfter", text);// ����һ�����䵽���   
        Dispatch paragraphs = Dispatch.get(wordContent, "Paragraphs").toDispatch(); // ���ж���   
        int paragraphCount = Dispatch.get(paragraphs, "Count").getInt();// һ���Ķ�����  
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
 // word ���ڶԱ����б�����ʱ�� �������к��� ��column ��cell   
    // �����±��1��ʼ   
    public void insertTable(String tableTitle, int row, int column) {   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
        Dispatch.call(selection, "TypeText", tableTitle); // д��������� // �������   
        Dispatch.call(selection, "TypeParagraph"); // ��һ�ж���   
        Dispatch.call(selection, "TypeParagraph"); // ��һ�ж���   
        Dispatch.call(selection, "MoveDown"); // �α�����һ��   
        // �������   
        Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
        // int count = Dispatch.get(tables,   
        // "Count").changeType(Variant.VariantInt).getInt(); // document�еı������   
        // Dispatch table = Dispatch.call(tables, "Item", new Variant(   
        // 1)).toDispatch();//�ĵ��е�һ�����   
        Dispatch range = Dispatch.get(selection, "Range").toDispatch();// /��ǰ���λ�û���ѡ�е�����   
        Dispatch newTable = Dispatch.call(tables, "Add", range,   
                new Variant(row), new Variant(column), new Variant(1))   
                .toDispatch(); // ����row,column,��������   
        Dispatch cols = Dispatch.get(newTable, "Columns").toDispatch(); // �˱�������У�   
        int colCount = Dispatch.get(cols, "Count").getInt();// һ���ж����� ʵ���������==column   
        System.out.println(colCount + "��");   
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
                putTxtToCell(newTable, j, i, "��" + j + "�У���" + i + "��");// �������ľ��������ͬ   
            }   
        }   
    }   
    /** */  
    /**  
     * ��ָ���ĵ�Ԫ������д����  
     *  
     * @param tableIndex  
     * @param cellRowIdx  
     * @param cellColIdx  
     * @param txt  
     */  
    public void putTxtToCell(Dispatch table, int cellRowIdx, int cellColIdx,   
            String txt) {   
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),   
                new Variant(cellColIdx)).toDispatch();   
        Dispatch.call(cell, "Select");   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
        Dispatch.put(selection, "Text", txt);   
    }   
    /** */  
    /**  
     * ��ָ���ĵ�Ԫ������д����  
     *  
     * @param tableIndex  
     * @param cellRowIdx  
     * @param cellColIdx  
     * @param txt  
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
    // �ϲ�������Ԫ��   
    public void mergeCell(Dispatch cell1, Dispatch cell2) {   
        Dispatch.call(cell1, "Merge", cell2);   
    }   
    public void mergeCell(Dispatch table, int row1, int col1, int row2, int col2) {   
        Dispatch cell1 = Dispatch.call(table, "Cell", new Variant(row1),   
                new Variant(col1)).toDispatch();   
        Dispatch cell2 = Dispatch.call(table, "Cell", new Variant(row2),   
                new Variant(col2)).toDispatch();   
        mergeCell(cell1, cell2);   
    }   
    public void mergeCellTest() {   
        Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
        int tableCount = Dispatch.get(tables, "Count").getInt(); // document�еı������   
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableCount))   
                .toDispatch();// �ĵ������һ��table   
        mergeCell(table, 1, 1, 2, 2);// ��table ��x=1,y=1 ��x=1,y=2��������Ԫ��ϲ�   
    }   
    // ========================================================   
    /** */  
    /**  
     * ��ѡ�������ݻ������������ƶ�  
     *  
     * @param pos  
     *            �ƶ��ľ���  
     */  
    public void moveUp(int pos) {   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // ����������Ҫ�Ķ���   
        for (int i = 0; i < pos; i++) {   
            // MoveDown MoveLeft moveRight   
            // moveStart ( Dispatch.call(selection, "HomeKey", new Variant(6));   
            // )   
            // moveEnd Dispatch.call(selection, "EndKey", new Variant(6));   
            Dispatch.call(selection, "MoveUp");   
        }   
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
        Dispatch.put(find, "Format", "True");   
        // ��Сдƥ��   
        Dispatch.put(find, "MatchCase", "True");   
        // ȫ��ƥ��   
        Dispatch.put(find, "MatchWholeWord", "True");   
        // ���Ҳ�ѡ��   
        return Dispatch.call(find, "Execute").getBoolean();   
    }  

    public static	void main(String[]args){ 
    	WordBean	wb	=	new	WordBean();
    	wb.setVisible(true);
    	wb.createNewDocument();
    	wb.insertFormatStr("hello xiaokai");
    	//wb.insertJpeg("d:\\TestBlob.jpg");
    }
}
