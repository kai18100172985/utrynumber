package com.utry.service;

import java.util.List;

import com.utry.pojo.Arraynb;

public interface NumberService {

	String getNumber();

	List<Arraynb> getNum(String num);

	void insertnb(String num);

	Integer selectnbid(String num);

	Integer selectcount(String num);

	List<Arraynb> numberlist(String num);

	String numberdelete(String num);

	String numberdeleteall(String num);

	List<Arraynb> download();

	List<Arraynb> downloadnum(String num);

	
}
