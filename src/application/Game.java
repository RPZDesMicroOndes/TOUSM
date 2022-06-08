package application;

import java.util.ArrayList;
import java.util.HashMap;

public class Game{

    private Dictionary dictionary;
    private ArrayList<String> wordGuess;
    private String[] hint;
    private HashMap<Integer,ArrayList<String>> wordGuesses;
    private Word wordToGuess;
    private int nbTry;
    private static final int MAX_TRY = 6;
    private int wordSize;
    private char firstLetter;

    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";


    public Game(Dictionary dictionary){

        this.dictionary = dictionary;
        this.wordGuess = new ArrayList<>();
        this.wordGuesses = new HashMap<>();

        this.wordToGuess = dictionary.pickRandomWord();
        this.wordSize = wordToGuess.getWord().length();
        this.firstLetter = wordToGuess.getWord().charAt(0);

        this.hint = new String[this.wordSize];
        this.hint[0] = this.firstLetter + "";

        this.nbTry = 1;

    }

    public void play() {
        
        boolean wordFound = false;
        displayBoard();

        while(nbTry <= MAX_TRY && !wordFound){
            
            String guess = playTurn();
            this.wordGuess = checkGuess(guess);
            this.wordGuesses.put(nbTry,this.wordGuess);
            //System.out.println(guess);
            //displayGuess();
            //System.out.println(this.wordToGuess.getWord());
            displayBoard();
            wordFound = checkWordFound(guess);
            nbTry++;

        }if(!wordFound){

            System.out.println(TEXT_YELLOW + "\nYou lost, the word was " + this.wordToGuess.getWord() + "...\n" + TEXT_RESET);

        }else{

            System.out.println(TEXT_GREEN + "\nYou won in " + (nbTry-1) + " attempts !\n" + TEXT_RESET);

        }

    }

    private boolean checkWordFound(String guess) {
            
            boolean wordFound = true;
    
            for(int i = 0; i < wordSize; i++){
    
                if(!guess.equals(wordToGuess.getWord())){
    
                    wordFound = false;
    
                }
    
            }
    
            return wordFound;
    
    }

    private String playTurn() {
        
        this.wordGuess = new ArrayList<>();
        String word = "";

        //displayHint();

        word = SimpleInput.getString("\nAttempt n°" + this.nbTry + " - What is your attempt ?\n> ");

        while (!checkConditions(word)){

            word = SimpleInput.getString("\nAttempt n°" + this.nbTry + " Wrong attempt, What is your attempt ?\n> ");

        }

        for (int i = 0; i < word.length(); i++) {
            
            this.wordGuess.add(word.charAt(i) + "");

        }

        return word;

    }

    private boolean checkConditions(String word) {

        boolean conditions = true;

        if(word != null){

            if(word.length() == wordSize){
                
                if(word.charAt(0) == firstLetter){
                    
                    if(this.dictionary.inDictionary(word)){

                    }else{

                        System.out.println("The word is not in the dictionary");
                        conditions = false;
                    }

                }else{

                    System.out.println("The first letter of the word must be " + firstLetter);
                    conditions = false;

                }

            }else{
                    
                System.out.println("The word must be " + wordSize + " letters long");
                conditions = false;
    
            }

        }else{

            System.out.println("The word must be a string");
            conditions = false;

        }

        return conditions;

    }

    private ArrayList<String> checkGuess(String guess) {

        ArrayList<String> result = new ArrayList<>();

        if(guess != null){

            char[] guessArray = guess.toCharArray();
            ArrayList<String> differentLetters = differentLetters(guessArray);
            int[] nbOccurences = nbOccurencesLetters(differentLetters, guess);

            int charIndex = 0;

            for (char character : guessArray) {

                int indexOccurencesArray = getIndexOccurencesArray(differentLetters, character);

                if(wordToGuess.getWord().contains(character + "")){

                    if(nbOccurences[indexOccurencesArray] > 0 && this.wordToGuess.getWord().charAt(charIndex) == character){

                        nbOccurences[indexOccurencesArray]--;
                        result.add(TEXT_GREEN + character + TEXT_RESET);
                        this.hint[charIndex] = character + "";
                    
                    }else if(nbOccurences[indexOccurencesArray] > 0){
                        
                        nbOccurences[indexOccurencesArray]--;
                        result.add(TEXT_YELLOW + character + TEXT_RESET);

                    }else{

                        result.add(character + "");

                    }

                }else{

                    result.add(character + "");

                }
                
                charIndex++;
            }

        }

        return result;

    }

    private int getIndexOccurencesArray(ArrayList<String> differentLetters, char character) {

        int indexOccurencesArray = 0;

        for (int i = 0; i < differentLetters.size(); i++) {

            if(differentLetters.get(i).equals(character + "")){

                indexOccurencesArray = i;

            }

        }

        return indexOccurencesArray;
    }

    private ArrayList<String> differentLetters(char[] guessArray) {

        ArrayList<String> differentLetters = new ArrayList<>();

        for (char character : guessArray) {

            if(!differentLetters.contains(character + "")){

                differentLetters.add(character + "");

            }

        }

        return differentLetters;

    }

    private int[] nbOccurencesLetters(ArrayList<String> differentLetters, String guess){

        int[] nbOccurences = new int[differentLetters.size()];

        for (int i = 0; i < differentLetters.size(); i++) {

            nbOccurences[i] = 0;

        }

        for (int i = 0; i < guess.length(); i++) {

            for (int j = 0; j < differentLetters.size(); j++) {

                if(guess.charAt(i) == differentLetters.get(j).charAt(0)){

                    nbOccurences[j]++;

                }

            }

        }

        return nbOccurences;

    }

    public void displayBoard(){

        //System.out.println(this.nbTry + " " + this.wordGuesses.size());

        for (int i = 0; i < this.wordGuesses.size(); i++) {

            System.out.print(" ");

            ArrayList<String> guess = this.wordGuesses.get(i+1);

            for (String character : guess) {

                System.out.print(character + " ");
                
            }

            System.out.print("\n");

        }

        for(int i = this.wordGuesses.size(); i < MAX_TRY; i++){

            displayHint();

        }

    }

    public void displayHint(){

        for (String character : this.hint) {

            if(character != null){

                System.out.print(" " + character + "");
            
            }else{
                    
                    System.out.print(" .");
    
            }
            
        }

        System.out.println();

    }

    public void displayGuess(){

        //System.out.println("On passe ici les copains !");
        //System.out.println(this.wordGuess.size());

        for(String character : this.wordGuess){

            //System.out.println("Ici aussi !");

            System.out.print(character + " ");

        }

        System.out.println();
        
    }

}