package org.study.base.javanew;

public class InterfaceDefault{
	
	public static void main(String[] args) {
		Formula formula = new Formula() {
			@Override
			public double calculate(int a) {
				return sqrt(100 * a);
			}
		};
		
		System.out.println(formula.calculate(12));
		
	}
	
}

interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
    
    static int getVal(int i) {
    	return i;
    }
}
