package test;

import java.util.ArrayList;
import java.util.List;

public class CollTest {
	public static void main(String[] args) {
		List<Integer> A = new ArrayList<Integer>();
		A.add(1);A.add(2);A.add(3);A.add(4);
		List<Integer> B = new ArrayList<Integer>();
		B.add(88);B.add(3);B.add(4);B.add(5);
		for(int i=0,j=0;i<A.size();){
			System.out.println("---i---" + i);
			if(A.get(i) == B.get(j)){
				System.out.println(A.get(i));
				i++;
			}
			else{
				j++;
				if(j == B.size()){
					i++;j=0;
				}
			}
		}
	}
}
