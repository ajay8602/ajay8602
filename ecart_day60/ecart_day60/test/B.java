import java.util.Random;

public class B{
	public static void main(String[] args){
		generateOTP();
	}
	
	public static void generateOTP(){
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for(int i=0;i<6;i++){
			sb.append(r.nextInt(10));
		}

		System.out.println(sb.toString());
	}
}