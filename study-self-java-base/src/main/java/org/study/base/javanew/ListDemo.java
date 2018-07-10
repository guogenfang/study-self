package org.study.base.javanew;

import java.util.List;

public class ListDemo {
	public static void main(String[] args) {
//		List<Point> list = List.of(new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4));
//		list.forEach(System.out::println);
	}
}

class Point {
	private Integer x;
	private Integer y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
	
}
