package org.study.base.collection.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**[简要描述]：
 * @author ggf
 * 2018年7月26日
 */
public class HashMap01 {

	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("1", 11);
		map.put("2", 22);
		map.put("3", 33);
		map.put("4", 44);
		map.put("4", 44);
		map.remove("3");
		System.out.println(map.keySet());
        String [] ar = genKeys(10000);

        System.out.println(15 & hash("1"));

        Set set = new HashSet();
        Set set2 = new HashSet();

        for (int i = 0; i < ar.length; i++) {
//            System.out.println(hash(ar[i]) + "====" + ar[i]);
            set.add(hash(ar[i]));
            set2.add(ar[i]);
        }
        System.out.println(set.size());
        System.out.println(set2.size());
	}

    public static String[] genKeys(int number) {
        String[] ary = new String[number];
        int length = 0;
        for(int j = 0; j < number; j++) {
            String temp = "";
            length = (int)(Math.random() * 8 + 2);
            for(int i = 0; i < length; i++) {
                int intValue = (int)(Math.random() * 26 + 97);
                temp += (char)intValue;
            }
            ary[j] = temp;
        }
        return ary;
    }

    public static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
