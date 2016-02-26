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
	static String charVariablesFileName = System.getProperty("user.dir") + "/src/Input/charVariableNames.txt";
	static String unsignedCharFileName = System.getProperty("user.dir") + "/src/Input/unsignedCharacters.txt";
	static String signedCharFileName = System.getProperty("user.dir") + "/src/Input/signedCharacters.txt";
	// integers
	static String intVariablesFileName = System.getProperty("user.dir")+ "/src/Input/integerVariableNames.txt";
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
	static String floatVariablesFileName = System.getProperty("user.dir") + "/src/Input/floatVariableNames.txt";
	static String doubleFileName = System.getProperty("user.dir") + "/src/Input/double.txt";
	static String doubleVariablesFileName = System.getProperty("user.dir") + "/src/Input/doubleVariableNames.txt";
	static String longDoubleFileName = System.getProperty("user.dir") + "/src/Input/longDouble.txt";
	// string
	static String stringFileName = System.getProperty("user.dir") + "/src/Input/strings.txt";
	static String stringVariablesFileName = System.getProperty("user.dir")+ "/src/Input/stringVariableNames.txt";
	// boolean
	static String booleanFileName = System.getProperty("user.dir") + "/src/Input/boolean.txt";
	static String booleanVariablesFileName = System.getProperty("user.dir") + "/src/Input/boolVariableNames.txt";
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
		//Declaration
		ArrayList<String> declarationStatements = new ArrayList<String>();
		//Initialisation
		//boolean 
		ArrayList<String> boolInitStatements = new ArrayList<String>();
		
		//char types
		ArrayList<String> charInitStatements = new ArrayList<String>();
		ArrayList<String> signedcharInitStatements = new ArrayList<String>();
		ArrayList<String> unsignedcharInitStatements = new ArrayList<String>();
		//Int types
		ArrayList<String> intInitStatements = new ArrayList<String>();
		ArrayList<String> longintInitStatements = new ArrayList<String>();
		ArrayList<String> shortintInitStatements = new ArrayList<String>();
		ArrayList<String> signedintInitStatements = new ArrayList<String>();
		ArrayList<String> signedlongintInitStatements = new ArrayList<String>();
		ArrayList<String> signedshortintInitStatements = new ArrayList<String>();
		ArrayList<String> unsignedintInitStatements = new ArrayList<String>();
		ArrayList<String> unsignedlongintInitStatements = new ArrayList<String>();
		ArrayList<String> unsignedshortintInitStatements = new ArrayList<String>();
		
		//Floating point types
		ArrayList<String> floatInitStatements = new ArrayList<String>();
		ArrayList<String> doubleInitStatements = new ArrayList<String>();
		ArrayList<String> longdoubleInitStatements = new ArrayList<String>();
		
		//String
		ArrayList<String> stringInitStatements = new ArrayList<String>();
		
		//if statements
		ArrayList<String> twoSubjectIfStatements = new ArrayList<String>();	
		declarationStatements = sentenceGenerator.constructSomeDeclarationStatements();
		twoSubjectIfStatements.addAll(sentenceGenerator.construct2SubjectIfStatementsNouns());
		twoSubjectIfStatements.addAll(sentenceGenerator.construct2SubjectIfStatementsNumbers());
		// twoSubjectIfStatements = sentenceGenerator.construct2SubjectIfStatementsNumbers();
		
		//Initialization
		//bool
		boolInitStatements = sentenceGenerator.constructBoolInit();
		
		//char types
		charInitStatements = sentenceGenerator.constructCharInit();
		signedcharInitStatements = sentenceGenerator.constructIntTypesInit("signed character");
		unsignedcharInitStatements = sentenceGenerator.constructIntTypesInit("unsigned character");
		
		//Int types
		intInitStatements = sentenceGenerator.constructIntTypesInit("integer");
		longintInitStatements = sentenceGenerator.constructIntTypesInit("long integer");
		shortintInitStatements = sentenceGenerator.constructIntTypesInit("short integer");
		signedintInitStatements = sentenceGenerator.constructIntTypesInit("signed integer");
		signedlongintInitStatements = sentenceGenerator.constructIntTypesInit("signed long integer");
		signedshortintInitStatements = sentenceGenerator.constructIntTypesInit("signed short integer");
		unsignedintInitStatements = sentenceGenerator.constructIntTypesInit("unsigned integer");
		unsignedlongintInitStatements = sentenceGenerator.constructIntTypesInit("unsigned long integer");
		unsignedshortintInitStatements = sentenceGenerator.constructIntTypesInit("unsigned short integer");
		
		//Floating point types
		floatInitStatements = sentenceGenerator.constructFloatingPointInit("float");
		doubleInitStatements = sentenceGenerator.constructFloatingPointInit("double");
		longdoubleInitStatements = sentenceGenerator.constructFloatingPointInit("long double");
		
		//String
		stringInitStatements = sentenceGenerator.constructStringInit();
		
		// writing statements to text file
		String outputFileName = System.getProperty("user.dir") + "/src/Output/DeclarationStatements.txt";
		sentenceGenerator.writeStatements(outputFileName, declarationStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/2SubjectIfStatements.txt"; 
		sentenceGenerator.writeStatements(outputFileName, twoSubjectIfStatements);
		
		//Initialisation
		//Boolean
		outputFileName = System.getProperty("user.dir") + "/src/Output/BoolInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, boolInitStatements);
		
		//Char types
		outputFileName = System.getProperty("user.dir") + "/src/Output/CharInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, charInitStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/SignedCharInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, signedcharInitStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/UnsignedCharInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, unsignedcharInitStatements);
		
		//Int types
		outputFileName = System.getProperty("user.dir") + "/src/Output/IntInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, intInitStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/LongIntInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, longintInitStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/ShortIntInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, shortintInitStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/SignedIntInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, signedintInitStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/SignedLongIntInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, signedlongintInitStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/SignedShortIntInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, signedshortintInitStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/UnsignedIntInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, unsignedintInitStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/UnsignedLongIntInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, unsignedlongintInitStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/UnsignedShortIntInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, unsignedshortintInitStatements);
		
		//Floating point types
		outputFileName = System.getProperty("user.dir") + "/src/Output/FloatInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, floatInitStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/DoubleInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, doubleInitStatements);
		outputFileName = System.getProperty("user.dir") + "/src/Output/LongDoubleInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, longdoubleInitStatements);
		
		//String
		outputFileName = System.getProperty("user.dir") + "/src/Output/StringInit.txt"; 
		sentenceGenerator.writeStatements(outputFileName, stringInitStatements);
		
		
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
			sentenceGenerator.loadWords(charVariablesFileName, sentenceGenerator.charVariables);
			sentenceGenerator.loadWords(unsignedCharFileName, sentenceGenerator.unsignedCharacters);				
			sentenceGenerator.loadWords(signedCharFileName, sentenceGenerator.signedCharacters);
			//integers
			sentenceGenerator.loadWords(intVariablesFileName, sentenceGenerator.intVariables);
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
			sentenceGenerator.loadWords(floatVariablesFileName, sentenceGenerator.floatVariables);
			sentenceGenerator.loadWords(doubleFileName, sentenceGenerator.doubles);
			sentenceGenerator.loadWords(doubleVariablesFileName, sentenceGenerator.doubleVariables);
			sentenceGenerator.loadWords(longDoubleFileName, sentenceGenerator.longDoubles);
			//string
			sentenceGenerator.loadWords(stringFileName, sentenceGenerator.strings);
			sentenceGenerator.loadWords(stringVariablesFileName, sentenceGenerator.stringVariables);
			//boolean
			sentenceGenerator.loadWords(booleanFileName, sentenceGenerator.booleans);
			sentenceGenerator.loadWords(booleanVariablesFileName, sentenceGenerator.boolVariables);
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
