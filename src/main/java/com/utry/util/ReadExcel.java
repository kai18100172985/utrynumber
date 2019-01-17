package com.utry.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.monitor.annotation.MField;
import com.utry.pojo.User;
/**
 * 读取excel文件
 * @author KaiLeon
 *
 */
public class ReadExcel{
	
	private int totalRows=0;
	private int totalCells=0;
	private String errorMsg;
	public ReadExcel() {
		
	}

	public int getTotalRows() {
		return totalRows;
	}

	public int getTotalCells() {
		return totalCells;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public List<User> getExcelInfo(MultipartFile mfile) {
		String fileName = mfile.getOriginalFilename();
		List<User> userList=null;
		try {
			if(!validateExcel(fileName)) {
				return null;
			}
			boolean isExcel2003=true;
			if(isExcel2007(fileName)) {
				isExcel2003=false;
			}
			userList = creatExcel(mfile.getInputStream(),isExcel2003);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}



	private boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$"); 
		
	}

	private boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$"); 
		
	}

	private boolean validateExcel(String filePath) {
		if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
			errorMsg = "文件名不是excel格式";  
			 return false;  
		}
		return true;
	}



	private List<User> creatExcel(InputStream is, boolean isExcel2003) {
		List<User> userList = null;
		try {
			Workbook wb = null;
			if(isExcel2003) {
				wb = new HSSFWorkbook(is);
			}else{
				wb = new XSSFWorkbook(is);
			}
			userList = readExcelValue(wb);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userList;
	}



	private List<User> readExcelValue(Workbook wb) {
		List<User> userList = null;
		Sheet sheet = wb.getSheetAt(0);
		this.totalRows = sheet.getPhysicalNumberOfRows();
		if(totalRows>1&&sheet.getRow(0)!=null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		userList = new ArrayList<User>();
		for(int r=1;r<totalRows;r++) {
			Row row = sheet.getRow(r);
			if(row == null) {
				continue;
			}
			User user = new User();
			for(int c = 0;c<this.totalCells;c++) {
				Cell cell = row.getCell(c);
				if(null!=cell) {
					if(c==0) {
						  if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
							  String name = String.valueOf(cell.getNumericCellValue());
							  user.setName(name.substring(0, name.length()-2>0?name.length()-2:1));//名称 
						  }else if(c==1){
							  if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
								  String sex = String.valueOf(cell.getNumericCellValue());  
								  user.setNikeName(sex.substring(0, sex.length()-2>0?sex.length()-2:1));//性别  
							  }else {
								  user.setNikeName(cell.getStringCellValue());//性别  
							  }
						  }else if(c==2) {
							  if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
								  String age = String.valueOf(cell.getNumericCellValue());  
								  user.setPassword(age.substring(0, age.length()-2>0?age.length()-2:1));//年龄  
							  }else {
								  user.setPassword(cell.getStringCellValue());//年龄  
							  }
						  }else if(c==3) {
							  if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
								  String age = String.valueOf(cell.getNumericCellValue());  
								  user.setTelephone(age.substring(0, age.length()-2>0?age.length()-2:1));//年龄  
							  }else {
								  user.setTelephone(cell.getStringCellValue());//年龄  
							  }
						  }else if(c==4) {
							  if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
								  String age = String.valueOf(cell.getNumericCellValue());  
								  user.setEmail(age.substring(0, age.length()-2>0?age.length()-2:1));//年龄  
							  }else {
								  user.setEmail(cell.getStringCellValue());//年龄  
							  }
						  }
					}
				}
			}
			userList.add(user); 
		}
		return userList;
	}

	
}