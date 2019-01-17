package com.utry.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.utry.mapper.UserMapper;
import com.utry.pojo.User;
import com.utry.service.UserService;
import com.utry.util.EXcelUtils;
import com.utry.util.ReadExcel;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

	/**
	 * 测试数据
	 */
	@Override
	public User findAll(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.find(id);
	}

	@Override
	public Integer login(String userName, String password) {
        Integer num= userMapper.login(userName,password);
		return num;
	}

	@Override
	public String insert(User user) {
		User num = userMapper.selectBytelephone(user.getTelephone());
		if(num==null) {
			userMapper.insert(user);
	        return "注册成功";	
		}else {
			return "电话被占用";
		}
	}

	@Override
	public String update(User user) {
		User num = userMapper.selectBytelephone(user.getTelephone());
		if(num==null) {
			return "您修改的用户不存在";
		}else {
			userMapper.update(user);
			return "修改成功";
		}
	}

	@Override
	public List<User> findlist(String name, String telephone) {

		return userMapper.findlist(name,telephone);
		
	}

	@Override
	public String deletebyname(String name, String telephone) {
		Integer num = userMapper.deletebyname(name,telephone);
		if(num==0) {
			return "您删除的用户不存在";
		}
		return "删除成功";
	}

	@Override
	public List<User> downloadfile(String name, String telephone) {
		
		return userMapper.findlist(name,telephone);
	}

	/**
	 * 上传excel文件
	 */
	@Override
	public String readExcelFile(MultipartFile file) {
		/*String result="";
		//创建处理Excel的类
		ReadExcel readExcel = new ReadExcel();
		//解析excel，获取上传的事件单
		List<User> userList = readExcel.getExcelInfo(file);
		//至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,  
		//和你具体业务有关,这里不做具体的示范 
		System.out.println(userList);
		
		return result;*/
		return null;
	}

	/**
	 * 上传excel文件 并且写入数据库
	 */
	@Override
	public String ajaxUploadExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        if(file.isEmpty()){  
            try {
                throw new Exception("文件不存在！");
            } catch (Exception e) {
                e.printStackTrace();
            }  
        }  

        InputStream in =null;  
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }  

        List<List<Object>> listob = null; 
        try {
            listob = new EXcelUtils().getBankListByExcel(in,file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }   
           for (int i = 0; i < listob.size(); i++) { 
            /*   List<Object> lo = listob.get(i); 
               if (lo.get(i)=="") {
                    continue;
                }*/
               System.out.println(listob.get(i));

           }
        for (int i = 0; i < listob.size(); i++) {  
            List<Object> lo = listob.get(i);  
            User vo = new User(); 
            User j = null;

            try {
                j = userMapper.selectBytelephone(String.valueOf(lo.get(3)));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                System.out.println("数据库中无该条数据，新增");

            }
                vo.setName(String.valueOf(lo.get(0)));
                vo.setNikeName(String.valueOf(lo.get(1)));
                vo.setPassword(String.valueOf(lo.get(2)));  
                vo.setTelephone(String.valueOf(lo.get(3)));
                vo.setEmail(String.valueOf(lo.get(4)));

            if(j == null)
            {

                    userMapper.insert(vo);
            }
            else
            {
                    userMapper.update(vo);
            }

        }   

        return "文件导入成功！";
    }
    }