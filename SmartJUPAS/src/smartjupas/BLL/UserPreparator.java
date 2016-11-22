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
		int preference;
		User user = new User();
		
		System.out.println("Please input your HKDSE result(5**=7, 5*=6, 5=5, 4=4..., U=0)");
		System.out.printf("Chinese: ");
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN, getInput("result")));
		System.out.printf("English: ");
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG, getInput("result")));
		System.out.printf("Mathematics: ");
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0, getInput("result")));
		System.out.printf("Liberal Studies: ");
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS, getInput("result")));
		
		System.out.printf("Please input your taken electives number(including M1/M2): ");	
		taken = getInput("electiveN");
		for(int i=0; i<taken; i++){
			int code;
			int result;
			boolean valid;
			//check invalid elective code
			do{
				System.out.printf("Please input elective %d code: ",i+1);
				//checked by User.isValid
				code = getInput("");
				valid = user.isValid(code);
			} while(!valid);
			System.out.printf("Please input elective %d result: " ,i+1);
			result = getInput("result");
			user.addStudiedSubject(new StudiedSubject(SubjectEnum.getSubject(code), result));
		}
		System.out.println("Please input one preference code from the preference list: ");
		preference = getInput("preference");
		user.setPreference(preference);
		return user;
	}
	
	private int getInput(String type){
		//type: result, electiveN, preference
		try{
			int r = 0;
			boolean valid = false;
			switch(type){
			case "result":
				do{
					r = reader.nextInt();
					if(r>=0 && r<=7)
						valid = true;
					else{
						valid = false;
						System.out.println("The subject result must be in range 0-7, please try again");
					}
				} while(!valid);
				break;
			case "electiveN":
				do{
					r = reader.nextInt();
					if(r>=0 && r<=5)
						valid = true;
					else{
						valid = false;
						System.out.println("The maximum input number of elective is restricted as 5, please try again");
					}
				} while(!valid);
				break;
			case "preference":
				do{
					r = reader.nextInt();
					if(r>=1 && r<=51)
						valid = true;
					else{
						valid = false;
						System.out.println("Preference code is incorrect, please try again");
					}
				} while(!valid);
				break;
			default: 
				r = reader.nextInt();
				break;
			}
		return r;
		} catch (Exception e){
			e.printStackTrace();
			System.exit(0);
			return -1;
		}
	}
}
