package org.design.pattern;

public class SilentQuack implements QuackBehaviour {

	@Override
	public void quack() {
		System.out.println("<<silent...>>");
	}

}
