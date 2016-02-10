package corpusgenerator;

import java.util.ArrayList;
import java.util.Arrays;

public class SentenceTagger {

	ArrayList<String> templateSent = new ArrayList<String>();
	ArrayList<String> formattedSent = new ArrayList<String>();
	ArrayList<String> declarationDependencies = new ArrayList<String>();
	String tab = "\t";

	public ArrayList<String> tag2SubjectIfStatement(ArrayList<String> sents)
	{
		templateSent.add("1\tif\tif\tSCONJ\tIN\t_\t4\tmark\t_\t_");
		templateSent.add("NOUN\tNN\tNumber=Sing\t4\tnsubj\t_\t_");
		templateSent.add("3\tis\tbe\tVERB\tVBZ\tMood=Ind|Number=Sing|Person=3|Tense=Pres|VerbForm=Fin\t4\tcop\t_\t_");
		templateSent.add("ADJ\tJJR\tDegree=Cmp\t0\troot\t_\t_");
		templateSent.add("ADP\tIN\t_\t6\tcase\t_\t_");
		templateSent.add("NUM\tCD\tNumType=Card\t4\tnmod\t_\t_");
		templateSent.add("7\t.\t.\tPUNCT\t.\t_\t4\tpunct\t_\t_");

		for (String s : sents) {
			// split sentence to individual words
			ArrayList<String> words = new ArrayList<String>(Arrays.asList(s
					.split(" ")));
			formattedSent.add(templateSent.get(0));
			formattedSent.add("2\t" + words.get(1) + "\t" + words.get(1) + "\t"
					+ templateSent.get(1));
			formattedSent.add(templateSent.get(2));
			formattedSent.add("4\t" + words.get(3) + "\t" + words.get(3) + "\t"
					+ templateSent.get(3));
			// formattedSent.add(templateSent.get(4));
			formattedSent.add("5\t" + words.get(4) + "\t" + words.get(4) + "\t"
					+ templateSent.get(4));
			formattedSent.add("6\t" + words.get(5) + "\t" + words.get(5) + "\t"
					+ templateSent.get(5));
			formattedSent.add(templateSent.get(6));
			formattedSent.add("\n");
		}
		return formattedSent;
	}

	public ArrayList<String> tagDeclarationStatements(ArrayList<String> sentences)
	{
		int sentenceNumber = 0;
		int sentenceIndex = 0;
		// four words
		for (String s : sentences)
		{
			sentenceNumber++;
			sentenceIndex++;
			if (sentenceIndex > 16)
				sentenceIndex = 1;

			String dependency = "";
			ArrayList<String> words = new ArrayList<String>(Arrays.asList(s.split(" ")));
			for (int i = 0; i < words.size(); i++)
			{
				if (sentenceNumber <= 80)
				{
					// Create/Declare a boolean temp
					// Anomaly, dobj is variable name
					if (sentenceIndex == 1 || sentenceIndex == 9)
					{
						// Create/Declare
						if (i == 0)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "VERB" + tab + "VB" + tab + "VerbForm=Inf" + tab +
							"0" + tab + "root" + tab + "_" + tab + "_";
						// a or an
						else if (i==1)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "DET" + tab + "DT" + tab + "Definite=Ind|PronType=Art" + tab
							+ "3" + tab + "det" + tab + "_" + tab + "_";
						// data type
						else if (i == 2)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "4" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 3)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "1" + tab + "dobj" + tab + "_" + tab + "_";
					}
					// Create/Declare a boolean named temp
					// Create/Declare a boolean called temp
					// Create/Declare a boolean as temp
					else if ((sentenceIndex >= 2 && sentenceIndex <= 4 ) || (sentenceIndex >= 10 && sentenceIndex <= 12 ))
					{
						if (i == 0)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "VERB" + tab + "VB" + tab + "VerbForm=Inf" + tab +
							"0" + tab + "root" + tab + "_" + tab + "_";
						// a or an
						else if (i==1)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "DET" + tab + "DT" + tab + "Definite=Ind|PronType=Art" + tab
							+ "3" + tab + "det" + tab + "_" + tab + "_";
						// data type
						else if (i == 2)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "1" + tab + "dobj" + tab + "_" + tab + "_";
						else if (i == 3 && words.get(i).equals("named"))
							dependency = (i+1) + tab + words.get(i) + tab + "name" + tab  + "VERB" + tab + "VBN" + tab + "Tense=Past|VerbForm=Part" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if(i == 3 && words.get(i).equals("called"))
							dependency = (i+1) + tab + words.get(i) + tab + "call" + tab  + "VERB" + tab + "VBN" + tab + "Tense=Past|VerbForm=Part" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if (i == 3 && words.get(i).equals("as"))
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab  + "ADP" + tab + "IN" + tab + "_" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if (i== 4)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "4" + tab + "xcomp" + tab + "_" + "_";
					}
					// Create/Declare a boolean variable prev
					// Anomaly, dobj is variable name
					else if (sentenceIndex == 5 || sentenceIndex == 13)
					{
						if (i == 0)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "VERB" + tab + "VB" + tab + "VerbForm=Inf" + tab +
							"0" + tab + "root" + tab + "_" + tab + "_";
						// a or an
						else if (i==1)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "DET" + tab + "DT" + tab + "Definite=Ind|PronType=Art" + tab
							+ "3" + tab + "det" + tab + "_" + tab + "_";
						// data type
						else if (i == 2)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "4" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 3)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "5" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 4)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "1" + tab + "dobj" + tab + "_" + tab + "_";	
					}

					// Create/Declare a boolean variable named temp
					// Create/Declare a boolean variable called temp
					// Create/Declare a boolean variable as temp
					else if ((sentenceIndex >= 6 && sentenceIndex <= 8 ) || (sentenceIndex >= 14 && sentenceIndex <= 16 ))
					{
						if (i == 0)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "VERB" + tab + "VB" + tab + "VerbForm=Inf" + tab +
							"0" + tab + "root" + tab + "_" + tab + "_";
						// a or an
						else if (i==1)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "DET" + tab + "DT" + tab + "Definite=Ind|PronType=Art" + tab
							+ "3" + tab + "det" + tab + "_" + tab + "_";
						else if (i == 2)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "4" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 3)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "1" + tab + "dobj" + tab + "_" + tab + "_";
						else if (i == 4 && words.get(i).equals("named"))
							dependency = (i+1) + tab + words.get(i) + tab + "name" + tab  + "VERB" + tab + "VBN" + tab + "Tense=Past|VerbForm=Part" + tab
							+ "4" + tab + "acl" + tab + "_" + tab + "_";
						else if(i == 4 && words.get(i).equals("called"))
							dependency = (i+1) + tab + words.get(i) + tab + "call" + tab  + "VERB" + tab + "VBN" + tab + "Tense=Past|VerbForm=Part" + tab
							+ "4" + tab + "acl" + tab + "_" + tab + "_";
						else if (i== 5)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "5" + tab + "xcomp" + tab + "_" + "_";
					}
				}
				else if (sentenceNumber <= 160)
				{
					if (sentenceIndex == 1 || sentenceIndex == 9)
					{
						// Create/Declare
						if (i == 0)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "VERB" + tab + "VB" + tab + "VerbForm=Inf" + tab +
							"0" + tab + "root" + tab + "_" + tab + "_";
						// a or an
						else if (i==1)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "DET" + tab + "DT" + tab + "Definite=Ind|PronType=Art" + tab
							+ "3" + tab + "det" + tab + "_" + tab + "_";
						// data type
						else if (i == 2)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "4" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 3)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "1" + tab + "dobj" + tab + "_" + tab + "_";
					}
					
					
				}
				
				
				
				
				
			}
		}
		return sentences;
	}






}
