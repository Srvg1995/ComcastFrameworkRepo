package practice.test;

import java.util.Date;

public class CaptureTimeStamp {
	public static void main(String[] args) {
		String time = new Date().toString().replace(" ", "_").replace(":", "_"); //Here we are using replace methods,otherwise we get the output with space&colon like this(Tue May 07 13:07:51 IST 2024) which can't be used as our file name) 
		System.out.println(time);
	}

}
