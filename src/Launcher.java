import application.Dictionary;

import application.*;

/**
 * launcher of the game.
 */
public class Launcher {

    public static void main(String[] args) {
        
        Dictionary dictionary = new Dictionary();
        /*

            for (Word word : dictionary.getDictionary()) {

                System.out.println(word.getword());
            
            }
        */

        // System.out.println(dictionary.pickRandomWord().getword());

        Game game = new Game(dictionary);
    }
    
}