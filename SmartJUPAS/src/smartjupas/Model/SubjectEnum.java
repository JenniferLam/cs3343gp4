package smartjupas.Model;

public enum SubjectEnum {
	M1(1),
	M2(2),
	PHY(3),
	CHEM(4),
	BIO(5),
	ISCI(6),
	PHYCHEM(7),
	PHYBIO(8),
	CHEMBIO(9),
	PE(10),
	ICT(11),
	TL(12),
	GEO(13),
	BAFS(14),
	ECON(15),
	DESIGN(16),
	HIST(17),
	CHIST(18),
	CLIT(19),
	ELIT(20),
	OTHERS(21),
	CHIN(0),
	ENG(0),
	M0(0),
	LS(0),
	DUMMY(0);

	
	private final int number;
	private static final int size = SubjectEnum.values().length;
	
	private SubjectEnum(int number){
		this.number = number;
	}
	
	public static SubjectEnum getSubject(int code){
		if(code >= size)
			return null;
		return SubjectEnum.values()[code-1];
	}
}
