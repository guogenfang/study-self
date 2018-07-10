package data.structure;

import java.util.LinkedList;
import java.util.Queue;

public class TestQueue {

	public static Queue<String> addElement(){
		Queue<String> queue = new LinkedList<>();
		for(int i = 0; i < 5; i++){
			queue.offer("msg----" + i);
		}
        return queue;
	}
	
	public static void readWithoutDelete(){
		Queue<String> queue = addElement();
		String temp = queue.peek();
		System.out.println("readWithoutDelete---"+temp);
		/*
		 * Retrieves, but does not remove, the head of this queue. 
		 * This method differs from peek() only in that 
		 * it throws an exception if this queue is empty.
		 * peek() returns null if this queue is empty.
		 */
		temp = queue.element();
		System.out.println("queue size---"+temp);
	}
	
	public static void read(){
		Queue<String> queue = addElement();
		/*
         * remove()::Retrieves and removes the head of this queue. 
         * This method differs from poll() only in that
         * it throws an exception if this queue is empty.
         * poll() returns null if this queue is empty.
         * 
         */
		String str;
        while((str=queue.poll())!=null){    
            System.out.println(str);
        }
        System.out.println("queue size---" + queue.size());
	}
	
	public static void main(String[] args) {
		readWithoutDelete();
		read();
	}
}
