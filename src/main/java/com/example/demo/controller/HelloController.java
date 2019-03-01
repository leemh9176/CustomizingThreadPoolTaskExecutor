package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ThreadThreadApplication;
import com.example.demo.service.HelloService;

@RestController
public class HelloController {

	@Autowired
    HelloService helloService;

    @RequestMapping("/")
    public String hello(){
    	System.out.println("controller start");
    	System.out.println("current thread id = " + Thread.currentThread().getId());
    	
    	helloService.getHello();
    	
        System.out.println("로그 컨트롤러" + ThreadThreadApplication.getTransactionId());
//        return helloService.getHello();
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String trxId = ThreadThreadApplication.getTransactionId();
        System.out.println("Current Thread Id = " + Thread.currentThread().getId());
        System.out.println("transaction Id = " + trxId);
        return "hello world";
    }
	
}
