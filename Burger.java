public class Burger {

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

    public void addIngredient(String ingredient) {
	
	ingredientStack.push(ingredient);

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
