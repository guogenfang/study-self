package org.study.base.hash;

/**[简要描述]：
 * @author ggf
 * 2018年7月25日
 */
public class HashDemo {
	public static void main(String[] args) {
		String v = "123";
		System.out.println(hashCode(v.toCharArray()));
		System.out.println(hashTo(v));
	}
	
	public static int hashCode(char [] value) {
		int hash = 0;
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
	
	static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
	
	static final int hashTo(Object key) {
        int h;
        if(key == null) {
        	return 0;
        }
        h = key.hashCode();
        System.out.println(get32BitBinString(h));
        int tmp = h >>> 16;
        System.out.println(tmp);
        h = h ^ tmp;
        
        return h;
    }
	
	private static String get32BitBinString(int number) {
	    StringBuilder sBuilder = new StringBuilder();
	    for (int i = 0; i < 32; i++){
	        sBuilder.append(number & 1);
	        number = number >>> 1;
	    }
	    return sBuilder.reverse().toString();
	}
}
