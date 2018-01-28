package com.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ExceptionHandling.MyException;

public class FileReaders {
	static Logger logger = Logger.getLogger("FileReaders.class");
	
	public static List<String> readFile(String filePath)
	{
		logger.log(Level.INFO, "Going to read file--->"+filePath);
		List<String> list =new ArrayList<>();
		try
		{
			File file = new File(filePath);
			if (! file.exists()) 
			{ // It's not there!
			      throw new FileNotFoundException("Could not find file: " + filePath);
			}
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String line;
			while((line=br.readLine())!=null)
			{
				list.add(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
