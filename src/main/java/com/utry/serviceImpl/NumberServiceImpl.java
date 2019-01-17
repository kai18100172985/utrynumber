package com.utry.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.utry.mapper.NumberMapper;
import com.utry.pojo.Arraynb;
import com.utry.service.NumberService;
import com.utry.util.PermAComb;

@Service
public class NumberServiceImpl implements NumberService{

	@Autowired
	private NumberMapper numberMapper;

	@Override
	public String getNumber() {

		String arraynumber=null;
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i<6;i++) {
			list.add((int)(Math.random() *33+1));
		}
		Collections.sort(list);
		arraynumber=String.valueOf(list.get(0))+' ';
		for (int j = 1;j<5;j++) {
			arraynumber=arraynumber+String.valueOf(list.get(j))+' ';
		}
		System.out.println(arraynumber+list.get(5));
		return arraynumber+list.get(5);
	}

	@Override
	public List<Arraynb> getNum(String num) {
		
		String dateDir= new SimpleDateFormat("yyyyMMdd").format(new Date());
		List<Arraynb> list = new ArrayList<>();
		
		String[] arraynum1 = num.split(" ");
		int[] arraynum= new int[arraynum1.length];
		for(int i = 0; i<arraynum1.length;i++) {
			arraynum[i] = Integer.parseInt(arraynum1[i]);
		}
		
		PermAComb.permutation(arraynum, 0, arraynum.length-1);
		int[][] a = new int[PermAComb.allSorts.size()][]; // 你要的二维数组a
        PermAComb.allSorts.toArray(a);
        
        List<String> listnum = new ArrayList<>();
        
        // 打印验证
        for (int i=0; i<a.length; i++) {
            int[] nums = a[i];
            String str = nums[0]+" "+nums[1]+" "
            +nums[2]+" "+nums[3]+" "+nums[4]+" "+nums[5];
            listnum.add(str);
            
            
            Arraynb array1= new Arraynb();
            array1.setId(i);
            array1.setArraynum(str);
            array1.setCreattime(dateDir);
            list.add(array1);
        }
		
        System.out.println(list.get(0).toString());
        
		return list;
	}

	@Override
	public void insertnb(String num) {
		numberMapper.insertnb(num);
		
	}

	@Override
	public Integer selectnbid(String num) {
		
		return numberMapper.selectnbid(num);
	}

	@Override
	public Integer selectcount(String num) {
		
		return numberMapper.selectcount(num);
	}

	@Override
	public List<Arraynb> numberlist(String num) {
		if(StringUtils.isEmpty(num)) {
			return numberMapper.numberlistarrayall();
		}
		
		List<Integer> nbid = numberMapper.selectarraynbid(num);
		if(nbid.get(0)==null) {
			return null;
		}else {
			return numberMapper.numberlist(nbid.get(0));
		}
		
	}

	@Override
	public String numberdelete(String num) {

		Integer n = numberMapper.selectarrynbcount(num);
		if(n==0) {
			return "您删除数据不存在";
		}else {
			List<Integer> nbid = numberMapper.selectarraynbid(num);
			numberMapper.numberarraydeleteall(nbid.get(0));
			numberMapper.numbernbdelete(nbid.get(0));
			return "删除成功";
		}
		
	}

	@Override
	public String numberdeleteall(String num) {
		Integer n = numberMapper.selectarrynbcount(num);
		if(n==0) {
			return "您删除数据不存在";
		}else {
			numberMapper.numberdeletearray(num);
			return "删除成功";
		}
	}

	@Override
	public List<Arraynb> download() {
		
		return numberMapper.numberlistarrayall();
	}

	@Override
	public List<Arraynb> downloadnum(String num) {
		List<Integer> nbid = numberMapper.selectarraynbid(num);
		
		return numberMapper.numberlist(nbid.get(0));
	}
}
