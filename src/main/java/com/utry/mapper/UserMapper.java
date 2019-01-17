package com.utry.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.utry.pojo.User;

public interface UserMapper {

	@Select("select * from hekai.users where id=#{id}")
	User find(@Param("id")Integer id);

	@Select("select count(*) from hekai.users where name=#{userName} and password=#{password}")
	Integer login(@Param("userName")String userName, 
			@Param("password")String password);

	@Select("select count(*) from hekai.users where email=#{email}")
	Integer selectByemail(@Param("email")String email);

	@Insert("insert into hekai.users(name,password,nikename,telephone,email) "
			+ "values(#{name},#{password},#{nikeName},#{telephone},#{email})")
	void insert(User user);

	@Update("update hekai.users set name=#{name},password=#{password},nikename=#{nikeName},telephone=#{telephone}"
			+ "where telephone=#{telephone}")
	void update(User user);

	List<User> findlist(@Param("name") String name,@Param("telephone") String telephone);

	Integer deletebyname(@Param("name") String name,@Param("telephone") String telephone);

	@Select("select * from hekai.users where telephone=#{valueOf}")
	User selectBytelephone(@Param("valueOf")String valueOf);

}
