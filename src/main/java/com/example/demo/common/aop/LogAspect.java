package com.example.demo.common.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.example.demo.ThreadThreadApplication;

@Aspect
public class LogAspect {
	
	public LogAspect() {
		// TODO Auto-generated constructor stub
		System.out.println("aspect class contructor");
		System.out.println("aspect class contructor");
		System.out.println("aspect class contructor");
		System.out.println("aspect class contructor");
		System.out.println("aspect class contructor");
	}
	
	@Before(value = "execution(* com.example.demo.controller.HelloController.*(..))")
	public void makeTransactionID(JoinPoint joinPoint) throws Throwable {
		
		System.out.println("makeTransactonID aop method");
		String trxId = System.currentTimeMillis() + "sdf";
		System.out.println("Current Thread ID = " + Thread.currentThread().getId() + ", trx id = " + trxId);
		ThreadThreadApplication.setTransactionId(trxId);
		
	}

	@Before(value = "execution(* com.example.demo.service.HelloService.*(..))")
	public void originThreadLogging(JoinPoint joinPoint) throws Throwable {
		
		String trxId = ThreadThreadApplication.getTransactionId();
		long threadID = Thread.currentThread().getId();
		
		System.out.println("Origin Thread");
		System.out.println("Thread ID = " + threadID);
		System.out.println("trx ID = " + trxId);
		
	}
	
	@Before(value = "execution(* com.example.demo.service.AsyncService.*(..))")
	public void asyncThreadLogging(JoinPoint joinPoint) throws Throwable {
		
		String trxId = ThreadThreadApplication.getTransactionId();
		long threadID = Thread.currentThread().getId();
		
		System.out.println("Async Thread");
		System.out.println("Thread ID = " + threadID);
		System.out.println("trx ID = " + trxId);
		
	}
	
}
