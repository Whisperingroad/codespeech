package corpusgenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class SentenceGenerator {

	// for if statements
	ArrayList<String> subjects = new ArrayList<String>();
	ArrayList<String> conditions = new ArrayList<String>();
	ArrayList<String> statements = new ArrayList<String>();
	ArrayList<String> conjunctions = new ArrayList<String>();

	// for declaration/initialization statements
	ArrayList<String> booleans = new ArrayList<String>();
	ArrayList<String> boolVariables = new ArrayList<String>();
	ArrayList<String> characters = new ArrayList<String>();
	ArrayList<String> charVariables = new ArrayList<String>();
	ArrayList<String> unsignedCharacters = new ArrayList<String>();
	ArrayList<String> signedCharacters = new ArrayList<String>();
	ArrayList<String> integers = new ArrayList<String>();
	ArrayList<String> intVariables = new ArrayList<String>();
	ArrayList<String> unsignedIntegers = new ArrayList<String>();
	ArrayList<String> signedIntegers = new ArrayList<String>();
	ArrayList<String> shortIntegers = new ArrayList<String>();
	ArrayList<String> unsignedShortIntegers = new ArrayList<String>();
	ArrayList<String> signedShortIntegers = new ArrayList<String>();
	ArrayList<String> longIntegers = new ArrayList<String>();
	ArrayList<String> signedLongIntegers = new ArrayList<String>();
	ArrayList<String> unsignedLongIntegers = new ArrayList<String>();
	ArrayList<String> floats = new ArrayList<String>();
	ArrayList<String> floatVariables = new ArrayList<String>();
	ArrayList<String> doubles = new ArrayList<String>();
	ArrayList<String> doubleVariables = new ArrayList<String>();
	ArrayList<String> longDoubles = new ArrayList<String>();
	ArrayList<String> strings = new ArrayList<String>();
	ArrayList<String> stringVariables = new ArrayList<String>();
	ArrayList<String> variables = new ArrayList<String>();
	ArrayList<String> declareCommands = new ArrayList<String>();
	ArrayList<String> dataTypes = new ArrayList<String>();

	static final String SPACE = " ";
	static final String INITIALISE = "Initialise";
	static final String PERIOD = ".";

	public ArrayList<String> loadWords(String fileName, ArrayList<String> list)
			throws FileNotFoundException, IOException {
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = "";

		// Repeat until all lines are read
		while ((line = bufferedReader.readLine()) != null) {
			list.add(line);
		}
		bufferedReader.close();
		return list;
	}

	public void writeStatements(String fileName, ArrayList<String> statements) throws FileNotFoundException {
		PrintWriter printWriter = new PrintWriter(fileName);
		for (String statement : statements) {
			printWriter.println(statement.trim());
		}

		printWriter.close();
	}

	// TODO Declare/Initialise a character temp as c
	// TODO Declare an unsigned character temp as 127
	public ArrayList<String> constructAllDeclarationStatements() {
		String determiner;
		// String declarationCommand;
		String declarationStatement;
		ArrayList<String> declarationStatements = new ArrayList<String>();
		ArrayList<String> declareWords = new ArrayList<String>(Arrays.asList("Create", "Declare"));
		ArrayList<String> adjective = new ArrayList<String>(Arrays.asList("named", "called", "as"));
		// for each data type
		for (String dataType : dataTypes) {
			char firstCharacter = dataType.charAt(0);
			if (firstCharacter == 'a' || firstCharacter == 'e' || firstCharacter == 'i' || firstCharacter == 'o'
					|| firstCharacter == 'u')
				determiner = "an";
			else
				determiner = "a";
			// for each variable name
			for (String variableName : variables) {
				// This is done to minimize repetition in the corpus
				// 1/2 of statements will begin with 'Declare'
				// 1/2 of statements will begin with 'Create'
				for (String declareWord : declareWords) {
					// e.g. declare an integer y
					declarationStatement = declareWord + SPACE + determiner + SPACE + dataType + SPACE + variableName;
					declarationStatements.add(declarationStatement);
					// Adding adjectives
					for (String adj : adjective) {
						// e.g. declare an integer named y
						declarationStatement = declareWord + SPACE + determiner + SPACE + dataType + SPACE + adj + SPACE
								+ variableName;
						declarationStatements.add(declarationStatement);
					}
					// Adding the word variable to the statement
					// e.g. declare an integer variable y
					declarationStatement = declareWord + SPACE + determiner + SPACE + dataType + SPACE + "variable"
							+ SPACE + variableName;
					declarationStatements.add(declarationStatement);
					for (String adj : adjective) {
						// e.g. declare an integer variable named y
						declarationStatement = declareWord + SPACE + determiner + SPACE + dataType + SPACE + "variable"
								+ SPACE + adj + SPACE + variableName;
						declarationStatements.add(declarationStatement);
					}
				}
			}

		}
		return declarationStatements;
	}
	
	public ArrayList<String> constructSomeDeclarationStatements() {
		//Number of variable names
		int noOfVar = variables.size();
		int index = 0;
		String determiner;
		// String declarationCommand;
		String declarationStatement;
		ArrayList<String> declarationStatements = new ArrayList<String>();
		ArrayList<String> declareWords = new ArrayList<String>(Arrays.asList("Create", "Declare"));
		ArrayList<String> adjective = new ArrayList<String>(Arrays.asList("named", "called", "as"));

		// for each data type
		for (String dataType : dataTypes) {
			char firstCharacter = dataType.charAt(0);
			if (firstCharacter == 'a' || firstCharacter == 'e' || firstCharacter == 'i' || firstCharacter == 'o'
					|| firstCharacter == 'u')
				determiner = "an";
			else
				determiner = "a";
			// for each variable name
			// This is done to minimize repetition in the corpus
			// 1/2 of statements will begin with 'Declare'
			// 1/2 of statements will begin with 'Create'
			for (String declareWord : declareWords) {
				// e.g. declare an integer y
				declarationStatement = declareWord + SPACE + determiner + SPACE + dataType + SPACE
						+ variables.get(index++ % noOfVar) + ".";
				declarationStatements.add(declarationStatement);
				// Adding adjectives
				for (String adj : adjective) {
					// e.g. declare an integer named y
					declarationStatement = declareWord + SPACE + determiner + SPACE + dataType + SPACE + adj + SPACE
							+ variables.get(index++ % noOfVar) + ".";
					declarationStatements.add(declarationStatement);
				}
				// Adding the word variable to the statement
				// e.g. declare an integer variable y
				declarationStatement = declareWord + SPACE + determiner + SPACE + dataType + SPACE + "variable" + SPACE
						+ variables.get(index++ % noOfVar) + ".";
				declarationStatements.add(declarationStatement);

				for (String adj : adjective) {
					// e.g. declare an integer variable named y
					declarationStatement = declareWord + SPACE + determiner + SPACE + dataType + SPACE + "variable"
							+ SPACE + adj + SPACE + variables.get(index++ % noOfVar) + ".";
					declarationStatements.add(declarationStatement);
				}
			}
		}
		return declarationStatements;
	}

	public ArrayList<String> constructBoolInit()
	{
		ArrayList<String> initStatements = new ArrayList<String>();
		String initStatement;

		//Number of variable names
		int noOfVar = boolVariables.size();
		int index = 0;
		
		for (int i = 0; i< 10 ; i++)
		{
			initStatement = INITIALISE + " a boolean variable called " + boolVariables.get(index % noOfVar) + " to " + booleans.get(index++ % 2) + PERIOD;
			initStatements.add(initStatement);
		}
		
		for (int i = 0; i< 10 ; i++)
		{
			initStatement = INITIALISE + " a boolean variable named " + boolVariables.get(index % noOfVar) + " to " + booleans.get(index++ % 2) + PERIOD;
			initStatements.add(initStatement);
		}
		
		for (int i = 0; i< 10 ; i++)
		{
			initStatement = INITIALISE + " a boolean " + boolVariables.get(index % noOfVar) + " as " + booleans.get(index++ % 2) + PERIOD;
			initStatements.add(initStatement);
		}	
		
		for (int i = 0; i< 10 ; i++)
		{
			initStatement = INITIALISE + " a boolean variable " + boolVariables.get(index % noOfVar) + " to " + booleans.get(index++ % 2) + PERIOD;
			initStatements.add(initStatement);
		}
		
		return initStatements;
	}
	
	public ArrayList<String> constructCharInit()
	{
		ArrayList<String> initStatements = new ArrayList<String>();
		String initStatement;

		//Number of variable names
		int noOfVar = charVariables.size();
		int noOfValues = characters.size();
		int index = 0;
		
		for (int i=0; i<20 ; i++)
		{
			initStatement = INITIALISE + " a character variable called " + charVariables.get(index % noOfVar) + " to " + characters.get(index++ % noOfValues) + PERIOD;
			initStatements.add(initStatement);
		}
		
		for (int i = 0; i<20 ; i++)
		{
			initStatement = INITIALISE + " a character variable named " + charVariables.get(index % noOfVar) + " to " + characters.get(index++ % noOfValues) + PERIOD;
			initStatements.add(initStatement);
		}
		
		for (int i = 0; i<20 ; i++)
		{
			initStatement = INITIALISE + " a character variable " + charVariables.get(index % noOfVar) + " as " + characters.get(index++ % noOfValues) + PERIOD;
			initStatements.add(initStatement);
		}
		
		for (int i = 0; i<20 ; i++)
		{
			initStatement = INITIALISE + " a character variable " + charVariables.get(index % noOfVar) + " to " + characters.get(index++ % noOfValues) + PERIOD;
			initStatements.add(initStatement);
		}
		
		return initStatements;
	}
	
	//Constructs integers,long integer,short integer,signed integer,signed long integer,
	//signed short integer, unsigned integer, unsigned long integer and unsigned short integer
	public ArrayList<String> constructIntTypesInit(String dataType)
	{
		ArrayList<String> initStatements = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		String initStatement;
		String determiner = "a";

		//Number of variable names
		int noOfVar = intVariables.size();
		int index = 0;
		
		if (dataType.equals("integer"))
		{
			values = integers;
			determiner = "an";
		}
		else if (dataType.equals("long integer"))
		{
			values = longIntegers;
		}
		else if (dataType.equals("short integer"))
		{
			values = shortIntegers;
		}
		else if (dataType.equals("signed integer"))
		{
			values = signedIntegers;
		}
		else if (dataType.equals("signed long integer"))
		{
			values = signedLongIntegers;
		}
		else if (dataType.equals("signed short integer"))
		{
			values = signedShortIntegers;
		}
		else if (dataType.equals("unsigned integer"))
		{
			values = unsignedIntegers;
			determiner = "an";
		}
		else if (dataType.equals("unsigned long integer"))
		{
			values = unsignedLongIntegers;
			determiner = "an";
		}
		else if (dataType.equals("unsigned short integer"))
		{
			values = unsignedShortIntegers;
			determiner = "an";
		}
		
		else if (dataType.equals("signed character"))
		{
			values = signedCharacters;
		}
		else
		{
			values = unsignedCharacters;
			determiner = "an";
		}
		
		int noOfVal = values.size();
		
		for (int i = 0; i <20 ; i++)
		{
			initStatement = INITIALISE + SPACE + determiner+ SPACE + dataType + SPACE + intVariables.get(index % noOfVar) + " to " + values.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
			
		}
		
		for (int i = 0; i <20 ; i++)
		{
			initStatement = INITIALISE + SPACE + determiner + SPACE + dataType + " variable "  + intVariables.get(index % noOfVar) + " to " + values.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
		}
		
		for (int i = 0; i <20 ; i++)
		{
			initStatement = INITIALISE + SPACE + determiner + SPACE + dataType + " variable called " + intVariables.get(index % noOfVar) + " to " + values.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
		}
		
		for (int i = 0; i <20 ; i++)
		{
			initStatement = INITIALISE + SPACE + determiner + SPACE + dataType + " variable named " + intVariables.get(index % noOfVar) + " to " + values.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
		}
		
		for (int i = 0; i <20 ; i++)
		{
			initStatement = INITIALISE + SPACE + determiner + SPACE + dataType + " called " + intVariables.get(index % noOfVar) + " to " + values.get(index++ % noOfVal) + PERIOD;			
			initStatements.add(initStatement);
		}
		return initStatements;
	}
	
	public ArrayList<String> constructStringInit()
	{
		ArrayList<String> initStatements = new ArrayList<String>();
		String initStatement;
		int noOfVar = stringVariables.size();
		int noOfVal = strings.size();
		int index = 0;
		
		for (int i = 0; i<20 ; i++)
		{
			initStatement = INITIALISE + " a string " + stringVariables.get(index % noOfVar) + " to " + strings.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
			
		}
		
		for (int i = 0; i<20 ; i++)
		{
			initStatement = INITIALISE + " a string variable " + stringVariables.get(index % noOfVar) + " to " + strings.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
			
		}
		
		for (int i = 0; i<20 ; i++)
		{
			initStatement = INITIALISE + " a string variable called " + stringVariables.get(index % noOfVar) + " to " + strings.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
			
		}
		
		for (int i = 0; i<20 ; i++)
		{
			initStatement = INITIALISE + " a string variable named " + stringVariables.get(index % noOfVar) + " to " + strings.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
			
		}
		
		for (int i = 0; i<20 ; i++)
		{
			initStatement = INITIALISE + " a string called " + stringVariables.get(index % noOfVar) + " to " + strings.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
			
		}
		
		for (int i = 0; i<20 ; i++)
		{
			initStatement = INITIALISE + " a string called " + stringVariables.get(index % noOfVar) + " to the string " + strings.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
			
		}
		
		for (int i = 0; i<20 ; i++)
		{
			initStatement = INITIALISE + " a string " + stringVariables.get(index % noOfVar) + " as " + strings.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
			
		}
		
		return initStatements;
	}
	
	//Constructs float,double and long double. 
	public ArrayList<String> constructFloatingPointInit(String dataType)
	{
		ArrayList<String> initStatements = new ArrayList<String>();
		String initStatement;
		ArrayList<String> variableNames = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		if (dataType.equals("float"))
		{
			variableNames = floatVariables;
			values = floats;
		}
		else
		{
			variableNames = doubleVariables;
			values = doubles;
		}
		
		int noOfVar = variableNames.size();
		int noOfVal = values.size();
		int index = 0;
		
		for (int i=0 ; i<20 ; i ++)
		{
			initStatement = INITIALISE + " a " + dataType + SPACE + variableNames.get(index % noOfVar) + " to " + values.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
			
		}
		
		for (int i=0 ; i<20 ; i ++)
		{
			initStatement = INITIALISE + " a " + dataType + " variable " + variableNames.get(index % noOfVar) + " to " + values.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
		}
		
		for (int i=0 ; i<20 ; i ++)
		{
			initStatement = INITIALISE + " a " + dataType  + " variable called " + variableNames.get(index % noOfVar) + " to " + values.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
		}
		
		for (int i=0 ; i<20 ; i ++)
		{
			initStatement = INITIALISE + " a " + dataType + " variable named " + variableNames.get(index % noOfVar) + " to " + values.get(index++ % noOfVal) + PERIOD;
			initStatements.add(initStatement);
		}
		
		for (int i=0 ; i<20 ; i ++)
		{
			initStatement = INITIALISE + " a " + dataType + " called " + variableNames.get(index % noOfVar) + " to " + values.get(index++ % noOfVal) + PERIOD;			
			initStatements.add(initStatement);
		}
		
		
		return initStatements;
	}
	
	// if statements in which second subjects are numerals
	public ArrayList<String> construct2SubjectIfStatementsNumbers() {
		ArrayList<String> subjects1 = new ArrayList<String>();
		ArrayList<String> subjects2 = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			subjects2.add(subjects.get(i));
		}

		for (int i = 5; i < subjects.size(); i++) {
			subjects1.add(subjects.get(i));
		}
		// for every first subject
		for (int i = 0; i < subjects1.size(); i++) {
			String subject1 = subjects1.get(i).trim();
			// for every second subject
			for (int j = 0; j < subjects2.size(); j++) {
				String subject2 = subjects2.get(j).trim();
				// for every condition
				for (int k = 0; k < conditions.size(); k++) {
					String statement = "If ";
					String condition = conditions.get(k).trim();
					statement = statement + subject1 + SPACE + condition + SPACE + subject2 + ".";
					statements.add(statement);
				}
			}
		}
		return statements;
	}
	// if statements in which both first and second subjects are nouns
	public ArrayList<String> construct2SubjectIfStatementsNouns() {
		ArrayList<String> subjects1 = new ArrayList<String>();
		ArrayList<String> subjects2 = new ArrayList<String>();
		for (int i = 20; i < subjects.size(); i++) {
			subjects2.add(subjects.get(i));
		}
		// from temp to j
		for (int i = 5; i < 20; i++) {
			subjects1.add(subjects.get(i));
		}
		// for every first subject
		for (int i = 0; i < subjects1.size(); i++) {
			String subject1 = subjects1.get(i).trim();
			// for every second subject
			for (int j = 0; j < subjects2.size(); j++) {
				String subject2 = subjects2.get(j).trim();
				// for every condition
				for (int k = 0; k < conditions.size(); k++) {
					String statement = "If ";
					String condition = conditions.get(k).trim();
					statement = statement + subject1 + SPACE + condition + SPACE + subject2 + ".";
					statements.add(statement);
				}
			}
		}
		return statements;
	}
}