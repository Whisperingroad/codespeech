package corpusgenerator;

import java.util.ArrayList;
import java.util.Arrays;

public class SentenceTagger {

public ArrayList<String> tag(ArrayList<String> sents){
	ArrayList<String> templateSent = new ArrayList<String>();
	ArrayList<String> formattedSent = new ArrayList<String>();
	templateSent.add("1 if if SCONJ IN _ 4 mark _ _"); 
	templateSent.add("NOUN NN Number=Sing 4 nsubj _ _"); 
	templateSent.add("3 is be VERB VBZ Mood=Ind|Number=Sing|Person=3|Tense=Pres|VerbForm=Fin 4 cop _ _");
	templateSent.add("ADJ JJR Degree=Cmp 0 root _ _");
	templateSent.add("5 than than ADP IN _ 6 case _ _");
	templateSent.add("NOUN NN Number=Sing 4 nsubj _ _");
	templateSent.add("7 . . PUNCT . _	4 punct _ _");
	
	for (String s : sents){
		//split sentence to individual words
		ArrayList<String> words = new ArrayList<String>(Arrays.asList(s.split(" ")));
		formattedSent.add(templateSent.get(0));
		formattedSent.add("2 " + words.get(1) + " " + words.get(1) + " " + templateSent.get(1));
		formattedSent.add(templateSent.get(2));
		formattedSent.add("4 " + words.get(3) + " " + words.get(3) + " " + templateSent.get(3));
		formattedSent.add(templateSent.get(4));
		formattedSent.add("6 " + words.get(5) + " " + words.get(5) + " " + templateSent.get(5));
		formattedSent.add(templateSent.get(6));
		formattedSent.add("/n");
		}
	
	return formattedSent;
}
}
