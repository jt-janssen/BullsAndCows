/*
Created by JT Janssen
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class BullsAndCows{
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_BLACK = "\u001B[30m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_PURPLE = "\u001B[35m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_WHITE = "\u001B[37m";

    static final String EASY_HEADER_1_4 = "\t\t\t\t\t\t       KEY | GUESS\n\t\t\t\t\t\t     ------+-------";
    static final String EASY_HEADER_5 = "\t\t\t\t\t\t      KEY  | GUESS\n\t\t\t\t\t\t    -------+-------";
    static final String EASY_HEADER_6 = "\t\t\t\t\t\t      KEY  |  GUESS\n\t\t\t\t\t\t   --------+--------";
    static final String EASY_HEADER_7 = "\t\t\t\t\t\t     KEY   |  GUESS\n\t\t\t\t\t\t  ---------+---------";
    static final String EASY_HEADER_8 = "\t\t\t\t\t\t     KEY   |   GUESS\n\t\t\t\t\t         ----------+----------";
    static final String EASY_HEADER_9 = "\t\t\t\t\t\t    KEY    |   GUESS\n\t\t\t\t\t        -----------+-----------";
    static final String EASY_HEADER_10 = "\t\t\t\t\t\t    KEY    |    GUESS\n\t\t\t\t\t       ------------+------------";

    static final String HARD_HEADER_1_5 = "\t\t\t\t\t\t     B | C | GUESS\n\t\t\t\t\t\t    ---+---+-------";
    static final String HARD_HEADER_6 = "\t\t\t\t\t\t    B | C |  GUESS\n\t\t\t\t\t\t   ---+---+---------";
    static final String HARD_HEADER_7 = "\t\t\t\t\t\t    B | C |  GUESS\n\t\t\t\t\t\t   ---+---+----------";
    static final String HARD_HEADER_8 = "\t\t\t\t\t\t   B | C |   GUESS\n\t\t\t\t\t          ---+---+-----------";
    static final String HARD_HEADER_9 = "\t\t\t\t\t\t   B | C |    GUESS\n\t\t\t\t\t          ---+---+------------";
    static final String HARD_HEADER_10 = "\t\t\t\t\t\t  B | C |    GUESS\n\t\t\t\t\t         ---+---+-------------";
    
    static final String[] EASY_HEADER_SET= {EASY_HEADER_1_4, EASY_HEADER_5, EASY_HEADER_6, EASY_HEADER_7, EASY_HEADER_8, EASY_HEADER_9, EASY_HEADER_10};
    static final String[] HARD_HEADER_SET= {HARD_HEADER_1_5, HARD_HEADER_6, HARD_HEADER_7, HARD_HEADER_8, HARD_HEADER_9, HARD_HEADER_10};
    
    private enum gameStates{startGame, easyMode, hardMode, endGame, giveSolution}

    static boolean gameActive = true;
    static boolean duplicate = false;
    static int numGuessDigits = 4;
    static int guessCount = 1;
    static String output = "";
    static String gameBoard = "";
    static boolean isHardMode = false; //this will always change in the first switch 
    static int modeInput = 0;

    static ArrayList<Integer> solution = new ArrayList<Integer>();
    static int[] guessArray;
    static gameStates gameState = gameStates.startGame;

    static int bulls, cows;
    static String easyBullsCows = "";
    static Scanner keys = new Scanner(System.in);

    public static void main(String[] args) {
        while(gameActive){   //runs & accepts guesses until game is over
            switch(gameState){
                case startGame: {
                    gameBoard = "";
                    modeInput = 0; //needs to reset after each round

                System.out.println(ANSI_BLUE + "\t\t\t<------------------------ BULLS & COWS ------------------------>" + ANSI_RESET);
                System.out.println("\t\t\t\t\t   Enter 0000 to see solution\n");

                while(modeInput != 1 && modeInput != 2){
                    System.out.print("\t\t\tGame mode\t" + ANSI_CYAN + "1: Easy\t\t2: Hard" + ANSI_RESET + "\t\tSelection: ");
                    modeInput = keys.nextInt();
                    if(modeInput == 1){
                        isHardMode = false;
                    } else if(modeInput == 2){
                        isHardMode = true;
                    }
                }


                System.out.print("\t\t\tEnter number of digits to guess: ");
                numGuessDigits = keys.nextInt();

                if(numGuessDigits > 10){
                    numGuessDigits = 10;
                    System.out.println("\t\t\tNumber of digits set to 10 (max)");
                } else if(numGuessDigits < 1){
                    numGuessDigits = 1;
                    System.out.println("\t\t\tNumber of digits set to 1 (min)");
                }





                System.out.print("\n");

                solution.clear();
                //add elements in the list
                solution.clear();
                for(int i = 0; i < 10; i++){
                    solution.add(i);
                }     

                Collections.shuffle(solution); //shuffles array of numbers
                for(int i = solution.size()-1; i >= numGuessDigits; i--){
                    solution.remove(i);
                }
                solution.trimToSize();
                guessCount = 1;

                // for(int i = 0; i < solution.size(); i++){  //TODO will print solution; must be commented out
                //     System.out.print(solution.get(i));
                // } 
                
                if(isHardMode){
                    if(numGuessDigits >= 1 && numGuessDigits <= 5){
                        gameBoard += HARD_HEADER_SET[0];
                    } else {
                        gameBoard += HARD_HEADER_SET[numGuessDigits-4];
    
                    }
                    gameState = gameStates.hardMode;

                } else {
                    if(numGuessDigits >= 1 && numGuessDigits <= 4){
                        gameBoard += EASY_HEADER_SET[0];
                    } else {
                        gameBoard += EASY_HEADER_SET[numGuessDigits-4];
    
                    }
                    gameState = gameStates.easyMode;
                }
                break;
                }

                case easyMode:{
                //resets and clears certain variables after each guess
                duplicate = false;
                easyBullsCows = "";
                bulls = 0;

                //takes in and stores user input
                System.out.print("\t\t\tEnter guess " + guessCount + ": " + ANSI_YELLOW);
                guessArray = new int[numGuessDigits];
                // for(int i = 0; i < guessArray.length; i++){  //TODO will print input; must be commented out
                //     System.out.print(guessArray[i]);
                // }  

                String guess = keys.next(); 
                //will send to giveSolution if input is '0000'
                if(guess.equals("0000")){
                    gameState = gameStates.giveSolution;
                    break;
                }
                System.out.println(ANSI_RESET);
                //reads the length
                if(guess.length() != numGuessDigits){
                    System.out.println(ANSI_RED + "\t\t\tGuess must be " + numGuessDigits + " digits. Enter another guess\n" + ANSI_RESET);
                    break;
                }
                for(int i = 0; i < guessArray.length; i++){    //converts string to int array
                    guessArray[i] = Character.getNumericValue(guess.charAt(i));
                }



                for(int i = 0; i < guessArray.length; i++){
                    for(int j = i + 1; j < guessArray.length; j++){
                        if(guessArray[i] == guessArray[j]){
                            duplicate = true;
                        }
                    }
                }
                if(duplicate){
                    System.out.println(ANSI_RED + "\t\t\tTwo numbers cannot be the same in a guess. Enter another guess.\n" + ANSI_RESET);
                    break;
                }



                        
                //compares each digit in the guess array to the solution array to determine # of bulls & cows
                for(int i = 0; i < numGuessDigits; i++){
                    if(guessArray[i] == solution.get(i)){
                        easyBullsCows += "B";
                        bulls++;
                    }  else if(solution.contains(guessArray[i])){
                        easyBullsCows += "C";
                    } else {
                        easyBullsCows += "-";

                    }
                }  

                printTable(easyBullsCows, guess.toString());

                if (bulls == numGuessDigits){
                    output = "";
                    System.out.println(ANSI_CYAN + "\t\t\tYou won in " + guessCount + " guesses!");
                    for(int i = 0; i < numGuessDigits; i++){
                        output += solution.get(i);
                    }
                    System.out.println("\t\t\tNumber to guess: " + output + ANSI_RESET);
                    gameState = gameStates.endGame;
                }
                guessCount++;
                break;
                }

                case hardMode: {
                    //resets and clears certain variables after each guess
                    duplicate = false;
                    bulls = 0;
                    cows = 0;

                    //takes in and stores user input
                    System.out.print("\t\t\tEnter guess " + guessCount + ": " + ANSI_YELLOW);
                    guessArray = new int[numGuessDigits];
                    // for(int i = 0; i < guessArray.length; i++){  //TODO will print input; must be commented out
                    //     System.out.print(guessArray[i]);
                    // }  

                    String guess = keys.next(); 
                    //will send to giveSolution if input is '0000'
                    if(guess.equals("0000")){
                        gameState = gameStates.giveSolution;
                        break;
                    }
                    System.out.println(ANSI_RESET);
                    //reads the length
                    if(guess.length() != numGuessDigits){
                        System.out.println(ANSI_RED + "\t\t\tGuess must be " + numGuessDigits + " digits. Enter another guess\n" + ANSI_RESET);
                        break;
                    }
                    for(int i = 0; i < guessArray.length; i++){    //converts string to int array
                        guessArray[i] = Character.getNumericValue(guess.charAt(i));
                    }



                    for(int i = 0; i < guessArray.length; i++){
                        for(int j = i + 1; j < guessArray.length; j++){
                            if(guessArray[i] == guessArray[j]){
                                duplicate = true;
                            }
                        }
                    }
                    if(duplicate){
                        System.out.println(ANSI_RED + "\t\t\tTwo numbers cannot be the same in a guess. Enter another guess.\n" + ANSI_RESET);
                        break;
                    }



                            
                    //compares each digit in the guess array to the solution array to determine # of bulls & cows
                    for(int i = 0; i < numGuessDigits; i++){
                        if(guessArray[i] == solution.get(i)){
                            bulls++;
                        }  else if(solution.contains(guessArray[i])){
                            cows++;
                        }
                    }  

                    // gameBoard += ANSI_GREEN + "\t\t\t\t\t\t  " + bulls + ANSI_RESET + "  |  " + ANSI_GREEN + cows + ANSI_RESET +"  |  " + ANSI_YELLOW + guess +"\n" + ANSI_RESET;
                    // System.out.println(gameBoard);
                    printTable(bulls, cows, guess);


                    if (bulls == numGuessDigits){
                        System.out.println(ANSI_CYAN + "\t\t\tYou won in " + guessCount + " guesses!");
                        for(int i = 0; i < numGuessDigits; i++){
                            output += solution.get(i);
                        }
                        System.out.println("\t\t\tNumber to guess: " + output + ANSI_RESET);
                        gameState = gameStates.endGame;
                    }
                    guessCount++;
                    break;
                }

                case endGame: {
                    output = "";
                    System.out.print("\n\t\t\tPlay again? Y/N: ");
                    String playAgain = keys.next(); 
                    if(playAgain.equals("y") || playAgain.equals("Y")){
                        gameState = gameStates.startGame;
                    } else if(playAgain.equals("n") || playAgain.equals("N")){
                        gameActive = false;
                        keys.close();
                        break;
                    } 
                    break;
                }

                case giveSolution: {
                    System.out.print(ANSI_RED + "\n\t\t\tGame over. ");
                    System.out.print("\t\t\tThe solution is: ");
                    for(int i = 0; i < solution.size(); i++){
                        System.out.print(solution.get(i));
                    }
                    System.out.println(ANSI_RESET);
                    gameState = gameStates.endGame;
                }
            }
        }
    }

    public static void printTable(String easyBullsCows, String guess){
        final String BODY_1 = "\n\t\t\t\t\t\t        " + ANSI_GREEN + easyBullsCows + ANSI_RESET + "  |  " + ANSI_YELLOW + guess + ANSI_RESET;
        final String BODY_2 = "\n\t\t\t\t\t\t       " + ANSI_GREEN + easyBullsCows + ANSI_RESET + "  |  " + ANSI_YELLOW + guess + ANSI_RESET;
        final String BODY_3 = "\n\t\t\t\t\t\t      " + ANSI_GREEN + easyBullsCows + ANSI_RESET + "  |  " + ANSI_YELLOW + guess + ANSI_RESET;
        final String BODY_4 = "\n\t\t\t\t\t\t      " + ANSI_GREEN + easyBullsCows + ANSI_RESET + " | " + ANSI_YELLOW + guess + ANSI_RESET;
        final String BODY_5 = "\n\t\t\t\t\t\t     " + ANSI_GREEN + easyBullsCows + ANSI_RESET +  " | " + ANSI_YELLOW + guess + ANSI_RESET;
        final String BODY_6 = "\n\t\t\t\t\t\t    " + ANSI_GREEN + easyBullsCows + ANSI_RESET +  " | " + ANSI_YELLOW + guess + ANSI_RESET;
        final String BODY_7 = "\n\t\t\t\t\t\t   " + ANSI_GREEN + easyBullsCows + ANSI_RESET +  " | " + ANSI_YELLOW + guess + ANSI_RESET;
        final String BODY_8 = "\n\t\t\t\t\t          " + ANSI_GREEN + easyBullsCows + ANSI_RESET +  " | " + ANSI_YELLOW + guess + ANSI_RESET;
        final String BODY_9 = "\n\t\t\t\t\t         " + ANSI_GREEN + easyBullsCows + ANSI_RESET +  " | " + ANSI_YELLOW + guess + ANSI_RESET;
        final String BODY_10 = "\n\t\t\t\t\t        " + ANSI_GREEN + easyBullsCows + ANSI_RESET +  " | " + ANSI_YELLOW + guess + ANSI_RESET;
        final String[] BODY_SET= {BODY_1, BODY_2, BODY_3, BODY_4, BODY_5, BODY_6, BODY_7, BODY_8, BODY_9, BODY_10};

        gameBoard += BODY_SET[numGuessDigits-1];
        System.out.println(gameBoard);
    }

    public static void printTable(int bulls, int cows, String guess){
        final String HARD_BODY_1 = "\n\t\t\t\t\t\t     " + bulls + " | " + cows + " |   " + guess;
        final String HARD_BODY_2 = "\n\t\t\t\t\t\t     " + bulls + " | " + cows + " |  " + guess;
        final String HARD_BODY_3 = "\n\t\t\t\t\t\t     " + bulls + " | " + cows + " |  " + guess;
        final String HARD_BODY_4 = "\n\t\t\t\t\t\t     " + bulls + " | " + cows + " | " + guess;
        final String HARD_BODY_5 = "\n\t\t\t\t\t\t     " + bulls + " | " + cows + " | " + guess;
        final String HARD_BODY_6 = "\n\t\t\t\t\t\t    " + bulls + " | " + cows + " | " + guess;
        final String HARD_BODY_7 = "\n\t\t\t\t\t\t    " + bulls + " | " + cows + " | " + guess;
        final String HARD_BODY_8 = "\n\t\t\t\t\t           " + bulls + " | " + cows + " |  " + guess;
        final String HARD_BODY_9 = "\n\t\t\t\t\t           " + bulls + " | " + cows + " | " + guess;
        final String HARD_BODY_10 = "\n\t\t\t\t\t          " + bulls + " | " + cows + " | " + guess;

        final String[] HARD_BODY_SET= {HARD_BODY_1, HARD_BODY_2, HARD_BODY_3, HARD_BODY_4, HARD_BODY_5, HARD_BODY_6, HARD_BODY_7, HARD_BODY_8, HARD_BODY_9, HARD_BODY_10};

        gameBoard += HARD_BODY_SET[numGuessDigits-1];
        System.out.println(gameBoard);
   }
}