package designpatterns.bridge;

import org.junit.Test;
import designpatterns.bridge.model.GoodRectangle;
import designpatterns.bridge.model.GoodShape;
import designpatterns.bridge.platform.WindowsDraw;

public class TestObj {
	@Test
	public void createObj(){
		ShapeDraw shapeDraw = new WindowsDraw();
		GoodShape shap = new GoodRectangle(shapeDraw);
		System.out.println(shap.toString());
	}
}
