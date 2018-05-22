package org.service.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {

	public static void main(String[] args) throws Exception {
		System.setProperty("java.net.preferIPv4Stack", "true");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/user-provider.xml"});
		context.start();
		System.in.read();
	}
}
