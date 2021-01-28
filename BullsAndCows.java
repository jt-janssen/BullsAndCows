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

    public static void main(String[] args) {
        System.out.println("<------------------------ BULLS & COWS ------------------------>");
        System.out.println("If you want the solution before you solve it, enter 0000.\n");

        ArrayList<Integer> solution = new ArrayList<Integer>();
        ArrayList<Integer> guessArray = new ArrayList<Integer>(4);

        // use add() method to add elements in the list
        for(int i = 0; i < 10; i++){
            solution.add(i);
        }     

        Collections.shuffle(solution); //shuffles array of numbers

        // for(int i = 0; i < 4; i++){  //TODO will print solution; must be commented out
        //     System.out.println(solution.get(i));
        // }  

        //define variables used in while loop
        boolean isAnswer = false;
        int guessCount = 1;
        int guessNumber;
        int bulls, cows;

        while(!isAnswer){   //runs & accepts guesses ustil right guess is guessed
            //resets and clears certain variables after each guess
            guessArray.clear();
            bulls = 0;
            cows = 0;
            
            //takes in and stores user input
            Scanner keys = new Scanner(System.in);
            System.out.print("Enter guess " + guessCount + ": " + ANSI_YELLOW);
            int guess = keys.nextInt(); 
            
            if(guess == 0000){
                System.out.println(ANSI_RED + "Solution is: " + solution.get(0) + solution.get(1) + solution.get(2) + solution.get(3) + ANSI_RESET);
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
            //System.out.println(guessArray);
            
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
                isAnswer = true;
                System.out.println(ANSI_CYAN + "You won in " + guessCount + " guesses!");
                System.out.println("Number to guess: " + solution.get(0) + solution.get(1) + solution.get(2) + solution.get(3) + ANSI_RESET);
                keys.close();   //do I need this? It worked w/o it.
            }
            guessCount++;
        }
        System.out.println("<-------------------------------------------------------------->");
    }
}