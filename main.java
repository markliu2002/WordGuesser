import java.io.*;
import java.util.*;


public class main {

  
  
  // Method for counting the vowels. Returns an int
  // Sting word parameter is the real word that is being guessed. This method will help you user by determining how many vowerls are in the word
  public static int numOfVowels(String word) {

    // int variable called counter to count the number of vowels 
    // Initially set to 0
    int count = 0;

    // String array called vowels with all the vowels in the English alphabet
    String[] vowels = {"A", "E", "I", "E", "O", "U"}; 

    // Converts the vowels array into a String List called mylist
    List<String> mylist = Arrays.asList(vowels); 
    
    // for loop to loop through the whole word to search for any possible vowels
    for(int i = 0; i<word.length(); i++) {

      // If the list(containing the vowels) contains the current letter in the chosen word, then that means the current letter is a vowel
      if(mylist.contains(String.valueOf(word.charAt(i)))) {

        // increase count which represents number of vowels
        count++; 

      }
    }

    //return the variable count, which represent the amount of vowels in each word
    return count;
  }



  
  // Method to print out all the use in a grid pattern using a 2D array
  // ArrayList passed in the the usedLetters arraylist which hold all the used letters
  public static void printUsedLetters(ArrayList<Character> array) {

    // Character ArrayList called listToPrint
    ArrayList<Character> listToPrint = new ArrayList<>();

    // Int variable called counter to represent the current index in the listToPrint ArrayList
    int counter = 0;

    // When we print the 2D array, we are going to fill it up with the used letters. If there are any free spots remaining however, we will just fill those with a hypthen '-'.
    // int variable called needToFill to determine the number of spots inthe 2D array that still need to be filled
    int needToFill;
    
    // For every Char in the ArrayList that was passed in, add those Chars to the listToPrint ArrayList
    for (char c : array) {

      // Add the current Char to the listToPrint ArrayList
      listToPrint.add(c);
  
    }
      
    // The 2D array has 25 spots. We subtract the number of used letters we have(listToPrint.size) from 25 in order to determine how many remaining free spots we will have in the 2D array.
    // We store this value inside the needToFill variable
    needToFill = 25 - listToPrint.size();

    // Only if needToFill is greater than 0, we need to add those additional hyphens '-' into the listToPrint arraylist
    if(needToFill > 0) {

      // For all the additional free spots we will have in the 2D array, we will add hyphens into the listToPrint arraylist
      for(int a = 0; a<needToFill; a++) {

        // Adds a hyphen into the listToPrint arraylist
        listToPrint.add('-');
      }
    }

    // 2D char array called twoDArray. Set to have 5 rows and 5 columns
    char[][] twoDArray = new char[5][5];

    // For each spot in the twoDArray, we fill it up with the corresponding Char in the listToPrint arraylist. The twoDArray array will contain BOTH the used letters AND all the hyphens which 
    // where used to fill up the remaining free space
    for(int i = 0; i<5; i++) {
      for(int j = 0; j<5; j++) {

        // Sets the currest position in the twoDArray to the current Char i the listToPrint Arraylist
        twoDArray[i][j] = listToPrint.get(counter);

        // Increases the counter which represents the current index in the listToPrint ArrayList
        counter++;
      }
    }

    // Prints a title for the 2D array list of used letters
    System.out.println("=========USED===LETTERS=========");
    
    
    // 2 for loops to loop through all the positions in the twoDArray
    for(int i = 0; i<5; i++) {
      for(int j = 0; j<5; j++) {

        // Prints out the current element in the twoDArray. It may be a letter or could be a hyphen
        System.out.print(twoDArray[i][j]);
      }
      
      // Skips to next line
      System.out.println();
    }
    
    // Prints a closing line for the 2D array
    System.out.println("================================");
  }



  
  
  
  // Main method
  public static void main(String[] args) {

    // Initialize a Scanner object called input. Will be used to get the letter that the user guesses
    Scanner input = new Scanner(System.in);

   // Initialize a "die" object of the dice class
    dice die = new dice();

    // Int variable called roll1 for the number that the user rolls first
    int roll1=0;

    // Int variable called roll2 for the number that the user rolls second
    int roll2=0;

    // Int variable called lives for the number of lives that the user has. Initially is set to 10 to avoid making the game too hard to win. 
    // The user's number of lives will increase depending on what their second roll is
    int lives = 10; 

    // Int variable called length for the length of the word that the user must guess
    int length = 0; 

    // String variable called stringRealWord for the Real Word that the user is tring to guess
    String stringRealWord ="";

    // String variable called stringCategory for the Category of the word that the user is trying to guess
    String stringCategory ="";

    // Declares and initializes file object. We will be reading from words.txt
    File file1 = new File("words.txt");

    // Arraylist used to store names of categories read from txt file
    ArrayList<String> categories = new ArrayList<String>();

    // create Arraylist used to store the words(each related to a catagory) that we read from the txt file
    ArrayList<String> wordbank = new ArrayList<String>();


    // String variable called line for when we read the file
    String line;

    // String array to store data from each line that is read from the txt file
    String[] data;

    // Char array called alphabet that stores all the letters of the english alphabet. We need this in order to check if the user actually entered a valid letter.
    char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',};

    // Char variable called letter for the letter that the user has currently guessed
    char letter;

    // Character arraylist called usedLetters to store all the letters that the user has already guessed. If the user guessed a letter that they have already guessed before,
    // a message will appear to notify them
    ArrayList<Character> usedLetters = new ArrayList<Character>();

    // Char array called charRealWordArray to store all the Chars of the Real Word that the user is trying to guess
    char[] charRealWordArray; 

    // String array called stringGuessedWordArray to store all the characters in the Guessed Word(the word that the user has guessed so far) 
    String[] stringGuessedWordArray; 

    // Boolean variable called isAlreadyGuessed to determine if the user entered a letter that they have already guessed before. Initially set to false
    boolean isAlreadyGuessed = false;



    // Try-catch needed in case of any errors while reading the file
    try {
      
      // Initialize FileReader, called fr. Pass file1 as the parameter
      FileReader fr = new FileReader(file1);
      
      // Initialize BufferedReader, called in. Pass the FileReader fe as the parameter
      BufferedReader in = new BufferedReader(fr);
      
      // Declares string variable called line
      // Reads the first line from the text file
      line = in.readLine();
      
      // While the line that we have read is not null
      while(line != null) {

        // spilts each line and places first part of line "categories" into data[o] 
        // the part aftet the comma is placed into data[1]
        data = line.split(", ");
        
        // adds the first element in the string array "data" into the arraylist "categories"
        categories.add(data[0]);

        // adds the second element in the string array "data" into the arraylist "words"
        wordbank.add(data[1]);

        //reads next line
        line = in.readLine();
      }
      
      // Closes BufferedReader stream.
      in.close();
    
    }
    
    // Output error message if exception is thrown
    catch (IOException e) { 

      // use the .getMessage method to get the error message. Prints error message
      System.err.println("IOException e: " + e.getMessage());
    
    }

    
    // Rolls first die and assigns it a value 
    // The value of 1st roll decides how much extra lives the user receives 
    roll1 = die.rollDice();

    // Adds that rolled number to the user's lives
    lives = lives + roll1;


    // Rolls second die assigns it a value
    // The value of 2nd roll decides the length of the word that the user needs to guess
    roll2 = die.rollDice();
    
    // Sets the length variable to that rolled number
    length = roll2;

    // Sets the length of the charRealWordArray to the length variable. The length variable determines the length of the word that the user needs to guess
    charRealWordArray = new char[length];

    // Sets the length of the stringGuessedWordArray to the length variable. The length variable determines the length of the word that the user needs to guess
    stringGuessedWordArray = new String[length];

    // Prompts users with what rolls they got and the length of the word that needs to be guessed
    System.out.println("Rolling the die twice...");
    System.out.println("Your first roll was a: " + roll1);
    System.out.println("You have " + lives + " lives!");
    System.out.println("Your second roll was a: " + roll2);
    System.out.println("You have to guess a word that is " + length + " letters long!" );
    
    
    // For loop to loop through the wordbank which holds all the possible words
    for(int i = 0; i<wordbank.size(); i++) {

      // If the current word in the wordbank is of the chosen length, then we choose this word
      if(wordbank.get(i).length() == length) {
        
        // sets the real word 
        stringRealWord = wordbank.get(i); 

        // saves the category that corresponds to that real word
        stringCategory = categories.get(i); 
      }
    }

    
    // For loop to loop through the letters of the stringRealWord
    for (int i = 0; i < stringRealWord.length(); i++) {

        // Sets each element in the Real Word array to each letter in the randomly picked word
        charRealWordArray[i] = stringRealWord.charAt(i); 

        // Sets each element in the Guessed Word to a underscore "_" to represent the number of letters in the word
        stringGuessedWordArray[i] = "_"; 
    }


    
    // String variable called stringGuessedWord is set the to stringGuessedWordArray, does this by joining each letter in the Guessed Word array. 
    
    String stringGuessedWord = String.join("", stringGuessedWordArray); 
    
    

    // While-loop while the Guessed Word and Real Word are not the same, AND lives are greater than 0.
    while ((!stringGuessedWord.equals(stringRealWord)) && lives > 0) {

        //set boolean variable to false - this will later be used to check if user has already guessed their letter
        isAlreadyGuessed = false;

        // Updates the Guessed Word string by joining each letter in the Guessed Word array.
        // NOTE: this requires java 8 and above.
        stringGuessedWord = String.join("", stringGuessedWordArray);

        // If the Guessed Word and Real Word are the same, print they won and break out of while-loop.
        if (stringGuessedWord.equals(stringRealWord)) {

            // Prints winning message
            System.out.println("Y O U   W I N !");

            // Prints the Real Word
            System.out.println("You successfully guessed the word: " + stringRealWord + "!");

            // Break out of the while loop
            break;
        }


        // Prints the user's current Guessed Word, number of lives and their used letters
        System.out.println("Current word: " + stringGuessedWord);
        System.out.println("Lives: " + lives);

        // Prints word category and gives a hint as to how many vowels are in the word
        System.out.println("The word category is: " + stringCategory);
        System.out.println("The word has " + numOfVowels(stringRealWord) + " vowels!");

        // Prompts and stores user's letter
        System.out.println("Guess a letter :)\n");

        // Takes in the Character that the user guessed and stores it in the letter variable
        // We need to take in the user's input as a string, then convert it to a character(uppercase)
        letter = Character.toUpperCase(input.next().charAt(0));

        
      
        
        // Check if the letter is in the alphabet.
        if (String.valueOf(alphabet).contains(String.valueOf(letter))) {

          
          // for loop - checks if letter guessed was already guessed 
          // compares letter guessed with current letter in "usedLetters" arraylist 
          for(int i = 0; i<usedLetters.size(); i++) {

            // if Letter chosen by user was already guessed changes variable "isAlreadyGuessed" to true
            if (letter == usedLetters.get(i)) {
              
              isAlreadyGuessed = true;
            }
          }

          // If the variable "isAlreadyGuessed" remains false - means that the user has inputed a letter that has not been guessed yet
          // Now code can go ahead by further checking if the letter is within the "RealWord" that the user needs to guess
          if(isAlreadyGuessed != true) {

            // Check if the letter is in the Real Word.
            if (stringRealWord.contains(String.valueOf(letter))) {

                // Cycle through each letter in the Real Word.
                for (int j = 0; j < stringRealWord.length(); j++) {

                    // Check if user's letter is equal to the current letter in the Real Word.
                    if (stringRealWord.charAt(j) == letter) {

                        // Change that index of the Guessed Word from a "_" to the current letter.
                        stringGuessedWordArray[j] = String.valueOf(letter);
                    }
                }

                //Add letter to used letters. - KEEPS TRACK of letters that have been guessed already by user
                usedLetters.add(letter);

                // Calls the printUsedLetters method to print the used letters in a 5X5 2D array. Passes in the usedLetters arraylist
                printUsedLetters(usedLetters); 
            }

            // Else means the user's letter is not in the Real Word.
            else {

                // Add letter to used letters array list.
                usedLetters.add(letter);

                // Calls the printUsedLetters method to print the used letters in a 5X5 2D array. Passes in the usedLetters arraylist
                printUsedLetters(usedLetters); 

                // Take away a life.
                lives = lives - 1;
            }
          }

          
          //If the letter the user imputs is already a guessed letter (isAlreadyGuessed = True) 
          //then leave message to user saying that they have already guessed that letter
          else {
            System.out.println("You have already guessed that letter");
          }

        }


        
        // If the user's letter is not in the alphabet, we don't take away a life and leave a message indicated user to input a valid letter.
        else {
          System.out.println("Please enter a valid letter");
        }


      
        // If user's lives = 0, they lost, print hangman and break out of while-loop. **GAME OVER**
        if (lives == 0) {
            System.out.println("G A M E   O V E R");
            System.out.println("The word was: " + stringRealWord);
            break;
        }
    }
      

  }
}



