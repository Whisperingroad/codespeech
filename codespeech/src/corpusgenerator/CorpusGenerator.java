package corpusgenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import utils.Constants;

public class CorpusGenerator {
	
	
	public static void main(String Args[]) throws FileNotFoundException, IOException
	{
		SentenceGenerator sentenceGenerator = new SentenceGenerator();
		String subjectsFileName = Constants.YIXIU + "Input/subjects.txt";
		String conditionsFileName = Constants.YIXIU + "Input/conditions.txt";
		sentenceGenerator.loadWords(subjectsFileName, sentenceGenerator.subjects);
		sentenceGenerator.loadWords(conditionsFileName, sentenceGenerator.conditions);
		
		// testing functions
		ArrayList<String> test = new ArrayList<String>();
		ArrayList<String> formattedOutput = new ArrayList<String>();
		test = sentenceGenerator.construct2SubjectIfStatements();
		formattedOutput = SentenceTagger.tag(test);
		for (String s : formattedOutput)
		{
			System.out.println(s);
		}
		System.out.println("size of statements is " + test.size());
		
		// writing them out
		String outputFileName = Constants.YIXIU + "Output/statements.txt";
		sentenceGenerator.writeStatements(outputFileName, formattedOutput);
		
	}


}
