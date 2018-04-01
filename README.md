# Burger-Baron
This was the first assignment for my data structures course TCSS 342. The formal specifications for the assignment are below.

The Burger Baron has customers that will order in different ways and he wants his menus to automatically construct the burger ingredients in the proper order so he can display it to his gourmet burger chef’s.

That is, most customers use one of two styles to order:
{Patty Count} {Patty Type} Baron Burger with no {omissions} but {exceptions}
{Patty Count} {Patty Type} Burger with {additions} but no {exceptions}

The {omissions} and {additions} may include ingredients or categories of ingredients:
  
Categories: Cheese, Sauce, Veggies
Ingredients: Cheddar, Mozzarella, Pepperjack, Lettuce, Tomato, Onions, Pickle,
Mushrooms, Ketchup, Mustard, Mayonnaise, and Baron-Sauce

The {exceptions} are always ingredients only and are exceptions to the categories listed in the
{omissions} or {additions}.
  
Each of the components can be omitted when ordering with the following defaults:

{Patty Count} defaults to Single.
{Patty Type} defaults to Beef
{omissions}, {additions}, {exceptions} default to empty.
  
The Burger Baron takes orders one at a time and gives them a number 0 to 99.  The input to
your program will be a file with one line per burger.

Your program should assign each order a number starting with 0.
Each line will be a string in the format above.
Your output (to System.out) will be the ingredients of the ordered burger listed from top
to bottom.
