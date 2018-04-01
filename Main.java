import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class MyStack<Type> {
    
    private static class Node <Type> {
	
	private Type element;
	
	private Node <Type> next;

	public Node (Type type, Node <Type> node) {

	    element = type;

	    next = node;

	}

	public Type getElement () {

	    return element;
	    
	}

	public Node <Type> getNext () {

	    return next;
	    
	}

	public void setNext (Node <Type> node) {

	    next = node;
	    
	}

    }

    private Node <Type> head = null;

    private Node <Type> tail = null;

    private int size = 0;

    public MyStack () {};

    public int size () {

	return size;
	
    }

    public boolean isEmpty () {

	return size == 0;
	
    }

    public Type first () {

	if(isEmpty()) {
	    
	    return null;

	}

	return head.getElement();
	
    }

    public Type last () {

	if(isEmpty()) {

	    return null;
	    
	}

	return tail.getElement();
	
    }

    public void push (Type element) {

	head = new Node<Type>(element, head);

	if(size == 0) {

	    tail = head;
	    
	}

	size++;

    }
    public Type pop() {

	if(isEmpty()) {

	    return null;
	    
	}

	Type node = head.getElement();

	head = head.getNext();

	size--;

	if(size == 0) {

	    tail = null;
	    
	}

	return node;
    }

    public Type peek() {

	return head.getElement();
	
    }
    
    public String toString(){
	
        StringBuilder result = new StringBuilder();

        Node<Type> current = head;
	
        while (current != null) {
	    
            result.append(current.element);

	    current = current.next;
         
        }
	
        return result.toString();

    }

}


class Burger {

    private int cntPatty;

    private String typOfPatty;

    private MyStack<String> ingredientStack;
    
    private MyStack<String> orderStack;

    private MyStack<String> baronStack;

    private MyStack<String> tempStack;
    
    
    public Burger (boolean theWorks) {

	orderStack = new MyStack<String>();

	baronStack = new MyStack<String>();

	ingredientStack = new MyStack<String>();

	tempStack = new MyStack<String>();

	cntPatty = 1;

	typOfPatty = "Beef";

	ingredientStack.push("Bun");
	
	ingredientStack.push(typOfPatty);

	ingredientStack.push("Bun");

	if (theWorks) {
	    
	    addCategory("Sauce");

	    addCategory("Cheese");

	    addCategory("Veggies");
	}
	
	createBaronBurger();

	createBurger();
	
    }
    
    private void createBaronBurger () {

	baronStack.push("Pickle");

	baronStack.push("Bun");
	
	baronStack.push("Mayonnaise");
	
	baronStack.push("Baron-Sauce");

	baronStack.push("Lettuce");

	baronStack.push("Tomato");

	baronStack.push("Onions");

	if (cntPatty > 1) pushPatty(typOfPatty, cntPatty); 

	baronStack.push("Pepperjack");

	baronStack.push("Mozzarella");

	baronStack.push("Cheddar");

	baronStack.push(typOfPatty);

	baronStack.push("Mushrooms");

	baronStack.push("Mustard");

	baronStack.push("Ketchup");

	baronStack.push("Bun");
	
    }

    private void pushPatty(String typOfPatty, int cntPatty) {

	for (int i = 1; i < cntPatty; i++ )  baronStack.push(typOfPatty);
    }

    private void createBurger() {

	while (!baronStack.isEmpty()) {

	    if (!checkRecipe(baronStack.peek())) {

		baronStack.pop();

	    } else {

		orderStack.push(baronStack.pop());

	    }

	}

	createBaronBurger();

    }

    private boolean checkRecipe(String type) {

	boolean checkBill = false;

	while (!ingredientStack.isEmpty()) {

	    if (type.equals(ingredientStack.peek())) {

		ingredientStack.pop();

		checkBill = true;

		break;

	    }

	    tempStack.push(ingredientStack.pop());

	}

	while (!tempStack.isEmpty()) ingredientStack.push(tempStack.pop());

	return checkBill;

    }
    
    public void changePatties(String curPatty) {

	if (curPatty == typOfPatty) return;

	while (!orderStack.isEmpty()) {

	    if (orderStack.peek().equals(typOfPatty)) {

		orderStack.pop();

		orderStack.push(curPatty);

	    } else {

		tempStack.push(orderStack.pop());

	    }

	}

	while (!tempStack.isEmpty()) orderStack.push(tempStack.pop());

	typOfPatty = curPatty;

	updateRecipe();

    }

    private void updateRecipe() {

	while (!baronStack.isEmpty()) baronStack.pop();

	createBaronBurger();
	
    }

    public void addPatty() {

	cntPatty++;

	updateRecipe();

	addIngredient(typOfPatty);

    }

    public void addCategory(String type) {

	if (type.equals("Sauce")) {

	    pushSauce();
	    
	} else if (type.equals("Cheese")) {

	    pushCheese();
	    
	} else if (type.equals("Veggies")) {

	    pushVeggies();

	}

	swapIngredients();

	createBurger();
    }

    private void pushSauce() {

	ingredientStack.push("Mayonnaise");

	ingredientStack.push("Baron-Sauce");

	ingredientStack.push("Mustard");
	
	ingredientStack.push("Ketchup");

    }

    private void pushCheese() {

	ingredientStack.push("Cheddar");

	ingredientStack.push("Mozzarella");

	ingredientStack.push("Pepperjack");

	
    }

    private void pushVeggies() {

	ingredientStack.push("Mushrooms");

	ingredientStack.push("Onions");

	ingredientStack.push("Tomato");

	ingredientStack.push("Lettuce");

	ingredientStack.push("Pickle");

	
    }
    
    public void removeCategory(String type) {
	
	if (type.equals("Sauce")) {

	    removeSauce();

	} else if (type.equals("Cheese")) {

	    removeCheese();

	} else if (type.equals("Veggies")) {

	    removeVeggies();
	    
	}

    }

    private void removeSauce() {

	removeIngredient("Mayonnaise");

	removeIngredient("Baron-Sauce");

	removeIngredient("Mustard");

	removeIngredient("Ketchup");
    }

    private void removeCheese() {

	removeIngredient("Cheddar");

	removeIngredient("Mozzarella");

	removeIngredient("Pepperjack");

    }

    private void removeVeggies() {

	removeIngredient("Mushrooms");

	removeIngredient("Onions");

	removeIngredient("Tomato");

	removeIngredient("Lettuce");

	removeIngredient("Pickle");

    }

    public void removeIngredient(String type) {

	while (!orderStack.isEmpty()) {

	    if (type.equals(orderStack.peek())) {

		orderStack.pop();

		break;

	    } else {

		tempStack.push(orderStack.pop());

	    }

	}

	while (!tempStack.isEmpty()) orderStack.push(tempStack.pop());

    }

    public void addIngredient(String type) {

	ingredientStack.push(type);

	swapIngredients();

	createBurger();

    }

    private void swapIngredients() {

	while (!orderStack.isEmpty()) ingredientStack.push(orderStack.pop());

    }

    public String toString() {

	StringBuilder string = new StringBuilder();

	while (!orderStack.isEmpty()) {

	    string.append(orderStack.pop());

	    if (!orderStack.isEmpty()) {

		string.append(", ");

	    }

	}

	return string.toString();

    }

}

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
    
    public static void testMyStack() {
        
        String string = "Call me Ishmael. Some years ago- never mind how long precisely-"+
	    "having little or no money in my purse, and nothing particular to interest me on shore,"+
	    "I thought I would sail about a little and see the watery part of the world. It is a way "+
	    "I have of driving off the spleen and regulating the circulation.";
	
        ArrayList<String> testParse = new ArrayList<String>(Arrays.asList(string.split(" ")));

	long startTime;
	
	long endTime;

	long elapsedTime;

	MyStack<String> testStack1 = new MyStack<>();

	for (int i = 0; i < 56; i++) testStack1.push(testParse.get(timing));

        System.out.println("Initializing a stack:");

	for (int i = 0; i < 10; i++) {

	    startTime = System.nanoTime();

            MyStack<String> testStack2 = new MyStack<>();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");
        }

	System.out.println("\nSize:");

	for (int timing = 0; timing < 10; ++timing) {

	    startTime = System.nanoTime();

            testStack1.size();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}

	System.out.println("\nFirst method:");

	for (int timing = 0; timing < 10; ++timing) {

	    startTime = System.nanoTime();

            testStack1.first();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}

	System.out.println("\nLast method:");

	for (int timing = 0; timing < 10; ++timing) {

	    startTime = System.nanoTime();

            testStack1.last();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}
        
        System.out.println("\nPush method:");

	MyStack<String> testStack3 = new MyStack<>();

	for (int timing = 0; timing < testStack1.size(); ++timing) {

	    startTime = System.nanoTime();

            testStack3.push(testParse.get(timing));
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}
   
        System.out.println("\nIsEmpty:");

	for (int timing = 0; timing < 10; ++timing) {

	    startTime = System.nanoTime();

            testStack1.isEmpty();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}
	
	System.out.println("\nPeek method:");

	for (int timing = 0; timing < 10; ++timing) {

	    startTime = System.nanoTime();

            testStack1.peek();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}

	System.out.println("\nToString method:");

	for (int timing = 0; timing < 10; ++timing) {

	    startTime = System.nanoTime();

            testStack1.toString();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}

	System.out.println("\nPop method:");

	for (int timing = 0; timing < 10; ++timing) {

	    startTime = System.nanoTime();

            testStack1.pop();
          
            endTime = System.nanoTime();

	    elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " nanoseconds elapsed");

	}

	//System.out.println("\n");
	
    }

    public void testBurger() {
	
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

    public void parseLine(String line) throws IOException {

	boolean theWorks = false;	

	int pattyCount = 1;

	String pattyType = "Beef";

	Pattern veggiePattern1 = Pattern.compile("\\bVeggie Burger\\b");

	Matcher veggieMatcher1 = veggiePattern1.matcher(line);

	Pattern veggiePattern2 = Pattern.compile("\\bVeggie Baron Burger\\b");

	Matcher veggieMatcher2 = veggiePattern2.matcher(line);

	//Pattern veggiePattern3 = Pattern.compile("\\bBaron Veggie Burger\\b");

	//Matcher veggieMatcher3 = veggiePattern3.matcher(line);

	if (veggieMatcher1.find() || veggieMatcher2.find()) pattyType = "Veggie";
	
	Pattern chickenPattern1 = Pattern.compile("\\bChicken Burger\\b");

	Matcher chickenMatcher1 = chickenPattern1.matcher(line);

	Pattern chickenPattern2 = Pattern.compile("\\bChicken Baron Burger\\b");

	Matcher chickenMatcher2 = chickenPattern2.matcher(line);

	//Pattern chickenPattern3 = Pattern.compile("\\bBaron Chicken Burger\\b");

	//Matcher chickenMatcher3 = chickenPattern3.matcher(line);

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

	    if (tokens.get(i).equals("with") && tokens.get(i + 1).equals("no")) {

		for (int j = i + 2; j < tokens.size(); j++) {

		    burger.removeIngredient(tokens.get(j));
		    
		    burger.removeCategory(tokens.get(j));		    
			
		    if (tokens.get(j).equals("but")) {

			for (int k = j + 1; k < tokens.size(); k++) burger.addIngredient(tokens.get(k).toString());

			break label;

		    }
			
		}
		
	    } else if (tokens.get(i).equals("with")) {

		for (int j = i + 1; j < tokens.size(); j++) {

		    if (tokens.get(j).equals("but")) {

			for (int k = j + 2; k < tokens.size(); k++) burger.removeIngredient(tokens.get(k).toString());

			break label;

		    }

		    if (checkCategory(tokens.get(j))) burger.addCategory(tokens.get(j));

		    if (!checkCategory(tokens.get(j))) burger.addIngredient(tokens.get(j).toString());
			    
		    
			
		}
		
	    }
	    
	}

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

	testMyStack();
    }

}

