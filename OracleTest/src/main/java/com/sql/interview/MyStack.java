package com.sql.interview;

import java.util.EmptyStackException;

public class MyStack<T> {	
	private class MyStackNode<T> {
		T data;
		MyStackNode<T> next;
		
		private MyStackNode(T data) {
			this.data = data;
		}
	}
	
	private MyStackNode<T> top;
	
	/**
	 * 
	 * @param item to be pushed on to the top of the stack
	 */
	public void push(T item) {
		MyStackNode<T> node = new MyStackNode<T>(item);
		node.next = top;
		top = node;
	}
	
	public T pop() {
		if (top == null) {
			throw new EmptyStackException();
		}
		T data = top.data;
		top = top.next;
		return data;		
	}	
	
	public T peek() {
		return top.data;
	}
	
	public void display() {
		String value = "{ ";
		MyStackNode<T> temp = top;
		while (top != null) {
			value += (top.data + " ");
			top = top.next;
		}
		value += "}";
		System.out.println(value);
		top = temp;
	}
}