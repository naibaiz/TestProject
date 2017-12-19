package com.sql;

import com.sql.interview.MyStack;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		MyStack<Integer> stack = new MyStack<Integer>();
		stack.push(1);
		stack.push(1);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println("Peek: " + stack.peek());
		stack.display();
		stack.push(6);
		System.out.println("After one push: 6");
		stack.display();
		System.out.println("After one pop: " + stack.pop());
		System.out.println("After one pop: " + stack.pop());
		System.out.println("After one pop: " + stack.pop());
		System.out.println("After one pop: " + stack.pop());
		System.out.println("After one pop: " + stack.pop());
		System.out.println("After one pop: " + stack.pop());
		System.out.println("After one pop: " + stack.pop());
		stack.display();
	}

}
