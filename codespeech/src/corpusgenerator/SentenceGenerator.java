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
	ArrayList<String> characters = new ArrayList<String>();
	ArrayList<String> unsignedCharacters = new ArrayList<String>();
	ArrayList<String> signedCharacters = new ArrayList<String>();
	ArrayList<String> integers = new ArrayList<String>();
	ArrayList<String> unsignedIntegers = new ArrayList<String>();
	ArrayList<String> signedIntegers = new ArrayList<String>();
	ArrayList<String> shortIntegers = new ArrayList<String>();
	ArrayList<String> unsignedShortIntegers = new ArrayList<String>();
	ArrayList<String> signedShortIntegers = new ArrayList<String>();
	ArrayList<String> longIntegers = new ArrayList<String>();
	ArrayList<String> signedLongIntegers = new ArrayList<String>();
	ArrayList<String> unsignedLongIntegers = new ArrayList<String>();
	ArrayList<String> floats = new ArrayList<String>();
	ArrayList<String> doubles = new ArrayList<String>();
	ArrayList<String> longDoubles	 = new ArrayList<String>();
	ArrayList<String> strings	 = new ArrayList<String>();
	ArrayList<String> variables = new ArrayList<String>();
	ArrayList<String> declareCommands = new ArrayList<String>();
	ArrayList<String> dataTypes = new ArrayList<String>();
	
	static final String SPACE = " ";
	

	public ArrayList<String> loadWords(String fileName, ArrayList<String> list) throws FileNotFoundException, IOException
	{
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = "";

		// Repeat until all lines are read
		while ((line = bufferedReader.readLine()) != null)
		{
			list.add(line);
		}
		bufferedReader.close();		
		return list;
	}

	public void writeStatements(String fileName, ArrayList<String> statements) throws FileNotFoundException
	{
		PrintWriter printWriter = new PrintWriter(fileName);
		for (String statement : statements)
		{
			printWriter.println(statement.trim());
		}

		printWriter.close();	
	}
	
	
// TODO Declare/Initialise a character temp as c
// TODO Declare	an unsigned character temp as 127
	

	public ArrayList<String> constructAllDeclarationStatements()
	{
		String determiner;
		//String declarationCommand;
		String declarationStatement;
		ArrayList<String> declarationStatements = new ArrayList<String>();
		ArrayList<String> declareWords = new ArrayList<String>(Arrays.asList("Create","Declare"));
		ArrayList<String> adjective = new ArrayList<String>(Arrays.asList("named","called","as"));
		// for each data type
		for (String dataType : dataTypes)
		{
			char firstCharacter = dataType.charAt(0);
			if (firstCharacter == 'a' || firstCharacter == 'e' || firstCharacter == 'i' || firstCharacter == 'o' 
					|| firstCharacter == 'u' )
				determiner = "an";
			else
				determiner = "a";	
			// for each variable name
			for (String variableName : variables)
			{
				// This is done to minimize repetition in the corpus
				// 1/2 of statements will begin with 'Declare'
				// 1/2 of statements will begin with 'Create'
				for (String declareWord : declareWords){
					//e.g. declare an integer y
					declarationStatement = declareWord + SPACE +  determiner + SPACE + dataType + SPACE + variableName;
					declarationStatements.add(declarationStatement);
					//Adding adjectives
					for (String adj : adjective)
					{
						//e.g. declare an integer named y
						declarationStatement = declareWord + SPACE +  determiner + SPACE + dataType + SPACE + adj + SPACE + variableName;
						declarationStatements.add(declarationStatement);
					}
					
					//Adding the word variable to the statement
					//e.g. declare an integer variable y
					declarationStatement = declareWord + SPACE +  determiner + SPACE + dataType + SPACE + "variable" + SPACE + variableName;
					declarationStatements.add(declarationStatement);
					
					for (String adj : adjective)
					{
						//e.g. declare an integer variable named y
						declarationStatement = declareWord + SPACE +  determiner + SPACE + dataType + SPACE + "variable" + SPACE + adj + SPACE + variableName;
						declarationStatements.add(declarationStatement);
					}
				}
			}

		}
		
		return declarationStatements;

	}
	
	public ArrayList<String> constructSomeDeclarationStatements() {
		int noOfVar = 27;
		int index = 0;
		String determiner;
		// String declarationCommand;
		String declarationStatement;
		ArrayList<String> declarationStatements = new ArrayList<String>();
		ArrayList<String> declareWords = new ArrayList<String>(Arrays.asList(
				"Create", "Declare"));
		ArrayList<String> adjective = new ArrayList<String>(Arrays.asList(
				"named", "called", "as"));
		
		// for each data type
		for (String dataType : dataTypes) {
			char firstCharacter = dataType.charAt(0);
			if (firstCharacter == 'a' || firstCharacter == 'e'
					|| firstCharacter == 'i' || firstCharacter == 'o'
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
				declarationStatement = declareWord + SPACE + determiner + SPACE
						+ dataType + SPACE + variables.get(index++ % noOfVar);
				declarationStatements.add(declarationStatement);
				// Adding adjectives
				for (String adj : adjective) {
					// e.g. declare an integer named y
					declarationStatement = declareWord + SPACE + determiner
							+ SPACE + dataType + SPACE + adj + SPACE
							+ variables.get(index++ % noOfVar);
					declarationStatements.add(declarationStatement);
				}

				// Adding the word variable to the statement
				// e.g. declare an integer variable y
				declarationStatement = declareWord + SPACE + determiner + SPACE
						+ dataType + SPACE + "variable" + SPACE + variables.get(index++ % noOfVar);
				declarationStatements.add(declarationStatement);

				for (String adj : adjective) {
					// e.g. declare an integer variable named y
					declarationStatement = declareWord + SPACE + determiner
							+ SPACE + dataType + SPACE + "variable" + SPACE
							+ adj + SPACE + variables.get(index++ % noOfVar);
					declarationStatements.add(declarationStatement);
				}

			}

		}

		return declarationStatements;

	}


	public ArrayList<String> construct2SubjectIfStatements()
	{
		ArrayList<String> subjects1 = new ArrayList<String>();
		ArrayList<String> subjects2 = new ArrayList<String>();
		for (int i=0; i < 5 ; i++){
			subjects2.add(subjects.get(i));
		}

		for (int i= 5; i < subjects.size(); i++){
			subjects1.add(subjects.get(i));
		}
		// for every first subject
		for (int i = 0; i < subjects1.size(); i++)
		{
			String subject1 = subjects1.get(i).trim();
			// for every second subject
			for (int j = 0; j < subjects2.size(); j++ )
			{
				String subject2 = subjects2.get(j).trim();
				// for every condition
				for (int k = 0; k < conditions.size(); k++)
				{
					String statement = "If ";
					String condition = conditions.get(k).trim();
					statement = statement + subject1 + SPACE + condition + SPACE + subject2;
					statements.add(statement);
				}
			}
		}
		return statements;
	}

}