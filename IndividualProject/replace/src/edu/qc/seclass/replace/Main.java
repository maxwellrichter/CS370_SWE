package edu.qc.seclass.replace;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
	protected static boolean caseInsensitive = false;
	protected static boolean backup = false;
	protected static boolean firstOnly = false;
	protected static boolean lastOnly = false;
	protected static String to = "";
	protected static String from = "";


	protected static ArrayList<String> toList;
	protected static ArrayList<String> fromList;
	
	protected static ArrayList<File> files = new ArrayList<File>();
	
    private static ByteArrayOutputStream outStream;
    private static ByteArrayOutputStream errStream;
    private static PrintStream outOrig;
    private static PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;
	
	

    public static void main(String[] args) {
    	
//        outStream = new ByteArrayOutputStream();
//        PrintStream out = new PrintStream(outStream);
//        errStream = new ByteArrayOutputStream();
//        PrintStream err = new PrintStream(errStream);
//        outOrig = System.out;
//        errOrig = System.err;
//        System.setOut(out);
//        System.setErr(err);
//    	
    	resetArgs();


    
        // TODO: Empty skeleton method
    	try {
        	setArgs(args);
    	}
    	catch(Exception e) {
    		usage();
    	}
 
    	
    	if(backup) {
    		for(File file : files) {
    			createBackup(file);
    		}
    	}
    	
    	for(File file : files) {

    		for(int i = 0; i < fromList.size(); ++i) {
    			from = fromList.get(i);
    			to = toList.get(i);
        		changeOriginal(file);

    		}
    	}
    	
    	
    	
    }

    private static void setArgs(String[] args) throws Exception {
    	boolean optionsAllSet = false;
    	for(int i = 0; i < args.length; ++i) {

    		if(!optionsAllSet) {
    			
	    		switch(args[i]) {
	    		case "-b":
	    			backup = true;
	    			break;
	    		case "-i":
	    			caseInsensitive = true;
	    			break;
	    		case "-f":
	    			firstOnly = true;
	    			break;
	    		case "-l":
	    			lastOnly = true;
	    			break;
	    		default:
	    			optionsAllSet = true;
	    		}
    		}
    		if(optionsAllSet) {
        		if(args[i].equals("--")) {
        			++i;
        		}
        		while(!args[i].equals("--") || fromList.size() < 1) {
        			fromList.add(args[i]);
	        		if(args[i].equals("")) {
	        			throw new Exception();
	        		}
	        		i++;
	        		toList.add(args[i]);
	        		i++;
        		}
        		
        		
        		
        		if(!args[i].equals("--")) {
        			throw new Exception();
        		}
        		i++;
        		while(i < args.length) {
        			files.add(new File(args[i]));
        			i++;
        		}
        		
    		}

    	}
    }
    
    
    private static void createBackup(File file) {
    	File dest = new File(file.getPath() + ".bck");
    	try {
			Files.copy(file.toPath(), dest.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Not performing replace for " + file.getName() + ": Backup file already exists");
		}
    }
    
    private static void changeOriginal(File file){
    	
    	try {

    		String text = "";
        	FileReader fr = new FileReader(file);
        	BufferedReader br = new BufferedReader(fr);
        	String line;

        	int curChar;
        	
        	while( (curChar = br.read()) != -1) {
        		if(curChar != '\r' || true) {
            		text += (char)curChar;
        		}
        	}
        	br.close();
        	
        	if(firstOnly) {
        		if(!caseInsensitive) {
        			text = text.replaceFirst(from, to);
        		}
        		else {
        			text = text.replaceFirst( "(?i)" + from, to );
        		}
        	}
        	if(lastOnly) {
        		int index = 0;
        		if(!caseInsensitive) {
        			index = text.lastIndexOf(from, text.length()-1);
	        		if(index > -1) {
	        			text = text.substring(0, index) + to + text.substring(index + from.length());;
	        		}
	        	}
        		else {
        			String revText = new StringBuilder(text).reverse().toString();
        			String revFrom = new StringBuilder(from).reverse().toString();
        			String revTo = new StringBuilder(to).reverse().toString();
        			revText = revText.replaceFirst("(?i)" + revFrom, revTo);
        			text = new StringBuilder(revText).reverse().toString();
        			
        			index = text.lastIndexOf("(?i)" + from, text.length()-1);
        		}
        		
        	}
        	if(!firstOnly && !lastOnly) {
        		if(!caseInsensitive) {
            		text = text.replaceAll(from, to);

        		}
        		else {
        			text = text.replaceAll("(?i)" + from, to);
        		}
        	}
        	        	
        	FileWriter fw = new FileWriter(file);
        	BufferedWriter bw = new BufferedWriter(fw);
        	bw.write(text);
        	bw.close();

    	}
    	catch(FileNotFoundException e) {
    		System.err.println("File " + file.getName() + " not found");
    	}
    	catch(FileAlreadyExistsException e) {
    		System.err.println("File " + file.getName() + " already exists");
    	}
    	catch(Exception e) {
    		
    	}
    }
    
    private static void resetArgs() {
    	firstOnly = false;
    	lastOnly = false;
    	caseInsensitive = false;
    	backup =false;
    	fromList = new ArrayList<String>();
    	toList = new ArrayList<String>();
    	files.clear();

    }
    
    private static void usage() {
        System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*" );
    }

}