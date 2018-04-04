import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Random;
import java.util.Collections;

public class Main {
    
    private static int ordCnt;

    private static BufferedWriter bufferedWriter;

    private static String line;

    private static Burger burger;

    public Main() {

	ordCnt = 0;

	bufferedWriter = null;

	burger = null;
	
    };
    
    public void testMyStack() {

	/**
	 * This is my base case test.
	 **/

	System.out.println("\nTest MyStack with (uniquely random) Integer type:");

	MyStack<Integer> intStack = new MyStack<Integer>();

	ArrayList<Integer> urn = new ArrayList<Integer>();

	/**
	 * This is my check to see whether the stack is sufficiently generic.
	 **/

	for (int i = 1; i < 11; i++) urn.add(new Integer(i));

	Collections.shuffle(urn);

	for (int i = 0; i < 10; i++) intStack.push(urn.get(i));

	for (int i = 0; i < 10; i++) System.out.println(urn.get(i));

	System.out.println();

	while (!intStack.isEmpty()) {

	    System.out.println(intStack.peek().toString());

	    intStack.pop();

	}
	
	MyStack<String> testSize = new MyStack<String>();

	System.out.println("\nAfter initialization I expect the size of MyStack<String> testSize to be zero and it is: " + testSize.size());
	testSize.push("Test");

	System.out.println("\nAfter push I expect the size of MyStack<String> testSize to be one and it is: " + testSize.size());
	testSize.pop();
	
	System.out.println("\nAfter pop I expect the size of MyStack<String> testSize to be zero and it is: " + testSize.size());

	/**
	 * This is my test to see the run times of each method within MyStack.
	 * todo: optimize push (sometimes there are unknown slowdowns)
	 **/
	
        String string = "Call me Ishmael. Some years ago- never mind how long precisely-"+
	    "having little or no money in my purse, and nothing particular to interest me on shore,"+
	    "I thought I would sail about a little and see the watery part of the world. It is a way "+
	    "I have of driving off the spleen and regulating the circulation.";
	
        ArrayList<String> testParse = new ArrayList<String>(Arrays.asList(string.split(" ")));

	long startTime;
	
	long endTime;

	long elapsedTime;

	MyStack<String> testStack1 = new MyStack<>();

	for (int i = 0; i < 56; i++) testStack1.push(testParse.get(i));

	System.out.println("Here are my speed tests for the MyStack class:");

        System.out.println("\nInitializing a stack:");

	System.out.println();
	
	for (int i = 0; i < 10; i++) {

	    startTime = System.nanoTime();

            MyStack<String> testStack2 = new MyStack<>();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");
        }

	System.out.println("\nSize:");

	for (int i = 0; i < 10; i++) {

	    startTime = System.nanoTime();

            testStack1.size();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}

	System.out.println("\nFirst method:");

	for (int i = 0; i < 10; i++) {

	    startTime = System.nanoTime();

            testStack1.first();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}

	System.out.println("\nLast method:");

	for (int i = 0; i < 10; i++) {

	    startTime = System.nanoTime();

            testStack1.last();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}
        
        System.out.println("\nPush method:");

	MyStack<String> testStack3 = new MyStack<>();

	for (int i = 0; i < testStack1.size(); i++) {

	    startTime = System.nanoTime();

            testStack3.push(testParse.get(i));
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

	    System.out.println(elapsedTime + " nanoseconds elapsed");

	}
   
        System.out.println("\nIsEmpty:");

	for (int i = 0; i < 10; i++) {

	    startTime = System.nanoTime();

            testStack1.isEmpty();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}
	
	System.out.println("\nPeek method:");

	for (int i = 0; i < 10; i++) {

	    startTime = System.nanoTime();

            testStack1.peek();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}

	System.out.println("\nToString method:");

	for (int i = 0; i < 10; i++) {

	    startTime = System.nanoTime();

            testStack1.toString();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}

	System.out.println("\nPop method:");

	for (int i = 0; i < 10; i++) {

	    startTime = System.nanoTime();

            testStack1.pop();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}
	
    }

    public void testBurger() {

	/**
	 * This is my base cases tests.
	 **/
	
	long startTime;
	
	long endTime;

	long elapsedTime;
	
	Burger testTheWorks = new Burger(true);

	Burger testPlain = new Burger(false);

	ArrayList<String> ingredientsUrn = new ArrayList<String>(Arrays.asList("Pickle",
									       "Mayonnaise",
									       "Baron-Sauce",
									       "Lettuce",
									       "Tomato",
									       "Onions",
									       "Pepperjack",
									       "Mozzarella",
									       "Cheddar",
									       "Mushrooms",
									       "Mustard",
									       "Ketchup"));
	
	ArrayList<String> categoriesUrn = new ArrayList<String>(Arrays.asList("Sauce",
									      "Cheese",
									      "Veggies"));

	ArrayList<String> pattiesUrn = new ArrayList<String>(Arrays.asList("Chicken",
									   "Veggie",
									   ""));

	ArrayList<String> cntPattyUrn = new ArrayList<String>(Arrays.asList("Double",
									    "Triple"
									    ,""));
	
	ArrayList<String> baronBurgerUrn = new ArrayList<String>(Arrays.asList("Baron Burger",
									       "Burger"));

	/**
	 * Plain burger is the base case of my set and Baron Burger is my N-th case.
	 * From these two cases I can make any burger that I need, but I need to see whether
	 * I can initialize them properly.
	 **/

	System.out.println("\nThis should be a baron burger: " + "\n" + testTheWorks.toString());

	System.out.println("\nThis should be a plain burger: " + "\n" + testPlain.toString() + "\n");

	shuffleUrn(ingredientsUrn);

	shuffleUrn(categoriesUrn);

	shuffleUrn(pattiesUrn);

	shuffleUrn(cntPattyUrn);

	shuffleUrn(baronBurgerUrn);
	
	for (int i = 0; i < 3; i++) {

	    testPlain = new Burger(false);

	    testPlain.addCategory(categoriesUrn.get(i));

	    System.out.println("Testing addCategory with " + categoriesUrn.get(i) + " and the output is: \n" + testPlain.toString() + "\n");
    	    
	}

	for (int i = 0; i < 3; i++) {

	    testTheWorks = new Burger(true);

	    testTheWorks.removeCategory(categoriesUrn.get(i));

	    System.out.println("Testing removeCategory with " + categoriesUrn.get(i) + " and the output is: \n" + testTheWorks.toString() + "\n");
        
	}

	/**
	 * Here are my speed tests for my burger class. I did not include all functions
	 * because these are the workhorses of the burger class. 
	 **/

	System.out.println("Here are my speed tests for the Burger class:");

	System.out.println("\naddCategories():");
	
	for (int i = 0; i < 10; i++) {

	    shuffleUrn(categoriesUrn);
	    
	    testPlain = new Burger(false);

	    startTime = System.nanoTime();

            testPlain.addCategory(categoriesUrn.get(0));
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}

	System.out.println("\nremoveCategories():");
	
	for (int i = 0; i < 10; i++) {

	    shuffleUrn(categoriesUrn);
	    
	    testTheWorks = new Burger(true);

	    startTime = System.nanoTime();

            testTheWorks.removeCategory(categoriesUrn.get(0));
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}

	System.out.println("\naddIngredients():");
	
	for (int i = 0; i < 10; i++) {

	    shuffleUrn(ingredientsUrn);
	    
	    testPlain = new Burger(false);

	    startTime = System.nanoTime();

            testPlain.addIngredient(ingredientsUrn.get(0));
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}

	System.out.println("\nremoveIngredients():");
	
	for (int i = 0; i < 10; i++) {

	    shuffleUrn(ingredientsUrn);
	    
	    testTheWorks = new Burger(true);

	    startTime = System.nanoTime();

            testTheWorks.removeCategory(ingredientsUrn.get(0));
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}
    
    }

    /**
     * I wrote this for my own benefit. I don't know how to write a proper unit tests
     * and would like. I attempted to hit most edge cases with this test and I think
     * I hit many of them.
     **/

    public void testParseLine() throws IOException {

	ArrayList<String> ingredientsUrn = new ArrayList<String>(Arrays.asList("Pickle",
									       "Mayonnaise",
									       "Baron-Sauce",
									       "Lettuce",
									       "Tomato",
									       "Onions",
									       "Pepperjack",
									       "Mozzarella",
									       "Cheddar",
									       "Mushrooms",
									       "Mustard",
									       "Ketchup"));
	
	ArrayList<String> categoriesUrn = new ArrayList<String>(Arrays.asList("Sauce",
									      "Cheese",
									      "Veggies"));

	ArrayList<String> pattiesUrn = new ArrayList<String>(Arrays.asList("Chicken",
									   "Veggie",
									   ""));

	ArrayList<String> cntPattyUrn = new ArrayList<String>(Arrays.asList("Double",
									    "Triple"
									    ,""));
	
	ArrayList<String> baronBurgerUrn = new ArrayList<String>(Arrays.asList("Baron Burger",
									       "Burger"));

	System.out.println();
	
	String s;
	
	for(int i = 0; i < 2; i++) {

	    shuffleUrn(ingredientsUrn);

	    shuffleUrn(categoriesUrn);

	    shuffleUrn(pattiesUrn);

	    shuffleUrn(cntPattyUrn);

	    shuffleUrn(baronBurgerUrn);

	    StringBuilder string = new StringBuilder();

	    string.append(cntPattyUrn.get(i) + " " + pattiesUrn.get(i) + " " + baronBurgerUrn.get(i) + " with no " + ingredientsUrn.get(i) + " " + ingredientsUrn.get(i + 1) + " but " + ingredientsUrn.get(i + 2) + " " + ingredientsUrn.get(i + 3));

	    s = string.toString();
	    
	    if (string.charAt(0) == ' ' || string.charAt(1) == ' ')  s = s.substring(1);

	    s = s.replaceAll("\\s+", " ");
	    
	    System.out.println(s);

	    parseLine(s);

	    System.out.println();
	    
	}

	for(int i = 0; i < 2; i++) {

	    shuffleUrn(ingredientsUrn);

	    shuffleUrn(categoriesUrn);

	    shuffleUrn(pattiesUrn);

	    shuffleUrn(cntPattyUrn);

	    shuffleUrn(baronBurgerUrn);
	    
	    StringBuilder string = new StringBuilder();

	    string.append(cntPattyUrn.get(i) + " " + pattiesUrn.get(i) + " " + baronBurgerUrn.get(i) + " with " + ingredientsUrn.get(i) + " " + ingredientsUrn.get(i + 1) + " but no " + ingredientsUrn.get(i + 2) + " " + ingredientsUrn.get(i + 3));
	    s = string.toString();
	    
	    if (string.charAt(0) == ' '|| string.charAt(1) == ' ')  s = s.substring(1);

	    s = s.replaceAll("\\s+", " ");
	    
	    System.out.println(s);

	    parseLine(s);
	    
	    System.out.println();
	    
	}

	for(int i = 0; i < 2; i++) {

	    shuffleUrn(ingredientsUrn);

	    shuffleUrn(categoriesUrn);

	    shuffleUrn(pattiesUrn);

	    shuffleUrn(cntPattyUrn);

	    shuffleUrn(baronBurgerUrn);

	    StringBuilder string = new StringBuilder();

	    //String s;

	    string.append(cntPattyUrn.get(i) + " " + pattiesUrn.get(i) + " " + baronBurgerUrn.get(i) + " with no " + categoriesUrn.get(i) + " but " + ingredientsUrn.get(i + 2) + " " + ingredientsUrn.get(i + 3));

	    s = string.toString();
	    
	    if (string.charAt(0) == ' '|| string.charAt(1) == ' ')  s = s.substring(1);

	    s = s.replaceAll("\\s+", " ");
	    
	    System.out.println(s);

	    parseLine(s);
	    
	    System.out.println();
	    
	}

	for(int i = 0; i < 2; i++) {

	    shuffleUrn(ingredientsUrn);

	    shuffleUrn(categoriesUrn);

	    shuffleUrn(pattiesUrn);

	    shuffleUrn(cntPattyUrn);

	    shuffleUrn(baronBurgerUrn);

	    StringBuilder string = new StringBuilder();

	    string.append(cntPattyUrn.get(i) + " " + pattiesUrn.get(i) + " " + baronBurgerUrn.get(i) + " with " + categoriesUrn.get(i) + " but no " + ingredientsUrn.get(i + 2) + " " + ingredientsUrn.get(i + 3));

	    s = string.toString();
	    
	    if (string.charAt(0) == ' '|| string.charAt(1) == ' ')  s = s.substring(1);

	    s = s.replaceAll("\\s+", " ");
	    
	    System.out.println(s);

	    parseLine(s);
	    
	    System.out.println();
	    
	}
	
    }

    public void shuffleUrn(ArrayList<String> list) {

	Collections.shuffle(list);
	
    }

    public void parseFile(String filename) throws IOException {
	
	PrintWriter writer = new PrintWriter("test2.txt");

	writer.print("");

	writer.close();

	BufferedReader bufferReader = new BufferedReader(new FileReader(filename));

	while (bufferReader.ready()) {

	    line = bufferReader.readLine();

	    parseLine(line);

	}

	bufferReader.close();
	
    }

    /**
     * @parseLine
     * This parses a line parses takes a line from parseFile
     * then creates a stack depending on the order given to it.
     *
     * Note: I haven't taken Java in two years and used this
     * method to explore some of the newer features of
     * Java.
     **/

    public void parseLine(String line) throws IOException {

	boolean theWorks = false;	

	int pattyCount = 1;

	String pattyType = "Beef";

	Pattern veggiePattern1 = Pattern.compile("\\bVeggie Burger\\b");

	Matcher veggieMatcher1 = veggiePattern1.matcher(line);

	Pattern veggiePattern2 = Pattern.compile("\\bVeggie Baron Burger\\b");

	Matcher veggieMatcher2 = veggiePattern2.matcher(line);

	if (veggieMatcher1.find() || veggieMatcher2.find()) pattyType = "Veggie";
	
	Pattern chickenPattern1 = Pattern.compile("\\bChicken Burger\\b");

	Matcher chickenMatcher1 = chickenPattern1.matcher(line);

	Pattern chickenPattern2 = Pattern.compile("\\bChicken Baron Burger\\b");

	Matcher chickenMatcher2 = chickenPattern2.matcher(line);

	if (chickenMatcher1.find() || chickenMatcher2.find()) pattyType = "Chicken";

	Pattern doublePattern = Pattern.compile("\\bDouble\\b");

	Matcher doubleMathcher = doublePattern.matcher(line);

	if (doubleMathcher.find()) pattyCount = 2;

	Pattern triplePattern = Pattern.compile("\\bTriple\\b");

	Matcher tripleMathcher = triplePattern.matcher(line);

	if (tripleMathcher.find()) pattyCount = 3;

	Pattern baronBurgerPattern = Pattern.compile("\\bBaron Burger\\b");

	Matcher baronBurgerMatcher = baronBurgerPattern.matcher(line);

	if (baronBurgerMatcher.find()) theWorks = true;

	ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(line.split(" ")));
	
	burger = new Burger(theWorks);

	for (int i = 1; i < pattyCount; i++) burger.addPatty();

	burger.changePatties(pattyType);
	
	label:
	for (int i = 0; i < tokens.size(); i++) {

	    if (tokens.get(i).equals("with") && !tokens.get(i + 1).equals("no")) {

		for (int j = i + 1; j < tokens.size(); j++) {

		    burger.addIngredient(tokens.get(j));
		    
		    burger.addCategory(tokens.get(j));		    
			
		    if (tokens.get(j).equals("but")) {

			for (int k = j + 2; k < tokens.size(); k++) burger.removeIngredient(tokens.get(k));

			break label;

		    }
			
		}
		
	    } else if (tokens.get(i).equals("with")) {

		for (int j = i + 2; j < tokens.size(); j++) {

		    if (tokens.get(j).equals("but")) {

			for (int k = j + 1; k < tokens.size(); k++) burger.addIngredient(tokens.get(k));

			break label;

		    }

		     burger.removeCategory(tokens.get(j));

		     burger.removeIngredient(tokens.get(j));
			    	
		}

		
	    }
	    
	}

	System.out.println(line);

	writeFile(line, burger);
	
    }

    public static boolean checkCategory(final String string) {

        return (string.equals("Cheese")||string.equals("Sauce")||string.equals("Veggies"));

    }

    public void writeFile(String line, Burger burger) throws IOException {

	try {

	    bufferedWriter = new BufferedWriter(new FileWriter(new File("test2.txt"),true));

	} catch (IOException e) {

	    e.printStackTrace();
	}
	
	bufferedWriter.write("Processing Order " + (ordCnt++) + ": " + line+"\n");

	bufferedWriter.write("["+burger.toString()+"]\n");

	bufferedWriter.newLine();

	bufferedWriter.flush();
    }

    public static void main(String[] args) throws IOException {

	Main test = new Main();

	test.parseFile("test.txt");

	test.testMyStack();

	test.testBurger();

	//for my own usage to test parseLine().
	
	//test.testParseLine();
    }

}
