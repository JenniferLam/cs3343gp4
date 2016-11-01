package smartjupas.PL;

import java.util.List;
import smartjupas.BLL.*;
import smartjupas.Model.*;
import java.io.*;

public class Main {

	public static void main(String[] args){
		/*For Eddie testing*/
		try {
		ProgrammePreparator pPre = new ProgrammePreparator();
		List<Programme> plist = pPre.PrepareList();
		File f = new File("./abc.txt");
		BufferedWriter out = new BufferedWriter(new FileWriter("file.txt"));
		if(plist!=null){
			System.out.printf("Total %d records fetched\n",plist.size());
			for(int i=0;i<plist.size();i++){
				out.write("JsCode: "+plist.get(i).getJscode());
				out.newLine();
				out.write("Name: "+plist.get(i).getName());
				out.newLine();
				out.write("Median: "+plist.get(i).getMedian());
				out.newLine();
				out.write("Competitors: "+plist.get(i).getCompetitors());
				out.newLine();
				out.write("Formula Id: "+plist.get(i).getFormulaId());
				out.newLine();
				out.write("CHIN: "+plist.get(i).getCHIN().isCompulsory() + "Weighting: " + plist.get(i).getCHIN().getWeighting());
				out.newLine();
				out.write("ENG: "+plist.get(i).getENG().isCompulsory() + "Weighting: " + plist.get(i).getENG().getWeighting());
				out.newLine();
				out.write("M0: "+plist.get(i).getM0().isCompulsory() + "Weighting: " + plist.get(i).getM0().getWeighting());
				out.newLine();
				out.write("LS: "+plist.get(i).getLS().isCompulsory() + "Weighting: " + plist.get(i).getLS().getWeighting());
				out.newLine();
				out.write("M1: "+plist.get(i).getM1().isCompulsory() + "Weighting: " + plist.get(i).getM1().getWeighting());
				out.newLine();
				out.write("M2: "+plist.get(i).getM2().isCompulsory() + "Weighting: " + plist.get(i).getM2().getWeighting());
				out.newLine();
				out.write("PHY: "+plist.get(i).getPHY().isCompulsory() + "Weighting: " + plist.get(i).getPHY().getWeighting());
				out.newLine();
				out.write("CHEM: "+plist.get(i).getCHEM().isCompulsory() + "Weighting: " + plist.get(i).getCHEM().getWeighting());
				out.newLine();
				out.write("BIO: "+plist.get(i).getBIO().isCompulsory() + "Weighting: " + plist.get(i).getBIO().getWeighting());
				out.newLine();
				out.write("ISci: "+plist.get(i).getISci().isCompulsory() + "Weighting: " + plist.get(i).getISci().getWeighting());
				out.newLine();
				out.write("PHY_CHEM: "+plist.get(i).getPHY_CHEM().isCompulsory() + "Weighting: " + plist.get(i).getPHY_CHEM().getWeighting());
				out.newLine();
				out.write("PHY_BIO : "+plist.get(i).getPHY_BIO().isCompulsory() + "Weighting: " + plist.get(i).getPHY_BIO().getWeighting());
				out.newLine();
				out.write("CHEM_BIO: "+plist.get(i).getCHEM_BIO().isCompulsory() + "Weighting: " + plist.get(i).getCHEM_BIO().getWeighting());
				out.newLine();
				out.write("PE: "+plist.get(i).getPE().isCompulsory() + "Weighting: " + plist.get(i).getPE().getWeighting());
				out.newLine();
				out.write("ICT: "+plist.get(i).getICT().isCompulsory() + "Weighting: " + plist.get(i).getICT().getWeighting());
				out.newLine();
				out.write("TL: "+plist.get(i).getTL().isCompulsory() + "Weighting: " + plist.get(i).getTL().getWeighting());
				out.newLine();
				out.write("GEO: "+plist.get(i).getGEO().isCompulsory() + "Weighting: " + plist.get(i).getGEO().getWeighting());
				out.newLine();
				out.write("BAFS: "+plist.get(i).getBAFS().isCompulsory() + "Weighting: " + plist.get(i).getBAFS().getWeighting());
				out.newLine();
				out.write("ECON: "+plist.get(i).getECON().isCompulsory() + "Weighting: " + plist.get(i).getECON().getWeighting());
				out.newLine();
				out.write("DESIGN: "+plist.get(i).getDESIGN().isCompulsory() + "Weighting: " + plist.get(i).getDESIGN().getWeighting());
				out.newLine();
				out.write("HIST: "+plist.get(i).getHIST().isCompulsory() + "Weighting: " + plist.get(i).getHIST().getWeighting());
				out.newLine();
				out.write("CHIST: "+plist.get(i).getCHIST().isCompulsory() + "Weighting: " + plist.get(i).getCHIST().getWeighting());
				out.newLine();
				out.write("CLIT: "+plist.get(i).getCLIT().isCompulsory() + "Weighting: " + plist.get(i).getCLIT().getWeighting());
				out.newLine();
				out.write("ELIT: "+plist.get(i).getELIT().isCompulsory() + "Weighting: " + plist.get(i).getELIT().getWeighting());
				out.newLine();
				out.newLine();
				out.newLine();
				out.newLine();
			}
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**/
	}
}
