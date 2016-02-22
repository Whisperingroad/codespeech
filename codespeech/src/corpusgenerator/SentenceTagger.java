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
		// for nouns
		templateSent.add("NOUN\tNN\tNumber=Sing\t4\tnmod\t_\t_");
		// for numbers
		templateSent.add("NUM\tCD\tNumType=Card\t4\tnmod\t_\t_");
		templateSent.add("7\t.\t.\tPUNCT\t.\t_\t4\tpunct\t_\t_");

		String s = "";
		String subject = "";

		for (int i = 0; i < sents.size(); i ++)
		{
			s = sents.get(i);
			// split sentence to individual words
			ArrayList<String> words = new ArrayList<String>(Arrays.asList(s
					.split(" ")));
			// removing full stop from subject
			subject = words.get(5).substring(0,words.get(5).length()-1);
			
			formattedSent.add(templateSent.get(0));
			formattedSent.add("2\t" + words.get(1) + "\t" + words.get(1) + "\t"
					+ templateSent.get(1));
			formattedSent.add(templateSent.get(2));
			formattedSent.add("4\t" + words.get(3) + "\t" + words.get(3) + "\t"
					+ templateSent.get(3));
			// formattedSent.add(templateSent.get(4));
			formattedSent.add("5\t" + words.get(4) + "\t" + words.get(4) + "\t"
					+ templateSent.get(4));
			
			// if statements with 2 nouns
			if (i< 3570)
			{		

				formattedSent.add("6\t" + subject + "\t" + subject + "\t"
						+ templateSent.get(5));
			}
			// if statements with a noun and a number
			else
			{
				formattedSent.add("6\t" + subject + "\t" + subject + "\t"
						+ templateSent.get(6));
			}
			formattedSent.add(templateSent.get(7));
			formattedSent.add("\n");
		}
		return formattedSent;
	}

	public ArrayList<String> tagDeclarationStatements(ArrayList<String> sentences)
	{
		int sentenceNumber = 0;
		int sentenceIndex = 0;
		ArrayList<String> declarationCorpus = new ArrayList<String>();
		for (String s : sentences)
		{
			sentenceNumber++;
			sentenceIndex++;
			if (sentenceIndex > 16)
				sentenceIndex = 1;

			String dependency = "";
			String object = "";
			ArrayList<String> words = new ArrayList<String>(Arrays.asList(s.split(" ")));
			for (int i = 0; i < words.size(); i++)
			{
				if (sentenceNumber <= 80 || (sentenceNumber >= 193 && sentenceNumber <= 208))
				{
					// Create/Declare a boolean temp
					// Anomaly, dobj is variable name
					// wordcount = 4
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
						{
							object = words.get(i).substring(0,words.get(i).length()-1);
							dependency = (i+1) + tab + object + tab + object + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
									+ "1" + tab + "dobj" + tab + "_" + tab + "_";
						}

					}
					// Create/Declare a boolean named temp
					// Create/Declare a boolean called temp
					// Create/Declare a boolean as temp
					// wordcount = 5
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
						{
							object = words.get(i).substring(0,words.get(i).length()-1);
							dependency = (i+1) + tab + object + tab + object + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
									+ "4" + tab + "xcomp" + tab + "_" + tab + "_";
						}
					}
					// Create/Declare a boolean variable prev
					// Anomaly, dobj is variable name
					// wordcount = 5
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
							+ "5" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 3)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "5" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 4)
						{
							object = words.get(i).substring(0,words.get(i).length()-1);
							dependency = (i+1) + tab + object + tab + object + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
									+ "1" + tab + "dobj" + tab + "_" + tab + "_";
						}
					}

					// Create/Declare a boolean variable named temp
					// Create/Declare a boolean variable called temp
					// Create/Declare a boolean variable as temp
					// word count = 6
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
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if(i == 4 && words.get(i).equals("called"))
							dependency = (i+1) + tab + words.get(i) + tab + "call" + tab  + "VERB" + tab + "VBN" + tab + "Tense=Past|VerbForm=Part" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if (i == 4 && words.get(i).equals("as"))
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab  + "ADP" + tab + "IN" + tab + "_" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if (i== 5)
						{
							object = words.get(i).substring(0,words.get(i).length()-1);
							dependency = (i+1) + tab + object + tab + object + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
									+ "5" + tab + "xcomp" + tab + "_" + tab + "_";
						}
					}
				}
				else if (sentenceNumber <= 160 || (sentenceNumber >= 209 && sentenceNumber <= 240))
				{
					// Create/Declare a long double z
					// wordcount = 5
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
						// data type (1)
						else if (i == 2)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "5" + tab + "compound" + tab + "_" + tab + "_";
						// data type (2)
						else if (i == 3)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "5" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 4)
						{
							object = words.get(i).substring(0,words.get(i).length()-1);
							dependency = (i+1) + tab + object + tab + object + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
									+ "1" + tab + "dobj" + tab + "_" + tab + "_";
						}
					}

					// Create/Declare a long double named temp
					// Create/Declare a long double called temp
					// Create/Declare a long double as temp
					// wordcount = 6
					else if ((sentenceIndex >= 2 && sentenceIndex <= 4 ) || (sentenceIndex >= 10 && sentenceIndex <= 12 ))
					{
						if (i == 0)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "VERB" + tab + "VB" + tab + "VerbForm=Inf" + tab +
							"0" + tab + "root" + tab + "_" + tab + "_";
						// a or an
						else if (i==1)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "DET" + tab + "DT" + tab + "Definite=Ind|PronType=Art" + tab
							+ "3" + tab + "det" + tab + "_" + tab + "_";
						// data type (1)
						else if (i == 2)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "4" + tab + "compound" + tab + "_" + tab + "_";
						// data type (2)
						else if (i == 3)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "1" + tab + "dobj" + tab + "_" + tab + "_";
						else if (i == 4 && words.get(i).equals("named"))
							dependency = (i+1) + tab + words.get(i) + tab + "name" + tab  + "VERB" + tab + "VBN" + tab + "Tense=Past|VerbForm=Part" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if(i == 4 && words.get(i).equals("called"))
							dependency = (i+1) + tab + words.get(i) + tab + "call" + tab  + "VERB" + tab + "VBN" + tab + "Tense=Past|VerbForm=Part" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if (i == 4 && words.get(i).equals("as"))
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab  + "ADP" + tab + "IN" + tab + "_" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if (i== 5)
						{
							object = words.get(i).substring(0,words.get(i).length()-1);
							dependency = (i+1) + tab + object + tab + object + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
									+ "5" + tab + "xcomp" + tab + "_" + tab + "_";
						}
					}
					// Create/Declare a long double variable curr
					// wordcount = 6
					else if (sentenceIndex == 5 || sentenceIndex == 13)
					{
						if (i == 0)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "VERB" + tab + "VB" + tab + "VerbForm=Inf" + tab +
							"0" + tab + "root" + tab + "_" + tab + "_";
						// a or an
						else if (i==1)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "DET" + tab + "DT" + tab + "Definite=Ind|PronType=Art" + tab
							+ "3" + tab + "det" + tab + "_" + tab + "_";
						// data type (1)
						else if (i == 2)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "6" + tab + "compound" + tab + "_" + tab + "_";
						// data type (2)
						else if (i == 3)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "6" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 4)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "6" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 5)
						{
							object = words.get(i).substring(0,words.get(i).length()-1);
							dependency = (i+1) + tab + object + tab + object + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
									+ "1" + tab + "dobj" + tab + "_" + tab + "_";
						}
					}
					// Create/Declare a long double variable named temp
					// Create/Declare a long double variable called temp
					// Create/Declare a long double variable as temp
					// word count = 7
					else if ((sentenceIndex >= 6 && sentenceIndex <= 8 ) || (sentenceIndex >= 14 && sentenceIndex <= 16 ))
					{
						if (i == 0)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "VERB" + tab + "VB" + tab + "VerbForm=Inf" + tab +
							"0" + tab + "root" + tab + "_" + tab + "_";
						// a or an
						else if (i==1)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "DET" + tab + "DT" + tab + "Definite=Ind|PronType=Art" + tab
							+ "3" + tab + "det" + tab + "_" + tab + "_";
						// data type (1)
						else if (i == 2)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "5" + tab + "compound" + tab + "_" + tab + "_";
						// data type (2)
						else if (i == 3)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "5" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 4)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "1" + tab + "dobj" + tab + "_" + tab + "_";
						else if (i == 5 && words.get(i).equals("named"))
							dependency = (i+1) + tab + words.get(i) + tab + "name" + tab  + "VERB" + tab + "VBN" + tab + "Tense=Past|VerbForm=Part" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if(i == 5 && words.get(i).equals("called"))
							dependency = (i+1) + tab + words.get(i) + tab + "call" + tab  + "VERB" + tab + "VBN" + tab + "Tense=Past|VerbForm=Part" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if (i == 5 && words.get(i).equals("as"))
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab  + "ADP" + tab + "IN" + tab + "_" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if (i == 6)
						{
							object = words.get(i).substring(0,words.get(i).length()-1);
							dependency = (i+1) + tab + object + tab + object + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
									+ "6" + tab + "xcomp" + tab + "_" + tab + "_";
						}
					}
				}
				else if (sentenceNumber <= 192 || (sentenceNumber >= 241 && sentenceNumber <= 272))
				{
					// Create/Declare a signed long integer y
					// wordcount = 6
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
						// data type (1)
						else if (i == 2)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "6" + tab + "compound" + tab + "_" + tab + "_";
						// data type (2)
						else if (i == 3)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "6" + tab + "compound" + tab + "_" + tab + "_";
						// data type (3)
						else if (i == 4)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "6" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 5)
						{
							object = words.get(i).substring(0,words.get(i).length()-1);
							dependency = (i+1) + tab + object + tab + object + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
									+ "1" + tab + "dobj" + tab + "_" + tab + "_";
						}
					}

					// Create/Declare a signed long integer named temp
					// Create/Declare a signed long integer called temp
					// Create/Declare a signed long integer as temp
					// wordcount = 7
					else if ((sentenceIndex >= 2 && sentenceIndex <= 4 ) || (sentenceIndex >= 10 && sentenceIndex <= 12 ))
					{
						if (i == 0)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "VERB" + tab + "VB" + tab + "VerbForm=Inf" + tab +
							"0" + tab + "root" + tab + "_" + tab + "_";
						// a or an
						else if (i==1)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "DET" + tab + "DT" + tab + "Definite=Ind|PronType=Art" + tab
							+ "3" + tab + "det" + tab + "_" + tab + "_";
						// data type (1)
						else if (i == 2)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "5" + tab + "compound" + tab + "_" + tab + "_";
						// data type (2)
						else if (i == 3)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "5" + tab + "compound" + tab + "_" + tab + "_";
						// data type (3)
						else if (i == 4)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "1" + tab + "dobj" + tab + "_" + tab + "_";						
						else if (i == 5 && words.get(i).equals("named"))
							dependency = (i+1) + tab + words.get(i) + tab + "name" + tab  + "VERB" + tab + "VBN" + tab + "Tense=Past|VerbForm=Part" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if(i == 5 && words.get(i).equals("called"))
							dependency = (i+1) + tab + words.get(i) + tab + "call" + tab  + "VERB" + tab + "VBN" + tab + "Tense=Past|VerbForm=Part" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if (i == 5 && words.get(i).equals("as"))
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab  + "ADP" + tab + "IN" + tab + "_" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if (i== 6)
						{
							object = words.get(i).substring(0,words.get(i).length()-1);
							dependency = (i+1) + tab + object + tab + object + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
									+ "6" + tab + "xcomp" + tab + "_" + tab + "_";
						}
					}
					// Create/Declare a signed long integer variable curr
					// wordcount = 7
					else if (sentenceIndex == 5 || sentenceIndex == 13)
					{
						if (i == 0)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "VERB" + tab + "VB" + tab + "VerbForm=Inf" + tab +
							"0" + tab + "root" + tab + "_" + tab + "_";
						// a or an
						else if (i==1)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "DET" + tab + "DT" + tab + "Definite=Ind|PronType=Art" + tab
							+ "3" + tab + "det" + tab + "_" + tab + "_";
						// data type (1)
						else if (i == 2)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "7" + tab + "compound" + tab + "_" + tab + "_";
						// data type (2)
						else if (i == 3)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "7" + tab + "compound" + tab + "_" + tab + "_";
						// data type (3)
						else if (i == 4)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "7" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 5)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "7" + tab + "compound" + tab + "_" + tab + "_";						
						else if (i == 6)
						{
							object = words.get(i).substring(0,words.get(i).length()-1);
							dependency = (i+1) + tab + object + tab + object + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
									+ "1" + tab + "dobj" + tab + "_" + tab + "_";
						}
					}
					// Create/Declare a signed long integer variable named temp
					// Create/Declare a signed long integer variable called temp
					// Create/Declare a signed long integer variable as temp
					// word count = 8
					else if ((sentenceIndex >= 6 && sentenceIndex <= 8 ) || (sentenceIndex >= 14 && sentenceIndex <= 16 ))
					{
						if (i == 0)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "VERB" + tab + "VB" + tab + "VerbForm=Inf" + tab +
							"0" + tab + "root" + tab + "_" + tab + "_";
						// a or an
						else if (i==1)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "DET" + tab + "DT" + tab + "Definite=Ind|PronType=Art" + tab
							+ "3" + tab + "det" + tab + "_" + tab + "_";
						// data type (1)
						else if (i == 2)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "6" + tab + "compound" + tab + "_" + tab + "_";
						// data type (2)
						else if (i == 3)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "6" + tab + "compound" + tab + "_" + tab + "_";
						// data type (3)
						else if (i == 4)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "6" + tab + "compound" + tab + "_" + tab + "_";
						else if (i == 5)
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
							+ "1" + tab + "dobj" + tab + "_" + tab + "_";
						else if (i == 6 && words.get(i).equals("named"))
							dependency = (i+1) + tab + words.get(i) + tab + "name" + tab  + "VERB" + tab + "VBN" + tab + "Tense=Past|VerbForm=Part" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if(i == 6 && words.get(i).equals("called"))
							dependency = (i+1) + tab + words.get(i) + tab + "call" + tab  + "VERB" + tab + "VBN" + tab + "Tense=Past|VerbForm=Part" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if (i == 6 && words.get(i).equals("as"))
							dependency = (i+1) + tab + words.get(i) + tab + words.get(i) + tab  + "ADP" + tab + "IN" + tab + "_" + tab
							+ "3" + tab + "acl" + tab + "_" + tab + "_";
						else if (i == 7)
						{
							object = words.get(i).substring(0,words.get(i).length()-1);
							dependency = (i+1) + tab + object + tab + object + tab + "NOUN" + tab + "NN" + tab + "Number=SING" + tab
									+ "7" + tab + "xcomp" + tab + "_" + tab + "_";
						}
					}

				}

				declarationCorpus.add(dependency);	
			}
			dependency = (words.size()+1) + tab + "." + tab + "." + tab + "PUNCT" + tab + "." + tab + "_" + tab
					+ "1" + tab + "punct" + tab + "_" + tab + "_";
			declarationCorpus.add(dependency);
			declarationCorpus.add("\n");

		}
		return declarationCorpus;
	}
}
