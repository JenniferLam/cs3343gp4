package smartjupas.Model;

public class StudiedSubject {
	
	private SubjectEnum Name;
	private int Result;
	
	public StudiedSubject(SubjectEnum name, int result) {
		setName(name);
		setResult(result);
	}

	public SubjectEnum getName() {
		return Name;
	}

	public void setName(SubjectEnum name) {
		Name = name;
	}

	public int getResult() {
		return Result;
	}

	public void setResult(int result) {
		Result = result;
	}
	
	
}
