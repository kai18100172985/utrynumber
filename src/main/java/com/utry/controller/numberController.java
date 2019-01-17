package com.utry.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.utry.pojo.Arraynb;
import com.utry.service.NumberService;

@Controller
@RequestMapping("/number")
public class numberController {

	@Autowired
	private NumberService numberService; 
	
	@GetMapping("/get")
	@ResponseBody
	public String getNumber() {
		return numberService.getNumber();
	}
	
	@GetMapping("/getnum")
	@ResponseBody
	public List<Arraynb> getNum(@RequestParam("num") String num) {
		
		List<Arraynb> num2 = numberService.getNum(num);
		List<Arraynb> list = new ArrayList<>();
		int total = num2.size();
		for(int i=0;list.size()<=5;i++) {
			int n = (int)(Math.random() *total+1);
			if(!list.contains(num2.get(n))) {
				list.add(num2.get(n));
			}
		}
		return list;
	}
	
	@GetMapping("/list")
	@ResponseBody
	public List<Arraynb> numberlist(@RequestParam("arraynum") String num) {
		
	
		return numberService.numberlist(num);
	}
	
	
	@GetMapping("/numberdelete")
	@ResponseBody
	public String numberdelete(@RequestParam("arraynum") String num) {
	
		return numberService.numberdelete(num);
	}
	
	@GetMapping("/numberdeleteall")
	@ResponseBody
	public String numberdeleteall(@RequestParam("arraynum") String num) {
	
		return numberService.numberdeleteall(num);
	}
}
