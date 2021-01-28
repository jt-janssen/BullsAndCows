/*
Created by JT Janssen
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class BullsAndCows{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    private enum gameStates{startGame, inputAndCompare, endGame, giveSolution}

    public static void main(String[] args) {
        ArrayList<Integer> solution = new ArrayList<Integer>();
        ArrayList<Integer> guessArray = new ArrayList<Integer>(4);
        gameStates gameState = gameStates.startGame;
        boolean gameActive = true;
        int guessCount = 1;

        //define variables used in while loop
        int guessNumber;
        int bulls, cows;
        Scanner keys = new Scanner(System.in);

        while(gameActive){   //runs & accepts guesses ustil right guess is guessed
            switch(gameState){
                case startGame: {
                System.out.println(ANSI_BLUE + "<------------------------ BULLS & COWS ------------------------>" + ANSI_RESET);
                System.out.println("If you want the solution before you solve it, enter 0000.\n");
                //add elements in the list
                for(int i = 0; i < 10; i++){
                    solution.add(i);
                }     

                Collections.shuffle(solution); //shuffles array of numbers
                guessCount = 1;

                // for(int i = 0; i < 4; i++){  //TODO will print solution; must be commented out
                //     System.out.println(solution.get(i));
                // }  

                gameState = gameStates.inputAndCompare;
                break;
                }

                case inputAndCompare: {
                    //resets and clears certain variables after each guess
                    bulls = 0;
                    cows = 0;
                    guessArray.clear();

                    //takes in and stores user input
                    System.out.print("Enter guess " + guessCount + ": " + ANSI_YELLOW);
                    int guess = keys.nextInt(); 

                    //will send to giveSolution if input is '0000'
                    if(guess == 0000){
                        gameState = gameStates.giveSolution;
                        break;
                    }

                    //logic so that you are not allowed to enter anything else other than 4 digits
                    if(String.valueOf(guess).length() != 4){
                        System.out.println(ANSI_RED + "Guess must be 4 digits. Enter another guess\n" + ANSI_RESET);
                        break;
                    }

                    //converts the inputted 4 digit int into an arraylist so that each digit can be compared
                    for(int i = 0; i < 4; i++){
                        guessNumber = guess % 10;
                        guess = guess / 10;
                        if(guessArray.contains(guessNumber)){
                            System.out.println(ANSI_RED + "Two numbers cannot be the same in a guess. Enter another guess." + ANSI_RESET);
                            guessCount--;
                            break;
                        }
                        guessArray.add(guessNumber);
                    } 


        
                    Collections.reverse(guessArray);    //reverses order of the guess array so that is in the correct order.
                    
                    //compares each digit in the guess array to the solution array to determine # of bulls & cows
                    for(int i = 0; i < 4; i++){
                        if(guessArray.get(i) == solution.get(i)){
                            bulls++;
                        } else if(guessArray.get(i) == solution.get(0) || guessArray.get(i) == solution.get(1) || guessArray.get(i) == solution.get(2) || guessArray.get(i) == solution.get(3)){
                            cows++;
                        }
                    }  
        
                    System.out.println(ANSI_GREEN + "Bulls: " + bulls + "\tCows: " + cows + "\n" + ANSI_RESET);
                    if (bulls == 4){
                        System.out.println(ANSI_CYAN + "You won in " + guessCount + " guesses!");
                        System.out.println("Number to guess: " + solution.get(0) + solution.get(1) + solution.get(2) + solution.get(3) + ANSI_RESET);
                        gameState = gameStates.endGame;
                    }
                    guessCount++;
                    break;
                }

                case endGame: {
                    System.out.print("\nPlay again? Y/N: ");
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
                    System.out.print(ANSI_RED + "\nYou did not solve it. ");
                    System.out.println("The solution is: " + solution.get(0) + solution.get(1) + solution.get(2) + solution.get(3) + ANSI_RESET);
                    gameState = gameStates.endGame;
                }
            }
        }
    }
}
