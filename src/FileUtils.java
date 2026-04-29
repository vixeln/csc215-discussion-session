package com.github.hexadecalice; 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashSet;

public final class FileUtils { 
	
	public static HashSet<String> readWordList(String fileName) { 
			File wordListFile = new File(fileName);
			HashSet<String> wordList = new HashSet<>();
			try(Scanner scanner = new Scanner(wordListFile)) { 
				
				while(scanner.hasNextLine()) { 
					
					String line = scanner.nextLine();
					if(line.isEmpty()) { 
						continue;
					}
					String betterLine = line.replaceAll("\\s+", "");
					wordList.add(betterLine);
					
				} 
				
				
				
			} catch(FileNotFoundException e) { 
				System.out.println("File " + fileName + " could not be located");
				System.exit(1);
			} 
			return wordList;
		
		
		
	} 
	
	
	
	
}
