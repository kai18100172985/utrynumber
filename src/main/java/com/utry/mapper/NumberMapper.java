package com.utry.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.utry.pojo.Arraynb;

public interface NumberMapper {

	@Insert("insert into hekai.nb(num) "
			+ "values(#{num})")
	void insertnb(@Param("num")String num);//数据插入

	@Select("select id from hekai.nb where num=#{num}")
	Integer selectnbid(@Param("num")String num);   //获取nb表id

	@Select("select count(*) from hekai.nb where num=#{num}")
	Integer selectcount(@Param("num")String num); //查询nb表是否存num数据

	@Select("select * from hekai.arraynb where nbid=#{nbid}")
	List<Arraynb> numberlist(@Param("nbid")Integer nbid); //获取arraynb表的查询数据

	@Select("select count(*) from hekai.arraynb where arraynum=#{arraynum}")
	Integer selectarrynbcount(@Param("arraynum")String arraynum); //查询arraynb表数据是否存在

	@Select("select nbid from hekai.arraynb where arraynum=#{arraynum}")
	List<Integer> selectarraynbid(@Param("arraynum")String arraynum); //获取arraynb表的nbid

	@Delete("delete from hekai.arraynb where arraynum=#{arraynum}")
	void numberdeletearray(@Param("arraynum")String arraynum);//删除arraynb表的数据

	@Select("select * from hekai.arraynb")
	List<Arraynb> numberlistarrayall(); //获取arraynb表的所有数据

	@Delete("delete from hekai.arraynb where nbid=#{nbid}")
	void numberarraydeleteall(@Param("nbid")Integer nbid);//删除所有数据

	@Delete("delete from hekai.nb where id=#{id}")
	void numbernbdelete(@Param("id")Integer id);

}
