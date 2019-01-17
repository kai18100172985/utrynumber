package com.utry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utry.util.RedisClient;

@RestController
public class testController {
	 @Autowired  
    private RedisClient redisClinet;  
 
    @RequestMapping("/set")  
    public String set(String key, String value) throws Exception{  
        redisClinet.set(key, value);  
        return "success";  
    }  
      
    @RequestMapping("/get")  
    public String get(String key) throws Exception {  
        return redisClinet.get(key);  
    }  
}
