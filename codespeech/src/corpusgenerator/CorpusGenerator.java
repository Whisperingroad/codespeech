package corpusgenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import utils.Constants;

public class CorpusGenerator {
	
	
	public static void main(String Args[]) throws FileNotFoundException, IOException
	{
		SentenceGenerator sentenceGenerator = new SentenceGenerator();
		
		// generating if condition statements
		// variable names
		String subjectsFileName = System.getProperty("user.dir") + "/src/Input/subjects.txt";
		String conditionsFileName = System.getProperty("user.dir")+ "/src/Input/conditions.txt";
		sentenceGenerator.loadWords(subjectsFileName, sentenceGenerator.subjects);
		sentenceGenerator.loadWords(conditionsFileName, sentenceGenerator.conditions);
		
		// for declaration/initialization statements
		
		// characters
		String characterFileName = System.getProperty("user.dir") + "/src/Input/characters.txt";
		String unsignedCharFileName = System.getProperty("user.dir") + "/src/Input/unsignedCharacter.txt";
		String signedCharFileName = System.getProperty("user.dir") + "/src/Input/signedCharacter.txt";
		// integers
		String integerFileName = System.getProperty("user.dir") + "/src/Input/integers.txt";
		String unsignedIntFileName = System.getProperty("user.dir") + "/src/Input/unsignedIntegers.txt";
		String signedIntFileName = System.getProperty("user.dir") + "/src/Input/signedIntegers.txt";
		String shortIntFileName = System.getProperty("user.dir") + "/src/Input/shortIntegers.txt";
		String unsignedShortIntFileName = System.getProperty("user.dir") + "/src/Input/unsignedShortIntegers.txt";
		String signedShortIntFileName = System.getProperty("user.dir") + "/src/Input/signedShortIntegers.txt";
		String longIntFileName = System.getProperty("user.dir") + "/src/Input/longIntegers.txt";
		String signedLongIntFileName = System.getProperty("user.dir") + "/src/Input/signedLongIntegers.txt";
		String unsignedLongIntFileName = System.getProperty("user.dir") + "/src/Input/unsignedLongIntegers.txt";
		String floatFileName = System.getProperty("user.dir") + "/src/Input/float.txt";
		String doubleFileName = System.getProperty("user.dir") + "/src/Input/double.txt";
		String longDoubleFileName = System.getProperty("user.dir") + "/src/Input/longDouble.txt";
		// string
		String stringFileName = System.getProperty("user.dir") + "/src/Input/strings.txt";
		// boolean
		String booleanFileName = System.getProperty("user.dir") + "/src/Input/boolean.txt";
		
				
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
		String outputFileName = Constants.SEBASTIAN + "Output/statements.txt";
		sentenceGenerator.writeStatements(outputFileName, formattedOutput);
		
	}


}
