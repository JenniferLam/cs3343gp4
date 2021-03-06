package smartjupas.BLL;
import java.sql.*;
import java.util.*;
import smartjupas.Model.*;
import smartjupas.DAL.*;

public class ProgrammePreparator {

	private static ProgrammePreparator instance = new ProgrammePreparator();
	private DBController db;
	private final int ColumnSize = 55;
	private final int SubjectOffset = 16;
	private final int CoreOffset = 8;
	private String dbS = "jdbc:sqlserver://localhost";
	private String username = "gp4";
	private String password = "cs3343";
	

	public static ProgrammePreparator getInstance(){
		return instance;
	}
	
	private ProgrammePreparator(){
		db = DBController.getInstance();
		db.Initalize(dbS, username, password);
	}
	
	public List<Programme> PrepareList(){
		String sql = "select * from [JUPAS_Programme].[dbo].[main];";
		try (ResultSet rs = db.FetchData(sql)){
			List<Programme> plist = new ArrayList<Programme>();
			while(rs.next()){
				List<Subject> slist = new ArrayList<Subject>();
				Programme p = new Programme(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), rs.getFloat(5), rs.getString(6), rs.getFloat(7));
				int getSubjectCounter = 1;
				int getCoreCounter = 22;
				for(int i=CoreOffset; i<SubjectOffset; i+=2){
					Subject s;
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
				else
					throw new Exception("Cannot initialize the Subject.");
			}
			return plist;
		} catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
