package corpusgenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import utils.Constants;

public class CorpusGenerator {
	
	static String subjectsFileName = System.getProperty("user.dir") + "/src/Input/subjects.txt";
	static String conditionsFileName = System.getProperty("user.dir")+ "/src/Input/conditions.txt";
	static String variablesFileName = System.getProperty("user.dir")+ "/src/Input/variables.txt";
	// for declaration/initialization statements	
	// characters
	static String characterFileName = System.getProperty("user.dir") + "/src/Input/characters.txt";
	static String unsignedCharFileName = System.getProperty("user.dir") + "/src/Input/unsignedCharacters.txt";
	static String signedCharFileName = System.getProperty("user.dir") + "/src/Input/signedCharacters.txt";
	// integers
	static String integerFileName = System.getProperty("user.dir") + "/src/Input/integers.txt";
	static String unsignedIntFileName = System.getProperty("user.dir") + "/src/Input/unsignedIntegers.txt";
	static String signedIntFileName = System.getProperty("user.dir") + "/src/Input/signedIntegers.txt";
	static String shortIntFileName = System.getProperty("user.dir") + "/src/Input/shortIntegers.txt";
	static String unsignedShortIntFileName = System.getProperty("user.dir") + "/src/Input/unsignedShortIntegers.txt";
	static String signedShortIntFileName = System.getProperty("user.dir") + "/src/Input/signedShortIntegers.txt";
	static String longIntFileName = System.getProperty("user.dir") + "/src/Input/longIntegers.txt";
	static String signedLongIntFileName = System.getProperty("user.dir") + "/src/Input/signedLongIntegers.txt";
	static String unsignedLongIntFileName = System.getProperty("user.dir") + "/src/Input/unsignedLongIntegers.txt";
	static String floatFileName = System.getProperty("user.dir") + "/src/Input/float.txt";
	static String doubleFileName = System.getProperty("user.dir") + "/src/Input/double.txt";
	static String longDoubleFileName = System.getProperty("user.dir") + "/src/Input/longDouble.txt";
	// string
	static String stringFileName = System.getProperty("user.dir") + "/src/Input/strings.txt";
	// boolean
	static String booleanFileName = System.getProperty("user.dir") + "/src/Input/boolean.txt";
	//data types
	static String dataTypeFileName = System.getProperty("user.dir") + "/src/Input/datatypes.txt";
	//declare commands
	static String declareWordsFileName = System.getProperty("user.dir") + "/src/Input/declarationcommands.txt";
	
	public static void main(String Args[]) throws FileNotFoundException, IOException
	{
		SentenceGenerator sentenceGenerator;
		SentenceTagger sentenceTagger = new SentenceTagger();
		sentenceGenerator = loadAll();
		
		// Constructing statements
		ArrayList<String> declarationStatements = new ArrayList<String>();
		ArrayList<String> twoSubjectIfStatements = new ArrayList<String>();	
		declarationStatements = sentenceGenerator.constructSomeDeclarationStatements();
		twoSubjectIfStatements.addAll(sentenceGenerator.construct2SubjectIfStatementsNouns());
		twoSubjectIfStatements.addAll(sentenceGenerator.construct2SubjectIfStatementsNumbers());
		// twoSubjectIfStatements = sentenceGenerator.construct2SubjectIfStatementsNumbers();
		
		// writing statements to text file
		String outputFileName = System.getProperty("user.dir") + "/src/Output/DeclarationStatements.txt";
		sentenceGenerator.writeStatements(outputFileName, declarationStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/2SubjectIfStatements.txt"; 
		sentenceGenerator.writeStatements(outputFileName, twoSubjectIfStatements);
		
		// constructing corpus
		ArrayList<String> twoSubjectIfCorpus = new ArrayList<String>();
		ArrayList<String> declarationCorpus = new ArrayList<String>();
		
		twoSubjectIfCorpus = sentenceTagger.tag2SubjectIfStatement(twoSubjectIfStatements);
		declarationCorpus = sentenceTagger.tagDeclarationStatements(declarationStatements);
		
		// printing corpus
		outputFileName = System.getProperty("user.dir") + "/src/Output/twoSubjectIfCorpus.txt";
		sentenceGenerator.writeStatements(outputFileName, twoSubjectIfCorpus);
		outputFileName = System.getProperty("user.dir") + "/src/Output/DeclarationStatementsCorpus.txt";		
		sentenceGenerator.writeStatements(outputFileName, declarationCorpus);		
	}
	
	public static SentenceGenerator loadAll()
	{
		SentenceGenerator sentenceGenerator = new SentenceGenerator();		
		try {
			sentenceGenerator.loadWords(subjectsFileName, sentenceGenerator.subjects);
			sentenceGenerator.loadWords(conditionsFileName, sentenceGenerator.conditions);
			sentenceGenerator.loadWords(variablesFileName, sentenceGenerator.variables);
			//characters
			sentenceGenerator.loadWords(characterFileName, sentenceGenerator.characters);
			sentenceGenerator.loadWords(unsignedCharFileName, sentenceGenerator.unsignedCharacters);				
			sentenceGenerator.loadWords(signedCharFileName, sentenceGenerator.signedCharacters);
			//integers
			sentenceGenerator.loadWords(integerFileName, sentenceGenerator.integers);		
			sentenceGenerator.loadWords(unsignedIntFileName, sentenceGenerator.unsignedIntegers);
			sentenceGenerator.loadWords(signedIntFileName, sentenceGenerator.signedIntegers);		
			sentenceGenerator.loadWords(shortIntFileName, sentenceGenerator.shortIntegers);
			sentenceGenerator.loadWords(unsignedShortIntFileName, sentenceGenerator.unsignedShortIntegers);		
			sentenceGenerator.loadWords(signedShortIntFileName, sentenceGenerator.signedShortIntegers);
			sentenceGenerator.loadWords(longIntFileName, sentenceGenerator.longIntegers);		
			sentenceGenerator.loadWords(signedLongIntFileName, sentenceGenerator.signedLongIntegers);
			sentenceGenerator.loadWords(unsignedLongIntFileName, sentenceGenerator.unsignedLongIntegers);	
			sentenceGenerator.loadWords(floatFileName, sentenceGenerator.floats);		
			sentenceGenerator.loadWords(doubleFileName, sentenceGenerator.doubles);
			sentenceGenerator.loadWords(longDoubleFileName, sentenceGenerator.longDoubles);
			//string
			sentenceGenerator.loadWords(stringFileName, sentenceGenerator.strings);
			//boolean
			sentenceGenerator.loadWords(booleanFileName, sentenceGenerator.booleans);
			//data type
			sentenceGenerator.loadWords(dataTypeFileName, sentenceGenerator.dataTypes);
			//declare commands
			sentenceGenerator.loadWords(declareWordsFileName, sentenceGenerator.declareCommands);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sentenceGenerator;
	}
	


}
