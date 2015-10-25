package corpusgenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import utils.Constants;

public class CorpusGenerator {
	
	
	public static void main(String Args[]) throws FileNotFoundException, IOException
	{
		SentenceGenerator sentenceGenerator = new SentenceGenerator();
		String subjectsFileName = Constants.SEBASTIAN + "Input/subjects.txt";
		String conditionsFileName = Constants.SEBASTIAN + "Input/conditions.txt";
		sentenceGenerator.loadWords(subjectsFileName, sentenceGenerator.subjects);
		sentenceGenerator.loadWords(conditionsFileName, sentenceGenerator.conditions);
		
		// testing functions
		ArrayList<String> test = new ArrayList<String>();
		test = sentenceGenerator.construct2SubjectIfStatements();
		for (String s : test)
		{
			System.out.println(s);
		}
		System.out.println("size of statements is " + test.size());
		
		// writing them out
		String outputFileName = Constants.SEBASTIAN + "Output/statements.txt";
		sentenceGenerator.writeStatements(outputFileName, test);
		
	}


}
