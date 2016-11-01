package smartjupas.Model;

import java.util.List;

public class Programme {
	//Programme Attributes
	private String jscode;
	private String name;
	private int typeId;
	private float competitors;
	private float median;
	private String formulaId;
	private int divide;
	private Subject CHIN;
	private Subject ENG;
	private Subject M0;
	private Subject LS;
	private Subject M1;
	private Subject M2;
	private Subject PHY;
	private Subject CHEM;
	private Subject BIO;
	private Subject ISci;
	private Subject PHY_CHEM;
	private Subject PHY_BIO;
	private Subject CHEM_BIO;
	private Subject PE;
	private Subject ICT;
	private Subject TL;
	private Subject GEO;
	private Subject BAFS;
	private Subject ECON;
	private Subject DESIGN;
	private Subject HIST;
	private Subject CHIST;
	private Subject CLIT;
	private Subject ELIT;
	
	
	public Programme(String jscode, String name, float competitors, float median, String formulaId) {
		this.jscode = jscode;
		this.name = name;
		this.competitors = competitors;
		this.median = median;
		this.formulaId = formulaId;
	}

	public boolean SetSubject(List<Subject> list){
		try{
			CHIN = list.get(0);
			ENG = list.get(1);
			M0 = list.get(2);
			LS = list.get(3);
			M1 = list.get(4);
			M2 = list.get(5);
			PHY = list.get(6);
			CHEM = list.get(7);
			BIO = list.get(8);
			ISci = list.get(9);
			PHY_CHEM = list.get(10);
			PHY_BIO = list.get(11);
			CHEM_BIO = list.get(12);
			PE = list.get(13);
			ICT = list.get(14);
			TL = list.get(15);
			GEO = list.get(16);
			BAFS = list.get(17);
			ECON = list.get(18);
			DESIGN = list.get(19);
			HIST = list.get(20);
			CHIST = list.get(21);
			CLIT = list.get(22);
			ELIT = list.get(23);
			return true;
		} catch (Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		
	}

	
	public String getJscode() {
		return jscode;
	}
	
	public void setJscode(String jscode) {
		this.jscode = jscode;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTypeId() {
		return typeId;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public float getCompetitors() {
		return competitors;
	}
	
	public void setCompetitors(float competitors) {
		this.competitors = competitors;
	}
	
	public float getMedian() {
		return median;
	}
	
	public void setMedian(float median) {
		this.median = median;
	}
	
	public String getFormulaId() {
		return formulaId;
	}
	
	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}
	
	public int getDivide() {
		return divide;
	}
	
	public void setDivide(int divide) {
		this.divide = divide;
	}
	
	public Subject getCHIN() {
		return CHIN;
	}
	
	public void setCHIN(Subject cHIN) {
		CHIN = cHIN;
	}
	
	public Subject getENG() {
		return ENG;
	}
	
	public void setENG(Subject eNG) {
		ENG = eNG;
	}
	
	public Subject getM0() {
		return M0;
	}
	
	public void setM0(Subject m0) {
		M0 = m0;
	}
	
	public Subject getLS() {
		return LS;
	}
	
	public void setLS(Subject lS) {
		LS = lS;
	}
	
	public Subject getM1() {
		return M1;
	}
	
	public void setM1(Subject m1) {
		M1 = m1;
	}
	
	public Subject getM2() {
		return M2;
	}
	
	public void setM2(Subject m2) {
		M2 = m2;
	}
	
	public Subject getPHY() {
		return PHY;
	}
	
	public void setPHY(Subject pHY) {
		PHY = pHY;
	}
	
	public Subject getCHEM() {
		return CHEM;
	}
	
	public void setCHEM(Subject cHEM) {
		CHEM = cHEM;
	}
	
	public Subject getBIO() {
		return BIO;
	}
	
	public void setBIO(Subject bIO) {
		BIO = bIO;
	}
	
	public Subject getISci() {
		return ISci;
	}
	
	public void setISci(Subject iSci) {
		ISci = iSci;
	}
	
	public Subject getPHY_CHEM() {
		return PHY_CHEM;
	}
	
	public void setPHY_CHEM(Subject pHY_CHEM) {
		PHY_CHEM = pHY_CHEM;
	}
	
	public Subject getPHY_BIO() {
		return PHY_BIO;
	}
	
	public void setPHY_BIO(Subject pHY_BIO) {
		PHY_BIO = pHY_BIO;
	}
	
	public Subject getCHEM_BIO() {
		return CHEM_BIO;
	}
	
	public void setCHEM_BIO(Subject cHEM_BIO) {
		CHEM_BIO = cHEM_BIO;
	}
	
	public Subject getPE() {
		return PE;
	}
	
	public void setPE(Subject pE) {
		PE = pE;
	}
	
	public Subject getICT() {
		return ICT;
	}
	
	public void setICT(Subject iCT) {
		ICT = iCT;
	}
	
	public Subject getTL() {
		return TL;
	}
	
	public void setTL(Subject tL) {
		TL = tL;
	}
	
	public Subject getGEO() {
		return GEO;
	}
	
	public void setGEO(Subject gEO) {
		GEO = gEO;
	}
	
	public Subject getBAFS() {
		return BAFS;
	}
	
	public void setBAFS(Subject bAFS) {
		BAFS = bAFS;
	}
	
	public Subject getECON() {
		return ECON;
	}
	
	public void setECON(Subject eCON) {
		ECON = eCON;
	}
	
	public Subject getDESIGN() {
		return DESIGN;
	}
	
	public void setDESIGN(Subject dESIGN) {
		DESIGN = dESIGN;
	}
	
	public Subject getHIST() {
		return HIST;
	}
	
	public void setHIST(Subject hIST) {
		HIST = hIST;
	}
	
	public Subject getCHIST() {
		return CHIST;
	}
	
	public void setCHIST(Subject cHIST) {
		CHIST = cHIST;
	}
	
	public Subject getCLIT() {
		return CLIT;
	}
	
	public void setCLIT(Subject cLIT) {
		CLIT = cLIT;
	}
	
	public Subject getELIT() {
		return ELIT;
	}
	
	public void setELIT(Subject eLIT) {
		ELIT = eLIT;
	}
	
}
