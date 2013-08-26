package net.ib.util.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface ReadExcel {
	public List<Map> ReadExcelList(String fileUrl) throws IOException;
	public String TeacherExcelToDatabase(List<Map> list,String TeacherDepartmentId);
	public String StudentExcelToDatabase(List<Map> list , String StudentDepartmentId);
	public String StudentScoreToDatabase(List<Map> list, String CurseId, String CurseTime);

}
