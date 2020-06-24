import java.util.Random;
//dice class
public class dice {
  
    //Instance variable of a dice
    int roll;

    //stores random object(random number) in rnd
    Random rnd = new Random();

    //method 
    public int rollDice()
    { 
        // Rolls a number from 5-10 because the shortest possible word in 5 letters and the longest possible word is 10 letters
        return rnd.nextInt(6) + 5;
    }
}