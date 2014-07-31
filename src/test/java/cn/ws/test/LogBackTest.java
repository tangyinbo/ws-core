package cn.ws.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackTest {
	private static final Logger log= LoggerFactory.getLogger(LogBackTest.class);
	public static void main(String[] args) {
		log.info("===>>>呵呵info");
		log.debug("===>>> hehe debug");
		log.error("====>>> 呵呵 error");
		log.trace("===>haha .....");
	}
}
