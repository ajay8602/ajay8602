package utils;


import java.util.Random;

public class OTPGenerator{	
	public static String generateOTP(){
		StringBuffer sb = new StringBuffer();
		Random r = new Random();

		for(int i=0;i<6;i++){
			sb.append(r.nextInt(10));
		}

		return sb.toString();
	}
}