package com.utry.pojo;

import java.io.Serializable;

public class Arraynb implements Serializable{

	private Integer id;
	private String arraynum;
	private String creattime;
	private Integer nbid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArraynum() {
		return arraynum;
	}
	public void setArraynum(String arraynum) {
		this.arraynum = arraynum;
	}
	@Override
	public String toString() {
		return "Arraynb [id=" + id + ", arraynum=" + arraynum + ", creattime=" + creattime + ", nbid=" + nbid + "]";
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	public Integer getNbid() {
		return nbid;
	}
	public void setNbid(Integer nbid) {
		this.nbid = nbid;
	}
}
