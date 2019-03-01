package com.example.demo.common;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import com.example.demo.ThreadThreadApplication;

public class MyThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
	
	private Runnable doMagic(Runnable task) {
		final String trxId = ThreadThreadApplication.getTransactionId();
		long prevthreadId = Thread.currentThread().getId();
        return new Runnable() {
			public void run() {
				if(trxId == null) {
					ThreadThreadApplication.setTransactionId(trxId);
					System.out.println("prev trxid is null, Thread Id = " + prevthreadId);
				}
	            task.run();
	            ThreadThreadApplication.baseThreadId.set(null);
			}
		};
            
       
    }

    private <T> Callable<T> doMagic(Callable<T>  task) {
    	final String trxId = ThreadThreadApplication.getTransactionId();
        return () -> {
            ThreadThreadApplication.baseThreadId.set(trxId);
            T result = task.call();
            ThreadThreadApplication.baseThreadId.set(null);
            return result;
        };
    }

    @Override
    public void execute(Runnable task) {
        super.execute(doMagic(task));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        super.execute(doMagic(task), startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(doMagic(task));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(doMagic(task));
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        return super.submitListenable(doMagic(task));
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        return super.submitListenable(doMagic(task));
    }

}
