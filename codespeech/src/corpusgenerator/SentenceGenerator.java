package corpusgenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SentenceGenerator {

	 ArrayList<String> subjects = new ArrayList<String>();
	 ArrayList<String> conditions = new ArrayList<String>();
	 ArrayList<String> statements = new ArrayList<String>();
	 ArrayList<String> conjunctions = new ArrayList<String>();
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
	
	public ArrayList<String> construct2SubjectIfStatements()
	{
		ArrayList<String> subjects1 = new ArrayList<String>();
		ArrayList<String> subjects2 = new ArrayList<String>();
		subjects1 = subjects;
		subjects2 = subjects;
		
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
					statement = statement + subject1 + space + condition + space + subject2 + ".";
					statements.add(statement);
				}
			}
		}
		return statements;
	}
	
}