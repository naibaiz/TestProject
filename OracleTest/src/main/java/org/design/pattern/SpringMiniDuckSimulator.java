package org.design.pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMiniDuckSimulator {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		Duck myDuck = context.getBean("myDuck", Duck.class);
		myDuck.performFly();
		myDuck.performQuack();
		myDuck.display();

		Duck myModelDuck = context.getBean("myModelDuck", Duck.class);
		myModelDuck.performFly();
		myModelDuck.performQuack();
		myModelDuck.display();
		
		Duck myMutelDuck = context.getBean("mySilentDuck", Duck.class);
		myMutelDuck.performFly();
		myMutelDuck.performQuack();
		myMutelDuck.display();		
		
		NewDuck myNewDuck = context.getBean("myNewDuck", NewDuck.class);
		myNewDuck.performFly();
		myNewDuck.performQuack();
		myNewDuck.display();
		System.out.println(myNewDuck.getEmailAddress());
		System.out.println(myNewDuck.getTeam());
		
	}

}
