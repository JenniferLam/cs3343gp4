package smartjupas.BLL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import smartjupas.Model.Programme;
import smartjupas.Model.Subject;
import smartjupas.Model.User;

public class Calculator {
	
	private Programme CurrentCalProgramme;
	private static Calculator instance = new Calculator();
	
	private Calculator(){
		CurrentCalProgramme = null;
	}
	public static Calculator getInstance(){
		return instance; 
	}
	
	public List<Programme> CalculateUserScore(List<Programme> list, User user){
		try{
			for(Programme p : list){
				CurrentCalProgramme = p;
				checkEligibleForUser(user);
				calculateUserScore(user);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	public void checkEligibleForUser(User user) {
		List<Subject> either1SubjectList = new ArrayList<Subject>();
		List<Subject> either2SubjectList = new ArrayList<Subject>();

		if (user.getElectiveNumber() < 3 && CurrentCalProgramme.getFormulaId().equals("4C3X")) {
			CurrentCalProgramme.SetEligible(false);
		} else if (user.getElectiveNumber() < 2 && CurrentCalProgramme.getFormulaId().equals("4C2X")) {
			CurrentCalProgramme.SetEligible(false);
		} else if (user.getElectiveNumber() < 1) {
			CurrentCalProgramme.SetEligible(false);
		} else {
			for (Subject subject : CurrentCalProgramme.getSubjectList()) {
				switch (subject.isCompulsory()) {
				case Compulsory:
					if (!user.hasSubject(subject)) {
						CurrentCalProgramme.SetEligible(false);
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
					CurrentCalProgramme.SetEligible(false);
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
					CurrentCalProgramme.SetEligible(false);
				}
			}
		}
	}

	public void calculateUserScore(User user) {
		if (CurrentCalProgramme.isEligible()) {
			if (CurrentCalProgramme.getFormulaId().equals("4C1X")) {
				calculate4C1XScore(user);
			} else if (CurrentCalProgramme.getFormulaId().equals("4C2X")) {
				calculate4C2XScore(user);
			} else if (CurrentCalProgramme.getFormulaId().equals("4C3X")) {
				calculate4C3XScore(user);
			} else if (CurrentCalProgramme.getFormulaId().equals("B5")) {
				calculateBest5Score(user);
			} else if (CurrentCalProgramme.getFormulaId().equals("1C4B")) {
				calculate1C4BScore(user);
			} else if (CurrentCalProgramme.getFormulaId().equals("2C3B")) {
				calculate2C3BScore(user);
			}
//			if(user.getPreference()==CurrentCalProgramme.getTypeId()){
//				CurrentCalProgramme.addUserScore(5);
//			}

		} else {
			CurrentCalProgramme.setUserScore(-1);
		}
	}

	private void calculate1C4BScore(User user) {
		List<Float> notCompulsoryScoreList = new ArrayList<Float>();
		List<Float> either1ScoreList = new ArrayList<Float>();
		List<Float> either2ScoreList = new ArrayList<Float>();

		fillScoreList(user, notCompulsoryScoreList, either1ScoreList, either2ScoreList);
		if (either1ScoreList.size() > 0) {
			CurrentCalProgramme.addUserScore(best4Score(either1ScoreList, notCompulsoryScoreList));
		} else if (either2ScoreList.size() > 0) {
			CurrentCalProgramme.addUserScore(best4ScoreWithEither2(either2ScoreList, notCompulsoryScoreList));
		} else {
			CurrentCalProgramme.addUserScore(best4Score(notCompulsoryScoreList));
		}

	}

	private void calculate2C3BScore(User user) {
		List<Float> notCompulsoryScoreList = new ArrayList<Float>();
		List<Float> either1ScoreList = new ArrayList<Float>();
		List<Float> either2ScoreList = new ArrayList<Float>();

		fillScoreList(user, notCompulsoryScoreList, either1ScoreList, either2ScoreList);
		if (either1ScoreList.size() > 0) {
			CurrentCalProgramme.addUserScore(best3Score(either1ScoreList, notCompulsoryScoreList));
		} else if (either2ScoreList.size() > 0) {
			CurrentCalProgramme.addUserScore(best3ScoreWithEither2(either2ScoreList, notCompulsoryScoreList));
		} else {
			CurrentCalProgramme.addUserScore(best3Score(notCompulsoryScoreList));
		}
	}

	private void calculate4C1XScore(User user) {
		List<Float> notCompulsoryScoreList = new ArrayList<Float>();
		List<Float> either1ScoreList = new ArrayList<Float>();
		List<Float> either2ScoreList = new ArrayList<Float>();

		fillScoreList(user, notCompulsoryScoreList, either1ScoreList, either2ScoreList);

		if (either1ScoreList.size() > 0) {
			CurrentCalProgramme.addUserScore(bestScore(either1ScoreList));
		} else {
			CurrentCalProgramme.addUserScore(bestScore(notCompulsoryScoreList));
		}

	}

	private void calculate4C2XScore(User user) {
		List<Float> notCompulsoryScoreList = new ArrayList<Float>();
		List<Float> either1ScoreList = new ArrayList<Float>();
		List<Float> either2ScoreList = new ArrayList<Float>();

		fillScoreList(user, notCompulsoryScoreList, either1ScoreList, either2ScoreList);
		if (either1ScoreList.size() > 0) {
			CurrentCalProgramme.addUserScore(best2Score(either1ScoreList, notCompulsoryScoreList));
		} else if (either2ScoreList.size() > 0) {
			CurrentCalProgramme.addUserScore(best2Score(either2ScoreList));
		} else {
			CurrentCalProgramme.addUserScore(best2Score(notCompulsoryScoreList));
		}

	}

	private void calculate4C3XScore(User user) {
		List<Float> notCompulsoryScoreList = new ArrayList<Float>();
		List<Float> either1ScoreList = new ArrayList<Float>();
		List<Float> either2ScoreList = new ArrayList<Float>();

		fillScoreList(user, notCompulsoryScoreList, either1ScoreList, either2ScoreList);
		if (either1ScoreList.size() > 0) {
			CurrentCalProgramme.addUserScore(best3Score(either1ScoreList, notCompulsoryScoreList));
		} else if (either2ScoreList.size() > 0) {
			CurrentCalProgramme.addUserScore(best3ScoreWithEither2(either2ScoreList, notCompulsoryScoreList));
		} else {
			CurrentCalProgramme.addUserScore(best3Score(notCompulsoryScoreList));
		}
	}

	private void calculateBest5Score(User user) {
		List<Float> scoreList = new ArrayList<Float>();
		for (Subject subject : CurrentCalProgramme.getSubjectList()) {
			if (user.hasSubject(subject))
				scoreList.add(subject.getWeighting() * user.getSubjectScore(subject));
		}
		CurrentCalProgramme.addUserScore(best5Score(scoreList));

	}

	private float bestScore(List<Float> scoreList) {
		Collections.sort(scoreList, Collections.reverseOrder());
		return scoreList.get(0);
	}

	private float best2Score(List<Float> scoreList) {
		Collections.sort(scoreList, Collections.reverseOrder());
		return scoreList.get(0) + scoreList.get(1);
	}

	private float best2Score(List<Float> either1ScoreList, List<Float> notCompulsoryScoreList) {
		int result = 0;
		Collections.sort(either1ScoreList, Collections.reverseOrder());
		result += either1ScoreList.get(0);
		either1ScoreList.remove(0);

		List<Float> scoreList = new ArrayList<Float>(either1ScoreList);
		scoreList.addAll(notCompulsoryScoreList);
		Collections.sort(scoreList, Collections.reverseOrder());
		result += scoreList.get(0);

		return result;
	}

	private float best3Score(List<Float> scoreList) {
		Collections.sort(scoreList, Collections.reverseOrder());
		return scoreList.get(0) + scoreList.get(1) + scoreList.get(2);
	}

	private float best3Score(List<Float> either1ScoreList, List<Float> notCompulsoryScoreList) {
		int result = 0;
		Collections.sort(either1ScoreList, Collections.reverseOrder());
		result += either1ScoreList.get(0);
		either1ScoreList.remove(0);

		List<Float> scoreList = new ArrayList<Float>(either1ScoreList);
		scoreList.addAll(notCompulsoryScoreList);
		Collections.sort(scoreList, Collections.reverseOrder());
		result += scoreList.get(0) + scoreList.get(1);

		return result;
	}

	private int best3ScoreWithEither2(List<Float> either2ScoreList, List<Float> notCompulsoryScoreList) {
		int result = 0;
		Collections.sort(either2ScoreList, Collections.reverseOrder());
		result += either2ScoreList.get(0) + either2ScoreList.get(1);
		either2ScoreList.remove(0);
		either2ScoreList.remove(1);

		List<Float> scoreList = new ArrayList<Float>(either2ScoreList);
		scoreList.addAll(notCompulsoryScoreList);
		Collections.sort(scoreList, Collections.reverseOrder());
		result += scoreList.get(0);

		return result;
	}

	private float best4Score(List<Float> scoreList) {
		Collections.sort(scoreList, Collections.reverseOrder());
		return scoreList.get(0) + scoreList.get(1) + scoreList.get(2) + scoreList.get(3);
	}

	private int best4Score(List<Float> either1ScoreList, List<Float> notCompulsoryScoreList) {
		int result = 0;
		Collections.sort(either1ScoreList, Collections.reverseOrder());
		result += either1ScoreList.get(0);
		either1ScoreList.remove(0);

		List<Float> scoreList = new ArrayList<Float>(either1ScoreList);
		scoreList.addAll(notCompulsoryScoreList);
		Collections.sort(scoreList, Collections.reverseOrder());
		result += scoreList.get(0) + scoreList.get(1) + scoreList.get(2);

		return result;
	}

	private int best4ScoreWithEither2(List<Float> either2ScoreList, List<Float> notCompulsoryScoreList) {
		int result = 0;
		Collections.sort(either2ScoreList, Collections.reverseOrder());
		result += either2ScoreList.get(0) + either2ScoreList.get(1);
		either2ScoreList.remove(0);
		either2ScoreList.remove(1);

		List<Float> scoreList = new ArrayList<Float>(either2ScoreList);
		scoreList.addAll(notCompulsoryScoreList);
		Collections.sort(scoreList, Collections.reverseOrder());
		result += scoreList.get(0) + scoreList.get(1);

		return result;
	}

	private int best5Score(List<Float> scoreList) {
		int result = 0;
		Collections.sort(scoreList, Collections.reverseOrder());
		for (int i = 0; i < 5; i++)
			result += scoreList.get(i);
		return result;
	}

	private void fillScoreList(User user, List<Float> notCompulsoryScoreList, List<Float> either1ScoreList,
			List<Float> either2ScoreList) {
		for (Subject subject : CurrentCalProgramme.getSubjectList()) {
			switch (subject.isCompulsory()) {
			case Compulsory:
				if (user.hasSubject(subject))
					CurrentCalProgramme.addUserScore(subject.getWeighting() * user.getSubjectScore(subject));
				break;
			case Either1:
				if (user.hasSubject(subject))
					either1ScoreList.add( subject.getWeighting() * user.getSubjectScore(subject));
				break;
			case Either2:
				if (user.hasSubject(subject))
					either2ScoreList.add( subject.getWeighting() * user.getSubjectScore(subject));
				break;
			default:
				if (user.hasSubject(subject))
					notCompulsoryScoreList.add( subject.getWeighting() * user.getSubjectScore(subject));
				break;
			}
		}
	}
}
