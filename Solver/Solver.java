package Solver;

import java.util.ArrayList;
import java.util.Random;



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

    public static void main(String[] args) {
        createCombos();


        //ENTER STUFF HERE
        threeOfFourCorrect(1,2,3,4);
        noBulls(1,2,3,4);

        threeOfFourCorrect(4,8,2,1);
        oneBull(4,8,2,1);

        noBulls(2,4,1,7);
        twoOfFourCorrect(2,4,1,7);

        oneBull(8,1,2,3);
        threeOfFourCorrect(8,1,2,3);

        oneBull(4,3,8,2);
        threeOfFourCorrect(4,3,8,2);


        System.out.println(chooseRandomGuess());


        



    }


    static void noNumbers(int w, int x, int y, int z){
        String a=String.valueOf(w); 
        String b=String.valueOf(x); 
        String c=String.valueOf(y); 
        String d=String.valueOf(z); 
        //narrows down combos that do NOT have any of these numbers
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).contains(a) || combos.get(i).contains(b) || combos.get(i).contains(c) || combos.get(i).contains(d)){
                combos.remove(i);
            }
        }
        // System.out.println(ANSI_BLUE + combos.toString());
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
    }
    static void noNumbers(int x, int y, int z){
        String b=String.valueOf(x); 
        String c=String.valueOf(y); 
        String d=String.valueOf(z); 
        //narrows down combos that do NOT have any of these numbers
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).contains(b) || combos.get(i).contains(c) || combos.get(i).contains(d)){
                combos.remove(i);
            }
        }
        // System.out.println(ANSI_BLUE + combos.toString());
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
    }
    static void noNumbers(int y, int z){ 
        String c=String.valueOf(y); 
        String d=String.valueOf(z); 
        //narrows down combos that do NOT have any of these numbers
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).contains(c) || combos.get(i).contains(d)){
                combos.remove(i);
            }
        }
        // System.out.println(ANSI_BLUE + combos.toString());
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
    }
    static void noNumbers(int z){ 
        String d=String.valueOf(z); 
        //narrows down combos that do NOT have any of these numbers
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).contains(d)){
                combos.remove(i);
            }
        }
        // System.out.println(ANSI_BLUE + combos.toString());
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
    }

    static void eitherOr(int x, int y){

        String a=String.valueOf(x); 
        String b=String.valueOf(y); 
        //narrows down based on one or the other #

        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).indexOf(a) != -1 && combos.get(i).indexOf(b) != -1){
                combos.remove(i);
            }
        }
        // System.out.println(ANSI_BLUE + combos.toString());
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
    }
    
    //TODO __make SEPERATE methods that check for cows, call on these methods, but if they are in the same pos, then remove - add to noBull()
    static void oneOfFourCorrect(int w, int x, int y, int z){
        String a=String.valueOf(w); 
        String b=String.valueOf(x); 
        String c=String.valueOf(y); 
        String d=String.valueOf(z); 

        //only three of the four numbers entered are correct
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).contains(a) ^ combos.get(i).contains(b) ^ combos.get(i).contains(c) ^ combos.get(i).contains(d)){

            } else {
                combos.remove(i);
            }
        }

        // System.out.println(ANSI_BLUE + combos.toString());
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
    }
    static void twoOfFourCorrect(int w, int x, int y, int z){
        String a=String.valueOf(w); 
        String b=String.valueOf(x); 
        String c=String.valueOf(y); 
        String d=String.valueOf(z); 

        //only two of the four numbers entered are correct
        for(int i = combos.size()-1; i >= 0; i--){
            //next else if removes it if all 4 are present -> must be first bc the other ones will run if its not
            if(combos.get(i).contains(a) && combos.get(i).contains(b) && combos.get(i).contains(c) && combos.get(i).contains(d)){
                combos.remove(i);
            } else if(combos.get(i).contains(a) && combos.get(i).contains(b)){
                //This is what we want to keep, so when none of these actually run, it will go to the else and remove
            } else if(combos.get(i).contains(a) && combos.get(i).contains(c)){

            } else if(combos.get(i).contains(a) && combos.get(i).contains(d)){

            } else if(combos.get(i).contains(b) && combos.get(i).contains(c)){

            } else if(combos.get(i).contains(b) && combos.get(i).contains(d)){

            }else if(combos.get(i).contains(c) && combos.get(i).contains(d)){

            }else {
                combos.remove(i);
            }
        }

        // System.out.println(ANSI_BLUE + combos.toString());
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);

    }
    static void threeOfFourCorrect(int w, int x, int y, int z){
        String a=String.valueOf(w); 
        String b=String.valueOf(x); 
        String c=String.valueOf(y); 
        String d=String.valueOf(z); 

        //only three of the four numbers entered are correct
        for(int i = combos.size()-1; i >= 0; i--){
            //next else if removes it if all 4 are present -> must be first bc the other ones will run if its not
            if(combos.get(i).contains(a) && combos.get(i).contains(b) && combos.get(i).contains(c) && combos.get(i).contains(d)){
                combos.remove(i);
            } else if(combos.get(i).contains(a) && combos.get(i).contains(b) && combos.get(i).contains(c)){
                //This is what we want to keep, so when none of these actually run, it will go to the else and remove
            } else if(combos.get(i).contains(b) && combos.get(i).contains(c) && combos.get(i).contains(d)){

            } else if(combos.get(i).contains(a) && combos.get(i).contains(c) && combos.get(i).contains(d)){

            } else if(combos.get(i).contains(a) && combos.get(i).contains(b) && combos.get(i).contains(d)){

            } else {
                combos.remove(i);
            }
        }

        //System.out.println(ANSI_BLUE + combos.toString());
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
    }
    static void fourOfFourCorrect(int w, int x, int y, int z){
        String a=String.valueOf(w); 
        String b=String.valueOf(x); 
        String c=String.valueOf(y); 
        String d=String.valueOf(z); 

        //only three of the four numbers entered are correct
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).contains(a) && combos.get(i).contains(b) && combos.get(i).contains(c) && combos.get(i).contains(d)){

            } else {
                combos.remove(i);
            }
        }

        //System.out.println(ANSI_BLUE + combos.toString());
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
    }

    static void noBulls(int w, int x, int y, int z){
        String a=String.valueOf(w); 
        String b=String.valueOf(x); 
        String c=String.valueOf(y); 
        String d=String.valueOf(z);

        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).indexOf(a) == 0 || combos.get(i).indexOf(b) == 1 || combos.get(i).indexOf(c) == 2 || combos.get(i).indexOf(d) == 3){
                combos.remove(i);
            }
        }
        //System.out.println(ANSI_BLUE + combos.toString());
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
    }
    static void oneBull(int w, int x, int y, int z){
        String a=String.valueOf(w); 
        String b=String.valueOf(x); 
        String c=String.valueOf(y); 
        String d=String.valueOf(z);
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
        //System.out.println(ANSI_BLUE + combos.toString());
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
        
    }
    static void twoBulls(int w, int x, int y, int z){
        String a=String.valueOf(w); 
        String b=String.valueOf(x); 
        String c=String.valueOf(y); 
        String d=String.valueOf(z);

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
        //System.out.println(ANSI_BLUE + combos.toString());
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
    }

    static void threeBulls(int w, int x, int y, int z){
        String a=String.valueOf(w); 
        String b=String.valueOf(x); 
        String c=String.valueOf(y); 
        String d=String.valueOf(z);

        for(int i = combos.size()-1; i >= 0; i--){
            if((combos.get(i).indexOf(a) == 0 && (combos.get(i).indexOf(b) == 1) && combos.get(i).indexOf(c) == 2  && combos.get(i).indexOf(d) != 3)){

            } else if((combos.get(i).indexOf(b) == 1 && (combos.get(i).indexOf(c) == 2) && combos.get(i).indexOf(d) == 3 && combos.get(i).indexOf(a) != 0)){

            }else if((combos.get(i).indexOf(a) == 0 && (combos.get(i).indexOf(c) == 2) && combos.get(i).indexOf(d) == 3 && combos.get(i).indexOf(b) != 1)){

            } else if((combos.get(i).indexOf(a) == 0 && (combos.get(i).indexOf(b) == 1) && combos.get(i).indexOf(d) == 3 && combos.get(i).indexOf(c) != 2)){

            } else {
                combos.remove(i);
            }
        }
        //System.out.println(ANSI_BLUE + combos.toString());
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
    }

    static String chooseRandomGuess(){
        System.out.print(ANSI_GREEN + "next number to guess: ");
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
        System.out.println(ANSI_RED + combos.size() + ANSI_RESET);
    }

    static void checkForAnswer(String x){
        boolean result = false;
        for(int i = combos.size()-1; i >= 0; i--){
            if(combos.get(i).equals(x)){
                result = true;
            }
         } 
        if(result){
            System.out.println(ANSI_CYAN + "yeet" + ANSI_RESET);
        } else {
            System.out.println(ANSI_PURPLE + "Something broke" + ANSI_RESET);
        }
    }

}

