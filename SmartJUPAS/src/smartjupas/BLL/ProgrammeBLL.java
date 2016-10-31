package smartjupas.BLL;

import smartjupas.DAL.DBController;

public class ProgrammeBLL {

	private DBController db;
	private String dbS = "jdbc:sqlserver://<hostname>";
	private String username = "<user>";
	private String password = "<password>";
	
	
	public ProgrammeBLL(){
		db = DBController.getInstance();
		db.Initalize(dbS, username, password);
	}
}
