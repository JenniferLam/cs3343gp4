package smartjupas.Model;

public enum SubjectEnum {
	CHIN(0),
	ENG(1),
	M0(2),
	LS(3),
	PHY(4),
	CHEM(5),
	BIO(6),
	ISCI(7),
	PHYCHEM(8),
	PHYBIO(9),
	CHEMBIO(10),
	PE(11),
	ICT(12),
	TL(13),
	GEO(14),
	BAFS(15),
	ECON(16),
	DESIGN(17),
	HIST(18),
	CHIST(19),
	CLIT(20),
	ELIT(21),
	THS(22),
	HMSC(23),
	MUSIC(24),
	VA(25),
	M1(26),
	M2(27),
	OTHERS(28);

	
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
