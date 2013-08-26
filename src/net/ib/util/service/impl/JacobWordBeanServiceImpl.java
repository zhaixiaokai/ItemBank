
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	JacobWordBean.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-27 下午5:03:02
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-27 下午5:03:02
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import net.ib.config.WordBeanProperty;

import org.apache.log4j.Logger;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


  /**
 * <p>类名：net.ib.util.service.impl.JacobWordBean </p>
 * <p>描述：本类定义了Jacob操作Word的基本方法</p>
 * <p></p>
 */
public class JacobWordBeanServiceImpl {
	private static Logger logger = Logger.getLogger(JacobWordBeanServiceImpl.class);
	private ActiveXComponent MsWordApp = null;   
    // 代表进行处理的word 文档   
    private Dispatch document = null;   
    public JacobWordBeanServiceImpl() {   
        // Open Word if we\'ve not done it already   
        if (MsWordApp == null) {   
            MsWordApp = new ActiveXComponent("Word.Application");   
        }   
    }
    /**
     * 
     * <p>名称：SetVisible</p>
     * <p>说明：设置是否在前台打开 word 程序</p>
     * <p>参数：@param visible 设定文件 true:在前台打开word程序  false：在后台运行word程序</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param visible</p>
     */
    public void SetVisible(boolean visible) {   
        MsWordApp.setProperty("Visible", new Variant(visible));   
        // 这一句作用相同   
        // Dispatch.put(MsWordApp, "Visible", new Variant(visible));   
    }   
    /**
     * 
     * <p>名称：CreateNewDocument</p>
     * <p>说明：创建一个新文档</p>
     * <p>参数： 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p></p>
     */
    public void CreateNewDocument() {   
        // Find the Documents collection object maintained by Word   
        // documents表示word的所有文档窗口，（word是多文档应用程序）   
        Dispatch documents = Dispatch.get(MsWordApp, "Documents").toDispatch();   
        document = Dispatch.call(documents, "Add").toDispatch();   
    }   
    /**
     * 
     * <p>名称：OpenFile</p>
     * <p>说明：打开一个存在的word文档,并用document引用它  </p>
     * <p>参数：@param wordFilePath 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param wordFilePath</p>
     */
    public void OpenFile(String wordFilePath) {   
        Dispatch documents = Dispatch.get(MsWordApp, "Documents").toDispatch();   
        document = Dispatch.call(documents, "Open", wordFilePath,   
        		//是否进行转换ConfirmConversions 
                new Variant(true),   
                //是否只读
                new Variant(false)).toDispatch();   
    }   
    /**
     * 
     * <p>名称：InsertText</p>
     * <p>说明：向 document 中插入文本内容  </p>
     * <p>参数：@param textToInsert 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param textToInsert</p>
     */
    public void InsertText(String textToInsert) {   
        // Get the current selection within Word at the moment.   
        // a new document has just been created then this will be at   
        // the top of the new doc 获得选 中的内容，如果是一个新创建的文件，因里面无内容，则光标应处于文件开头处   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch();   
        // 取消选中,应该就是移动光标 ，否则 新添加的内容会覆盖选中的内容   
        Dispatch.call(selection, "MoveRight", new Variant(1), new Variant(1));   
        // Put the specified text at the insertion point   
        Dispatch.put(selection, "Text", textToInsert);   
        // 取消选中,应该就是移动光标   
        Dispatch.call(selection, "MoveRight", new Variant(1), new Variant(1));   
    }   
    /**
     * 
     * <p>名称：InsertJpeg</p>
     * <p>说明：向文档中添加一个图片</p>
     * <p>参数：@param jpegFilePath 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param jpegFilePath</p>
     */
    public void InsertJpeg(String jpegFilePath) {   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch();   
        Dispatch image = Dispatch.get(selection, "InLineShapes").toDispatch();   
        Dispatch.call(image, "AddPicture", jpegFilePath);   
    }   
    /**
     * 
     * <p>名称：InsertFormatStr</p>
     * <p>说明：段落的处理,插入格式化的文本</p>
     * <p>参数：@param text 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param text</p>
     */
	public void InsertFormatStr(String text) {
		// 取得word文件的内容
		Dispatch wordContent = Dispatch.get(document, "Content").toDispatch();
		// 插入一个段落到最后
		Dispatch.call(wordContent, "InsertAfter", text);
		// 所有段落
		Dispatch paragraphs = Dispatch.get(wordContent, "Paragraphs")
				.toDispatch();
		// 一共的段落数
		int paragraphCount = Dispatch.get(paragraphs, "Count").getInt();
		// 找到刚输入的段落，设置格式
		Dispatch lastParagraph = Dispatch.call(paragraphs, "Item",
				new Variant(paragraphCount)).toDispatch(); // 最后一段（也就是刚插入的）
		// Range 对象表示文档中的一个连续范围，由一个起始字符位置和一个终止字符位置定义
		Dispatch lastParagraphRange = Dispatch.get(lastParagraph, "Range")
				.toDispatch();
		Dispatch font = Dispatch.get(lastParagraphRange, "Font").toDispatch();
		Dispatch.put(font, "Bold", new Variant(true)); // 设置为黑体
		Dispatch.put(font, "Italic", new Variant(false)); // 设置为斜体
		Dispatch.put(font, "Name", new Variant("宋体")); //
		Dispatch.put(font, "Size", new Variant(12)); // 小四
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch();
		Dispatch.call(selection, "TypeParagraph");// 插入一个空行
		Dispatch alignment = Dispatch.get(selection, "ParagraphFormat")
				.toDispatch();// 段落格式
		Dispatch.put(alignment, "Alignment", "1"); // (1:置中 2:靠右 3:靠左)
	}

    /**
     * 
     * <p>名称：InsertTable</p>
     * <p>说明：插入表格</p>
     * <p>参数：@param tableTitle
     * <p>参数：@param row
     * <p>参数：@param column 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param tableTitle
     * <p>@param row
     * <p>@param column</p>
     */
    // word 中在对表格进行遍历的时候 ，是先列后行 先column 后cell   
    // 另外下标从1开始  
    public void InsertTable(String tableTitle, int row, int column) {   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象   
        Dispatch.call(selection, "TypeText", tableTitle); // 写入标题内容 // 标题格行   
        Dispatch.call(selection, "TypeParagraph"); // 空一行段落   
        Dispatch.call(selection, "TypeParagraph"); // 空一行段落   
        Dispatch.call(selection, "MoveDown"); // 游标往下一行   
        // 建立表格   
        Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
        Dispatch range = Dispatch.get(selection, "Range").toDispatch();// /当前光标位置或者选中的区域   
        Dispatch newTable = Dispatch.call(tables, "Add", range,   
                new Variant(row), new Variant(column), new Variant(1))   
                .toDispatch(); // 设置row,column,表格外框宽度   
        Dispatch cols = Dispatch.get(newTable, "Columns").toDispatch(); // 此表的所有列，   
        int colCount = Dispatch.get(cols, "Count").getInt();// 一共有多少列 实际上这个数==column   
        logger.debug(colCount + "列");   
        for (int i = 1; i <= colCount; i++) { // 循环取出每一列   
            Dispatch col = Dispatch.call(cols, "Item", new Variant(i))   
                    .toDispatch();   
            Dispatch cells = Dispatch.get(col, "Cells").toDispatch();// 当前列中单元格   
            int cellCount = Dispatch.get(cells, "Count").getInt();// 当前列中单元格数 实际上这个数等于row   
            for (int j = 1; j <= cellCount; j++) {// 每一列中的单元格数   
                // Dispatch cell = Dispatch.call(cells, "Item", new   
                // Variant(j)).toDispatch(); //当前单元格   
                // Dispatch cell = Dispatch.call(newTable, "Cell", new   
                // Variant(j) , new Variant(i) ).toDispatch(); //取单元格的另一种方法   
                // Dispatch.call(cell, "Select");//选中当前单元格   
                // Dispatch.put(selection, "Text",   
                // "第"+j+"行，第"+i+"列");//往选中的区域中填值，也就是往当前单元格填值   
                PutTxtToCell(newTable, j, i, "第" + j + "行，第" + i + "列");// 与上面四句的作用相同   
            }   
        }   
    } 
    /**
     * 
     * <p>名称：addTableRowBefore</p>
     * <p>说明：在指定的表格中指定行前面增加行</p>
     * <p>参数：@param tableIndex		文件中的第N 张表(从1 开始)
     * <p>参数：@param rowIndex 		指定行的序号(从1 开始)</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param tableIndex
     * <p>@param rowIndex</p>
     */
	public void addTableRowBefore(int tableIndex, int rowIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.call(rows, "Item", new Variant(rowIndex))
				.toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}
	/**
	 * 
	 * <p>名称：addTableRowBeforeFirst</p>
	 * <p>说明：在第1 行前增加一行</p>
	 * <p>参数：@param tableIndex 		文档中的第N 张表(从1 开始)</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param tableIndex</p>
	 */
	
	public void addTableRowBeforeFirst(int tableIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.get(rows, "First").toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}
	/**
	 * 
	 * <p>名称：addTableRowBeforeLast</p>
	 * <p>说明：在最后1 行前增加一行</p>
	 * <p>参数：@param tableIndex 	word 文档中的第N 张表(从1 开始)</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param tableIndex</p>
	 */
	public void addTableRowBeforeLast(int tableIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.get(rows, "Last").toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}
	/**
	 * 
	 * <p>名称：addRow</p>
	 * <p>说明：增加一行</p>
	 * <p>参数：@param tableIndex 	文档中的第N 张表(从1 开始)</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param tableIndex</p>
	 */
	public void addRow(int tableIndex) {
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch.call(rows, "Add");
	}
	/**
	 * 
	 * <p>名称：addCol</p>
	 * <p>说明：增加一列</p>
	 * <p>参数：@param tableIndex 	文档中的第N 张表(从1 开始)</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param tableIndex</p>
	 */
	public void addCol(int tableIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch.call(cols, "Add").toDispatch();
		Dispatch.call(cols, "AutoFit");
	}
	/**
	 * 
	 * <p>名称：addTableColBefore</p>
	 * <p>说明：在指定列前面增加表格的列</p>
	 * <p>参数：@param tableIndex		文档中的第N 张表(从1 开始)
	 * <p>参数：@param colIndex 		指定列的序号(从1 开始)</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param tableIndex
	 * <p>@param colIndex</p>
	 */
	public void addTableColBefore(int tableIndex, int colIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		logger.debug(Dispatch.get(cols, "Count"));
		Dispatch col = Dispatch.call(cols, "Item", new Variant(colIndex))
				.toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}
	/**
	 * 
	 * <p>名称：addTableColBeforeFirst</p>
	 * <p>说明：在第1 列前增加一列</p>
	 * <p>参数：@param tableIndex 文档中的第N 张表(从1 开始)</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param tableIndex</p>
	 */
	public void addTableColBeforeFirst(int tableIndex) {
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch col = Dispatch.get(cols, "First").toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}
	/**
	 * 
	 * <p>名称：addLastTableCol</p>
	 * <p>说明：在最后一列前增加一列</p>
	 * <p>参数：@param tableIndex 文档中的第N 张表(从1 开始)</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param tableIndex</p>
	 */
	public void addLastTableCol(int tableIndex) {
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
				.toDispatch();
		// 表格的所有行
		Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
		Dispatch col = Dispatch.get(cols, "Last").toDispatch();
		Dispatch.call(cols, "Add", col).toDispatch();
		Dispatch.call(cols, "AutoFit");
	}	
	/**
	 * 
	 * <p>名称：autoFitTable</p>
	 * <p>说明：表格自动适应</p>
	 * <p>参数： 设定文件</p>
	 * <p>返回值：void 返回类型</p>
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
     * <p>名称：PutTxtToCell</p>
     * <p>说明：在指定的单元格里填写数据</p>
     * <p>参数：@param table
     * <p>参数：@param cellRowIdx
     * <p>参数：@param cellColIdx
     * <p>参数：@param txt 设定文件</p>
     * <p>返回值：void 返回类型</p>
     */
    public void PutTxtToCell(Dispatch table, int cellRowIdx, int cellColIdx,   
            String txt) {   
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),   
                new Variant(cellColIdx)).toDispatch();   
        Dispatch.call(cell, "Select");   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象   
        Dispatch.put(selection, "Text", txt);   
    }   
    /**
     * 
     * <p>名称：PutTxtToCell</p>
     * <p>说明：在指定的单元格里填写数据</p>
     * <p>参数：@param table
     * <p>参数：@param cellRowIdx
     * <p>参数：@param cellColIdx
     * <p>参数：@param txt 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param table
     * <p>@param cellRowIdx
     * <p>@param cellColIdx
     * <p>@param txt</p>
     */ 
    public void putTxtToCell(int tableIndex, int cellRowIdx, int cellColIdx,   
            String txt) {   
        // 所有表格   
        Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
        // 要填充的表格   
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))   
                .toDispatch();   
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),   
                new Variant(cellColIdx)).toDispatch();   
        Dispatch.call(cell, "Select");   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象   
        Dispatch.put(selection, "Text", txt);   
    }   
    /**
     * 
     * <p>名称：getTxtFromCell</p>
     * <p>说明：获取指定表格中指定位置的表格中的内容，并且复制到剪切板</p>
     * <p>参数：@param tableIndex
     * <p>参数：@param cellRowIdx
     * <p>参数：@param cellColIdx
     * <p>参数：@return 设定文件</p>
     * <p>返回值：String 返回类型</p>
     */
	public String getTxtFromCell(int tableIndex, int cellRowIdx, int cellColIdx) {
		Dispatch tables = Dispatch.get(document, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),new Variant(cellColIdx)).toDispatch();
		Dispatch Range = Dispatch.get(cell, "Range").toDispatch();
//		System.out.println(Dispatch.get(Range, "Text").toString().substring(0, Dispatch.get(Range, "Text").toString().length()-2));
		String a=Dispatch.get(Range, "Text").toString().substring(0, Dispatch.get(Range, "Text").toString().length()-2);
		return a;

	}

    /**
     * 
     * <p>名称：mergeCell</p>
     * <p>说明：合并两个单元格</p>
     * <p>参数：@param cell1
     * <p>参数：@param cell2 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param cell1
     * <p>@param cell2</p>
     */
    public void mergeCell(Dispatch cell1, Dispatch cell2) {   
        Dispatch.call(cell1, "Merge", cell2);   
    }   
    /**
     * 
     * <p>名称：mergeCell</p>
     * <p>说明：合并(row1,col1)(row2,col2)坐标所确定的矩形覆盖的所有单元格</p>
     * <p>参数：@param table
     * <p>参数：@param row1	第一个单元格row
     * <p>参数：@param col1	第一个单元格col
     * <p>参数：@param row2	第二个单元格row
     * <p>参数：@param col2 	第二个单元格col设定文件</p>
     * <p>返回值：void 返回类型</p>
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
     * <p>名称：mergeCellTest</p>
     * <p>说明：测试合并单元格的实例方法</p>
     * <p>参数： 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p></p>
     */
    public void mergeCellTest() {   
        Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
        int tableCount = Dispatch.get(tables, "Count").getInt(); // document中的表格数量   
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableCount))   
                .toDispatch();// 文档中最后一个table   
        mergeCell(table, 1, 1, 1, 2);// 将table 中x=1,y=1 与x=1,y=2的两个单元格合并   
    }   
    /**
     * 
     * <p>名称：moveUp</p>
     * <p>说明：光标向上移动</p>
     * <p>参数：@param pos 向上移动的距离设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param pos</p>
     */
    public void moveUp(int pos) {   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象   
        for (int i = 0; i < pos; i++) {   
            Dispatch.call(selection, "MoveUp");   
        }   
    }
    /**
     * 
     * <p>名称：moveDown</p>
     * <p>说明：光标向下移动</p>
     * <p>参数：@param pos 向下移动的距离设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param pos</p>
     */
    public void moveDown(int pos) {   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象   
        for (int i = 0; i < pos; i++) {   
            Dispatch.call(selection, "MoveDown");   
        }   
    } 
    /**
     * 
     * <p>名称：moveLeft</p>
     * <p>说明：光标向左移动</p>
     * <p>参数：@param pos 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param pos</p>
     */
    public void moveLeft(int pos) {
    	Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象   
        for (int i = 0; i < pos; i++) {   
            Dispatch.call(selection, "MoveLeft");   
        } 
    }
    /**
     * 
     * <p>名称：moveRight</p>
     * <p>说明：光标向右移动</p>
     * <p>参数：@param pos 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param pos</p>
     */
    public void moveRight(int pos) {
    	Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象   
        for (int i = 0; i < pos; i++) {   
            Dispatch.call(selection, "MoveRight");   
        } 
    }
    /**
     * 
     * <p>名称：moveStart</p>
     * <p>说明：光标移动到文件首部</p>
     * <p>参数： 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p></p>
     */
    public void moveStart() {
    	Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象   
    	Dispatch.call(selection, "HomeKey", new Variant(6));
    }
    /**
     * 
     * <p>名称：moveEnd</p>
     * <p>说明：光标移动到文件底部</p>
     * <p>参数： 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p></p>
     */
    public void moveEnd() {
    	Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象   
    	Dispatch.call(selection, "EndKey", new Variant(6));
    }
    /** */  
    /**  
     * 从选定内容或插入点开始查找文本  
     *  
     * @param toFindText  
     *            要查找的文本  
     * @return boolean true-查找到并选中该文本，false-未查找到文本  
     */  
    public boolean find(String toFindText) {   
        if (toFindText == null || toFindText.equals(""))   
            return false;   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象   
        // 从selection所在位置开始查询   
        Dispatch find = Dispatch.call(selection, "Find").toDispatch();   
        // 设置要查找的内容   
        Dispatch.put(find, "Text", toFindText);   
        // 向前查找   
        Dispatch.put(find, "Forward", "True");   
        // 设置格式   
        Dispatch.put(find, "Format", "false");   
        // 大小写匹配   
        Dispatch.put(find, "MatchCase", "True");   
        // 全字匹配   
        Dispatch.put(find, "MatchWholeWord", "True");   
        // 查找并选中   
        return Dispatch.call(find, "Execute").getBoolean();   
    }  
    /**
     * 
     * <p>名称：replaceText</p>
     * <p>说明：把选定选定内容设定为替换文本</p>
     * <p>参数：@param toFindText 查找字符串
     * <p>参数：@param newText	要替换的内容  
     * <p>参数：@return 设定文件</p>
     * <p>返回值：boolean 返回类型   true:替换成功;false:未找到对应字符串</p>
     */
    public boolean replaceText(String toFindText, String newText) {   
        if (!find(toFindText))   
            return false;   
        Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象   
        Dispatch.put(selection, "Text", newText);   
        return true;   
    }   
    /**
     * 
     * <p>名称：replaceAllText</p>
     * <p>说明：找到符合条件的文本并且替换所有为新的文本</p>
     * <p>参数：@param toFindText	源文本
     * <p>参数：@param newText 	目标文本	设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param toFindText
     * <p>@param newText</p>
     */
    public void replaceAllText(String toFindText, String newText) {
    	Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象   
    	while (find(toFindText)) {
    		Dispatch.put(selection, "Text",newText);
    		Dispatch.call(selection, "MoveRight");
    	}
    }
    /**
     * 
     * <p>名称：replaceTextWithImage</p>
     * <p>说明：用指定路径的图片替换文档中当前位置之后找到的第一个文本内容</p>
     * <p>参数：@param toFindText
     * <p>参数：@param imagePath
     * <p>参数：@return 设定文件</p>
     * <p>返回值：boolean 返回类型</p>
     * <p>@param toFindText
     * <p>@param imagePath
     * <p>@return</p>
     */
	public boolean replaceTextWithImage(String toFindText, String imagePath) {
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
		Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
				"AddPicture", imagePath);
		return true;
	}
	/**
	 * 
	 * <p>名称：replaceAllTextWithImageAfterCurrentPos</p>
	 * <p>说明：用指定路径的图片替换文档中当光标位置之后所有查找到的文本内容</p>
	 * <p>参数：@param toFindText
	 * <p>参数：@param imagePath 设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param toFindText
	 * <p>@param imagePath</p>
	 */
	public void replaceAllTextWithImageAfterCurrentPos(String toFindText, String imagePath) {
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
		while (find(toFindText)) {
			Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
					"AddPicture", imagePath);
			Dispatch.call(selection, "MoveRight");
		}
	}
	/**
	 * 
	 * <p>名称：insertImage</p>
	 * <p>说明：在当前位置插入图片</p>
	 * <p>参数：@param imagePath 	图片路径	设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param imagePath</p>
	 */
	public void insertImage(String imagePath) {
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
		Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
				"AddPicture", imagePath);
	}
	/**
	 * 
	 * <p>名称：findTextAndCopy</p>
	 * <p>说明：从光标当前位置找到下一个文本并且将其复制到剪贴板</p>
	 * <p>参数：@param toCopyText 设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param toCopyText</p>
	 */
	public void findTextAndCopy(String toCopyText) {
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
		moveStart();
		if (this.find(toCopyText)) {
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			Dispatch.call(textRange, "Copy");
		}
	}
	/**
	 * 
	 * <p>名称：findTextAndPaste</p>
	 * <p>说明：找到指定文本并且用剪切板的文本替换</p>
	 * <p>参数：@param pos 设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param pos</p>
	 */
	public void findTextAndPaste(String pos) {
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
		moveStart();
		if (this.find(pos)) {
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			Dispatch.call(textRange, "Paste");
		}
	}
	
	public	void	paste(){
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
		Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
		Dispatch.call(textRange, "Paste");
	}
	/**
	 * 
	 * <p>名称：insertEnter</p>
	 * <p>说明：插入回车</p>
	 * <p>参数： 设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 * <p></p>
	 */
	public	void	insertEnter(){
		InsertText("\n");
	}
	
	/**
	 * 
	 * <p>名称：copyParagraphFromAnotherDoc</p>
	 * <p>说明：在当前文档末尾拷贝来自另一个文档中的段落</p>
	 * <p>参数：@param anotherDocPath	另一个文档的磁盘路径
	 * <p>参数：@param paragraphIndex 被拷贝的段落在另一格文档中的序号(从1开始)</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param anotherDocPath
	 * <p>@param paragraphIndex</p>
	 */
	public void copyParagraphFromAnotherDoc(String anotherDocPath,
			int paragraphIndex) {
		Dispatch wordContent = Dispatch.get(document, "Content").toDispatch(); // 取得当前文档的内容
		Dispatch.call(wordContent, "InsertAfter", "$selection$");// 插入特殊符定位插入点
		this.moveStart();
		if(this.find("$selection$")){
			copyParagraphFromAnotherDoc(anotherDocPath, paragraphIndex,
					"selection");
		}

	}
	/**
	 * 
	 * <p>名称：copyParagraphFromAnotherDoc</p>
	 * <p>说明：在当前文档指定的位置拷贝来自另一个文档中的段落</p>
	 * <p>参数：@param anotherDocPath	另一个文档的磁盘路径
	 * <p>参数：@param paragraphIndex	被拷贝的段落在另一格文档中的序号(从1开始)
	 * <p>参数：@param pos 当前文档指定的位置</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param anotherDocPath
	 * <p>@param paragraphIndex
	 * <p>@param pos</p>
	 */
	public void copyParagraphFromAnotherDoc(String anotherDocPath,
			int paragraphIndex, String pos) {
		Dispatch doc2 = null;
		Dispatch documents	=	this.MsWordApp.getProperty("Documents").toDispatch();
		Dispatch selection 	= 	Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
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
	 * <p>名称：copyTableFromAnotherDoc</p>
	 * <p>说明：在当前文档指定的位置拷贝来自另一个文档中的表格</p>
	 * <p>参数：@param anotherDocPath		另一个文档的磁盘路径
	 * <p>参数：@param tableIndex			被拷贝的表格在另一格文档中的序号(从1开始)
	 * <p>参数：@param pos 设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 */
	public void copyTableFromAnotherDoc(String anotherDocPath, int tableIndex) {
		Dispatch doc2 = null;
		Dispatch documents	=	this.MsWordApp.getProperty("Documents").toDispatch();
		Dispatch selection 	= 	Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
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
	 * <p>名称：setFont</p>
	 * <p>说明：设置当前选定内容的字体</p>
	 * <p>参数：@param bold		粗体
	 * <p>参数：@param italic		斜体
	 * <p>参数：@param underLine	下划线
	 * <p>参数：@param colorSize	字体颜色
	 * <p>参数：@param size		字体大小
	 * <p>参数：@param name 		字体名称</p>
	 * <p>返回值：void 返回类型</p>
	 */
	public void setFont(boolean bold, boolean italic, boolean underLine,
			String colorSize, String size, String name) {
		Dispatch selection 	= 	Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
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
	 * <p>名称：printFile</p>
	 * <p>说明：打印文件</p>
	 * <p>参数： 设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 * <p></p>
	 */
	public void printFile() {   
        Dispatch.call(document, "PrintOut");   
    }   
	/**
	 * 
	 * <p>名称：save</p>
	 * <p>说明：保存当前文档</p>
	 * <p>参数： 设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 * <p></p>
	 */
    public void save() {   
        Dispatch.call(document, "Save");   
    }   
    /**
     * 
     * <p>名称：saveFileAs</p>
     * <p>说明：文件另存为</p>
     * <p>参数：@param filename 文件另存为的文件名（可带路径）</p>
     * <p>返回值：void 返回类型</p>
     * <p>@param filename</p>
     */
    public void saveFileAs(String filename) {   
        Dispatch.call(document, "SaveAs", filename);   
    }   
    /**
     * 
     * <p>名称：closeDocument</p>
     * <p>说明：关闭文件</p>
     * <p>参数：@param Para 	0：不保存关闭	1:保存关闭		other:提示是否保存</p>
     * <p>返回值：void 返回类型</p>
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
     * <p>名称：closeWord</p>
     * <p>说明：关闭word程序</p>
     * <p>参数： 设定文件</p>
     * <p>返回值：void 返回类型</p>
     * <p></p>
     */
    public void closeWord() {   
        Dispatch.call(MsWordApp, "Quit");   
        MsWordApp = null;   
        document = null;   
    }   
    /**
     * 
     * <p>名称：setLocation</p>
     * <p>说明：设置wordApp打开后窗口的位置   </p>
     * <p>参数： 设定文件</p>
     * <p>返回值：void 返回类型</p>
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
    * <p>名称：gettableColumn</p>
    * <p>说明：获取表格列数</p>
    * <p>参数：@param anotherDocPath
    * <p>参数：@param tableIndex 设定文件</p>
    * <p>返回值：void 返回类型</p>
    * <p>@param anotherDocPath
    * <p>@param tableIndex</p>
    * <p>author:lijuan</p>
    */
   	public int  gettableColumn() {
           Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
           int tableCount = Dispatch.get(tables, "Count").getInt(); // document中的表格数量   
           Dispatch table = Dispatch.call(tables, "Item", new Variant(tableCount))   
                   .toDispatch();// 文档中最后一个table
           Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
   		int colCount = Dispatch.get(cols, "Count").getInt();
           return colCount;
         
   	}
   	
   /**
    * 	
    * <p>名称：gettableRow</p>
    * <p>说明：TODO(这里用一句话描述这个方法的作用)</p>
    * <p>参数：@return 设定文件</p>
    * <p>返回值：int 返回类型</p>
    * <p>@return</p>
    * author:lijuan
    */
   	
   	public int  gettableRow() {
           Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
           int tableCount = Dispatch.get(tables, "Count").getInt(); // document中的表格数量   
           Dispatch table = Dispatch.call(tables, "Item", new Variant(tableCount))   
                   .toDispatch();// 文档中最后一个table
           Dispatch rows = Dispatch.get(table, "Rows").toDispatch(); // 此表的所有行，   
           int rowCount = Dispatch.get(rows, "Count").getInt();
           return rowCount;
           }
   	
   	
   	/**
   	 * 
   	 * <p>名称：getCols</p>
   	 * <p>说明：获取某一行的列数</p>
   	 * <p>参数：@return 设定文件</p>
   	 * <p>返回值：int 返回类型</p>
   	 * <p>@return</p>
   	 * author:lijuan
   	 */
   	public int getCols(int a){
        Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
        int tableCount = Dispatch.get(tables, "Count").getInt(); // document中的表格数量   
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableCount))   
                .toDispatch();// 文档中最后一个table
        Dispatch rows = Dispatch.get(table, "Rows").toDispatch(); // 此表的所有行
        int cellCount=0;
            Dispatch col = Dispatch.call(rows, "Item", new Variant(a))   
                    .toDispatch();   
            Dispatch cells = Dispatch.get(col, "Cells").toDispatch();// 当前列中单元格   
            cellCount = Dispatch.get(cells, "Count").getInt();// 当前列中单元格数 实际上这个数等于row
   		return cellCount;
   	}
 
   	/**
   	 * 
   	 * <p>名称：getRowContent</p>
   	 * <p>说明：获取行的内容</p>
   	 * <p>参数：@param a
   	 * <p>参数：@return 设定文件</p>
   	 * <p>返回值：String 返回类型</p>
   	 * <p>@param a
   	 * <p>@return</p>
   	 * author：lijuan
   	 */
   	public String getRowContent(int a){
   	   Dispatch tables = Dispatch.get(document, "Tables").toDispatch();   
       int tableCount = Dispatch.get(tables, "Count").getInt(); // document中的表格数量   
       Dispatch table = Dispatch.call(tables, "Item", new Variant(tableCount))   
               .toDispatch();// 文档中最后一个table
       Dispatch rows = Dispatch.get(table, "Rows").toDispatch(); // 此表的所有行
       int rowCount = Dispatch.get(rows, "Count").getInt();// 一共有多少列 实际上这个数==column   
       int cellCount=0;
           Dispatch col = Dispatch.call(rows, "Item", new Variant(a))   
                   .toDispatch();   
           Dispatch cells = Dispatch.get(col, "Cells").toDispatch();// 当前列中单元格   
           cellCount = Dispatch.get(cells, "Count").getInt();// 当前列中单元格数 实际上这个数等于row
           String content="";
   	   for (int j = 1; j <= cellCount; j++) {// 每一列中的单元格数   
    //       Dispatch cell = Dispatch.call(cells, "Item", new  Variant(j)).toDispatch(); //当前单元格   
      //     Dispatch.call(cell, "Select");//选中当前单元格   
       	Dispatch cell = Dispatch.call(table, "Cell", new Variant(a),new Variant(j)).toDispatch();
		Dispatch Range = Dispatch.get(cell, "Range").toDispatch();
		content=Dispatch.get(Range,"Item").getString();
       }   
   		return content;
   		
   	} 
   	
   	/**
   	 * 
   	 * <p>名称：CreateDoc</p>
   	 * <p>说明：创建新的word文档</p>
   	 * <p>参数： 设定文件</p>
   	 * <p>返回值：void 返回类型</p>
   	 * <p></p>
   	 * author:lijuan
   	 */
   	public void CreateDoc(String name,String path){
   		JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
		wordBean.SetVisible(WordBeanProperty.WORD_VISABLE);
		wordBean.CreateNewDocument();// 创建一个新文档
		wordBean.setLocation();// 设置打开后窗口的位置
		wordBean.saveFileAs(path+'\\'+name+".doc");
		wordBean.closeDocument(0);
		wordBean.closeWord();
   	}
   	
   	
   	/**
   	 * 
   	 * <p>名称：CountTable</p>
   	 * <p>说明：计算表格数量TODO(这里用一句话描述这个方法的作用)</p>
   	 * <p>参数：@param DocPath
   	 * <p>参数：@return 设定文件</p>
   	 * <p>返回值：String 返回类型</p>
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
   	 * <p>名称：wordToHtml</p>
   	 * <p>说明：将word转化为htmlTODO(这里用一句话描述这个方法的作用)</p>
   	 * <p>参数：@param docfile
   	 * <p>参数：@param htmlfile 设定文件</p>
   	 * <p>返回值：void 返回类型</p>
   	 * <p>@param docfile
   	 * <p>@param htmlfile</p>
   	 *   author:lijuan
   	 */
	public  void wordToHtml(String docfile, String htmlfile) {
		ActiveXComponent app = new ActiveXComponent("Word.Application"); // 启动word
		try {
			app.setProperty("Visible", new Variant(false));
			// 设置word不可见
			Object docs = app.getProperty("Documents").toDispatch();
			Dispatch doc = Dispatch.invoke((Dispatch) docs,"Open",Dispatch.Method,new Object[] { docfile, new Variant(false),new Variant(true) }, new int[1]).toDispatch();
			// 打开word文件
			Dispatch.invoke((Dispatch) doc, "SaveAs", Dispatch.Method,new Object[] { htmlfile, new Variant(8) }, new int[1]);
			// 作为html格式保存到临时文件
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
	 * <p>名称：getContextFromAnotherFileTableCell</p>
	 * <p>说明：从另一个文档中的第tableIndex个表格中的第row行第col列复制表格内容并且粘贴到当前文档</p>
	 * <p>参数：@param anotherFileName
	 * <p>参数：@param tableIndex
	 * <p>参数：@param row
	 * <p>参数：@param col 设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param anotherFileName
	 * <p>@param tableIndex
	 * <p>@param row
	 * <p>@param col</p>
	 */
	public	void getContextFromAnotherFileTableCell(String anotherFileName,int	tableIndex,int row,int col){
		Dispatch doc2 = null;
		Dispatch documents	=	this.MsWordApp.getProperty("Documents").toDispatch();
		Dispatch selection 	= 	Dispatch.get(MsWordApp, "Selection").toDispatch(); // 输入内容需要的对象
		try {
			doc2 = Dispatch.call(documents, "Open", anotherFileName)
					.toDispatch();
			
			
			Dispatch tables = Dispatch.get(doc2, "Tables").toDispatch();
			// 要填充的表格
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
	 * <p>名称：disableTableBorder</p>
	 * <p>说明：将第index个表格的边框宽度设为width</p>
	 * <p>参数：@param index
	 * <p>参数：@param width 设定文件</p>
	 * <p>返回值：void 返回类型</p>
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
