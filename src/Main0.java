package com.github.hexadecalice;
import java.util.HashSet;
import java.net.ConnectException;
	public class Main 
{
    public static void main( String[] args ) throws Exception
    {
		try{ 
			HashSet<String> results = FileUtils.readWordList("wordlist.txt");
			Crawler.crawl(results, "theracoonlady.com");
		}
		catch(ConnectException e){ 
			System.out.println("Couldn't connnect to host, check your internet and URL format.");
			System.out.println("URLs must be formatted as hostname.com (or .org, .net, etc)");
		}
		
		
		
        
        
        
        
        
        
        
    }
}
