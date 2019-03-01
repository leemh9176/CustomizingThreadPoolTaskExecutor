package com.example.demo.service;

import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	@Async
	public void asyncService() {
		System.out.println("Async Service");
		System.out.println("Async Service Thread ID = " + Thread.currentThread().getId());
		try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
}
