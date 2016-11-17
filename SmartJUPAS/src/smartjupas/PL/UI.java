package smartjupas.PL;

import smartjupas.Model.*;

import java.util.List;

import smartjupas.BLL.*;

public class UI {
	
	private UserPreparator UserPreparator;
	
	public UI(){
		UserPreparator = new UserPreparator();
	}
	
	
	
	public void CreateMenu(){
		System.out.println("*****2017 HKDSE Smart JUPAS*****");
		System.out.println("Elective Code:");
		System.out.println("1. Physics");
		System.out.println("2. Chemistry");
		System.out.println("3. Biology");
		System.out.println("4. Science: Integrated Science");
		System.out.println("5. Science: Integrated Science(Physics and Chemistry)");
		System.out.println("6. Science: Integrated Science(Physics and Biology)");
		System.out.println("7. Science: Integrated Science(Chemistry and_Biology)");
		System.out.println("8. Physics Education");
		System.out.println("9. Information & Communication Technology");
		System.out.println("10. Technology and Living");
		System.out.println("11. Geography");
		System.out.println("12. Business, Accounting and Financial Studies");
		System.out.println("13. Economics");
		System.out.println("14. Design and Applied Technology");
		System.out.println("15. History");
		System.out.println("16. Chinese History");
		System.out.println("17. Chinese Literature");
		System.out.println("18. Literature in English");
		System.out.println("19. Tourism and Hospitality Studies");
		System.out.println("20. Health Management and Social Care");
		System.out.println("21. Music");
		System.out.println("22. Visual Arts");
		System.out.println("23. Mathematics Extended Module 1");
		System.out.println("24. Mathematics Extended Module 2");
		System.out.println("25. Others");
	}
	
	public User getUserInput(){
		return UserPreparator.GenerateUser();
	}
	
	public void GenerateOutput(List<Programme> olist){
		System.out.println();
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Suggested JUPAS Choice List:");
		for(int i=0; i<20; i++){
			Programme p = olist.get(i);
			System.out.printf("%d. %s %s \n", (i+1), p.getJscode(), p.getName());
		}
		System.out.println("-----------------------------------------------------------------------");
	}
}
