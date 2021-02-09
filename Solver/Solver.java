/*
Created by JT Janssen
*/

package Solver; //not sure why i need this but it wont run w/o

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Solver{
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_BLACK = "\u001B[30m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_PURPLE = "\u001B[35m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_WHITE = "\u001B[37m";

    static ArrayList<String> combos = new ArrayList<String>();
    static Random random = new Random();
    static Scanner keys = new Scanner(System.in);
    static boolean gameActive = true;

    static enum modes{start, input, remove, guess, end}
    static modes mode = modes.start;

    static int counter;
    static String guess;
    static int bulls, cows, totalRight;

    public static void main(String[] args) {
        while(gameActive){
            switch(mode){
                case start: {
                    createCombos();
                    counter = 1;
                    mode = modes.guess;
                }
                case guess: {
                    guess = chooseRandomGuess();
                    System.out.println(ANSI_GREEN + "Guess " + counter + ": " + guess + ANSI_RESET);
                    counter++;
                    mode = modes.input;
                    break;
                }
                case input: {
                    System.out.print("Number of bulls: ");
                    bulls = keys.nextInt();
        
                    System.out.print("Number of cows: ");
                    cows = keys.nextInt();
        
                    totalRight = bulls + cows;
        
                    if(!(totalRight >= 0 && totalRight <=4)){
                        System.out.println(ANSI_RED + "Bulls and cows must be less than 4" + ANSI_RESET);
                        break;
                    }
                    mode = modes.remove;
                }
                case remove: {
                    if(totalRight == 0){
                        noNumbers(guess);
                    } else if(totalRight == 1){
                        oneOfFourCorrect(guess);
                    } else if(totalRight == 2){
                        twoOfFourCorrect(guess);
                    } else if(totalRight == 3){
                        threeOfFourCorrect(guess);
                    } else if(totalRight == 4){
                        fourOfFourCorrect(guess);
                    } 
        
                    if(bulls == 0){
                        noBulls(guess);
                    } else if(bulls == 1){
                        oneBull(guess);
                    } else if(bulls == 2){
                        twoBulls(guess);
                    } else if(bulls == 3){
                        threeBulls(guess);
                    } else if(bulls == 4){
                        mode = modes.end;
                        break;
                    }

                    System.out.println(ANSI_BLUE + combos.size() + " possibilities left\n" + ANSI_RESET);
                    mode = modes.guess;
                    break;
                }
                case end: {
                    System.out.print("Run again? Y/N: ");
                    String runAgain = keys.next(); 
                    if(runAgain.equals("y") || runAgain.equals("Y")){
                        mode = modes.start;
                        System.out.print("\n");
                    } else if(runAgain.equals("n") || runAgain.equals("N")){
                        gameActive = false;
                        keys.close();
                        break;
                    } 
                    break;
                }
            }
        }
    }


    static void noNumbers(String x){
        String a = ""+x.charAt(0);
        String b = ""+x.charAt(1);
        String c = ""+x.charAt(2);
        String d = ""+x.charAt(3);

        //narrows down combos that do NOT have any of these numbers
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).contains(a) || combos.get(i).contains(b) || combos.get(i).contains(c) || combos.get(i).contains(d)){
                combos.remove(i);
            }
        }
        // System.out.println(ANSI_BLUE + combos.toString());
        // System.out.println(ANSI_RED + combos.size() + " possibilities left" + ANSI_RESET);
    }
    
    static void oneOfFourCorrect(String x){
        String a = ""+x.charAt(0);
        String b = ""+x.charAt(1);
        String c = ""+x.charAt(2);
        String d = ""+x.charAt(3); 

        //only one of the four numbers entered are correct
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).contains(a) && !(combos.get(i).contains(b) || combos.get(i).contains(c) || combos.get(i).contains(d))){
 
            } else if (combos.get(i).contains(b) && !(combos.get(i).contains(a) || combos.get(i).contains(c) || combos.get(i).contains(d))){

            } else if (combos.get(i).contains(c) && !(combos.get(i).contains(b) || combos.get(i).contains(a) || combos.get(i).contains(d))){

            } else if (combos.get(i).contains(d) && !(combos.get(i).contains(b) || combos.get(i).contains(c) || combos.get(i).contains(a))){

            } else {
                combos.remove(i);
            }
        }

        // System.out.println(ANSI_BLUE + combos.toString());
        // System.out.println(ANSI_RED + combos.size() + " possibilities left" + ANSI_RESET);
    }
    static void twoOfFourCorrect(String x){
        String a = ""+x.charAt(0);
        String b = ""+x.charAt(1);
        String c = ""+x.charAt(2);
        String d = ""+x.charAt(3);

        //only two of the four numbers entered are correct
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).contains(a) && combos.get(i).contains(b) && !(combos.get(i).contains(c) || combos.get(i).contains(d))){
                //This is what we want to keep, so when none of these actually run, it will go to the else and remove
            } else if(combos.get(i).contains(a) && combos.get(i).contains(c) && !(combos.get(i).contains(b) || combos.get(i).contains(d))){

            } else if(combos.get(i).contains(a) && combos.get(i).contains(d) && !(combos.get(i).contains(b) || combos.get(i).contains(c))){

            } else if(combos.get(i).contains(b) && combos.get(i).contains(c) && !(combos.get(i).contains(a) || combos.get(i).contains(d))){

            } else if(combos.get(i).contains(b) && combos.get(i).contains(d) && !(combos.get(i).contains(c) || combos.get(i).contains(a))){

            }else if(combos.get(i).contains(c) && combos.get(i).contains(d) && !(combos.get(i).contains(a) || combos.get(i).contains(b))){

            }else {
                combos.remove(i);
            }
        }

        // System.out.println(ANSI_BLUE + combos.toString());
        // System.out.println(ANSI_RED + combos.size() + " possibilities left" + ANSI_RESET);

    }
    static void threeOfFourCorrect(String x){
        String a = ""+x.charAt(0);
        String b = ""+x.charAt(1);
        String c = ""+x.charAt(2);
        String d = ""+x.charAt(3);

        //only three of the four numbers entered are correct
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).contains(a) && combos.get(i).contains(b) && combos.get(i).contains(c) && !combos.get(i).contains(d)){
                //This is what we want to keep, so when none of these actually run, it will go to the else and remove
            } else if(combos.get(i).contains(b) && combos.get(i).contains(c) && combos.get(i).contains(d) && !combos.get(i).contains(a)){

            } else if(combos.get(i).contains(a) && combos.get(i).contains(c) && combos.get(i).contains(d) && !combos.get(i).contains(b)){

            } else if(combos.get(i).contains(a) && combos.get(i).contains(b) && combos.get(i).contains(d) && !combos.get(i).contains(c)){

            } else {
                combos.remove(i);
            }
        }

        // System.out.println(ANSI_BLUE + combos.toString());
        // System.out.println(ANSI_RED + combos.size() + " possibilities left" + ANSI_RESET);
    }
    static void fourOfFourCorrect(String x){
        String a = ""+x.charAt(0);
        String b = ""+x.charAt(1);
        String c = ""+x.charAt(2);
        String d = ""+x.charAt(3);

        //only three of the four numbers entered are correct
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).contains(a) && combos.get(i).contains(b) && combos.get(i).contains(c) && combos.get(i).contains(d)){

            } else {
                combos.remove(i);
            }
        }

        // System.out.println(ANSI_BLUE + combos.toString());
        // System.out.println(ANSI_RED + combos.size() + " possibilities left" + ANSI_RESET);
    }

    static void noBulls(String x){
        String a = ""+x.charAt(0);
        String b = ""+x.charAt(1);
        String c = ""+x.charAt(2);
        String d = ""+x.charAt(3);

        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).indexOf(a) == 0 || combos.get(i).indexOf(b) == 1 || combos.get(i).indexOf(c) == 2 || combos.get(i).indexOf(d) == 3){
                combos.remove(i);
            }
        }
        // System.out.println(ANSI_BLUE + combos.toString());
        // System.out.println(ANSI_RED + combos.size() + " possibilities left" + ANSI_RESET);
    }
    static void oneBull(String x){
        String a = ""+x.charAt(0);
        String b = ""+x.charAt(1);
        String c = ""+x.charAt(2);
        String d = ""+x.charAt(3);
        //narrows down based on ONE bull
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).indexOf(a) == 0 && (combos.get(i).indexOf(b) == 1 || combos.get(i).indexOf(c) == 2 || combos.get(i).indexOf(d) == 3)){
                combos.remove(i);
            }
            else if(combos.get(i).indexOf(b) == 1 && (combos.get(i).indexOf(a) == 0 || combos.get(i).indexOf(c) == 2 || combos.get(i).indexOf(d) == 3)){
                combos.remove(i);
            }
            else if(combos.get(i).indexOf(c) == 2 && (combos.get(i).indexOf(a) == 0 || combos.get(i).indexOf(b) == 1 || combos.get(i).indexOf(d) == 3)){
                combos.remove(i);
            }
            else if(combos.get(i).indexOf(d) == 3 && (combos.get(i).indexOf(b) == 1 || combos.get(i).indexOf(c) == 2 || combos.get(i).indexOf(a) == 0)){
                combos.remove(i);
            }
        }
        // System.out.println(ANSI_BLUE + combos.toString());
        // System.out.println(ANSI_RED + combos.size() + " possibilities left" + ANSI_RESET);
        
    }
    static void twoBulls(String x){
        String a = ""+x.charAt(0);
        String b = ""+x.charAt(1);
        String c = ""+x.charAt(2);
        String d = ""+x.charAt(3);

        for(int i = combos.size()-1; i >= 0; i--){
            if((combos.get(i).indexOf(a) == 0 && (combos.get(i).indexOf(b) == 1) && (combos.get(i).indexOf(c) != 2 || combos.get(i).indexOf(d) != 3))){

            } else if((combos.get(i).indexOf(a) == 0 && (combos.get(i).indexOf(c) == 2) && (combos.get(i).indexOf(b) != 1 || combos.get(i).indexOf(d) != 3))){

            }else if((combos.get(i).indexOf(a) == 0 && (combos.get(i).indexOf(d) == 3) && (combos.get(i).indexOf(b) != 1 || combos.get(i).indexOf(c) != 2))){

            } else if((combos.get(i).indexOf(b) == 1 && (combos.get(i).indexOf(c) == 2) && (combos.get(i).indexOf(a) != 0 || combos.get(i).indexOf(d) != 3))){

            } else if((combos.get(i).indexOf(b) == 1 && (combos.get(i).indexOf(d) == 3) && (combos.get(i).indexOf(a) != 0 || combos.get(i).indexOf(c) != 2))){

            } else if((combos.get(i).indexOf(c) == 2 && (combos.get(i).indexOf(d) == 3) && (combos.get(i).indexOf(a) != 0 || combos.get(i).indexOf(b) != 1))){

            } else {
                combos.remove(i);
            }
        }
        // System.out.println(ANSI_BLUE + combos.toString());
        // System.out.println(ANSI_RED + combos.size() + " possibilities left" + ANSI_RESET);
    }

    static void threeBulls(String x){
        String a = ""+x.charAt(0);
        String b = ""+x.charAt(1);
        String c = ""+x.charAt(2);
        String d = ""+x.charAt(3);

        for(int i = combos.size()-1; i >= 0; i--){
            if((combos.get(i).indexOf(a) == 0 && (combos.get(i).indexOf(b) == 1) && combos.get(i).indexOf(c) == 2  && combos.get(i).indexOf(d) != 3)){

            } else if((combos.get(i).indexOf(b) == 1 && (combos.get(i).indexOf(c) == 2) && combos.get(i).indexOf(d) == 3 && combos.get(i).indexOf(a) != 0)){

            }else if((combos.get(i).indexOf(a) == 0 && (combos.get(i).indexOf(c) == 2) && combos.get(i).indexOf(d) == 3 && combos.get(i).indexOf(b) != 1)){

            } else if((combos.get(i).indexOf(a) == 0 && (combos.get(i).indexOf(b) == 1) && combos.get(i).indexOf(d) == 3 && combos.get(i).indexOf(c) != 2)){

            } else {
                combos.remove(i);
            }
        }
        // System.out.println(ANSI_BLUE + combos.toString());
        // System.out.println(ANSI_RED + combos.size() + " possibilities left" + ANSI_RESET);
    }

    static String chooseRandomGuess(){
        return combos.get(random.nextInt(combos.size())) + ANSI_RESET;
    }

    static void createCombos(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                for(int k = 0; k < 10; k++){
                    for(int l = 0; l < 10; l++){
                        if(i != j && i != k && i!=l&&j!=k&&j!=l&&k!=l){
                            combos.add(""+i+j+k+l);
                        }
                    }
                }
            }
        }
        // System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
    }

    //Only used for debug
    static void checkForAnswer(String x){
        boolean result = false;
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).equals(x)){
                result = true;
            }
         } 
        if(result){
            System.out.println(ANSI_CYAN + "Answer still possible" + ANSI_RESET);
        } else {
            System.out.println(ANSI_PURPLE + "ANSWER REMOVED" + ANSI_RESET);
        }
    }

}
