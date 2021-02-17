import java.util.*;
import java.util.Arrays;
class BattleShip {
  public static void main(String[] args) {
	  
	  /* initialize two arrays with the same amount of rows and columns*/
	final int ROWS = 5, COLS = 5;
	char [] [] board1 = new char [ROWS] [COLS];
	char [] [] board2 = new char [ROWS] [COLS];
	
	/* create board1 */
	for (int i = 0; i < board1.length; i++) {
		for (int j = 0; j < board1[0].length; j++) {
			board1[i][j] = '~';
		}
	}
	/* create board2 */
	for (int i = 0; i < board2.length; i++) {
		for (int j = 0; j < board2[0].length; j++) {
			board2[i][j] = '~';
		}
	}
	
	/*place ship on board2*/
	placeShip(board2);
	
	/* display board 1*/
	displayBoard(board1);
	
	/* initialize the user's number of hits to 0 */
	int hits = 0;
	
	/* while the user decides to fire shot/chooses 1 on the menu.......*/
	do {
		int x = getRow(board1); // gather user's x coordinate
		int y = getCol(board1); // gather user's y coordinate
		
		/* if the user inputs a coordinate that was already use, they will be informed and prompted to try again */
		if(board1[x][y] == 'X' || board1[x][y] == '*') {
			System.out.println("Cooordinates already used. Try again");
		/* otherwise, the user's shot will be fired onto board 1 where they will be informed if they hit the ship,
		 * or if they missed it. Once the user hits all four points of the ship, the user will be informed that
		 * they won, board1 & board2 will reset, a new ship will be placed onto board2, and the user will be 
		 * given the menu options to fire a shot or quit.  */
		}else {
			fireShot(board2, board1, x, y); // fire shot at given coordinates
			if(board2[x][y] == '*' && hits < 3 ) { // when the user hits up to 3 out of four points of the ship
				System.out.println();
				System.out.println("HIT!!");
				hits += 1;
			}
			else if(board2[x][y] == '~') {// when the user misses any points of the ship
				System.out.println();
				System.out.println("MISS!!");
			}
			else if (board2[x][y] =='*' && hits == 3) { // when the user hits the final/fourth point of the ship
				System.out.println();
				System.out.println("YOU WON!!!");
				System.out.println();
				displayBoard(board1); //display finished board
				
				hits = 0; //reset hits to 0
				for (int i = 0; i < board1.length; i++) { //reset board1
					for (int j = 0; j < board1[0].length; j++) {
						board1[i][j] = '~';
					}
				}
				
				for (int i = 0; i < board2.length; i++) { //reset board2
					for (int j = 0; j < board2[0].length; j++) {
						board2[i][j] = '~';
					}
				}
				placeShip(board2); //place new ship onto board2 
			}
		}
		

		displayBoard(board1); //always display the user's board (board1) after each action
	}while (menu() == 1); //loop will execute when the user selects 2 (Quit) on the menu
	

  }

  
  /* randomly place a ship on board2
   * @parm 2D char array
   * */
  public static void placeShip(char [] [] b2) {
	  
	  /*generate two random numbers*/
	  Random rand = new Random();
	  int rand1 = rand.nextInt(b2.length - 1); //random row
	  int rand2 = rand.nextInt(b2.length - 1); //random column
	  
	  /* place four '*' in the randomly selected rows and columns*/
	  b2[rand1][rand2] = '*'; //top left *
	  b2[rand1][rand2 + 1] = '*'; // top right *
	  b2[rand1 + 1][rand2] = '*'; // bottom left *
	  b2[rand1 + 1][rand2 + 1] = '*'; // bottom right *
	  
  }
  
  /* display desired board
   * @parm 2D char array
   * */
  public static void displayBoard(char [] [] b1) {
	  
	System.out.println("	1	2	3	4	5"); //label columns
	
	/*k will be used to keep track of the alphabet characters. 
	 * char 65 is A, which will be the first character 
	 * to label the first row.
	 * */
	int k = 65;
	
	/*print out the entire board*/
	for (int i = 0; i < b1.length; i++) { //row
		System.out.print((char)k);	//print out the labeled row
		k += 1; //k is now the next capital letter in the alphabet
		for (int j = 0; j < b1[0].length; j++) { //column
			System.out.print("	" + b1[i][j]); //print out each char in each row
		}
		System.out.println(); //move onto the next row
	}		
  }
  
  /* user will select/enter a row they'd like to check
   * @parm a 2D array that is being used as one of the boards
   * @return the coordinate for the row selected*/
  public static int getRow(char [] [] b1) {
	  
	  Scanner input = new Scanner(System.in); //create new scanner
	  System.out.println();
	  System.out.print("Enter a Row (A - E): "); // tell user to select a row
	  
	  char entry = 'A'; //initialize the user's entry
	  boolean valid = false; //set valid to FALSE
	  int row = 0; //initialize the row the user will choose
	  
	  /* check the user's input  to see if it is an upper or lower case letter
	   * then check to see if it is an available row on the board*/
	  while (!valid) {
		  entry = input.next().charAt(0); //set entry to the user's char input
		  int entry2 = (char)entry; //change the char to an int
		  
		  if (entry2 >= 65 && entry2 <= 90 ) {//if the user's input is a capital letter
			  if ((entry2 - 65) <= (b1.length - 1) && (entry2 - 65) >= 0) {//if the letter is on the board.....
				  row = entry2 - 65;
				  valid = true; //the while loop is broken
			  }else {//otherwise the user is informed and is allowed to input again
				  System.out.println("Invlaid input."); 
			  }
		  }
		  else if(entry2 >= 97 && entry2 <= 122) {//if the user's input is a lower case letter
			  if((entry2 - 97) <= (b1.length - 1) && (entry2 - 97) >= 0) {//if the letter is on the board....
				  row = entry2 - 97;
				  valid = true;//the while loop is broken
			  }else {//otherwise the user is informed and is allowed to input again
				  System.out.println("Invlaid input.");
			  }
			  
		  }
		  else {//if the input is not a letter, then the user is informed and allowed to input again
			  System.out.println("Invalid input.");
		  }
	  }
	  /*return the row the user has selected*/
	  return row;
  }
  
  /* user will select/enter a column they'd like to check
   * @parm a 2D array that is being used as one of the boards
   * @return the column that the user selected*/
  public static int getCol(char [] [] b1) {
	  
	  Scanner input = new Scanner(System.in); //create scanner
	  System.out.print("Enter a Column (1 - 5): "); //tell user to pick a column
	  
	  boolean valid = false; //set valid to false
	  int column = 0; //initialize the column # to 0
	  
	  while (!valid) {
		  if (input.hasNextInt()) { //make sure the user enters an int
			  column = input.nextInt(); //column is now the user's input
			  if (column <= b1.length && column > 0) {
				  valid = true; //if the user's column is within range, valid now becomes true
			  }else {
				  System.out.println("Invalid Range."); //otherwise the user will be informed of the invalid range
			  }
			  
		  }else {
			  input.next();
			  System.out.println("Invalid input.");//otherwise the user will be informed of the invalid input if they do not enter an int
		  }
	  }
	  return (column - 1); // return the user's column minus 1 because array indices start with 0
  }
  
  /*User will fire a shot at onto board1 
   * @parm two separate 2D arrays, and two separate ints representing the user's row and column selections
   * @return the board/2D array that is being altered to reflect where the user has fired their shot*/
  public static char [] [] fireShot(char[][] b2, char [][] b1, int row, int column){
	  /* check solution board if one of the four points is at the given coordinates
	   * if yes, then the user's board will reflect a '*' showing a hit,
	   * otherwise, their board will reflect a 'X' showing a miss */
	  if (b2[row][column] == '*') { 
		  b1[row][column] = '*';
		  
	  }else {
		  b1[row][column] = 'X';
		  
	  }
	  return b1; //return the altered board (the user's board)
  }
  
  public static int menu() {
	  Scanner input = new Scanner(System.in);
	  int choice = 0;
	  System.out.println();
	  System.out.println("Menu:");
	  System.out.println("1.	Fire Shot");
	  System.out.println("2.	Quit");
	  System.out.print("Enter Choice:	");
	  choice = input.nextInt();
	  switch(choice) {
	  case 1: choice = 1;
	  		  break;
	  case 2: choice = 2;
	  		  break;
	  default: System.out.println("Invalid input.");
	  		   break;
	  }
	  
  
  return choice;
  }
}