package net.ityin.imaqu.test.main;
import java.util.Calendar;

/**
 * (c)Xopen Ltd. All Rights Reserved.
 */

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public class TestCalendar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		System.out.println("before rolling: " + c.get(Calendar.DAY_OF_YEAR));
		System.out.println("before rolling: " + c.getTime());
		c.roll(Calendar.DAY_OF_YEAR, 1);
		System.out.println("after rolling: " + c.get(Calendar.DAY_OF_YEAR));
		System.out.println("after rolling: " + c.getTime());

		Calendar c1 = Calendar.getInstance();
		System.out.println("before adding: " + c1.get(Calendar.DAY_OF_YEAR));
		System.out.println("before adding: " + c1.getTime());
		c1.add(Calendar.DAY_OF_YEAR, 999999);
		System.out.println("after adding: " + c1.get(Calendar.DAY_OF_YEAR));
		System.out.println("after adding: " + c1.getTime());
	}

}
