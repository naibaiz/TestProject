package org.design.pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDuckApp {

	public static void main(String[] args) {
		// load spring config file
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationBeanScope.xml");
		
		// retrieve bean from spring container
		Duck myDuck = context.getBean("myDuck", Duck.class);	
		
		Duck myAlphaDuck = context.getBean("myDuck", Duck.class);
		
		// is this same?
		System.out.println(myDuck == myAlphaDuck);
		System.out.println("Memory location for myDuck: " + myDuck);
		System.out.println("Memory location for myAlphaDuck: " + myAlphaDuck);
	}

}
