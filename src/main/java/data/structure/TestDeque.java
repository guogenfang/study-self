package data.structure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class TestDeque {
	public static void create(){
		Deque<String> deque = new ArrayDeque<>(1);
		deque.add("11");
		deque.offer("12");
		deque.addFirst("14");
		System.out.println(deque.size());
	}
	
	public void operate(){
		Deque<String> deque = new LinkedList<>();
		deque.addFirst("msg-1");
		deque.add("msg-2");
		System.out.println(" head " + deque.element());
		deque.offerFirst("msg-3");
		System.out.println(" head " + deque.element());
		deque.add("msg-4");
		System.out.println(" head " + deque.element());
		deque.pollFirst();
		System.out.println(" head " + deque.element());
		System.out.println(" last " + deque.getLast());
	}
	
	public static void main(String[] args) {
		create();
	}
}
