package com.utry.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.utry.mapper.UserMapper;
import com.utry.pojo.User;

public interface UserService {

	User findAll(Integer id);

	Integer login(String userName, String password);

	String insert(User user);

	String update(User user);

	List<User> findlist(String name, String telephone);

	String deletebyname(String name, String telephone);

	List<User> downloadfile(String name, String telephone);

	String readExcelFile(MultipartFile file);

	String ajaxUploadExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response);


	
}
