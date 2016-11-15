package smartjupas.BLL;
import java.sql.*;
import java.util.*;
import smartjupas.Model.*;
import smartjupas.DAL.*;

public class ProgrammePreparator {

	private DBController db;
	private final int SubjectTotal = 24;
	private final int ColumnSize = 55;
	private final int SubjectOffset = 8;
	private String dbS = "jdbc:sqlserver://localhost";
	private String username = "gp4";
	private String password = "cs3343";
	
	
	public ProgrammePreparator(){
		db = DBController.getInstance();
		db.Initalize(dbS, username, password);
	}
	
	public List<Programme> PrepareList(){
		String sql = "select * from [JUPAS_Programme].[dbo].[main];";
		try (ResultSet rs = db.FetchData(sql)){
			List<Programme> plist = new ArrayList<Programme>();
			while(rs.next()){
				List<Subject> slist = new ArrayList<Subject>();
				Programme p = new Programme(rs.getString(1), rs.getString(2), rs.getFloat(4), rs.getFloat(5), rs.getString(6));
				for(int i=SubjectOffset; i<ColumnSize; i+=2){
					Subject s;
					String isCompulsory = rs.getString(i+1);
					
					s = new Subject(SubjectEnum.getSubject((i-8)/2),rs.getFloat(i), isCompulsory);
					slist.add(s);
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
