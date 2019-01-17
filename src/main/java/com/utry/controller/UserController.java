package com.utry.controller;

import java.util.Enumeration;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.utry.pojo.User;
import com.utry.service.UserService;
import com.utry.util.RedisClient;

@Controller
@RequestMapping("/page/user")
public class UserController {
	
	@Autowired  
    private RedisClient redisClinet;  

	@Autowired
	private UserService userService;
	
	/**
	 * 测试用例
	 * @param id
	 * @return
	 */
	@PostMapping("/find")
	@ResponseBody
	public User findAll(@RequestParam("id") Integer id) {
		
		return userService.findAll(id);
	}
	
/*	@PostMapping("/upload")
	@ResponseBody
	public String upload(
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request,
			HttpServletResponse response){
		    String result = userService.readExcelFile(file);
		    return result;
   }*/
	
	/**
	 * 导入excel表格
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("/upload")
	@ResponseBody
	public String upload(
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request,
			HttpServletResponse response){
		    //String result = userService.readExcelFile(file);
		    //return result;
		return userService.ajaxUploadExcel(file,request, response);
   }
	
	/**
	 * 退出
	 * @param request
	 * @param session
	 * @throws Exception
	 */
	@GetMapping("/exit")
	@ResponseBody
	public void exit(HttpServletRequest request,HttpSession session) throws Exception {
		  String userName = (String) session.getAttribute("userName");
		  Enumeration em = request.getSession().getAttributeNames();
		  while(em.hasMoreElements()){
		   request.getSession().removeAttribute(em.nextElement().toString());
		  }
		  redisClinet.rmove(userName);
	}
	
	/**
	 * 查询用户数据
	 * @param name
	 * @param telephone
	 * @return
	 */
	@GetMapping("/userfind")
	@ResponseBody
	public List<User> find(@RequestParam("name") String name,
			@RequestParam("telephone") String telephone) {
		return userService.findlist(name,telephone);
	}
	
	@GetMapping("/userdelete")
	@ResponseBody
	public String delete(@RequestParam("name") String name,
			@RequestParam("telephone") String telephone) {
		if(!StringUtils.isEmpty(name)||!StringUtils.isEmpty(telephone)) {
			return userService.deletebyname(name,telephone);
		}else {
			return "请输入您删除的用户";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("userName") String userName,
			@RequestParam("password") String password,HttpSession session) throws Exception {
		String oldpassword = redisClinet.get(userName);
		if(StringUtils.isEmpty(oldpassword)) {
			Integer num = userService.login(userName,password);
			
			if(num==1) {
				redisClinet.set(userName, password);
				session.setAttribute("userName", userName);
				return "redirect:/page/index";			
			}else {
				return "redirect:/page/error";
			}
		}else if(!StringUtils.equals(password, oldpassword)){
			return "redirect:/page/error";
		}else {
			session.setAttribute("userName", password);
			return "redirect:/page/index";	
		}

	}
	
	@PostMapping("/register")
	public String register(@RequestParam("userName")String name,
			@RequestParam("nikeName")String nikeName,
			@RequestParam("telephone")String telephone,
			@RequestParam("password")String password,
			@RequestParam("email")String email) {
		
		if(StringUtils.isEmpty(name)) {
			return "请输入正确的用户名";
		}else if(StringUtils.isEmpty(nikeName)) {
			return "请输入正确的昵称";
		}else if(StringUtils.isEmpty(telephone)) {
			return "请输入正确的电话";
		}else if(StringUtils.isEmpty(password)) {
			return "请输入正确的密码";
		}else if(StringUtils.isEmpty(email)) {
			return "请输入正确的邮箱";
		}
		User user=new User();
		user.setEmail(email);
		user.setName(name);
		user.setNikeName(nikeName);
		user.setTelephone(telephone);
		user.setPassword(password);
		String s = userService.insert(user);
		if(s.equals("注册成功")) {
			return "redirect:/page/login";
		}else {
			return "redirect:/page/error";
		}
	}
	
	@PostMapping("/add")
	@ResponseBody
	public String add(@RequestParam("name")String name,
			@RequestParam("nikeName")String nikeName,
			@RequestParam("telephone")String telephone,
			@RequestParam("password")String password,
			@RequestParam("email")String email) {
		if(StringUtils.isEmpty(name)) {
			return "请输入正确的用户名";
		}else if(StringUtils.isEmpty(nikeName)) {
			return "请输入正确的昵称";
		}else if(StringUtils.isEmpty(telephone)) {
			return "请输入正确的电话";
		}else if(StringUtils.isEmpty(password)) {
			return "请输入正确的密码";
		}else if(StringUtils.isEmpty(email)) {
			return "请输入正确的邮箱";
		}
		User user=new User();
		user.setEmail(email);
		user.setName(name);
		user.setNikeName(nikeName);
		user.setTelephone(telephone);
		user.setPassword(password);
		System.out.println(user.getEmail());
		String s = userService.insert(user);
		return s;
	}
	
	
	@PostMapping("/update")
	@ResponseBody
	public String update(@ModelAttribute User user) {
		if(StringUtils.isEmpty(user.getName())) {
			return "请输入正确的用户名";
		}else if(StringUtils.isEmpty(user.getNikeName())) {
			return "请输入正确的昵称";
		}else if(StringUtils.isEmpty(user.getTelephone())) {
			return "请输入正确的电话";
		}else if(StringUtils.isEmpty(user.getPassword())) {
			return "请输入正确的密码";
		}else if(StringUtils.isEmpty(user.getEmail())) {
			return "请输入正确的邮箱";
		}
		
		return userService.update(user);
	}
}
