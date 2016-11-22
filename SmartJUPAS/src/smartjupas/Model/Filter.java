package smartjupas.Model;

import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class Filter {
	public static List<Programme> FilterProgrammeByScore(List<Programme> programmeList, float percentage) {
		List<Programme> outputProgrammeList = new ArrayList<Programme>();
		for (Programme p : programmeList) {
			if(p.getScoreDifferencePercentage()>percentage)
				outputProgrammeList.add(p);
		}
		
		sortOutputProgrammeList(outputProgrammeList);
		return outputProgrammeList;
	}

	public static List<Programme> FilterProgrammeByPreference(List<Programme> programmeList, int preference) {
		List<Programme> outputProgrammeList = new ArrayList<Programme>();
		for (Programme p : programmeList) {
			if(p.getTypeId() == preference)
				outputProgrammeList.add(p);
		}
		sortOutputProgrammeList(outputProgrammeList);
		return outputProgrammeList;
	}
	
	private static void sortOutputProgrammeList(List<Programme> outputProgrammeList) {
		Collections.sort(outputProgrammeList, new Comparator<Programme>() {
		      @Override
		      public int compare(final Programme programme1, final Programme programme2) {
		          return (int)(programme2.getScoreDifferencePercentage()-programme1.getScoreDifferencePercentage());
		      }
		  });
	}
	
}
