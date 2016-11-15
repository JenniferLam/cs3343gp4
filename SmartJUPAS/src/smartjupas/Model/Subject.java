package smartjupas.Model;

public class Subject {
	private SubjectEnum Name;
	private float Weighting;
	private CompulsoryEnum Compulsory;
	
	public Subject(SubjectEnum name, float weighting, String compulsory){
		this.setName(name);
		this.setWeighting(weighting);
		this.setCompulsory(compulsory);
	}
	
	public SubjectEnum getName() {
		return Name;
	}
	
	public void setName(SubjectEnum name) {
		Name = name;
	}

	public float getWeighting() {
		return Weighting;
	}

	public void setWeighting(float weighting) {
		Weighting = weighting;
	}

	public CompulsoryEnum isCompulsory() {
		return Compulsory;
	}

	public void setCompulsory(String compulsory) {
		if(compulsory.equals(CompulsoryEnum.Compulsory.toString()))
			Compulsory = CompulsoryEnum.Compulsory;
		if(compulsory.equals(CompulsoryEnum.NotCompulsory.toString()))
			Compulsory = CompulsoryEnum.NotCompulsory;
		if(compulsory.equals(CompulsoryEnum.Either1.toString()))
			Compulsory = CompulsoryEnum.Either1;
		if(compulsory.equals(CompulsoryEnum.Either2.toString()))
			Compulsory = CompulsoryEnum.Either2;
	}
}
