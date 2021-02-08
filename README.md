# Bulls And Cows
A command line number guessing game

# Requirements
* Download and install [Java](https://www.java.com/en/download/help/download_options.html) if not already installed
* Terminal on Mac or Command Prompt on Windows
  * Note: for colors to work you must run this on a terminal emulator with support for ANSI colors (Such as [iTerm](https://iterm2.com/) for Mac)
  
# Getting Started
* Download the code in this repository (files may be placed in any directory)
* Open your command line application and navigate to the BullsAndCows directory
* Enter the command `java BullsAndCows.java`
* If you're on a Mac and would like to create an executable shortcut, create a .command file with the following: `java <filepath>/BullsAndCows.java`. There is a .png file in the Asset folder if you'd like a unique icon.

# How to Play
Bulls and Cows is a number guessing game. The program will generate a secret number for you to guess. A 'Bull' or 'B' means that there is a correct digit in the **correct** spot. A 'Cow' or 'C' means there is a correct digit in the **incorrect** spot.
* To start, choose your game mode. 1 for easy, 2 for hard.
  * Easy mode shows you which digits you've guessed are Bulls, Cows, or neither.
  * Hard mode only tells you how many Bulls and Cows are in your guess.
* Next, select how many digits to guess - 4 is standard.
* Start guessing!