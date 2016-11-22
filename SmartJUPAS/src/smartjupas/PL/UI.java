package smartjupas.PL;

import smartjupas.Model.*;

import java.util.List;

import smartjupas.BLL.*;

public class UI {

	private UserPreparator UserPreparator;

	public UI() {
		UserPreparator = new UserPreparator();
	}

	public void CreateMenu() {
		System.out.println("*****2017 HKDSE Smart JUPAS*****");
		System.out.println("Electives Code:");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("1. Mathematics Extended Module 1");
		System.out.println("2. Mathematics Extended Module 2");
		System.out.println("3. Physics");
		System.out.println("4. Chemistry");
		System.out.println("5. Biology");
		System.out.println("6. Science: Integrated Science");
		System.out.println("7. Science: Integrated Science(Physics and Chemistry)");
		System.out.println("8. Science: Integrated Science(Physics and Biology)");
		System.out.println("9. Science: Integrated Science(Chemistry and_Biology)");
		System.out.println("10. Physics Education");
		System.out.println("11. Information & Communication Technology");
		System.out.println("12. Technology and Living");
		System.out.println("13. Geography");
		System.out.println("14. Business, Accounting and Financial Studies");
		System.out.println("15. Economics");
		System.out.println("16. Design and Applied Technology");
		System.out.println("17. History");
		System.out.println("18. Chinese History");
		System.out.println("19. Chinese Literature");
		System.out.println("20. Literature in English");
		System.out.println("21. Others");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println("Preferences Code:");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("1. ART/SocSci");
		System.out.println("2. BBA");
		System.out.println("3. ENG/SCI");
		System.out.println("4. EDU");
		System.out.println("5. LAW");
		System.out.println("6. MED");
		System.out.println("7. ASSO / HD");
		System.out.println("---------------------------------------------------------------------------------");
	}

	public User getUserInput() {
		return UserPreparator.GenerateUser();
	}

	public void GenerateOutput(List<Programme> olist) {
		System.out.println();
		System.out.println();
		
		System.out.println("Suggested JUPAS Choice List:");
		System.out.println("#\tJSCode\tUniversity\tDiff.\tProgramme");
		System.out.println("---------------------------------------------------------------------------------");
		for (int i = 0; i < olist.size(); i++) {
			Programme p = olist.get(i);
			System.out.printf("%d.\tJS%s\t%s\t\t%.2f%%\t%s\n", (i + 1), p.getJscode(),getUniversityName(p.getJscode()),p.getScoreDifferencePercentage(), p.getName()
					);
		}
		System.out.println("---------------------------------------------------------------------------------");
	}

	private String getUniversityName(String jscode){
		int code = jscode.charAt(0);
		String universityName;
		switch(code){
		case '1':
			universityName = "CityU";
			break;
		case '2':
			universityName = "BU";
			break;
		case '3':
			universityName = "PolyU";
			break;
		case '4':
			universityName = "CUHK";
			break;
		case '5':
			universityName = "HKUST";
			break;
		case '6':
			universityName = "HKU";
			break;
		case '7':
			universityName = "LU";
			break;
		case '8':
			universityName = "EdUHK";
			break;
		case '9':
			universityName = "OUHK";
			break;
		default:
			universityName = "";
			break;		
		}
		return universityName;
	}
}
