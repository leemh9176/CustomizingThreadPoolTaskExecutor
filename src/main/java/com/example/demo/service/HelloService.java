package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.ThreadThreadApplication;

@Service
public class HelloService {

	@Autowired AsyncService asyncService;
	
    public String getHello() {
		System.out.println("Hello Service");
		System.out.println("Hello Service Thread ID = " + Thread.currentThread().getId());
		
		asyncService.asyncService();

		try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
//        System.out.println("로그 서비스" + ThreadThreadApplication.getTransactionId());
        return "Hello";
    }
	
}
