package com.utry.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.utry.pojo.Arraynb;
import com.utry.pojo.User;
import com.utry.service.NumberService;
import com.utry.service.UserService;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NumberService numberService;
	/**
	 * 导出为excel文件
	 * @param name
	 * @param telephone
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userdownloadfile")
	@ResponseBody
	public String userdownloadfile(@RequestParam("name") String name,
			@RequestParam("telephone") String telephone,HttpServletResponse response
			) throws Exception {
		List<User> listAll=userService.downloadfile(name,telephone);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        String fileName = "user"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "id", "姓名", "昵称", "电话", "邮箱"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (User p : listAll) {
            HSSFRow row1 = sheet.createRow(rowNum);
      
			
            row1.createCell(0).setCellValue(p.getId());
            row1.createCell(1).setCellValue(p.getName());
            row1.createCell(2).setCellValue(p.getNikeName());
            row1.createCell(3).setCellValue(p.getTelephone());
            row1.createCell(4).setCellValue(p.getEmail());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
        return "ok";
		
	}
	
	/**
	 * 导出为txt文件
	 */
	@RequestMapping("/userdownloadfiletxt")
	public void userdownloadfiletxt(@RequestParam("name") String name,
			@RequestParam("telephone") String telephone,
			HttpServletResponse response) throws Exception {
		
		List<User> userlist = userService.downloadfile(name, telephone);
		// 导出文件
        response.setContentType("text/plain");
        String fileName = "userlist";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".txt");
        BufferedOutputStream buff = null;
        StringBuffer write = new StringBuffer();
        String enter = "\r\n";
        ServletOutputStream outSTr = null;
        try {   
            outSTr = response.getOutputStream(); // 建立
            buff = new BufferedOutputStream(outSTr);
            // 把内容写入文件
            if (userlist.size() > 0) {
                for (int i = 0; i < userlist.size(); i++) {
                	User u = userlist.get(i);
                    write.append(u.getName()+' '
                    		+u.getPassword()+' '
                    		+u.getNikeName()+' '
                    		+u.getTelephone()+' '
                    		+u.getEmail()); 
                    write.append(enter);
                }
            }
            buff.write(write.toString().getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buff.close();
                outSTr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

	}

	
	@RequestMapping("/downloadnumbertxt")
	public void downloadnumbertxt(@RequestParam("num") String num,
			HttpServletResponse response) throws Exception {
		
		List<Arraynb> arraynblist = numberService.getNum(num);
		// 导出文件
        response.setContentType("text/plain");
        String fileName = "arraynblist";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".txt");
        BufferedOutputStream buff = null;
        StringBuffer write = new StringBuffer();
        String enter = "\r\n";
        ServletOutputStream outSTr = null;
        try {   
            outSTr = response.getOutputStream(); // 建立
            buff = new BufferedOutputStream(outSTr);
            // 把内容写入文件
            if (arraynblist.size() > 0) {
            	Integer n = numberService.selectcount(num);
            	if(n==0) {
            		numberService.insertnb(num);
            	}
            	
            	Integer nbid = numberService.selectnbid(num);
                for (int i = 0; i < arraynblist.size(); i++) {
                	Arraynb u = arraynblist.get(i);

                    write.append(u.getArraynum()+','
                    		+u.getCreattime()+','
                    		+nbid); 
                    write.append(enter);
                }
            }
            buff.write(write.toString().getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buff.close();
                outSTr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

	}
	
	
	@RequestMapping("/downloadnumber")
	public void downloadnumber(@RequestParam("arraynum") String num,
			HttpServletResponse response) throws Exception {
		
		List<Arraynb> listAll=null;
		if(StringUtils.isEmpty(num)) {
			listAll=numberService.download();
		}else {
			listAll = numberService.downloadnum(num);
		}
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        String fileName = "number"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "id", "组合数", "创建时间", "nbid"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (Arraynb p : listAll) {
            HSSFRow row1 = sheet.createRow(rowNum);
            		
            row1.createCell(0).setCellValue(p.getId());
            row1.createCell(1).setCellValue(p.getArraynum());
            row1.createCell(2).setCellValue(p.getCreattime());
            row1.createCell(3).setCellValue(p.getNbid());
    
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
	}
	
	
}
