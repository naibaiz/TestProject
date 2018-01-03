package org.design.pattern;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAnnotationApp {

	public static void main(String[] args) {
		// load app xml
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationAnnotation.xml");
		
		// get a bean
		Duck duck = context.getBean("mallardDuck", Duck.class);
		
		// call a method on a bean
		duck.display();
		duck.performFly();
		
		// close context
		context.close();
	}
}
