package smartjupas.DAL;
import java.sql.*;
import java.io.*;

public class DBController {
	private String dbS = "";
	private String username = "";
	private String password = "";
	private static DBController instance = new DBController();
	
	private DBController(){
		
	}
	
	public static DBController getInstance(){
		return instance;
	}
	
	public void Initalize(String dbS, String username, String password){
		this.dbS = dbS;
		this.username= username;
		this.password = password;
		System.out.println("Database Connection Initalized!");
	}
	
	public ResultSet FetchData(String sql){
		try{
			if(this.dbS=="" || this.username=="" || this.password==""){
				throw new IOException("Database not yet initialized.");
			}
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(this.dbS, this.username, this.password);
			System.out.println("Fetching data from the Database");
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs==null)
				throw new IOException("Cannot read from database.");
			return rs;
		} catch(IOException e){
			System.out.println(e.getMessage());
			return null;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}
}
