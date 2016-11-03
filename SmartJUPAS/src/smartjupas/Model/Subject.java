package smartjupas.Model;

public class Subject {
	private float Weighting;
	private boolean Compulsory;
	
	public Subject(float Weighting, boolean compulsory){
		this.setWeighting(Weighting);
		this.setCompulsory(compulsory);
	}

	public float getWeighting() {
		return Weighting;
	}

	public void setWeighting(float weighting) {
		Weighting = weighting;
	}

	public boolean isCompulsory() {
		return Compulsory;
	}

	public void setCompulsory(boolean compulsory) {
		Compulsory = compulsory;
	}
}
