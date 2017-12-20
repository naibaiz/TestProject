package com.sql.interview;

import java.util.NoSuchElementException;

public class MyQueue<T> {

	private class MyQueueNode<T> {
		private T data;
		private MyQueueNode<T> next;

		private MyQueueNode(T data) {
			this.data = data;
		}
	}

	private MyQueueNode<T> first;
	private MyQueueNode<T> last;

	public void add(T data) {
		MyQueueNode<T> node = new MyQueueNode<T>(data);
		if (last != null) {
			last.next = node;
		}
		last = node;
		if (first == null) {
			first = last;
		}
	}

	public T remove() {
		if (first == null) { // nothing to remove
			throw new NoSuchElementException();
		}
		T data = first.data;
		first = first.next;
		if (first == null) { // nothing left after remove
			last = null;
		}
		return data;
	}

	public T peek() {
		if (first == null) { // nothing to peek
			throw new NoSuchElementException();
		}
		return first.data;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void display() {
		String value = "{ ";
		MyQueueNode<T> temp = first;
		while (first != null) {
			value += (first.data + " ");
			first = first.next;
		}
		value += "}";
		System.out.println(value);
		first = temp;
	}
}
