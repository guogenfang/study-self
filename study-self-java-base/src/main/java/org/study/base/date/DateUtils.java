package org.study.base.date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class DateUtils {
    List<String> timers = new ArrayList<String>();
    private DateUtils dateUtils = new DateUtils();
    private DateUtils(){}

    public DateUtils getInstance(){
        return dateUtils;
    }
    
    public static void main(String[] args) throws Exception{
    	Calendar calendar = Calendar.getInstance();
		calendar.set(0, 0, 0, 0, 0, 0);
		for(int i=0;i<100;i++){
			calendar.add (Calendar.SECOND, 1);
			Thread.sleep(1000);
			System.out.println(Calendar.getInstance().getTime());
			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			try {
				System.out.println (format.format(calendar.getTime ()));
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
		}
	}
}
