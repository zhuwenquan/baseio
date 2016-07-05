package com.test.servlet;

import java.util.concurrent.atomic.AtomicInteger;

import com.gifisan.nio.common.Logger;
import com.gifisan.nio.common.LoggerFactory;
import com.gifisan.nio.common.StringUtil;
import com.gifisan.nio.component.Session;
import com.gifisan.nio.component.future.ReadFuture;
import com.gifisan.nio.component.future.WriteFuture;
import com.gifisan.nio.extend.service.FutureAcceptorService;

public class TestSimpleServlet extends FutureAcceptorService{
	
	public static final String SERVICE_NAME = TestSimpleServlet.class.getSimpleName();
	
	private Logger logger = LoggerFactory.getLogger(TestSimpleServlet.class);
	
	private TestSimple1 simple1 = new TestSimple1();
	
//	private AtomicInteger size = new AtomicInteger();

	public void accept(Session session,ReadFuture future) throws Exception {

//		accept.getAndIncrement();
		
		String test = future.getText();

		if (StringUtil.isNullOrBlank(test)) {
			test = "test";
		}
		future.write(simple1.dynamic());
		future.write(test);
		future.write("$");
		session.flush(future);
		
//		System.out.println("=============================="+size.incrementAndGet());
	}

//	private AtomicInteger sent = new AtomicInteger(1);
//	
//	private AtomicInteger accept = new AtomicInteger(0);
//	
//	public void futureSent(Session session, WriteFuture future) {
//		logger.info("sent:{}",sent.getAndIncrement());
//		logger.info("accept:{}",accept.get());
//	}

}
