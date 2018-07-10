package doc;

import java.util.ArrayList;
import java.util.List;

public class AT {
	public static void main(String[] args) {
		String a = "111";
		List<String> list = new ArrayList<String>();
		list.add(a);
		a = "dfadf";
		list.add(a);
		System.out.println(list.toString());
	}
}
