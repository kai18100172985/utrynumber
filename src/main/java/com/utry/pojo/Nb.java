package com.utry.pojo;

import java.io.Serializable;

public class Nb implements Serializable{

	private Integer id;
	private String num;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
}
