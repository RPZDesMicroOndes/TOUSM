import application.Dictionary;

import application.*;

/**
 * launcher of the game.
 */
public class Launcher {

    public static void main(String[] args) {
        
        Dictionary dictionary = new Dictionary();
        

            /*for (Word word : dictionary.getDictionary()) {

                System.out.println(word.getWord());
            
            }
        

        System.out.println(dictionary.pickRandomWord().getWord());*/

        Game game = new Game(dictionary);
        game.play();
    }
    
}