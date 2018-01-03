package org.design.pattern;

public class MiniDuckSimulator {

	public static void main(String[] args) {
		Duck mallard = new MallardDuck();
		mallard.display();
		mallard.performFly();
		mallard.performQuack();
		
		Duck model = new ModelDuck();
		model.display();
		model.performFly();
		model.performQuack();
		model.setFlyBehaviour(new FlyRocketPowered());
		model.performFly();
	}

}
