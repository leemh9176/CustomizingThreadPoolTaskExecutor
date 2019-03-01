package com.example.demo;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.example.demo.common.MyThreadPoolTaskExecutor;

@EnableAsync
@SpringBootApplication
public class ThreadThreadApplication {

	public static ThreadLocal<String> baseThreadId = new ThreadLocal<>();


	/**
	 * 스레드 아이디를 가져올때 null 이거나 0이면 현재 스레드 아이디 반환
	 * @return 부모 혹은 내 스레드 아이디
	 */
//	public static String getTransactionId(){
//		String maybeThreadId = baseThreadId.get();
//		if(maybeThreadId == null) {
//			return Thread.currentThread().getId();
//		}
//
//		String threadId = maybeThreadId;
//
//		if (threadId.isEmpty()) {
//			return Thread.currentThread().getId();
//		}
//
//		return threadId;
//	}
	
	public static String getTransactionId() {
		String trxID = baseThreadId.get();
		if(trxID == null || trxID.isEmpty()) {
			System.out.println("trx id is null current thread id = " + Thread.currentThread().getId());
			return null;
		}
		else {
			return trxID;
		}
	}
	
	public static void setTransactionId(String trxId) {
		baseThreadId.set(trxId);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ThreadThreadApplication.class, args);
	}

	
	
}
