package smartjupas.PL;

import java.util.List;
import smartjupas.BLL.*;
import smartjupas.Model.*;
import java.io.*;

public class Main {

	
	
	public static void main(String[] args){

		ProgrammePreparator ProgrammePreparator = new ProgrammePreparator();
		UI ui = new UI();
		User user;
		List<Programme> ProgrammeList;
		List<Programme> OutputList;
		
		
		ui.CreateMenu();
		user = ui.getUserInput();
		ProgrammeList = ProgrammePreparator.PrepareList();
		
		//Update user score for each programme
		OutputList = ProgrammePreparator.UpdateUserScore(ProgrammeList, user);
		
		//Filter by score
		OutputList = Filter.FilterProgrammeByScore(OutputList, -10);
		
		ui.GenerateOutput(OutputList);
	}
}
