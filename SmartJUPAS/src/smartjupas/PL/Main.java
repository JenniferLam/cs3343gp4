package smartjupas.PL;

import java.util.List;
import java.util.Scanner;

import smartjupas.BLL.*;
import smartjupas.Model.*;
import java.io.*;

public class Main {

	public static void main(String[] args){

		ProgrammePreparator _ProgrammePreparator = ProgrammePreparator.getInstance();
		Calculator _ProgrammeCalculator = Calculator.getInstance();
		UI _UI = UI.getInstance(); 
		
		User user;
		List<Programme> ProgrammeList;
		List<Programme> OutputList;
		
		_UI.CreateMenu();
		user = _UI.getUserInput();
		
		//Testing by Ray
		//Scanner reader = new Scanner(System.in);
		//user.setPreference(reader.nextInt());
		
		ProgrammeList = _ProgrammePreparator.PrepareList();
		
		//Update user score for each programme
		OutputList = _ProgrammeCalculator.CalculateUserScore(ProgrammeList, user);
		
		//Filter by preference
		OutputList = Filter.FilterProgrammeByPreference(OutputList, user);
		
		//Filter by score
		OutputList = Filter.FilterProgrammeByScore(OutputList, -10);		
		
		_UI.GenerateOutput(OutputList);
	}  
}
