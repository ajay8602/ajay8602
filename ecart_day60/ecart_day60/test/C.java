import java.text.SimpleDateFormat;
import java.util.Date;

class C{
	public static void main(String[] args){
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");

		Date dt = new Date();
		System.out.println(dt);


		System.out.println(sf.format(dt));
	}
}