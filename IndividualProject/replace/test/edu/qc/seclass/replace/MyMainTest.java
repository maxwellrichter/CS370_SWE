package edu.qc.seclass.replace;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class MyMainTest {

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Some utilities

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice");

        fileWriter.close();
        return file1;
    }

    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123");

        fileWriter.close();
        return file1;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // Actual test cases
    
    @Test // Implementation of test frame #6
    public void mainTest7() throws Exception {
    	File inputFile = createInputFile2();

        String args[] = {"-f", "HowDY", "HELLO", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #10
    public void mainTest8() throws Exception {
    	File inputFile = createInputFile2();

        String args[] = {"replace", "reput", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill,\n" +
                "This is another test file for the reput utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #11
    public void mainTest9() throws Exception {
    	File inputFile = createInputFile2();

        String args[] = {"-i", "Replace", "reput", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill,\n" +
                "This is another test file for the reput utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
   
    
    @Test // Implementation of test frame #12
    public void mainTest10() throws Exception {
    	File inputFile = createInputFile2();

        String args[] = {"is another", "simply is a", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill,\n" +
                "This simply is a test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #13
    public void mainTest11() throws Exception {
    	File inputFile = createInputFile2();

        String args[] = {"-i", "is aNother", "simply is a", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill,\n" +
                "This simply is a test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    
    @Test // Implementation of test frame #14
    public void mainTest12() throws Exception {
    	File inputFile = createInputFile2();

        String args[] = {"-f", "Bill", "Billiffer", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Billiffer,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #15
    public void mainTest13() throws Exception {
    	File inputFile = createInputFile2();

        String args[] = {"-l", "Bill", "Billiffer", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Billiffer\" twice";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    

    @Test // Implementation of test frame #16
    public void mainTest14() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-f", "-l", "abc", "xyz", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your xyz and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: xyz and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #18
    public void mainTest15() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-f", "-i", "aBc", "xYz", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your xYz and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #19
    public void mainTest16() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-l", "-i", "aBc", "xyZ", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: xyZ and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #20
    public void mainTest17() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-f", "-l", "-i", "aBc", "xyZ", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your xyZ and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: xyZ and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    
    @Test // Implementation of test frame #22
    public void mainTest18() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-f", "abc and 123", "xyz and also 789", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your xyz and also 789?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    
    @Test // Implementation of test frame #23
    public void mainTest19() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-l", "abc and 123", "xyz and also 789", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: xyz and also 789";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #24
    public void mainTest20() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-f", "-l", " abc and 123", "xyz and also 789", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned yourxyz and also 789?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me:xyz and also 789";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #26
    public void mainTest21() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-i", "-f", "AbC ANd 123", "xyz and also 789", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your xyz and also 789?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    
    @Test // Implementation of test frame #27
    public void mainTest22() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-i", "-l", "AbC ANd 123", "xyz and also 789", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: xyz and also 789";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    
    @Test // Implementation of test frame #28
    public void mainTest23() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-l", "-i", "-f", "AbC ANd 123", "xyz and also 789", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your xyz and also 789?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: xyz and also 789";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    
    @Test // Implementation of test frame #30
    public void mainTest24() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-f", "l", "ck", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bickl, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #31
    public void mainTest25() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-l", "l", "ck", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you shouckd study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #32
    public void mainTest26() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-f", "-l", "l", "ck", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bickl, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you shouckd study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #33
    public void mainTest27() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"l", "ck", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bickck, have you ckearned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you shouckd study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #34
    public void mainTest28() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-i", "-f", "L", "ck", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bickl, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #35
    public void mainTest29() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-i", "-l", "L", "ck", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you shouckd study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #36
    public void mainTest30() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-i", "-f", "-l", "L", "ck", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bickl, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you shouckd study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #37
    public void mainTest31() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-i", "L", "ck", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bickck, have you ckearned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you shouckd study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #38
    public void mainTest32() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-f", " i", "-y", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It-ys important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #39
    public void mainTest33() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-l", " i", "-y", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study-yt\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #40
    public void mainTest34() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-f", "-l", " i", "-y", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It-ys important to know your abc and 123," +
                "so you should study-yt\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #41
    public void mainTest35() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {" i", "-y", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It-ys-ymportant to know your abc and 123," +
                "so you should study-yt\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #42
    public void mainTest36() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-i", "-f", " I", "-y", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It-ys important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    
    @Test // Implementation of test frame #43
    public void mainTest37() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-i", "-l", " I", "-y", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study-yt\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #44
    public void mainTest38() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-f", "-i", "-l", " I", "-y", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It-ys important to know your abc and 123," +
                "so you should study-yt\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #45
    public void mainTest39() throws Exception {
    	File inputFile = createInputFile3();

        String args[] = {"-i", " I", "-y", "--", inputFile.getPath()};
        Main.main(args);
        
        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It-ys-ymportant to know your abc and 123," +
                "so you should study-yt\n" +
                "and then repeat with me: abc and 123";
        
        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);

        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
    }
    
    @Test // Implementation of test frame #48
    public void mainTest40() throws Exception {
    	File inputFile1 = createInputFile1();
    	File inputFile2 = createInputFile2();
    	
        String args[] = {"this is from blach", "that is from", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());


        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);


        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

    }
    
    @Test // Implementation of test frame #52
    public void mainTest41() throws Exception {
    	File inputFile1 = createInputFile1();
    	File inputFile2 = createInputFile2();
    	
        String args[] = {"interesting", "fascinating", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some fascinating test cases...\n" +
                "And let's say \"howdy bill\" again!";
        
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());


        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);


        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

    }
    
    @Test // Implementation of test frame #53
    public void mainTest42() throws Exception {
    	File inputFile1 = createInputFile1();
    	File inputFile2 = createInputFile2();
    	
        String args[] = {"-i", "interEsting", "fascinating", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some fascinating test cases...\n" +
                "And let's say \"howdy bill\" again!";
        
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());


        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);


        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

    }
    
    @Test // Implementation of test frame #54
    public void mainTest43() throws Exception {
    	File inputFile1 = createInputFile1();
    	File inputFile2 = createInputFile2();
    	
        String args[] = {"replace utility", "changing tool", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the changing tool\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the changing tool\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());


        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);


        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

    }
    
    @Test // Implementation of test frame #55
    public void mainTest44() throws Exception {
    	File inputFile1 = createInputFile1();
    	File inputFile2 = createInputFile2();
    	
        String args[] = {"-i", "replacE utility", "changing tool", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the changing tool\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        
        String expected2 = "Howdy Bill,\n" +
                "This is another test file for the changing tool\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());


        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);


        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

    }
    
    @Test // Implementation of test frame #56
    public void mainTest45() throws Exception {
    	File inputFile1 = createInputFile1();
    	File inputFile2 = createInputFile2();
    	
        String args[] = {"-f", "test", "zezt", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "Howdy Bill,\n" +
                "This is a zezt file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";
        
        String expected2 = "Howdy Bill,\n" +
                "This is another zezt file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());


        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);


        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

    }
    
    @Test // Implementation of test frame #57
    public void mainTest46() throws Exception {
    	File inputFile1 = createInputFile1();
    	File inputFile2 = createInputFile2();
    	
        String args[] = {"-l", "test", "zezt", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting zezt cases...\n" +
                "And let's say \"howdy bill\" again!";
        
        String expected2 = "Howdy Bill,\n" +
                "This is another zezt file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());


        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);


        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));

    }
    
    @Test // Implementation of test frame #58
    public void mainTest47() throws Exception {
    	File inputFile1 = createInputFile1();
    	File inputFile2 = createInputFile2();
    	
        String args[] = {"-f", "-l", "test", "zezt", "--", inputFile1.getPath(), inputFile2.getPath()};
        Main.main(args);
        
        String expected1 = "Howdy Bill,\n" +
                "This is a zezt file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting zezt cases...\n" +
                "And let's say \"howdy bill\" again!";
        
        String expected2 = "Howdy Bill,\n" +
                "This is another zezt file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual2 = getFileContent(inputFile2.getPath());


        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected2, actual2);


        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
    }

    
    
}