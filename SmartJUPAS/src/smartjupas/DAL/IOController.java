package smartjupas.DAL;
import java.util.Scanner;

public class IOController {

	private Scanner reader;
	
	public IOController(){
		
	}
	
	public int getInput(){
		try{
		reader = new Scanner(System.in);
		int r = reader.nextInt();
		return r;
		} catch (Exception e){
			e.printStackTrace();
			System.exit(0);
			return -1;
		}
		
	}
}
