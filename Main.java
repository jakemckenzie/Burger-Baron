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

    public Main() {

	ordCnt = 0;

	bufferedWriter = null;
	
    };
    
    public void testMyStack() {
	
    }

    public void testBurger() {
	
    }

    public void parseFile(String filename) throws IOException {
	
	PrintWriter writer = new PrintWriter("test2.txt");

	writer.print("");

	writer.close();

	BufferedReader bufferReader = new BufferedReader(new FileReader(filename));

	while (bufferReader.ready()) {

	    String line = bufferReader.readLine();

	    parseLine(line);

	}

	bufferReader.close();
	
    }

    public void parseLine(String line) throws IOException {

	boolean theWorks = false;

	Burger burger = null;

	int pattyCount = 1;

	String pattyType = "Beef";

	Pattern veggiePattern = Pattern.compile("\\bVeggie Burger\\b");

	Matcher veggieMatcher = veggiePattern.matcher(line);

	if (veggieMatcher.find()) pattyType = "Veggie";
	
	Pattern chickenPattern = Pattern.compile("\\bChicken Burger\\b");

	Matcher chickenMatcher = chickenPattern.matcher(line);

	if (chickenMatcher.find()) pattyType = "Chicken";

	Pattern doublePattern = Pattern.compile("\\bDouble\\b");

	Matcher doubleMathcher = doublePattern.matcher(line);

	if (doubleMathcher.find()) pattyCount = 2;

	Pattern triplePattern = Pattern.compile("\\bTriple\\b");

	Matcher tripleMathcher = triplePattern.matcher(line);

	if (tripleMathcher.find()) pattyCount = 3;

	ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(line.split(" ")));

	if (line.contains("Baron Burger")) theWorks = true;
	
	burger = new Burger(theWorks);

	for (int i = 1; i < pattyCount; i++) burger.addPatty();

	burger.changePatties(pattyType);
	
	label:
	for (int i = 0; i < tokens.size(); i++) {

	    if (tokens.get(i).equals("with")) {

		if (tokens.get(i + 1).equals("no")) {

		    for (int j = i + 2; j < tokens.size(); j++) {

			burger.removeCategory(tokens.get(j));

			burger.removeIngredient(tokens.get(j));
			
			if (tokens.get(j).equals("but")) {

			    for (int k = j + 1; k < tokens.size(); k++) {

				burger.addIngredient(tokens.get(k).toString());

			    }

			    break label;

			}
			
		    }

		} else {

		    for (int j = i + 1; j < tokens.size(); j++) {

			if (tokens.get(j).equals("but")) {

			    for (int k = j + 2; k < tokens.size(); k++) {

				burger.removeIngredient(tokens.get(k).toString());

			    }

			    break label;

			}

			if (tokens.get(j).equals("Sauce") ||
			    tokens.get(j).equals("Cheese")||
			    tokens.get(j).equals("Veggies")) {

			    burger.addCategory(tokens.get(j));
			    
			} else {
			    
			    burger.addIngredient(tokens.get(j).toString());
			    
			}
			
		    }
		    
		}
		
	    }
	    
	}

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

    public static boolean isCategory(final String theString) {
        return "Cheese".equals(theString) || "Veggies".equals(theString) 
	    || "Sauce".equals(theString);
    }

    public static void main(String[] args) throws IOException {

	Main test = new Main();

	test.parseFile("test.txt");
    }

}

