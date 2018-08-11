package org.study.base.server.slb;

/**一致性哈希算法Test类
 * author:Qcer
 * date:2018/07/18
 * */
public class ConsistentHashTest {

    // 产生随机字符串，视为key
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

    public static void main(String[] args){
        String[] nodes = {
                "10.10.25.11:6379",
                "10.10.25.12:6379",
                "10.10.25.13:6379",
                "10.10.25.14:6379",
                "10.10.25.15:6379"};

        int keyCount = 10000;
        String[] keys = genKeys(keyCount);

        System.out.println("--------初始状态-------");
        ConsistentHash ch = new ConsistentHash(nodes,200);
        ch.testLoadBalance(keys);

        System.out.println("--------模拟上线-------");
        String[] onLine = {"10.10.25.20:6379","10.10.25.21:6379"};
        ch.addNode(onLine);
        ch.testLoadBalance(keys);

        System.out.println("--------模拟下线-------");
        String[] offLine = {"10.10.25.11:6379","10.10.25.14:6379"};
        ch.removeNode(offLine);
        ch.testLoadBalance(keys);

        System.out.println("--------获取元数据-------");
        ch.getMetaData();
    }
}