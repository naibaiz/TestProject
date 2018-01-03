package org.design.pattern;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class Duck {
	FlyBehaviour flyBehaviour;
	QuackBehaviour quackBehaviour;

	public Duck() {

	}

	public void performQuack() {
		quackBehaviour.quack();
	}

	public void performFly() {
		flyBehaviour.Fly();
	}
	
	public abstract void display();
	
	public void swim() {
		System.out.println("All ducks float, even decoys!");
	}

	public FlyBehaviour getFlyBehaviour() {
		return flyBehaviour;
	}

	public QuackBehaviour getQuackBehaviour() {
		return quackBehaviour;
	}

	@Autowired
	public void setFlyBehaviour(FlyBehaviour flyBehaviour) {
		this.flyBehaviour = flyBehaviour;
	}

	@Autowired
	public void setQuackBehaviour(QuackBehaviour quackBehaviour) {
		this.quackBehaviour = quackBehaviour;
	}
	
	
}
