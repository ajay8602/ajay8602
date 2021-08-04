import java.util.Random;

class A{
	public static void main(String[] args){
		Random r = new Random();
		
		long val = r.nextLong();

		if(val<0)
			val *= -1;

		System.out.println(val);
	}
}