package org.study.base.collection.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<>();
		map.put("aa", "name");//没有同步锁
		Hashtable<String, String> table = new Hashtable<>();
		table.put("aa", "name");//有同步锁
		ConcurrentHashMap<String, String> concurrent = new ConcurrentHashMap<>();
		concurrent.put("aa", "name");//有同步锁
	}
}
