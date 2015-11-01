package corpusgenerator;

import java.util.ArrayList;
import java.util.Arrays;

public class SentenceTagger {

public static ArrayList<String> tag(ArrayList<String> sents){
	ArrayList<String> templateSent = new ArrayList<String>();
	ArrayList<String> formattedSent = new ArrayList<String>();
	templateSent.add("1\tif\tif\tSCONJ\tIN\t_\t4\tmark\t_\t_"); 
	templateSent.add("NOUN\tNN\tNumber=Sing\t4\tnsubj\t_\t_"); 
	templateSent.add("3\tis\tbe\tVERB\tVBZ\tMood=Ind|Number=Sing|Person=3|Tense=Pres|VerbForm=Fin\t4\tcop\t_\t_");
	templateSent.add("ADJ\tJJR\tDegree=Cmp\t0\troot\t_\t_");
	templateSent.add("ADP\tIN\t_\t6\tcase\t_\t_");
	templateSent.add("NUM\tCD\tNumType=Card\t4\tnmod\t_\t_");
	templateSent.add("7\t.\t.\tPUNCT\t.\t_\t4\tpunct\t_\t_");
	
	for (String s : sents){
		//split sentence to individual words
		ArrayList<String> words = new ArrayList<String>(Arrays.asList(s.split(" ")));
		formattedSent.add(templateSent.get(0));
		formattedSent.add("2\t" + words.get(1) + "\t" + words.get(1) + "\t" + templateSent.get(1));
		formattedSent.add(templateSent.get(2));
		formattedSent.add("4\t" + words.get(3) + "\t" + words.get(3) + "\t" + templateSent.get(3));
		//formattedSent.add(templateSent.get(4));
		formattedSent.add("5\t" + words.get(4) + "\t" + words.get(4) + "\t" + templateSent.get(4));
		formattedSent.add("6\t" + words.get(5) + "\t" + words.get(5) + "\t" + templateSent.get(5));
		formattedSent.add(templateSent.get(6));
		formattedSent.add("\n");
		}
	
	return formattedSent;
}
}
