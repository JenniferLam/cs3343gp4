package smartjupas.BLL;
import smartjupas.DAL.*;
import smartjupas.Model.*;

public class MenuHandler {

	private IOController ioController;
	
	public MenuHandler(){
		ioController = new IOController();
	}
	
	public User GenerateUser(){
		int taken;
		User user = new User();
		
		System.out.println("Please input your HKDSE result(5**=7, 5*=6, 5=5, 4=4...)");
		System.out.printf("Chinese: ");
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN, ioController.getInput()));
		System.out.printf("English: ");
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG, ioController.getInput()));
		System.out.printf("Mathematics: ");
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0, ioController.getInput()));
		System.out.printf("Liberal Studies: ");
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS, ioController.getInput()));
		
		System.out.printf("Please input your taken electives number: ");	
		taken = ioController.getInput();
		for(int i=0; i<taken; i++){
			int code;
			int result;
			boolean valid;
			//check invalid elective code
			do{
				System.out.printf("Please input elective %d code: ",i+1);
				code = ioController.getInput();
				valid = user.isValid(code);
			} while(!valid);
			System.out.printf("Please input elective %d result: " ,i+1);
			result = ioController.getInput();
			user.addStudiedSubject(new StudiedSubject(SubjectEnum.getSubject(code), result));
		}
		return user;
	}
}
