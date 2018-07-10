package hash;

import org.junit.Test;

public class StrHash {
	private int hash;
	
	@Test
	public void TestClassLoader(){
		char [] abc = {'a','b'};
		copyOf(abc, abc.length);
		toCharArray(abc);
	}
	
	// Cannot use Arrays.copyOf because of class initialization order issues
	public char[] toCharArray(char[] value) {
        char result[] = new char[value.length];
        System.arraycopy(value, 0, result, 0, value.length);
        return result;
    }
	
	public char[] copyOf(char[] original, int newLength) {
        char[] copy = new char[newLength];
        System.arraycopy(original, 0, copy, 0,Math.min(original.length, newLength));
        return copy;
    }
	
	@Test
	public void equal(){
		
		String ss = "123";
		Integer ii = 48690;
		System.out.println(hashCode(ss.toCharArray()));
		System.out.println(ii.hashCode());
		
	}
	
	public int hashCode(char[] value) {
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
	
	public static void main(String[] args) {  
        String str = "abc";  
        char[] array = {'a', 'b', 'c'};  
        String str2 = new String(array);  
        //使用intern()将str2字符串内容放入常量池  
        str2 = str2.intern();  
        //这个比较用来说明字符串字面常量和我们使用intern处理后的字符串是在同一个地方  
        System.out.println(str == str2);  
        
    }  
}
