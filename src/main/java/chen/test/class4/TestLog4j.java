package chen.test.class4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog4j {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//trace<debug<info<warn<error<fatal
		/*
		 * 该类是：创建org.apache.logging.log4j.LogManager类，
		 * 然后调用该类LogManager中的方法，由弱到强依次是：
		 * trace：是指【 跟踪级别】
		 * debug：是指【调试级别】
		 * info：是指【信息级别】
		 * error：是指【错误级别】
		 * fatal：是指【致命级别】
		 */
		//创建log4j的logger对象（日志记录器）
		Logger logger=LogManager.getLogger(TestLog4j.class);
		logger.trace("this is trace");
		logger.debug("this is debug");
		logger.info("this is info.");
		logger.info("************测试**Start************");
		logger.warn("this is warning");
		try {
			Integer.parseInt("abc");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.error(e);
			//加上e.fillInStackTrace（）方法后，会把报错的具体位置也输出到控制台
			logger.error(e,e.fillInStackTrace());
		}
		logger.fatal("this is a fatal error");
		return;
	}

}
