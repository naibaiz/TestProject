package org.design.pattern;

import org.springframework.stereotype.Component;

@Component
public class MallardDuck extends Duck {

	public MallardDuck() {
//		quackBehaviour = new Quack();
//		flyBehaviour = new FlyWithWings();
	}
	
	// add custom hook init method
	public void doMyStartupStuff() {
		System.out.println("MallardDuck: inside method doMyStartupMethod");
	}
	
	// add custom hook destroy method
	public void doMyDestroyStuffYoYo() {
		System.out.println("MallardDuck: inside method doMyDestroyStuffYoYo");
	}
	
	@Override
	public void display() {
		System.out.println("I am a mallard duck.");
	}
}
