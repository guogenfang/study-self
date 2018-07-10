package observer.java;

import java.util.Observable;
import java.util.Observer;

public class ObserverTest extends java.util.Observable{
	public static void main(String[] args) {
		ObserverTest test = new ObserverTest();
		Observer observer = new Observer() {
			public void update(Observable o, Object arg) {
				System.out.println("ssssssssssss");
			}
		};
		test.addObserver(observer);
		test.setChanged();
		test.notifyObservers();
	}
}
