package smartjupas.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {

	private List<StudiedSubject> StudiedSubject; 
	private int preferProgrammeType;
	
	public User(){
		StudiedSubject = new ArrayList<StudiedSubject>();
	}
	
	public int getElectiveNumber(){
		return StudiedSubject.size()-4;
	}
	
	public int getTotalStudiedSubject(){
		return StudiedSubject.size();
	}
	
	public int getPreference(){
		return preferProgrammeType;
	}
	
	public void setPreference(int preference){
		preferProgrammeType = preference;
	}
	
	public boolean addStudiedSubject(StudiedSubject s){
		try{
			StudiedSubject.add(s);
			return true;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public List<StudiedSubject> getStudiedSubjectList(){
		return this.StudiedSubject;
	}
	
	public boolean hasSubject(Subject subject) {
		for (StudiedSubject ss : this.StudiedSubject) {
			if (ss.getName() == subject.getName()) {
				return true;
			}
		}
		return false;
	}
	
	public int getSubjectScore(Subject subject) {
		for (StudiedSubject ss : this.StudiedSubject) {
			if (ss.getName() == subject.getName()) {
				return ss.getResult();
			}
		}
		return -1;
	} 
	
	public boolean isValid(int code){
		try{
			if(SubjectEnum.getSubject(code)== null)
				throw new IOException("Code is invalid, please try again");
			for(StudiedSubject ss : StudiedSubject){
				if(ss.getName() == SubjectEnum.getSubject(code))
					throw new IOException("Elective code has been inputed, please try again");
			}
			return true;
		} catch (IOException e){
			System.out.println(e.getMessage());
			return false;			 
		} catch (Exception e){
			e.printStackTrace();
			return false;			
		}
	}
}
