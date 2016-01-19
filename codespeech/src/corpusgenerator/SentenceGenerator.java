package corpusgenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SentenceGenerator {

	// for if statements
	ArrayList<String> subjects = new ArrayList<String>();
	ArrayList<String> conditions = new ArrayList<String>();
	ArrayList<String> statements = new ArrayList<String>();
	ArrayList<String> conjunctions = new ArrayList<String>();

	// for declaration/initialization statements
	ArrayList<String> variables = new ArrayList<String>();
	ArrayList<String> dataTypes = new ArrayList<String>();
	ArrayList<String> declareCommands = new ArrayList<String>();

	String space = " ";

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

	public ArrayList<String> constructDeclarationStatements()
	{
		int count = 0;
		String determiner;
		String declarationCommand;
		String declarationStatement;
		ArrayList<String> declarationStatements = new ArrayList<String>();

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
			for (String variable : variables)
			{
				// This is done to minimize repetition in the corpus
				// 1/3 of statements will begin with 'Initialize'
				// 1/3 of statements will begin with 'Declare'
				// 1/3 of statements will begin with 'Create'
				if (count == 0)
					declarationCommand = "Declare";
				else if (count == 1)
					declarationCommand = "Initialise";
				else if (count == 2)
				{
					declarationCommand = "Create";
					count = 0;
				}
				// "declare an integer x as 5
				//statement = declarationCommand + space + determiner + dataType
				






			}

		}

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
					statement = statement + subject1 + space + condition + space + subject2;
					statements.add(statement);
				}
			}
		}
		return statements;
	}

}