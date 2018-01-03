package org.design.pattern;

import org.springframework.stereotype.Component;

@Component
public class Quack implements QuackBehaviour {

	@Override
	public void quack() {
		System.out.println("Quack");
	}
}
