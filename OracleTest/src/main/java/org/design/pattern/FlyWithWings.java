package org.design.pattern;

import org.springframework.stereotype.Component;

@Component
public class FlyWithWings implements FlyBehaviour {

	@Override
	public void Fly() {
		System.out.println("I'm flying");
	}
}
