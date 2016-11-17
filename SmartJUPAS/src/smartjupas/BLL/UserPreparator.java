package smartjupas.BLL;
import java.util.Scanner;

import smartjupas.DAL.*;
import smartjupas.Model.*;

public class UserPreparator {

	private Scanner reader;
	
	public UserPreparator(){
		reader = new Scanner(System.in);
	}
	
	public User GenerateUser(){
		int taken;
		User user = new User();
		
		System.out.println("Please input your HKDSE result(5**=7, 5*=6, 5=5, 4=4...)");
		System.out.printf("Chinese: ");
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN, getInput()));
		System.out.printf("English: ");
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG, getInput()));
		System.out.printf("Mathematics: ");
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0, getInput()));
		System.out.printf("Liberal Studies: ");
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS, getInput()));
		
		System.out.printf("Please input your taken electives number(including M1/M2): ");	
		taken = getInput();
		for(int i=0; i<taken; i++){
			int code;
			int result;
			boolean valid;
			//check invalid elective code
			do{
				System.out.printf("Please input elective %d code: ",i+1);
				code = getInput();
				valid = user.isValid(code);
			} while(!valid);
			System.out.printf("Please input elective %d result: " ,i+1);
			result = getInput();
			user.addStudiedSubject(new StudiedSubject(SubjectEnum.getSubject(code), result));
		}
		return user;
	}
	
	private int getInput(){
		try{
		int r = reader.nextInt();
		return r;
		} catch (Exception e){
			e.printStackTrace();
			System.exit(0);
			return -1;
		}
	}
}
