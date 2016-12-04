package smartjupas.TEST;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import smartjupas.*;
import smartjupas.BLL.Calculator;
import smartjupas.BLL.Filter;
import smartjupas.BLL.ProgrammePreparator;
import smartjupas.BLL.UserPreparator;
import smartjupas.DAL.DBController;
import smartjupas.Model.CompulsoryEnum;
import smartjupas.Model.Programme;
import smartjupas.Model.StudiedSubject;
import smartjupas.Model.Subject;
import smartjupas.Model.SubjectEnum;
import smartjupas.Model.User;
import smartjupas.PL.Main;
import smartjupas.PL.UI;
import static org.junit.Assert.*;

public class Testing {
	
	
	//bottom up approach
	//Unit Test
	@Test
	//Subject.java
	public void testSub01() throws Exception{
		Subject subject = new Subject(SubjectEnum.CHIN,1,"Compulsory");
		CompulsoryEnum result = subject.isCompulsory();
		assertEquals(result,CompulsoryEnum.Compulsory);
	}
	
	@Test
	//Subject.java
	public void testSub02() throws Exception{
		Subject subject = new Subject(SubjectEnum.GEO,1,"NotCompulsory");
		CompulsoryEnum result = subject.isCompulsory();
		assertEquals(result,CompulsoryEnum.NotCompulsory);
	}
	
	@Test
	//Subject.java
	public void testSub03() throws Exception{
		Subject subject = new Subject(SubjectEnum.M1,1,"Either1");
		CompulsoryEnum result = subject.isCompulsory();
		assertEquals(result,CompulsoryEnum.Either1);
	}
	
	@Test
	//Subject.java
	public void testSub04() throws Exception{
		Subject subject = new Subject(SubjectEnum.M2,1,"Either2");
		CompulsoryEnum result = subject.isCompulsory();
		assertEquals(result,CompulsoryEnum.Either2);
	}
	
	@Test
	//Subject.java
	public void testSub05() throws Exception{
		Subject subject = new Subject(SubjectEnum.M2,1,"Either2");
		assertEquals(subject.getName(),SubjectEnum.M2);
	}
	
	@Test
	//Subject.java
	public void testSub06() throws Exception{
		Subject subject = new Subject(SubjectEnum.M2,1,"Either2");
		float res = 1;
		assertEquals(subject.getWeighting(),res,0.0f);
	}

	@Test
	//User.java
	public void testU01() throws Exception{
		User user = new User();
		assertEquals(user.isValid(-1),false);
	}

	@Test
	//User.java
	public void testU02() throws Exception{
		User user = new User();
		assertEquals(user.isValid(26),false);
	}
	
	@Test
	//User.java
	public void testU03() throws Exception{
		User user = new User();
		assertEquals(user.isValid(1),true);
	}
	
	@Test
	//User.java
	public void testU04() throws Exception{
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		assertEquals(user.isValid(3),false);
	}
	
	@Test
	//User.java
	public void testU05() throws Exception{
		User user = new User();
		boolean res=user.hasSubject(new Subject(SubjectEnum.CHIN, 3,"compulsory"));
		assertEquals(res,false);
	}
	
	@Test
	//User.java
	public void testU06() throws Exception{
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,4));
		boolean res=user.hasSubject(new Subject(SubjectEnum.CHIN, 3,"compulsory"));
		assertEquals(res,true);
	}
	
	@Test
	//User.java
	public void testU07() throws Exception{
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,4));
		int res=user.getSubjectScore(new Subject(SubjectEnum.CHIN, 3,"compulsory"));
		assertEquals(res,4);
	}
	
	@Test
	//User.java
	public void testU08() throws Exception{
		User user = new User();
		int res=user.getSubjectScore(new Subject(SubjectEnum.CHIN, 3,"compulsory"));
		assertEquals(res,-1);
	}
	
	@Test
	//User.java
	public void testU09() throws Exception{
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,4));
		int res=user.getTotalStudiedSubject();
		assertEquals(res,1);
	}
	
	@Test
	//User.java
	public void testU10() throws Exception{
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,4));
		assertEquals(user.getStudiedSubjectList().get(0).getName(),SubjectEnum.CHIN);
	}

	@Test
	//Programme.java
	public void testP01() throws Exception{
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		prog1.setJscode("1011");
		assertEquals(prog1.getJscode(),"1011");
	}
	
	@Test
	//Programme.java
	public void testP02() throws Exception{
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		prog1.setName("能源及環境學院 (工學士 - 能源科學)");
		assertEquals(prog1.getName(),"能源及環境學院 (工學士 - 能源科學)");
	}
	
	@Test
	//Programme.java
	public void testP03() throws Exception{
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		prog1.setMedian(20);
		assertEquals(prog1.getMedian(),20,0.0f);
	}
	
	@Test
	//Programme.java
	public void testP04() throws Exception{
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		prog1.setDivide(10);
		assertEquals(prog1.getDivide(),10,0.0f);
	}
	
	@Test
	//Programme.java
	public void testP05() throws Exception{
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		prog1.setFormulaId("4C1X");
		assertEquals(prog1.getFormulaId(),"4C1X");
	}
	
	@Test
	//Programme.java
	public void testP06() throws Exception{
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		prog1.setCompetitors(14);
		assertEquals(prog1.getCompetitors(),14,0.0f);
	}
	
	@Test
	//Programme.java
	public void testP07() throws Exception{
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		prog1.setTypeId(2);
		assertEquals(prog1.getTypeId(),2);
	}
	
	@Test
	//Programme.java
	public void testP08() throws Exception{
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		prog1.setUserScore(1);
		prog1.addUserScore(1);
		assertEquals(prog1.getUserScore(),2,0.0f);
	}
	
	@Test
	//Programme.java
	public void testP09() throws Exception{
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		prog1.setUserScore(100);
		float user =100;
		float divide = 48;
		float res = ((user-divide)/divide)*100;
		assertEquals(prog1.getScoreDifferencePercentage(),res,0.0f);

	}
	
	@Test
	//Programme.java
	public void testP10() throws Exception{
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"Either2");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		prog1.SetSubject(sub);
		assertEquals(prog1.getSubjectList().get(0).getName(),SubjectEnum.M2);
	}
	
	@Test
	//Programme.java
	public void testP11() throws Exception{
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		prog1.SetEligible(true);
		assertEquals(prog1.isEligible(),true);
	}
	
	
	@Test
	//DBController.java
	//no name
	public void testdb01() throws Exception{
		DBController db = DBController.getInstance();
		String dbS = "";
		String username = "";
		String password = "cs3343";
		db.Initalize(dbS, username, password);
		String sql = "select * from [JUPAS_Programme].[dbo].[main];";
		ResultSet rs = db.FetchData(sql);
		assertEquals(rs,null);
	}
	
	@Test
	//DBController.java
	//wrong sql
	public void testdb02() throws Exception{
		DBController db = DBController.getInstance();
		String dbS = "jdbc:sqlserver://localhost";
		String username = "gp4";
		String password = "cs3343";
		db.Initalize(dbS, username, password);
		String sql = "select [haha] from [JUPAS_Programme].[dbo].[main];";
		ResultSet rs = db.FetchData(sql);
		assertEquals(rs,null);
	}

	//Integration Test
	
	@Test
	//ProgrammePreparator.java, DBController.java, Programme.java, Subject.java
	public void testPP() throws Exception{
		ProgrammePreparator pp = ProgrammePreparator.getInstance();
		
		//new db
		DBController db = DBController.getInstance();
		String dbS = "jdbc:sqlserver://localhost";
		String username = "gp4";
		String password = "cs3343";
		db.Initalize(dbS, username, password);
		String sql = "select * from [JUPAS_Programme].[dbo].[main];";
		//get result set
		ResultSet rs = db.FetchData(sql);
		int ColumnSize = 55;
		int SubjectOffset = 16;
		int CoreOffset = 8;
		List<Programme> plist = new ArrayList<Programme>();	
		while(rs.next()){
			List<Subject> slist = new ArrayList<Subject>();
			Programme p = new Programme(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), rs.getFloat(5), rs.getString(6), rs.getFloat(7));
			int getSubjectCounter = 1;
			int getCoreCounter = 22;
			for(int i=CoreOffset; i<SubjectOffset; i+=2){
				Subject  s;
				String isCompulsory = rs.getString(i+1);
				s = new Subject(SubjectEnum.getSubject(getCoreCounter), rs.getFloat(i), isCompulsory);
				slist.add(s);
				getCoreCounter++;
			}
			for(int i=SubjectOffset; i<ColumnSize; i+=2){
				Subject s;
				String isCompulsory = rs.getString(i+1);
				s = new Subject(SubjectEnum.getSubject(getSubjectCounter), rs.getFloat(i), isCompulsory);
				slist.add(s);
				getSubjectCounter++;
			}
			if(p.SetSubject(slist))
				plist.add(p);
		}
		
		
		assertEquals(arrlistEq(plist,pp.PrepareList()),true);
		
	}	
	
	public boolean arrlistEq(List<Programme> l1, List<Programme> l2){
		int count =0;
		for(int i=0; i<l1.size();i++){
			Programme p1=l1.get(i);
			Programme p2=l2.get(i);
			if(p1.getJscode().equals(p2.getJscode())&&
			   p1.getName().equals(p2.getName())&&
			   p1.getTypeId()==(p2.getTypeId())&&
			   p1.getCompetitors()==(p2.getCompetitors())&&
			   p1.getMedian()==(p2.getMedian())&&
			   p1.getFormulaId().equals(p2.getFormulaId())&&
			   p1.getDivide()==(p2.getDivide())
			){count++;}
		}
		if(count==l1.size()&&l1.size()==l2.size())
			return true;
		else 
			return false;
	}
	
	@Test
	//Calculator.java, User.java, Subject.java, Programme.java
	//Negative Test Case
	public void testCAL01() throws Exception{
		//no elective with 4c3x //check 
		Calculator calculator = Calculator.getInstance();
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.setPreference(3);
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C3X",1);
		List<Programme> prog = new ArrayList<>();
		prog.add(prog1);
		prog =calculator.CalculateUserScore(prog, user);
		assertEquals(prog.get(0).isEligible(),false);
	}
	
	@Test
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL02() throws Exception{
		//no elective with 4c2x //check 
		Calculator calculator = Calculator.getInstance();
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.setPreference(3);
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		List<Programme> prog = new ArrayList<>();
		prog.add(prog1);
		prog =calculator.CalculateUserScore(prog, user);
		assertEquals(prog.get(0).isEligible(),false);
	}
	
	@Test
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL03() throws Exception{
		//no elective with 1C4B //check 
		Calculator calculator = Calculator.getInstance();
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.setPreference(3);
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"1C4B",1);
		List<Programme> prog = new ArrayList<>();
		prog.add(prog1);
		prog =calculator.CalculateUserScore(prog, user);
		assertEquals(prog.get(0).isEligible(),false);
	}

	@Test
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL04() throws Exception{
		//test cal XXXX with 2 either2
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M1,5));
		user.setPreference(3);
		//temp prog
		Programme C1B4 = new Programme("1051","能源及環境學院",3,13,48,"XXXX",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"Either2");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"Either2");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"NotCompulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"NotCompulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"NotCompulsory");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		C1B4.SetSubject(sub);
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C1B4);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),false);
	}
	
	@Test
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL05() throws Exception{
		//test cal 1C4B with not enough comp
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();

		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.setPreference(3);
		//temp prog
		Programme C1B4 = new Programme("1051","能源及環境學院",3,13,48,"1C4B",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"NotCompulsory");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"NotCompulsory");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"Compulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"NotCompulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"NotCompulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"NotCompulsory");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject3);
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		C1B4.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C1B4);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),false);
	}

	@Test
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL06() throws Exception{
		//test cal 4C1X with 2 either1
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.BIO,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIST,4));
		user.setPreference(3);
		//temp prog
		Programme C4X1 = new Programme("1051","能源及環境學院",3,13,48,"4C1X",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"Either1");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"Either1");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"Compulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"Compulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"Compulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"Compulsory");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		C4X1.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C4X1);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),false);
	}
	
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL07() throws Exception{
		//test cal 4C1X with 2 either1
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M1,5));
		user.setPreference(3);
		//temp prog
		Programme C4X1 = new Programme("1051","能源及環境學院",3,13,48,"4C1X",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"Either1");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"Either1");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"Compulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"Compulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"Compulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"Compulsory");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		C4X1.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C4X1);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}
	
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL08() throws Exception{
		//test cal 4C1X with NotCompulsory
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M1,5));
		user.setPreference(3);
		//temp prog
		Programme C4X1 = new Programme("1051","能源及環境學院",3,13,48,"4C1X",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"NotCompulsory");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"NotCompulsory");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"Compulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"Compulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"Compulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"Compulsory");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		C4X1.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C4X1);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}

	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL09() throws Exception{
		//test cal 4C2X with NotCompulsory
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.setPreference(3);
		//temp prog
		Programme C4X2 = new Programme("1051","能源及環境學院",3,13,48,"4C2X",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"Either1");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"Either1");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"Compulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"Compulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"Compulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"Compulsory");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		C4X2.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C4X2);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL10() throws Exception{
		//test cal 4C2X with either 2
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.setPreference(3);
		//temp prog
		Programme C4X2 = new Programme("1051","能源及環境學院",3,13,48,"4C2X",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"Either2");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"Either2");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"Compulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"Compulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"Compulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"Compulsory");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		C4X2.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C4X2);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}
	
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL11() throws Exception{
		//test cal 4C2X with NotCompulsory
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.setPreference(3);
		//temp prog
		Programme C4X2 = new Programme("1051","能源及環境學院",3,13,48,"4C2X",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"NotCompulsory");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"NotCompulsory");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"Compulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"Compulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"Compulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"Compulsory");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		C4X2.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C4X2);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}
	
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL12() throws Exception{
		//test cal 4C3X with Either1
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.BIO,5));
		user.setPreference(3);
		//temp prog
		Programme C4X3 = new Programme("1051","能源及環境學院",3,13,48,"4C3X",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"Either1");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"Either1");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"Compulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"Compulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"Compulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"Compulsory");
		Subject subject7 = new Subject(SubjectEnum.BIO,1,"Either1");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		sub.add(subject7);
		C4X3.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C4X3);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL13() throws Exception{
		//test cal 4C3X with either2
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.BIO,5));
		user.setPreference(3);
		//temp prog
		Programme C4X3 = new Programme("1051","能源及環境學院",3,13,48,"4C3X",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"Either2");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"Either2");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"Compulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"Compulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"Compulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"Compulsory");
		Subject subject7 = new Subject(SubjectEnum.BIO,1,"Either2");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		sub.add(subject7);
		C4X3.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C4X3);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}
	
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL14() throws Exception{
		//test cal 4C3X with NotCompulsory
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.BIO,5));
		user.setPreference(3);
		//temp prog
		Programme C4X3 = new Programme("1051","能源及環境學院",3,13,48,"4C3X",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"NotCompulsory");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"NotCompulsory");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"Compulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"Compulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"Compulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"Compulsory");
		Subject subject7 = new Subject(SubjectEnum.BIO,1,"NotCompulsory");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		sub.add(subject7);
		C4X3.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C4X3);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}
	
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL15() throws Exception{
		//test cal B5 with NotCompulsory
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.BIO,5));
		user.setPreference(3);
		//temp prog
		Programme B5 = new Programme("1051","能源及環境學院",3,13,48,"B5",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"NotCompulsory");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"NotCompulsory");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"NotCompulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"NotCompulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"NotCompulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"NotCompulsory");
		Subject subject7 = new Subject(SubjectEnum.BIO,1,"NotCompulsory");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		sub.add(subject7);
		B5.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(B5);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}
	
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL16() throws Exception{
		//test cal 1C4B with either1
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.BIO,5));
		user.setPreference(3);
		//temp prog
		Programme C1B4 = new Programme("1051","能源及環境學院",3,13,48,"1C4B",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"Either1");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"Either1");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"NotCompulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"NotCompulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"NotCompulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"NotCompulsory");
		Subject subject7 = new Subject(SubjectEnum.BIO,1,"Either1");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		sub.add(subject7);
		C1B4.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C1B4);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}
	
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL17() throws Exception{
		//test cal 1C4B with either2
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.BIO,5));
		user.setPreference(3);
		//temp prog
		Programme C1B4 = new Programme("1051","能源及環境學院",3,13,48,"1C4B",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"Either2");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"Either2");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"NotCompulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"NotCompulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"NotCompulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"NotCompulsory");
		Subject subject7 = new Subject(SubjectEnum.BIO,1,"Either2");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		sub.add(subject7);
		C1B4.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C1B4);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}
		
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL18() throws Exception{
		//test cal 1C4B with NotCompulsory
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.BIO,5));
		user.setPreference(3);
		//temp prog
		Programme C1B4 = new Programme("1051","能源及環境學院",3,13,48,"1C4B",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"NotCompulsory");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"NotCompulsory");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"NotCompulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"NotCompulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"NotCompulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"NotCompulsory");
		Subject subject7 = new Subject(SubjectEnum.BIO,1,"NotCompulsory");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		sub.add(subject7);
		C1B4.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C1B4);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}	
	
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL19() throws Exception{
		//test cal 2C3B with either1
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.BIO,5));
		user.setPreference(3);
		//temp prog
		Programme C2B3 = new Programme("1051","能源及環境學院",3,13,48,"2C3B",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"Either1");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"Either1");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"NotCompulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"NotCompulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"NotCompulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"NotCompulsory");
		Subject subject7 = new Subject(SubjectEnum.BIO,1,"Either1");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		sub.add(subject7);
		C2B3.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C2B3);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}
	
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL20() throws Exception{
		//test cal 2C3B with either2
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.BIO,5));
		user.setPreference(3);
		//temp prog
		Programme C2B3 = new Programme("1051","能源及環境學院",3,13,48,"2C3B",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"Either2");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"Either2");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"NotCompulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"NotCompulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"NotCompulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"NotCompulsory");
		Subject subject7 = new Subject(SubjectEnum.BIO,1,"Either2");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		sub.add(subject7);
		C2B3.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C2B3);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}
		
	@Test 
	//Calculator.java, User.java, Subject.java, Programme.java
	public void testCAL21() throws Exception{
		//test cal 2C3B with NotCompulsory
		Calculator calculator = Calculator.getInstance();
		//temp user
		User user = new User();
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.CHIN,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.ENG,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M0,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.LS,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.PHY,4));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.M2,5));
		user.addStudiedSubject(new StudiedSubject(SubjectEnum.BIO,5));
		user.setPreference(3);
		//temp prog
		Programme C2B3 = new Programme("1051","能源及環境學院",3,13,48,"2C3B",1);
		Subject subject1 = new Subject(SubjectEnum.M2,1,"NotCompulsory");
		Subject subject2 = new Subject(SubjectEnum.PHY,1,"NotCompulsory");
		Subject subject3 = new Subject(SubjectEnum.CHIN,1,"NotCompulsory");
		Subject subject4 = new Subject(SubjectEnum.ENG,1,"NotCompulsory");
		Subject subject5 = new Subject(SubjectEnum.M0,1,"NotCompulsory");
		Subject subject6 = new Subject(SubjectEnum.LS,1,"NotCompulsory");
		Subject subject7 = new Subject(SubjectEnum.BIO,1,"NotCompulsory");
		List<Subject> sub = new ArrayList<>();
		sub.add(subject1);
		sub.add(subject2);
		sub.add(subject3);
		sub.add(subject4);
		sub.add(subject5);
		sub.add(subject6);
		sub.add(subject7);
		C2B3.SetSubject(sub);
		
		// get prog list
		List<Programme> prog = new ArrayList<>();
		prog.add(C2B3);
		prog=calculator.CalculateUserScore(prog, user);
		
		assertEquals(prog.get(0).isEligible(),true);
	}	
	
	@Test
	//Filter.java, Programme.java
	public void testFIL01() throws Exception{
		Filter filter = new Filter();
		List<Programme> prog = new ArrayList<>();
		List<Programme> output = new ArrayList<>();
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		Programme prog2 = new Programme("1204","電腦科學系 (理學士 - 電腦科學)",3,9,28,"4C2X",1);
		Programme prog3 = new Programme("1209","系統工程及工程管理學系 (工學士 - 系統工程管理學)",4,4,34,"4C2X",1);
		prog1.addUserScore(0);
		prog.add(prog1);
		prog.add(prog2);
		prog.add(prog3);
		List<Programme> result = filter.FilterProgrammeByScore(prog,-10);
		assertEquals(result,output);
	}
	
	
	@Test
	//Filter.java, Programme.java
	public void testFIL02() throws Exception{
		Filter filter = new Filter();
		List<Programme> prog = new ArrayList<>();
		List<Programme> output = new ArrayList<>();
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		Programme prog2 = new Programme("1204","電腦科學系 (理學士 - 電腦科學)",3,9,28,"4C2X",1);
		Programme prog3 = new Programme("1209","系統工程及工程管理學系 (工學士 - 系統工程管理學)",4,4,2000,"4C2X",1);
		prog1.addUserScore(200);
		prog2.addUserScore(200);
		prog3.addUserScore(200);
		prog.add(prog1);
		prog.add(prog2);
		prog.add(prog3);
		output.add(prog2);
		output.add(prog1);
		List<Programme> result = filter.FilterProgrammeByScore(prog,-10);
		assertEquals(result,output);
	}

	@Test
	//Filter.java, Programme.java
	public void testFIL03() throws Exception{
		Filter filter = new Filter();
		List<Programme> prog = new ArrayList<>();
		List<Programme> output = new ArrayList<>();
		User user = new User();
		user.setPreference(3);
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		Programme prog2 = new Programme("1204","電腦科學系 (理學士 - 電腦科學)",3,9,28,"4C2X",1);
		Programme prog3 = new Programme("1209","系統工程及工程管理學系 (工學士 - 系統工程管理學)",4,4,34,"4C2X",1);
		prog.add(prog1);
		prog.add(prog2);
		prog.add(prog3);
		output.add(prog1);
		output.add(prog2);
		//output.add(prog3);
		List<Programme> result = filter.FilterProgrammeByPreference(prog,user);
		assertEquals(result,output);
	}

	@Test
	//UserPreparator.java, User.java
	public void testUP() throws Exception{
		//input 99,1,1,1,1,//core subject
		//98,1,//no of elective
		//97,1,1//code and score of elective
		//96,3//preference
		System.out.println("\n-----testing UserPreparator-----");
		UserPreparator up = UserPreparator.getInstance();
		User usert = up.GenerateUser();
		assertEquals(usert.getPreference(),3);
	}

	@Test
	//UI.java
	public void testUI() throws Exception{
		System.out.println("\n-----testing UI-----");
		UI ui = UI.getInstance();
		ui.CreateMenu();
		ui.getUserInput();
		List<Programme> prog = new ArrayList<>();
		Programme prog1 = new Programme("1051","能源及環境學院 (工學士 - 能源科學及工程學)",3,13,48,"4C2X",1);
		prog.add(prog1);
		ui.GenerateOutput(prog);
	}
	
	@Test
	// System Test
	public void testMain() throws Exception{
		System.out.println("\n-----testing Main-----");
		String[] args = null;
		Main.main(args);
	}
	
}