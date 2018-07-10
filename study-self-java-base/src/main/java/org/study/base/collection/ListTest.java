package org.study.base.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ListTest {
	
	public void fnArrayList(){
		print("fnArrayList");
		List<Integer> arr = new ArrayList<Integer>();
		for(int i=0;i<10000;i++){
			arr.add(i);
		}
		print("fnArrayList");
	}
	
	public void fnLinkedList(){
		print("fnLinkedList");
		LinkedList<Integer> arr = new LinkedList<Integer>();
		for(int i=0;i<10000;i++){
			arr.add(i);
		}
		print("fnLinkedList");
	}
	
	public void fnhashList(){
		Hashtable<?, ?> arr = new Hashtable();
		
		System.out.println(arr.hashCode());
	}
	
	public void vector(){
		print("vector");
		Vector<Integer> arr = new Vector<>();
		for(int i=0;i<10000;i++){
			arr.add(i);
		}
		print("vector");
	}
	
	public void print(String tag){
		System.out.println(tag + "---" + new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(new Date());
	}
}
