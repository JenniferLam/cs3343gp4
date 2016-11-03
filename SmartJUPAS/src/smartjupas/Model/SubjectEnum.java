package smartjupas.Model;

public enum SubjectEnum {
	PHY(1),
	CHEM(2),
	BIO(3),
	ISCI(4),
	PHYCHEM(5),
	PHYBIO(6),
	CHEMBIO(7),
	PE(8),
	ICT(9),
	TL(10),
	GEO(11),
	BAFS(12),
	ECON(13),
	DESIGN(14),
	HIST(15),
	CHIST(16),
	CLIT(17),
	ELIT(18),
	THS(19),
	HMSC(20),
	MUSIC(21),
	VA(22),
	M1(23),
	M2(24),
	OTHERS(25),
	CHIN(0),
	ENG(0),
	M0(0),
	LS(0);
	
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
