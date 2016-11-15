package smartjupas.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Programme {
	// Programme Attributes
	private String jscode;
	private String name;
	private int typeId;
	private float competitors;
	private float median;
	private String formulaId;
	private int divide;
	private List<Subject> subjectList;

	private int userScore;
	private boolean eligibleForUser;

	public Programme(String jscode, String name, float competitors, float median, String formulaId) {
		this.jscode = jscode;
		this.name = name;
		this.competitors = competitors;
		this.median = median;
		this.formulaId = formulaId;
		this.eligibleForUser = true;
		this.userScore = 0;

	}

	public boolean SetSubject(List<Subject> list) {
		try {
			subjectList = list;
			return true;
		} catch (Exception e) {
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

	public int getUserScore() {
		return userScore;
	}

	public float getScoreDifferencePercentage() {
		return (userScore - median) / median * 100;
	}

	public void checkEligibleForUser(User user) {
		List<Subject> either1SubjectList = new ArrayList<Subject>();
		List<Subject> either2SubjectList = new ArrayList<Subject>();

		if (user.getElectiveNumber() < 3 && this.formulaId.equals("4C3X")) {
			eligibleForUser = false;
		} else if (user.getElectiveNumber() < 2 && this.formulaId.equals("4C2X")) {
			eligibleForUser = false;
		} else if (user.getElectiveNumber() < 1) {
			eligibleForUser = false;
		} else {
			for (Subject subject : subjectList) {
				switch (subject.isCompulsory()) {
				case Compulsory:
					if (!user.hasSubject(subject)) {
						eligibleForUser = false;
					}
					break;
				case Either1:
					either1SubjectList.add(subject);
					break;
				case Either2:
					either2SubjectList.add(subject);
					break;
				default:
					break;
				}
			}

			if (either1SubjectList.size() > 0) {
				int either1Counter = 0;
				for (Subject subject : either1SubjectList) {
					if (user.hasSubject(subject)) {
						either1Counter++;
					}
				}
				if (either1Counter < 1) {
					eligibleForUser = false;
				}
			}
			if (either2SubjectList.size() > 0) {
				int either2Counter = 0;
				for (Subject subject : either2SubjectList) {
					if (user.hasSubject(subject)) {
						either2Counter++;
					}
				}
				if (either2Counter < 2) {
					eligibleForUser = false;
				}
			}
		}
	}

	public void calculateUserScore(User user) {
		if (eligibleForUser) {
			if (formulaId.equals("4C1X")) {
				calculate4C1XScore(user);
			} else if (formulaId.equals("4C2X")) {
				calculate4C2XScore(user);
			} else if (formulaId.equals("4C3X")) {
				calculate4C3XScore(user);
			} else if (formulaId.equals("B5")) {
				calculateBest5Score(user);
			} else if (formulaId.equals("1C4B")) {
				calculate1C4BScore(user);
			} else if (formulaId.equals("2C3B")) {
				calculate2C3BScore(user);
			}

		} else {
			userScore = -1;
		}
	}

	private void calculate1C4BScore(User user) {
		List<Integer> notCompulsoryScoreList = new ArrayList<Integer>();
		List<Integer> either1ScoreList = new ArrayList<Integer>();
		List<Integer> either2ScoreList = new ArrayList<Integer>();

		fillScoreList(user, notCompulsoryScoreList, either1ScoreList, either2ScoreList);
		if (either1ScoreList.size() > 0) {
			userScore += best4Score(either1ScoreList, notCompulsoryScoreList);
		} else if (either2ScoreList.size() > 0) {
			userScore += best4ScoreWithEither2(either2ScoreList, notCompulsoryScoreList);
		} else {
			userScore += best4Score(notCompulsoryScoreList);
		}

	}

	private void calculate2C3BScore(User user) {
		List<Integer> notCompulsoryScoreList = new ArrayList<Integer>();
		List<Integer> either1ScoreList = new ArrayList<Integer>();
		List<Integer> either2ScoreList = new ArrayList<Integer>();

		fillScoreList(user, notCompulsoryScoreList, either1ScoreList, either2ScoreList);
		if (either1ScoreList.size() > 0) {
			userScore += best3Score(either1ScoreList, notCompulsoryScoreList);
		} else if (either2ScoreList.size() > 0) {
			userScore += best3ScoreWithEither2(either2ScoreList, notCompulsoryScoreList);
		} else {
			userScore += best3Score(notCompulsoryScoreList);
		}
	}

	private void calculate4C1XScore(User user) {
		List<Integer> notCompulsoryScoreList = new ArrayList<Integer>();
		List<Integer> either1ScoreList = new ArrayList<Integer>();
		List<Integer> either2ScoreList = new ArrayList<Integer>();

		fillScoreList(user, notCompulsoryScoreList, either1ScoreList, either2ScoreList);

		if (either1ScoreList.size() > 0) {
			userScore += bestScore(either1ScoreList);
		} else {
			userScore += bestScore(notCompulsoryScoreList);
		}

	}

	private void calculate4C2XScore(User user) {
		List<Integer> notCompulsoryScoreList = new ArrayList<Integer>();
		List<Integer> either1ScoreList = new ArrayList<Integer>();
		List<Integer> either2ScoreList = new ArrayList<Integer>();

		fillScoreList(user, notCompulsoryScoreList, either1ScoreList, either2ScoreList);
		if (either1ScoreList.size() > 0) {
			userScore += best2Score(either1ScoreList, notCompulsoryScoreList);
		} else if (either2ScoreList.size() > 0) {
			userScore += best2Score(either2ScoreList);
		} else {
			userScore += best2Score(notCompulsoryScoreList);
		}

	}

	private void calculate4C3XScore(User user) {
		List<Integer> notCompulsoryScoreList = new ArrayList<Integer>();
		List<Integer> either1ScoreList = new ArrayList<Integer>();
		List<Integer> either2ScoreList = new ArrayList<Integer>();

		fillScoreList(user, notCompulsoryScoreList, either1ScoreList, either2ScoreList);
		if (either1ScoreList.size() > 0) {
			userScore += best3Score(either1ScoreList, notCompulsoryScoreList);
		} else if (either2ScoreList.size() > 0) {
			userScore += best3ScoreWithEither2(either2ScoreList, notCompulsoryScoreList);
		} else {
			userScore += best3Score(notCompulsoryScoreList);
		}
	}

	private void calculateBest5Score(User user) {
		List<Integer> scoreList = new ArrayList<Integer>();
		for (Subject subject : subjectList) {
			if (user.hasSubject(subject))
				scoreList.add((int) subject.getWeighting() * user.getSubjectScore(subject));
		}
		userScore += best5Score(scoreList);

	}

	private int bestScore(List<Integer> scoreList) {
		Collections.sort(scoreList, Collections.reverseOrder());
		return scoreList.get(0);
	}

	private int best2Score(List<Integer> scoreList) {
		Collections.sort(scoreList, Collections.reverseOrder());
		return scoreList.get(0) + scoreList.get(1);
	}

	private int best2Score(List<Integer> either1ScoreList, List<Integer> notCompulsoryScoreList) {
		int result = 0;
		Collections.sort(either1ScoreList, Collections.reverseOrder());
		result += either1ScoreList.get(0);
		either1ScoreList.remove(0);

		List<Integer> scoreList = new ArrayList<Integer>(either1ScoreList);
		scoreList.addAll(notCompulsoryScoreList);
		Collections.sort(scoreList, Collections.reverseOrder());
		result += scoreList.get(0);

		return result;
	}

	private int best3Score(List<Integer> scoreList) {
		Collections.sort(scoreList, Collections.reverseOrder());
		return scoreList.get(0) + scoreList.get(1) + scoreList.get(2);
	}

	private int best3Score(List<Integer> either1ScoreList, List<Integer> notCompulsoryScoreList) {
		int result = 0;
		Collections.sort(either1ScoreList, Collections.reverseOrder());
		result += either1ScoreList.get(0);
		either1ScoreList.remove(0);

		List<Integer> scoreList = new ArrayList<Integer>(either1ScoreList);
		scoreList.addAll(notCompulsoryScoreList);
		Collections.sort(scoreList, Collections.reverseOrder());
		result += scoreList.get(0) + scoreList.get(1);

		return result;
	}

	private int best3ScoreWithEither2(List<Integer> either2ScoreList, List<Integer> notCompulsoryScoreList) {
		int result = 0;
		Collections.sort(either2ScoreList, Collections.reverseOrder());
		result += either2ScoreList.get(0) + either2ScoreList.get(1);
		either2ScoreList.remove(0);
		either2ScoreList.remove(1);

		List<Integer> scoreList = new ArrayList<Integer>(either2ScoreList);
		scoreList.addAll(notCompulsoryScoreList);
		Collections.sort(scoreList, Collections.reverseOrder());
		result += scoreList.get(0);

		return result;
	}

	private int best4Score(List<Integer> scoreList) {
		Collections.sort(scoreList, Collections.reverseOrder());
		return scoreList.get(0) + scoreList.get(1) + scoreList.get(2) + scoreList.get(3);
	}

	private int best4Score(List<Integer> either1ScoreList, List<Integer> notCompulsoryScoreList) {
		int result = 0;
		Collections.sort(either1ScoreList, Collections.reverseOrder());
		result += either1ScoreList.get(0);
		either1ScoreList.remove(0);

		List<Integer> scoreList = new ArrayList<Integer>(either1ScoreList);
		scoreList.addAll(notCompulsoryScoreList);
		Collections.sort(scoreList, Collections.reverseOrder());
		result += scoreList.get(0) + scoreList.get(1) + scoreList.get(2);

		return result;
	}

	private int best4ScoreWithEither2(List<Integer> either2ScoreList, List<Integer> notCompulsoryScoreList) {
		int result = 0;
		Collections.sort(either2ScoreList, Collections.reverseOrder());
		result += either2ScoreList.get(0) + either2ScoreList.get(1);
		either2ScoreList.remove(0);
		either2ScoreList.remove(1);

		List<Integer> scoreList = new ArrayList<Integer>(either2ScoreList);
		scoreList.addAll(notCompulsoryScoreList);
		Collections.sort(scoreList, Collections.reverseOrder());
		result += scoreList.get(0) + scoreList.get(1);

		return result;
	}

	private int best5Score(List<Integer> scoreList) {
		int result = 0;
		Collections.sort(scoreList, Collections.reverseOrder());
		for (int i = 0; i < 5; i++)
			result += scoreList.get(i);
		return result;
	}

	private void fillScoreList(User user, List<Integer> notCompulsoryScoreList, List<Integer> either1ScoreList,
			List<Integer> either2ScoreList) {
		for (Subject subject : subjectList) {
			switch (subject.isCompulsory()) {
			case Compulsory:
				if (user.hasSubject(subject))
					userScore += (int) subject.getWeighting() * user.getSubjectScore(subject);
				break;
			case Either1:
				if (user.hasSubject(subject))
					either1ScoreList.add((int) subject.getWeighting() * user.getSubjectScore(subject));
				break;
			case Either2:
				if (user.hasSubject(subject))
					either2ScoreList.add((int) subject.getWeighting() * user.getSubjectScore(subject));
				break;
			default:
				if (user.hasSubject(subject))
					notCompulsoryScoreList.add((int) subject.getWeighting() * user.getSubjectScore(subject));
				break;
			}
		}
	}
}
