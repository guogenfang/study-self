import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**[简要描述]：
 * @author ggf
 * 2018年2月6日
 */
public class ByteTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		byte [] b1 = {123,34,115,116,97,116,117,115,34,58,49,44,34,100,111,99,80,97,116,104,34,58,34,104,116,116,112,58,47,47,49,57,50,46,49,54,56,46,54,48,46,52,58,52,48,55,48,47,112,117,98,108,105,99,47,49,48,48,49,48,95,49,48,48,49,48,46,116,120,116,34,125};
		byte [] b2 = {123,34,115,116,97,116,117,115,34,58,49,44,34,114,101,99,111,114,100,80,97,116,104,34,58,34,104,116,116,112,58,47,47,49,57,50,46,49,54,56,46,54,48,46,52,58,52,48,55,48,47,112,117,98,108,105,99,47,49,48,48,49,48,95,49,48,48,49,48,34,125};
		
		System.out.println(new String(b1,"utf-8"));
		System.out.println(new String(b2,"utf-8"));
		
		System.out.println(System.currentTimeMillis());
		System.out.println(System.currentTimeMillis() / 1000);
		System.out.println((int)(System.currentTimeMillis() / 1000));
		
		String role = "2,3,4";
		
	}
}
